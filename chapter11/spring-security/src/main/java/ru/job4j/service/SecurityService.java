package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Security service.
 *
 * @author Alexey Voronin.
 * @since 24.01.2018.
 */
@Service
public class SecurityService {

    /**
     * AuthenticationManager.
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * UserDetailService.
     */
    @Autowired
    private UserDetailsService detailsService;

    /**
     * Find logged user.
     *
     * @return logged user.
     */
    public String findLoggedUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

    /**
     * Set authorisation user.
     *
     * @param userName user name.
     * @param password password.
     */
    public void autoLogin(final String userName, final String password) {
        UserDetails userDetails = this.detailsService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);
        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
        }
    }
}
