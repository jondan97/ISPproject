package gr.isp.springbootapplication.service;

import gr.isp.springbootapplication.entity.SessionUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

public class SessionUserService {

    //roles: user, admin and visitor (has not signed in[null])
    private static String findRole(SessionUser user){
        String role;
        for (GrantedAuthority authority: user.getAuthorities()) {
            if(authority.getAuthority().equals("ROLE_USER")) {
                role="user";
                return role;
            }
            else if (authority.getAuthority().equals("ROLE_ADMIN")){
                role="admin";
                return role;
            }
            return null;
        }
        return null;
    }

    public static SessionUser getSessionUser(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        SessionUser user = null;
        if( SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) ){
            user = (SessionUser) securityContext.getAuthentication().getPrincipal();
        }
        return user;
    }

    public static void determineUser(Model model){
        SessionUser user = getSessionUser();
        if(user != null){
            String companyName = user.getCompanyName();
            model.addAttribute("companyName", companyName);
            String role = findRole(user);
            model.addAttribute("role",role);
        }
    }
}
