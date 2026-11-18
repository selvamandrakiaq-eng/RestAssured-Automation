package api.utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "UserData")
    public Object[][] getUserData() throws IOException {
        String path = System.getProperty("user.dir") + "/TestData/UserData.xlsx";
        String sheetName = "Sheet1";

        int rowCount = XL_Utility.getRowCount(path, sheetName);
        int colCount = XL_Utility.getCellCount(path, sheetName, 0);

        Object[][] data = new Object[rowCount][colCount];

        // Row 0 contains headers; data starts at Excel row 2.
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = XL_Utility.getCellData(path, sheetName, i, j);
            }
        }
        return data;
    }
}
