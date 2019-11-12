package gr.isp.springbootapplication.service;

import gr.isp.springbootapplication.entity.Role;
import gr.isp.springbootapplication.entity.SessionUser;
import gr.isp.springbootapplication.entity.User;
import gr.isp.springbootapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        //Search for the user within the repository, and if the user doesn't exist, throw an exception
        User appUser =
                userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));

        //Map the authority list with the spring security list
        List grantList = new ArrayList();
        for (Role roles : appUser.getRoles()) {
            // ROLE_USER or ROLE_ADMIN or BOTH
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roles.getRole());
            grantList.add(grantedAuthority);
        }

        SessionUser user = new SessionUser(appUser.getEmail(), appUser.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantList, appUser.getId(), appUser.getCompanyName());
        return user;
    }
}
