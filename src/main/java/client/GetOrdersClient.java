package client;


import dto.OrderListRequest;
import io.restassured.response.Response;

public class GetOrdersClient extends RestClient{
    public Response getOrderList(OrderListRequest orderListRequest) {
        return getDefaultRequestSpecification()
                .body(orderListRequest)
                .when()
                .get("/orders");

    }

    public Response getOrderByTrackId(int track) {
        return getDefaultRequestSpecification()
                .queryParam("t",track)
                .when()
                .get("/orders/track");
    }
}
