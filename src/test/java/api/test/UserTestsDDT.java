package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTestsDDT {
    private static final Logger logger = LogManager.getLogger(UserTestsDDT.class);

    @Test(dataProvider = "UserData", dataProviderClass = DataProviders.class)
    public void createUserFromExcel(String userId, String username,
            String firstName, String lastName, String email,
            String password, String phone) {

        User user = new User();
        user.setId(Long.parseLong(userId));
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(1);

        logger.info("Creating Excel user: {}", username);
        Response response = UserEndPoints.createUser(user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
