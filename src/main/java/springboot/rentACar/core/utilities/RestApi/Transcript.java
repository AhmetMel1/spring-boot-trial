package springboot.rentACar.core.utilities.RestApi;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class Transcript {
    private String audio_url;
    private String id;
    private String status;
    private String text;
}
