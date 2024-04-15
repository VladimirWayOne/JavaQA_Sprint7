package client;

import dto.OrderCreateRequest;
import io.restassured.response.Response;

public class OrderClient extends RestClient {
    public Response createOrder(OrderCreateRequest orderCreateRequest) {
        return getDefaultRequestSpecification()
                .body(orderCreateRequest)
                .when()
                .post("/orders");

    }
}
