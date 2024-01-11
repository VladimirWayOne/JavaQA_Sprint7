package client;

import dto.Courier;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import static defaults.RestConfig.BASE_URI;
import static io.restassured.RestAssured.given;

public class CourierAPI {

    Courier courier = new Courier(RandomStringUtils.randomAlphanumeric(5, 10), "password", "Firstname");


    public Response createCourier() {
       return given().log().all()
               .contentType(ContentType.JSON)
               .baseUri(BASE_URI)
               .body(courier)
               .when()
               .post("/courier");

    }

    public String getCourierId() {
        Response loginResponse = given().log().all()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post("/courier/login");

        return loginResponse.asString();
    }

//    public Response deleteCourier() {
//
//    }
}
