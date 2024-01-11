package client;

import dto.CourierCreateRequest;
import dto.CourierDeleteRequest;
import dto.CourierLoginRequest;
import io.restassured.response.Response;

public class CourierClient extends RestClient {



    public Response createCourier(CourierCreateRequest courierCreateRequest) {
       return getDefaultRequestSpecification()
               .body(courierCreateRequest)
               .when()
               .post("/courier");

    }

    public Response loginCourier(CourierLoginRequest courierLoginRequest) {
        return  getDefaultRequestSpecification()
                .body(courierLoginRequest)
                .when()
                .post("/courier/login");
    }


    public Response deleteCourier(CourierDeleteRequest courierDeleteRequest) {
        return  getDefaultRequestSpecification()
                .body(courierDeleteRequest)
                .when()
                .delete("/courier/:id");
    }

}
