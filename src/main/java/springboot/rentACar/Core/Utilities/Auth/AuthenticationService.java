package springboot.rentACar.Core.Utilities.Auth;


import springboot.rentACar.Core.Utilities.Config.JwtService;
import springboot.rentACar.Entities.Concretes.Token.Token;
import springboot.rentACar.Entities.Concretes.User.User;
import springboot.rentACar.Entities.Concretes.User.Role;
import springboot.rentACar.Entities.Concretes.Token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.rentACar.DataAccess.Abstracts.TokenDao;
import springboot.rentACar.DataAccess.Abstracts.UserDao;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDao userDao;
    private final TokenDao tokenDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private void saveUserToken(User user,String jwtToken){
        var token= Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenDao.save(token);
    }
    private void revokeAllUserTokens(User user){
        var validUserTokens=tokenDao.findAllValidTokenByUser(user.getId());
        if(validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenDao.saveAll(validUserTokens);
    }
    public AuthenticationResponse register(RegisterRequest request){
        var user=User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser=userDao.save(user);
        var jwtToken=jwtService.generateToken(user);
        saveUserToken(savedUser,jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        var user=userDao.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);//Her girişte yeni bir token oluşturuyor.
        revokeAllUserTokens(user);
        saveUserToken(user,jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

}
