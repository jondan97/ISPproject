package gr.isp.springbootapplication.web;

import gr.isp.springbootapplication.entity.Role;
import gr.isp.springbootapplication.entity.User;
import gr.isp.springbootapplication.repository.UserRepository;
import gr.isp.springbootapplication.service.SessionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @GetMapping("/admin")
    public String admin(Model model) {
        SessionUserService.determineUser(model);
        return "admin";
    }

    @RequestMapping(path="/admin/userAdding", method = RequestMethod.POST)
    public String editAdvert (RedirectAttributes redir,
                              @RequestParam String email,
                              @RequestParam String password,
                              @RequestParam String passwordConfirmation,
                              @RequestParam String userCompanyName) {

        boolean userExists = userRepository.findByEmail(email).isPresent();

        if (!userExists && !(email.isEmpty() || password.isEmpty() || userCompanyName.isEmpty() || passwordConfirmation.isEmpty() || !password.equals(passwordConfirmation))){
                User user = new User();
                user.setEmail(email);
                user.setCompanyName(userCompanyName);
                //encrypt password
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
                String encodedPassword = bCryptPasswordEncoder.encode(password);
                user.setPassword(encodedPassword);
                //create role for user
                Set<Role> userRoles = new HashSet<Role>();
                Role role = new Role();
                role.setId((long) 2);
                role.setRole("ROLE_USER");
                userRoles.add(role);
                user.setRoles(userRoles);
                userRepository.save(user);

                redir.addFlashAttribute("notificationMessage", true);
                redir.addFlashAttribute("userCreated", true);
                return "redirect:/admin";
        }
        else {

            if (userExists){
                redir.addFlashAttribute("userExists", true);
            }

            if (email.isEmpty()){
                redir.addFlashAttribute("emailError", true);
            }

            if(password.isEmpty()){
                redir.addFlashAttribute("passwordError", true);
            }

            if (passwordConfirmation.isEmpty()){
                redir.addFlashAttribute("passwordConfirmationError", true);
            }
            else if (!password.equals(passwordConfirmation)){
                redir.addFlashAttribute("passwordMismatch", true);
            }

            if(userCompanyName.isEmpty()){
                redir.addFlashAttribute("userCompanyNameError", true);
            }

        }

        redir.addFlashAttribute("email", email);
        redir.addFlashAttribute("password", password);
        redir.addFlashAttribute("passwordConfirmation", passwordConfirmation);
        redir.addFlashAttribute("userCompanyName", userCompanyName);

        return "redirect:/admin";
    }
}

