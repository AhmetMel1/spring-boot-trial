package springboot.rentACarApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.rentACarApp.Core.Image.Component.ImageComponent;
import springboot.rentACarApp.Core.Image.Enums.KYCImage;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    ImageComponent imageComponent;
    @PostMapping("/upload/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("name") KYCImage name, @PathVariable Long id) throws IOException {
        if(file!=null){
            imageComponent.uploadImage(file,name,id);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        else {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }
}
