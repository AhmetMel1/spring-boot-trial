package springboot.rentACarApp.Controller;

import springboot.rentACarApp.Business.Abstracts.UserService;
import springboot.rentACarApp.Business.Requests.RegisterRequest;
import springboot.rentACarApp.Business.Responses.AuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {
    private final UserService userService;
    @GetMapping
    public String registerPage(){
        return "register";
    }
    @ModelAttribute("user")
    public RegisterRequest registerRequest(){
        return new RegisterRequest();
    }
    @RequestMapping(method = RequestMethod.POST,params = {"name","email","password"})
    public ResponseEntity<AuthenticationResponse> registerPage(@ModelAttribute("user")RegisterRequest request){
        System.out.println(request);
        return ResponseEntity.ok(userService.saveUser(request));
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        System.out.println(request);
        return ResponseEntity.ok(userService.saveUser(request));
    }

}
