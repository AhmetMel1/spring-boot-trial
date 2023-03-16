package springboot.rentACar.WebApi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.rentACar.Core.Utilities.RestApi.SendApi;

@RestController
@RequestMapping("/api/transcript")
public class TranscriptController {

    private SendApi sendApi;

    @Autowired
    public TranscriptController(SendApi sendApi) {
        this.sendApi = sendApi;
    }

    @PostMapping()
    public void post(String audio_url) throws Exception {
        this.sendApi.post(audio_url);
    }
    @GetMapping("/{id}")
    public void get(@PathVariable String id) throws Exception
    {
    this.sendApi.get(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws Exception
    {
        this.sendApi.delete(id);
    }
}
