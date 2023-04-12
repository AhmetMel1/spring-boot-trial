package springboot.rentACarApp.Core.Config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import springboot.rentACarApp.Business.Requests.GoogleAuthenticationRequest;
import springboot.rentACarApp.Core.Auth.JwtAuthService;
import springboot.rentACarApp.DataAccess.Abstracts.UserDao;
import springboot.rentACarApp.Entities.Concretes.User.Provider;
import springboot.rentACarApp.Entities.Concretes.User.Role;
import springboot.rentACarApp.Entities.Concretes.User.User;


import java.io.IOException;
@Component
@RequiredArgsConstructor
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    private final UserDao userDao;
    private final JwtAuthService jwtAuthService;
    private final JwtService jwtService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("AuthenticationSuccessHandler invoked");
        System.out.println("Authentication name: " + authentication.getName());
        OAuth2User oAuth2User=(OAuth2User) authentication.getPrincipal();
        System.out.println(oAuth2User.getAttributes());
        System.out.println((String) oAuth2User.getAttribute("name"));
        System.out.println((String) oAuth2User.getAttribute("email"));
        GoogleAuthenticationRequest googleAuthenticationRequest= GoogleAuthenticationRequest.builder()
                .email(oAuth2User.getAttribute("email"))
                .name(oAuth2User.getAttribute("email")).build();
        System.out.println(googleAuthenticationRequest);
        var user=userDao.findByEmail((oAuth2User.getAttribute("email")));
        if(user!=null){
            jwtAuthService.revokeAllUserTokens(user);
            var jwtToken=jwtService.generateToken(user);
            jwtAuthService.saveUserToken(user,jwtToken);
            response.sendRedirect("/login/hello");
        }
        else{
            User newUser= User.builder()
                    .email(oAuth2User.getAttribute("email"))
                    .name(oAuth2User.getAttribute("name"))
                    .password("")
                    .role(Role.USER)
                    .provider(Provider.GOOGLE).build();
            userDao.save(newUser);
            var jwtToken=jwtService.generateToken(newUser);
            jwtAuthService.saveUserToken(newUser, jwtToken);
            response.sendRedirect("/login/hello2");
        }
    }
}
