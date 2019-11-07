package gr.isp.springbootapplication.service;

import java.util.ArrayList;
import java.util.List;

import gr.isp.springbootapplication.entity.Role;
import gr.isp.springbootapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Buscar el usuario con el repositorio y si no existe lanzar una exepcion
         gr.isp.springbootapplication.entity.User appUser =
                userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User does not exists"));

        //Mapear nuestra lista de Authority con la de spring security
        List grantList = new ArrayList();
        for (Role authority: appUser.getRole()) {
            // ROLE_USER, ROLE_ADMIN,..
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getRole());
            grantList.add(grantedAuthority);
        }

        //Crear El objeto UserDetails que va a ir en sesion y retornarlo.
        UserDetails user = (UserDetails) new User(appUser.getUsername(), appUser.getPassword(), grantList);
        return user;
    }
}
