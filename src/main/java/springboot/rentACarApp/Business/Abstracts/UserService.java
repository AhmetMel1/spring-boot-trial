package springboot.rentACarApp.Business.Abstracts;


import springboot.rentACarApp.Business.Requests.AuthenticationRequest;
import springboot.rentACarApp.Business.Requests.RegisterRequest;
import springboot.rentACarApp.Business.Responses.AuthenticationResponse;

public interface UserService {
    AuthenticationResponse saveUser(RegisterRequest request);
    AuthenticationResponse loginUser(AuthenticationRequest request);
}
