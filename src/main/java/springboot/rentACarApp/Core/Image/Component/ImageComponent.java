package springboot.rentACarApp.Core.Image.Component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import springboot.rentACarApp.Core.Image.Enums.ImageExtension;
import springboot.rentACarApp.Core.Image.Enums.KYCImage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Component
@Configuration
public class ImageComponent {
    @Value("${kyc.image.folder_path}")
    private String folderPath;

    public void uploadImage(MultipartFile file, KYCImage name, Long id) throws IOException {
        String folderPathWithId = folderPath + id;
        File folder = new File(folderPathWithId);
        if (!folder.exists()) {
            if(!folder.mkdirs()) throw new IOException("Cannot Create Inventory!");
        }
        String fileName = name + "." + getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
        String filePath = folderPathWithId + "/" + fileName;

        for (ImageExtension extension : ImageExtension.values()) {
            File existingFile = new File(folderPathWithId + "/" + name + "." +extension);
            if (existingFile.exists()) {
                existingFile.delete();
            }

        }
        file.transferTo(new File(filePath));
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}
