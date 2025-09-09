package org.example.myecommerceapp.rest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.myecommerceapp.model.Roles;
import org.example.myecommerceapp.model.Users;
import org.example.myecommerceapp.service.sign_upService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Component
public class oauth2Controller {

    @Autowired
    private sign_upService service;

    @GetMapping("/success")
    public RedirectView success(OAuth2AuthenticationToken authentication){

        Map<String, Object> attributes = authentication.getPrincipal().getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        Optional<Users> existingUser = service.findByEmail(email);

        if(existingUser.isEmpty()){
            Users user = new Users();
            user.setEmail(email);
            user.setPassword(""); //no password for Google users
            user.setName(name);
            user.setRole(Roles.USER);
            user.setEnabled(true);
            user.setVerificationToken(null);
            service.save(user);
        }
        else{
            Users users = existingUser.get();
            if(!users.isVerified()){
                users.setVerified(true);
                users.setVerificationToken(null);
                service.save(users);
            }
        }

        //redirect to frontend home page after login
        return new RedirectView("http://localhost:63342/myEcommerceApp/static/index.html");
    }

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
//        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//        Map<String, Object> attributes = oauthToken.getPrincipal().getAttributes();
//
//        String email = (String) attributes.get("email");
//        String name = (String) attributes.get("name");
//
//        if(service.findByEmail(email).isEmpty()){
//            Users user = new Users();
//            user.setEmail(email);
//            user.setPassword(new BCryptPasswordEncoder().encode("oauth2-user"));
//            user.setName(name);
//            user.setRole(Roles.USER);
//            service.save(user);
//        }
//
//        response.sendRedirect("http://localhost:63342/myEcommerceApp/static/index.html");
//    }
}
