import client.CourierClient;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import step.CourierSteps;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
@Feature("Логин курьера")
public class LoginTest {
    private CourierSteps courierSteps;
    private String login;
    private String password;

    @Before
    public void setUp() {
        courierSteps = new CourierSteps(new CourierClient());
        login = RandomStringUtils.random(10);
        password = RandomStringUtils.random(10);
    }

    @After
    public void tearDown() {
        try {

            courierSteps.deleteCourier(login, password);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    @Test
    @DisplayName("Проверка логина курьера")
    public void testLoginCourier() {
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .createCourier(login, password, firstName);

        courierSteps
                .loginCourier(login, password)
                .statusCode(SC_OK)
                .body("id", notNullValue());

    }

    @Test
    @DisplayName("Проверка логина курьера без пароля")
    public void testLoginCourierWithoutPassword() {
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .createCourier(login, password, firstName);

        courierSteps
                .loginCourier(login, "")
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));

    }

    @Test
    @DisplayName("Проверка логина несуществующего курьера")
    public void testLoginNotExistedCourier() {
        courierSteps
                .loginCourier("Несуществующийтоварищ", "gавпываавывап")
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Проверка логина курьера c некорректным паролем")
    public void testLoginCourierWrongPassword() {
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .createCourier(login, password, firstName);

        courierSteps
                .loginCourier(login, "123")
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));

    }


}
