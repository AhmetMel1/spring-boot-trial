package springboot.rentACarApp.Core.Auth;


import springboot.rentACarApp.Business.Requests.AuthenticationRequest;
import springboot.rentACarApp.Business.Requests.GoogleAuthenticationRequest;
import springboot.rentACarApp.Business.Requests.RegisterRequest;
import springboot.rentACarApp.Business.Responses.AuthenticationResponse;
import springboot.rentACarApp.Core.Config.JwtService;
import springboot.rentACarApp.DataAccess.Abstracts.TokenDao;
import springboot.rentACarApp.DataAccess.Abstracts.UserDao;
import springboot.rentACarApp.Entities.Concretes.Token.Token;
import springboot.rentACarApp.Entities.Concretes.Token.TokenType;
import springboot.rentACarApp.Entities.Concretes.User.Provider;
import springboot.rentACarApp.Entities.Concretes.User.Role;
import springboot.rentACarApp.Entities.Concretes.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JwtAuthService jwtAuthService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        var user=User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .provider(Provider.LOCAL)
                .build();
        var savedUser=userDao.save(user);
        var jwtToken=jwtService.generateToken(user);
        jwtAuthService.saveUserToken(savedUser,jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        var user=userDao.findByEmail(request.getEmail());
        if(user!=null) {
            var jwtToken = jwtService.generateToken(user);//Her girişte yeni bir token oluşturuyor.
            jwtAuthService.revokeAllUserTokens(user);
            jwtAuthService.saveUserToken(user, jwtToken);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
        else throw new RuntimeException();
    }
}
