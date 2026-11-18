package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {
    private static final Logger logger = LogManager.getLogger(UserTests.class);
    private User user;
    private String username;

    @BeforeClass
    public void setupData() {
        Faker faker = new Faker();
        user = new User();
        user.setId(faker.number().numberBetween(10000, 999999));
        username = "qa_" + faker.name().username().replaceAll("[^A-Za-z0-9]", "");
        user.setUsername(username);
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        user.setPhone("9876543210");
        user.setUserStatus(1);
    }

    @Test(priority = 1)
    public void createUser() {
        logger.info("Creating user: {}", username);
        Response response = UserEndPoints.createUser(user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dependsOnMethods = "createUser")
    public void readUser() {
        logger.info("Reading user: {}", username);
        Response response = UserEndPoints.readUser(username);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("username"), username);
    }

    @Test(priority = 3, dependsOnMethods = "readUser")
    public void updateUser() {
        logger.info("Updating user: {}", username);
        user.setFirstName("UpdatedFirstName");
        Response response = UserEndPoints.updateUser(username, user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 4, dependsOnMethods = "updateUser")
    public void deleteUser() {
        logger.info("Deleting user: {}", username);
        Response response = UserEndPoints.deleteUser(username);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
