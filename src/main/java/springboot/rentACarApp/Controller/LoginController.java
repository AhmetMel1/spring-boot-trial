package springboot.rentACarApp.Controller;

import springboot.rentACarApp.Business.Abstracts.UserService;
import springboot.rentACarApp.Business.Requests.AuthenticationRequest;
import springboot.rentACarApp.Business.Responses.AuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
    private final UserService userService;
    @GetMapping
    public String loginPage(){
        return "login";
    }
    @ModelAttribute("user")
    public AuthenticationRequest authenticationRequest(){
        return new AuthenticationRequest();
    }
    @RequestMapping(method = RequestMethod.POST,params = {"email","password"})
    public ResponseEntity<AuthenticationResponse> loginPage(@ModelAttribute("user")AuthenticationRequest request)
    {
        System.out.println(request);
        return ResponseEntity.ok(userService.loginUser(request));
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request)
    {
        System.out.println(request);
        return ResponseEntity.ok(userService.loginUser(request));
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/hello2")
    public String hello2(){
        return "hello2";
    }
}
