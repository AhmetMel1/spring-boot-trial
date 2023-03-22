package springboot.rentACarApp.Business.Concretes;

import springboot.rentACarApp.Business.Abstracts.UserService;
import springboot.rentACarApp.Business.Requests.AuthenticationRequest;
import springboot.rentACarApp.Business.Requests.RegisterRequest;
import springboot.rentACarApp.Business.Responses.AuthenticationResponse;
import springboot.rentACarApp.Core.Auth.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private AuthenticationService service;
    @Override
    public AuthenticationResponse saveUser(RegisterRequest request) {
        return service.register(request);
    }
    @Override
    public AuthenticationResponse loginUser(AuthenticationRequest request) {
        return service.authenticate(request);
    }

}
