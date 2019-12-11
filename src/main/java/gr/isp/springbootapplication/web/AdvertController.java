package gr.isp.springbootapplication.web;

import gr.isp.springbootapplication.entity.Advert;
import gr.isp.springbootapplication.entity.User;
import gr.isp.springbootapplication.repository.AdvertRepository;
import gr.isp.springbootapplication.service.SessionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdvertController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private AdvertRepository advertRepository;

    @GetMapping("/user/myAdverts")
    public String myAdverts(Model model) {
        SessionUserService.determineUser(model);
        Iterable<Advert> adverts = advertRepository.findByUserId(SessionUserService.getSessionUser().getId());
        List<Advert> advertArrayVisible = new ArrayList<Advert>();
        List<Advert> advertArrayInvisible = new ArrayList<Advert>();
        List<Advert> advertArrayDraft = new ArrayList<Advert>();
        List<Advert> advertArrayExpired = new ArrayList<Advert>();

        for (Advert ad: adverts) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime nowTooLong = LocalDateTime.now();
            String nowStr = nowTooLong.format(formatter);
            LocalDateTime now = LocalDateTime.parse(nowStr, formatter);
            Long daysPosted = Duration.between(ad.getTimePosted(), now).toDays();
            if (daysPosted >= 30) {
                ad.setStatus("Expired");
                advertRepository.save(ad);
            }
            ad.setDaysPosted(daysPosted);
            if (ad.getStatus().equals("Visible")){
                advertArrayVisible.add(ad);
            }
            else if (ad.getStatus().equals("Invisible")){
                advertArrayInvisible.add(ad);
            }
            else if (ad.getStatus().equals("Draft")){
                advertArrayDraft.add(ad);
            }
            else if (ad.getStatus().equals("Expired")){
                advertArrayExpired.add(ad);
            }

        }
        model.addAttribute("advertArrayVisible", advertArrayVisible);
        model.addAttribute("advertArrayInvisible", advertArrayInvisible);
        model.addAttribute("advertArrayDraft", advertArrayDraft);
        model.addAttribute("advertArrayExpired", advertArrayExpired);

        return "myAdverts";
    }

    @GetMapping(path = "/user/createAdvert")
    public String postAd(Model model) {
        SessionUserService.determineUser(model);
        return "createAdvert";
    }

    @PostMapping(path = "/user/addingAdvert") // Map ONLY POST Requests
    public String addNewAdvert(RedirectAttributes redir,
                               @RequestParam String action,
                               @RequestParam String title,
                               @RequestParam String body,
                               @RequestParam String salary,
                               @RequestParam String industry) {
        // @RequestParam means it is a parameter from the GET or POST request

        boolean salaryError = false;
        Integer salaryInt = 0;

        try {
            if (!(salary.isEmpty())) {
                salaryInt = Integer.parseInt(salary);
            }
        } catch (NumberFormatException | NullPointerException nfe) {
            salaryError = true;
            redir.addFlashAttribute("salaryError", true);
        }

        if (!(title.isEmpty() || body.isEmpty() || salaryError)) {
            Advert ad = new Advert();
            ad.setTitle(title);
            ad.setBody(body);
            if (industry.isEmpty()) industry = "No Industry";
            ad.setIndustry(industry);
            if (salaryInt == null) salaryInt = 0;
            ad.setSalary(salaryInt);
            if (action.equals("Post")){
                ad.setStatus("Visible");
                redir.addFlashAttribute("advertPosted", true);
            }
            else if (action.equals("Save")){
                ad.setStatus("Draft");
                redir.addFlashAttribute("advertDrafted", true);
            }

            //LocalDateTime.now() creates an object that is too long for SQL, so we have to cut the last parts (the nanoseconds) in order to not insert corrupt date to the DB, besides we don't need that much of a precision
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime nowTooLong = LocalDateTime.now();
            String nowStr = nowTooLong.format(formatter);
            LocalDateTime timePosted = LocalDateTime.parse(nowStr, formatter);
            ad.setTimePosted(timePosted);

            User u = new User(SessionUserService.getSessionUser().getId());
            ad.setUser(u);

            advertRepository.save(ad);
            redir.addFlashAttribute("notificationMessage", true);
            return "redirect:/user/myAdverts";
        } else {
            if (title.isEmpty()) {
                redir.addFlashAttribute("titleError", true);
            }
            if (body.isEmpty()) {
                redir.addFlashAttribute("bodyError", true);
            }

            redir.addFlashAttribute("title", title);
            redir.addFlashAttribute("body", body);
            redir.addFlashAttribute("industry", industry);
            redir.addFlashAttribute("salary", salary);

            return "redirect:/user/createAdvert";
        }
    }

    @GetMapping(path = "/user/allAdverts")
    public @ResponseBody
    Iterable<Advert> getAllUsers() {
        // This returns a JSON or XML with the users
        return advertRepository.findByUserId(SessionUserService.getSessionUser().getId());
    }

    @PostMapping(path = "/user/editAdvert") // Map ONLY POST Requests
    public String editAdvert(Model model,
                             RedirectAttributes redir,
                             @RequestParam String action,
                             @RequestParam String id

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
            redir.addFlashAttribute("idError", true);
        }
        if (idError) {
            redir.addFlashAttribute("notificationMessage", true);
            redir.addFlashAttribute("advertProblem", true);
            return "redirect:/user/myAdverts";
        }
        else {
            Advert advert = advertRepository.findFirstById(idLong); //could be i
            if (advert.getUser().getId() == SessionUserService.getSessionUser().getId()) {
                if (action.equals("Delete")) {
                    advertRepository.deleteById(idLong);
                    redir.addFlashAttribute("notificationMessage", true);
                    redir.addFlashAttribute("advertDeleted", true);
                    return "redirect:/user/myAdverts";
                } else if (action.equals("Update")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime nowTooLong = LocalDateTime.now();
                    String nowStr = nowTooLong.format(formatter);
                    LocalDateTime now = LocalDateTime.parse(nowStr, formatter);
                    Long daysPosted = Duration.between(advert.getTimePosted(), now).toDays();
                    advert.setDaysPosted(daysPosted);
                    model.addAttribute("advert", advert);
                    return "editAdvert";
                }
            }
        }
        return "myAdverts";
    }

    //redirected here only when body or title inserted was empty
    @GetMapping(path = "/user/editAdvert") // Map ONLY POST Requests
    public String editAdvertGet(Model model
    ) {
        SessionUserService.determineUser(model);
        return "editAdvert";
    }

    @PostMapping(path = "/user/editingAdvert") // Map ONLY POST Requests
    public String editAdvert(RedirectAttributes redir,
                             @RequestParam String action,
                             @RequestParam String id,
                             @RequestParam String title,
                             @RequestParam String body,
                             @RequestParam String salary,
                             @RequestParam String industry,
                             @RequestParam String status

    ) {
        // @RequestParam means it is a parameter from the GET or POST request

        boolean idError = false;
        long idLong = 0;
        try {
            if (!(id.isEmpty())) {
                idLong = Long.parseLong(id);
            }
        } catch (NumberFormatException | NullPointerException nfe) {
            idError = true;
            redir.addFlashAttribute("idError", true);
        }
        if (idError) {
            redir.addFlashAttribute("notificationMessage", true);
            redir.addFlashAttribute("advertProblem", true);
            return "redirect:/user/myAdverts";
        } else {

            Advert advertBefore = advertRepository.findFirstById(idLong);

            if (advertBefore == null || advertBefore.getStatus().equals("Expired") || !(status.equals("Visible") || status.equals("Invisible") || status.equals("Draft"))) {
                redir.addFlashAttribute("notificationMessage", true);
                redir.addFlashAttribute("advertProblem", true);
                return "redirect:/user/myAdverts";
            } else if (advertBefore.getUser().getId() == SessionUserService.getSessionUser().getId()) {
                if (action.equals("Update")) {
                    boolean salaryError = false;
                    Integer salaryInt = 0;

                    try {
                        if (!(salary.isEmpty())) {
                            salaryInt = Integer.parseInt(salary);
                        }
                    } catch (NumberFormatException | NullPointerException nfe) {
                        salaryError = true;
                        redir.addFlashAttribute("salaryError", salaryError);
                    }

                    if (!(title.isEmpty() || body.isEmpty() || salaryError)) {
                        Advert advertAfter = new Advert();
                        advertAfter.setId(idLong);
                        advertAfter.setTitle(title);
                        advertAfter.setBody(body);
                        advertAfter.setIndustry(industry);
                        if (salaryInt == null) salaryInt = 0;
                        advertAfter.setSalary(salaryInt);
                        advertAfter.setStatus(status);
                        // if it goes from invisible to visible or from draft to visible, the time posted needs to be updated (just a guess, needs confirmation from client)
                        // for now it will be commented out and will be checked at a later time
//                    if ((advertBefore.getStatus().equals("Invisible") || advertBefore.getStatus().equals("Draft"))  && status.equals("Visible")) {
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                        LocalDateTime nowTooLong = LocalDateTime.now();
//                        String nowStr = nowTooLong.format(formatter);
//                        LocalDateTime timePosted = LocalDateTime.parse(nowStr, formatter);
//                        advertAfter.setTimePosted(timePosted);
//                    }
//                    //if status is not changed or goes, from visible to invisible, we don't have to worry about timePosted so just take the string taken from the form
//                    else {
//                        //the timeposted comes as a string from the front-end, we need to convert it, string contains a T so
//                        //the formatter differs a bit from the one used for posting
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//                        LocalDateTime timePosted = LocalDateTime.parse(timePostedStr, formatter);
//                        advertAfter.setTimePosted(timePosted);
//                    }
                        advertAfter.setUser(advertBefore.getUser());
                        advertAfter.setTimePosted(advertBefore.getTimePosted());
                        advertRepository.save(advertAfter);
                        redir.addFlashAttribute("notificationMessage", true);
                        redir.addFlashAttribute("advertEdited", true);
                        return "redirect:/user/myAdverts";
                    } else {
                        if (title.isEmpty()) {
                            redir.addFlashAttribute("titleError", true);
                        }
                        if (body.isEmpty()) {
                            redir.addFlashAttribute("bodyError", true);
                        }
                        //only goes in here when title or body is empty, redirects to GET: editAdvert
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime nowTooLong = LocalDateTime.now();
                        String nowStr = nowTooLong.format(formatter);
                        LocalDateTime now = LocalDateTime.parse(nowStr, formatter);
                        Long daysPosted = Duration.between(advertBefore.getTimePosted(), now).toDays();
                        advertBefore.setDaysPosted(daysPosted);
                        redir.addFlashAttribute("advert", advertBefore);
                        return "redirect:/user/editAdvert";
                    }
                }
            } else {
                redir.addFlashAttribute("notificationMessage", true);
                redir.addFlashAttribute("advertProblem", true);
                return "redirect:/user/myAdverts";
            }
            // no idea why i need this return and the compiler insists that i put a return here
            redir.addFlashAttribute("noIdeaWhatYouDidError", true);
            return "redirect:/user/myAdverts";
        }
    }
}
