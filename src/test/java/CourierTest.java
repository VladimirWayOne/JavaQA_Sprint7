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
import static org.hamcrest.Matchers.is;

@Feature("Создание курьера")
public class CourierTest {
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
    public void tearDown()  {
            try {

                    courierSteps.deleteCourier(login, password);

            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }


    @Test
    @DisplayName("Проверка создания курьера с уникальным логином")
    public void testCreateCourier() {
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .createCourier(login, password, firstName)
                .statusCode(SC_CREATED)
                .body("ok", is(true));


    }

    @Test
    @DisplayName("Проверка создания двух одинаковых курьеров")
    public void testCreateFullDuplicateCourier() {
        String firstName = RandomStringUtils.random(10);
        courierSteps
                .createCourier(login, password, firstName);

        courierSteps
                .createCourier(login, password, firstName)
                .statusCode(SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется"));   // в API docs указан следующий ответ "message": "Этот логин уже используется"


    }

    @Test
    @DisplayName("Проверка создания курьеров c одинаковым логином")
    public void testCreateLoginDuplicateCourier() {
        String firstName = RandomStringUtils.random(10);
        courierSteps
                .createCourier(login, password, firstName);

        String passwordNew = RandomStringUtils.random(10);
        String firstNameNew = RandomStringUtils.random(10);

        courierSteps
                .createCourier(login, passwordNew, firstNameNew)
                .statusCode(SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется"));   // в API docs указан следующий ответ "message": "Этот логин уже используется"



    }

    @Test
    @DisplayName("Проверка создания курьера без логина")
    public void testCreateCourierWithoutLogin() {
        String firstName = RandomStringUtils.random(10);

        courierSteps
                .createCourierWithoutLogin(password, firstName)
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));


    }



}
