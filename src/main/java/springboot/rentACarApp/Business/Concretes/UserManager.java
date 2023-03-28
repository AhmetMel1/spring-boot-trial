package springboot.rentACarApp.Business.Concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import springboot.rentACarApp.Business.Abstracts.UserService;
import springboot.rentACarApp.Business.Requests.AuthenticationRequest;
import springboot.rentACarApp.Business.Requests.RegisterRequest;
import springboot.rentACarApp.Business.Responses.AuthenticationResponse;
import springboot.rentACarApp.Core.Auth.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springboot.rentACarApp.Core.Integration.Mail.MailComponent;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private AuthenticationService service;
    @Autowired
    private MailComponent mailComponent;
    @Override
    public AuthenticationResponse saveUser(RegisterRequest request) {
        return service.register(request);
    }
    @Override
    public AuthenticationResponse loginUser(AuthenticationRequest request) {
        return service.authenticate(request);
    }

    @Override
    public int generateOtp(String mail) {
        int randomPIN = (int) (Math.random() * 9000) + 1000;
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("ahmet.melih5656@gmail.com");
        msg.setTo(mail);

        msg.setSubject("Balance Network|Email Verify Code: " + randomPIN);
        msg.setText("Hello,\n You can verify your email address using the code below.\n" + randomPIN);
        mailComponent.getJavaMailSender().send(msg);
        return randomPIN;
    }

}
