import client.GetOrdersClient;
import client.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import step.OrderListSteps;
import step.OrderSteps;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Feature("Поиск заказов")
public class GetOrderTest {
    private OrderListSteps orderListSteps;

    @Before
    public void setUp() {
        orderListSteps = new OrderListSteps(new GetOrdersClient());

    }

    @Test
    @DisplayName("Проверка запроса списка заказов")
    @Description("Данный тест проверяет, что метод возвращает код 200 и в теле ответа orders является массивом")
    public void testGetOrderList() {
        orderListSteps.getOrderListWithEmptyBody().statusCode(SC_OK)
                .body("orders", notNullValue());
    }


    @Test
    @DisplayName("Проверка запроса определенного заказа")
    @Description("В рамках теста создается заказ и осуществляется его поиск")
    public void testGetOrderByTrackId() {
        OrderSteps orderSteps = new OrderSteps(new OrderClient());
        String firstName = RandomStringUtils.random(10);
        String lastName = RandomStringUtils.random(10);
        String address = RandomStringUtils.random(10);
        String metroStation = RandomStringUtils.random(10);
        String phone = RandomStringUtils.random(10);
        int rentTime = 5;
        String deliveryDate = "2024-02-02";
        String comment = RandomStringUtils.random(10);
        String colorStr = "";


        int track = orderSteps
                .createOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, colorStr.split(","))
                .extract().path("track");

        orderListSteps
                .getOrderByTrackId(track)
                .statusCode(SC_OK)
                .and()
                .assertThat().body("order.track", equalTo(track));


    }

}
