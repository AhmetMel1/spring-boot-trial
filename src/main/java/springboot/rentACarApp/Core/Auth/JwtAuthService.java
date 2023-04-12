package springboot.rentACarApp.Core.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.rentACarApp.DataAccess.Abstracts.TokenDao;
import springboot.rentACarApp.Entities.Concretes.Token.Token;
import springboot.rentACarApp.Entities.Concretes.Token.TokenType;
import springboot.rentACarApp.Entities.Concretes.User.User;
@Service
@RequiredArgsConstructor
public class JwtAuthService {
    private final TokenDao tokenDao;
    public void saveUserToken(User user, String jwtToken){
        var token= Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenDao.save(token);
    }
    public void revokeAllUserTokens(User user){
        var validUserTokens=tokenDao.findAllValidTokenByUser(user.getId());
        if(validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenDao.saveAll(validUserTokens);
    }
}
