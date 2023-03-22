package springboot.rentACarApp.Business.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateModelRequest {
    private String name;
    private int brandId;
}
