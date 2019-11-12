package gr.isp.springbootapplication.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class LoginListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private HttpServletRequest request;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        Object emailObj = event.getAuthentication().getPrincipal();
        String email = emailObj.toString();

        HttpSession session = request.getSession();
        session.setAttribute("userEmail", email);
    }
}