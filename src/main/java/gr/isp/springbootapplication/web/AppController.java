package gr.isp.springbootapplication.web;

import gr.isp.springbootapplication.entity.Advert;
import gr.isp.springbootapplication.repository.AdvertRepository;
import gr.isp.springbootapplication.service.EmailService;
import gr.isp.springbootapplication.service.SessionUserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {


    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private AdvertRepository advertRepository;

    @Autowired
    EmailService emailService;


    @GetMapping("/login")
    public String login(Model model,
                        HttpSession session
    ) {
        if (!(session.getAttribute("userEmail") == null)){
            String email = session.getAttribute("userEmail").toString();
            session.removeAttribute("userEmail");
            model.addAttribute("email", email);
        }
        return "login";
    }

    @GetMapping({"/"})
    public String mainPage(Model model) {
        SessionUserService.determineUser(model);
        Iterable<Advert> adverts = advertRepository.findByStatus("Visible");
        List<Advert> advertArray = new ArrayList<Advert>();
        for (Advert ad: adverts) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime nowTooLong = LocalDateTime.now();
            String nowStr = nowTooLong.format(formatter);
            LocalDateTime now = LocalDateTime.parse(nowStr, formatter);
            Long daysPosted = Duration.between(ad.getTimePosted(), now).toDays();
            if(daysPosted >= 30){
                ad.setStatus("Expired");
                advertRepository.save(ad);
            }
            else {
                ad.setDaysPosted(daysPosted);
                advertArray.add(ad);
            }
        }
        model.addAttribute("advertArray",advertArray);

        return "mainPage";
    }

    @RequestMapping(value="/view/{id}")
    public String viewAdvert(Model model,
                             @PathVariable String id
    ) {
        SessionUserService.determineUser(model);
        boolean idError = false;
        long idLong = 0;
        try {
            if (!(id.isEmpty())) {
                idLong = Long.parseLong(id);
            }
        } catch (NumberFormatException | NullPointerException nfe) {
            idError = true;
        }
        if (idError) {
            return "redirect:/error";
        }
        else {
            Advert advert = advertRepository.findFirstById(idLong);
            if (advert == null){
                return "redirect:/error";
            }
            else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime nowTooLong = LocalDateTime.now();
                String nowStr = nowTooLong.format(formatter);
                LocalDateTime now = LocalDateTime.parse(nowStr, formatter);
                Long daysPosted = Duration.between(advert.getTimePosted(), now).toDays();
                if(daysPosted >= 30){
                    advert.setStatus("Expired");
                    advertRepository.save(advert);
                 }
                advert.setDaysPosted(daysPosted);


                model.addAttribute("advert", advert);
                return "viewAdvert";
            }
        }
    }

    @RequestMapping(value="/view/{id}/apply")
    public String applyForAdvertPost(Model model,
                                @PathVariable String id
    ) {
        SessionUserService.determineUser(model);
        boolean idError = false;
        long idLong = 0;
        try {
            if (!(id.isEmpty())) {
                idLong = Long.parseLong(id);
            }
        } catch (NumberFormatException | NullPointerException nfe) {
            idError = true;
        }
        if (idError) {
            return "redirect:/error";
        }
        else {
            Advert advert = advertRepository.findFirstById(idLong);
            if (advert == null){
                return "redirect:/error";
            }
            else {
                model.addAttribute("advert", advert);
                return "applyForAdvert";
            }
        }
    }

    @PostMapping("/view/sendingApplication")
    public String uploadFile(RedirectAttributes redir,
                             @RequestParam String id,
                             @RequestParam String firstname,
                             @RequestParam String lastname,
                             @RequestParam String phone,
                             @RequestParam String email,
                             @RequestParam MultipartFile cv) throws IOException, MessagingException {

        //basic ID security against attackers that insert IDs for adverts that don't exist or strings for IDs
        boolean idError = false;
        long idLong = 0;
        try {
            if (!(id.isEmpty())) {
                idLong = Long.parseLong(id);
            }
        } catch (NumberFormatException | NullPointerException nfe) {
            idError = true;
        }
        if(idError){
            return "redirect:/error";
        }
        else {
            Advert advertApplyingFor = advertRepository.findFirstById(idLong);
            //could be a Long but still a random number (advert does not exist on the DB)
            if (advertApplyingFor == null) {
                return "redirect:/error";
            //business logic here
            } else {
                if (!(firstname.isEmpty() || lastname.isEmpty() || phone.isEmpty() || email.isEmpty() || !FilenameUtils.getExtension(cv.getOriginalFilename()).equals("pdf"))) {
                    String emailSentTo = advertApplyingFor.getUser().getEmail();
                    String emailSubject = "Applicant Form";
                    String emailBody = "Application from a job seeker has been received!" +
                            "<h2> Application Applying for: <a href='localhost:8080/view/" + advertApplyingFor.getId() + "'>" + advertApplyingFor.getTitle() + "</a></h2><br>" +
                            "<h1> First Name: " + firstname + "</h1>" +
                            "<h1> Last Name: " + lastname + "</h1>" +
                            "<h1> Phone Number: " + phone + "</h1>" +
                            "<h1> Contact E-mail: " + email + "</h1><br>" +
                            "CV can be found attached to this e-mail";
                    //emailService.sendEmailWithAttachement(emailSentTo, emailSubject, emailBody, cv);
                    redir.addFlashAttribute("userApplied", true);
                    return "redirect:/view/" + advertApplyingFor.getId();
                } else {
                    if (firstname.isEmpty()) {
                        redir.addFlashAttribute("firstNameError", true);
                    }
                    if (lastname.isEmpty()) {
                        redir.addFlashAttribute("lastNameError", true);
                    }

                    if (email.isEmpty()) {
                        redir.addFlashAttribute("emailError", true);
                    }

                    if (phone.isEmpty()) {
                        redir.addFlashAttribute("phoneError", true);
                    }

                    if (!(FilenameUtils.getExtension(cv.getOriginalFilename()).equals("pdf"))) {
                        redir.addFlashAttribute("cvError", true);
                    }

                }
                redir.addFlashAttribute("firstname", firstname);
                redir.addFlashAttribute("lastname", lastname);
                redir.addFlashAttribute("phone", phone);
                redir.addFlashAttribute("email", email);
                redir.addFlashAttribute("cv", cv);


                return "redirect:/view/" + advertApplyingFor.getId() + "/apply";
            }
        }
    }


    @GetMapping({"/contactUs"})
    public String contactUs(Model model) {
        SessionUserService.determineUser(model);
        return "contactUs";
    }

    @PostMapping({"/contactUs/contactingUs"})
    public String contactingUs(RedirectAttributes redir,
                               @RequestParam String email,
                               @RequestParam String phone
                               ) throws IOException, MessagingException {

        if (!(email.isEmpty() || phone.isEmpty())) {
            String emailSentTo = "jondan97@gmail.com";
            String emailSubject = "Credentials Request";
            String emailBody = "Request for credentials by a new user has been received!" +
                    "<h1> Contact E-mail: " + email + "</h1>" +
                    "<h1> Phone Number: " + phone + "</h1>";
            //emailService.sendEmail(emailSentTo, emailSubject, emailBody);
            redir.addFlashAttribute("userContacted", true);
            return "redirect:/";
        } else {

            if (email.isEmpty()) {
                redir.addFlashAttribute("emailError", true);
            }

            if(phone.isEmpty()){
                redir.addFlashAttribute("phoneError", true);
            }

            redir.addFlashAttribute("email", email);
            redir.addFlashAttribute("phone", phone);
            return "redirect:/contactUs";

        }
    }
}

