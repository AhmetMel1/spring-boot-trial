package springboot.rentACar.WebApi.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springboot.rentACar.Core.Utilities.Auth.AuthenticationRequest;
import springboot.rentACar.Core.Utilities.Auth.AuthenticationResponse;
import springboot.rentACar.Core.Utilities.Auth.AuthenticationService;
import springboot.rentACar.Core.Utilities.Auth.RegisterRequest;
import springboot.rentACar.Entities.Concretes.User.User;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST,params = {"firstname","lastname","email","password"})
    public ResponseEntity<AuthenticationResponse> register(
            @ModelAttribute User user, RegisterRequest request
    ) {
        request.setFirstname(user.getFirstname());
        request.setLastname(user.getLastname());
        request.setEmail(user.getEmail());
        request.setPassword(user.getEmail());
        System.out.println(request);
        return ResponseEntity.ok(service.register(request));
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        System.out.println(request);
        return ResponseEntity.ok(service.register(request));
    }
    @GetMapping("/authenticate")
    public String authenticate(){
        return "authenticate";
    }
    @RequestMapping(value = "/authenticate",method = RequestMethod.POST,params = {"email","password"})
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request,@ModelAttribute User user)
    {
        request.setEmail(user.getEmail());
        request.setPassword(user.getPassword());
        System.out.println(request);
        System.out.println(request.getEmail());
        return ResponseEntity.ok(service.authenticate(request));
    }
    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        System.out.println(request);
        System.out.println(request.getEmail());
        System.out.println(request.getPassword());
        return ResponseEntity.ok(service.authenticate(request));
    }
}
