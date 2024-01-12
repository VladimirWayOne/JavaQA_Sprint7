package step;


import client.OrderClient;
import dto.OrderCreateRequest;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;


public class OrderSteps {
    private final OrderClient orderClient;
    public OrderSteps(OrderClient orderClient) {
        this.orderClient = orderClient;
    }
    @Step("Создание заказа с полным набором данных")
    public ValidatableResponse createOrder(String firstName, String lastName, String address, String metroStation,
                                           String phone, int rentTime, String deliveryDate, String comment, String[] color) {

        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        orderCreateRequest.setFirstName(firstName);
        orderCreateRequest.setLastName(lastName);
        orderCreateRequest.setFirstName(firstName);
        orderCreateRequest.setAddress(address);
        orderCreateRequest.setMetroStation(metroStation);
        orderCreateRequest.setPhone(phone);
        orderCreateRequest.setRentTime(rentTime);
        orderCreateRequest.setDeliveryDate(deliveryDate);
        orderCreateRequest.setComment(comment);
        orderCreateRequest.setColor(color);


        return orderClient.createOrder(orderCreateRequest)
                .then();

    }
}
