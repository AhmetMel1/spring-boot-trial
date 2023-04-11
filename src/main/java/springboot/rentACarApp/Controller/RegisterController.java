package springboot.rentACarApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import springboot.rentACarApp.Business.Abstracts.UserService;
import springboot.rentACarApp.Business.Requests.RegisterRequest;
import springboot.rentACarApp.Business.Responses.AuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springboot.rentACarApp.Core.Redis.RedisCacheService;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
@EnableCaching
public class RegisterController {
    private final UserService userService;
    @Autowired
    private final RedisCacheService service;
    @GetMapping
    public String registerPage(){
        return "register";
    }
    @ModelAttribute("user")
    public RegisterRequest registerRequest(){
        return new RegisterRequest();
    }
    @RequestMapping(method = RequestMethod.POST,params = {"name","email","password"})
    public ResponseEntity<AuthenticationResponse> registerPage(@ModelAttribute("user")RegisterRequest request) throws InterruptedException {
        System.out.println(request);
        //int otp=userService.generateOtp(request.getEmail());
        //service.otpExpired(otp);
        //service.ClearCache(otp);
        return ResponseEntity.ok(userService.saveUser(request));
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        System.out.println(request);
        return ResponseEntity.ok(userService.saveUser(request));
    }

}
