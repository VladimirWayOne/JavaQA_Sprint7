package step;

import client.CourierClient;
import dto.CourierCreateRequest;
import dto.CourierDeleteRequest;
import dto.CourierLoginRequest;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class CourierSteps {


    private final CourierClient courierClient;
    public CourierSteps(CourierClient courierClient) {
        this.courierClient = courierClient;
    }
    @Step("Корректное создание курьера")
    public ValidatableResponse createCourier(String login, String password, String firstName) {

        CourierCreateRequest courierCreateRequest = new CourierCreateRequest();
        courierCreateRequest.setLogin(login);
        courierCreateRequest.setPassword(password);
        courierCreateRequest.setFirstName(firstName);

        return courierClient.createCourier(courierCreateRequest)
                .then();

    }

    @Step("Создание курьера без firstName")
    public ValidatableResponse createCourierWithoutLogin(String password, String firstName) {

        CourierCreateRequest courierCreateRequest = new CourierCreateRequest();
        courierCreateRequest.setPassword(password);
        courierCreateRequest.setFirstName(firstName);

        return courierClient.createCourier(courierCreateRequest)
                .then();

    }

    @Step("Логин курьера")
    public ValidatableResponse loginCourier(String login, String password) {

        CourierLoginRequest courierLoginRequest = new CourierLoginRequest();
        courierLoginRequest.setLogin(login);
        courierLoginRequest.setPassword(password);

        return courierClient.loginCourier(courierLoginRequest)
                .then();

    }


    @Step("Удаление курьера")
    public ValidatableResponse deleteCourier(String login, String password) {
        CourierLoginRequest courierLoginRequest = new CourierLoginRequest();
        courierLoginRequest.setLogin(login);
        courierLoginRequest.setPassword(password);

        int courierId = courierClient.loginCourier(courierLoginRequest)
                .then().extract().path("id");

        CourierDeleteRequest courierDeleteRequest = new CourierDeleteRequest();
        courierDeleteRequest.setCourierId(courierId);
        return courierClient.deleteCourier(courierDeleteRequest)
                .then();
    }
}
