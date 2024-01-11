package client;


import dto.OrderListRequest;
import io.restassured.response.Response;

public class OrderListClient extends RestClient{
    public Response getOrderList(OrderListRequest orderListRequest) {
        return getDefaultRequestSpecification()
                .body(orderListRequest)
                .when()
                .get("/orders");

    }
}
