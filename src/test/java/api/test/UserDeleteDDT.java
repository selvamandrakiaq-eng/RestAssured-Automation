package api.test;

import api.endpoints.UserEndPoints;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserDeleteDDT {

    /*
     * Run this class only after the corresponding Excel users
     * have been successfully created by UserTestsDDT.
     */
    @Test(dataProvider = "UserData", dataProviderClass = DataProviders.class)
    public void deleteExcelUser(String userId, String username,
            String firstName, String lastName, String email,
            String password, String phone) {

        Response response = UserEndPoints.deleteUser(username);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
