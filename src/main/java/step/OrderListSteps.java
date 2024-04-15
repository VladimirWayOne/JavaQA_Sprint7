package step;

import client.GetOrdersClient;
import dto.OrderListRequest;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class OrderListSteps {
    private final GetOrdersClient getOrdersClient;

    public OrderListSteps(GetOrdersClient getOrdersClient) {
        this.getOrdersClient = getOrdersClient;
    }

    @Step("Получение списка заказов с пустым телом запроса (0 страница с 30 записями)")
    public ValidatableResponse getOrderListWithEmptyBody() {
        OrderListRequest orderListRequest = new OrderListRequest();
        return getOrdersClient.getOrderList(orderListRequest)
                .then();
    }

    @Step("Получение заказа по трек-номеру")
    public ValidatableResponse getOrderByTrackId(int t) {
        OrderListRequest orderListRequest = new OrderListRequest();
        return getOrdersClient.getOrderByTrackId(t)
                .then();
    }
}
