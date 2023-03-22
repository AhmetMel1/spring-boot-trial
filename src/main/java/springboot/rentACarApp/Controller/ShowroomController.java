package springboot.rentACarApp.Controller;

import springboot.rentACarApp.Core.RestApi.ApiTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/showrooms")
public class ShowroomController {
    ApiTest apiTest;

    public ShowroomController(ApiTest apiTest) {
        this.apiTest = apiTest;
    }

    @GetMapping()
    public void get() throws Exception {
        this.apiTest.list();
    }
    @GetMapping("/{id}")
    public void getById(@PathVariable Object id) throws Exception{
        this.apiTest.listById(id);
    }
    @PostMapping()
    @ResponseStatus(code= HttpStatus.CREATED)
    public void post(@RequestBody String jsonRequest)throws Exception{
        this.apiTest.add(jsonRequest);
    }
    @PutMapping("/{id}")
    public void put(@PathVariable Object id,@RequestBody String jsonRequest) throws Exception {
        this.apiTest.update(id,jsonRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Object id) throws Exception{
        this.apiTest.delete(id);
    }
}
