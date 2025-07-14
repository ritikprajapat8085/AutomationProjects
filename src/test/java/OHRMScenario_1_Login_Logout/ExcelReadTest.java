
package OHRMScenario_1_Login_Logout;

import UtilityPackage.ExcelUtility;

public class ExcelReadTest
{
    public static void main(String[] args) throws Exception
    {
        // calling constructor of utility package and passing filepath and sheetnmae to it

        ExcelUtility excel = new ExcelUtility("C:\\Users\\Admin\\Documents\\Staragile Automation course\\CapstoneProjects\\OHRMProject\\OHRMTestData.xlsx");
        int rowCount = excel.getRowCount();
        System.out.println("Reading all test data:");

        for(int i=0;i<=rowCount;i++)
        {
            String username =    excel.getCellData(i,0);  // column 0 = Username
            String password =  excel.getCellData(i,1); // column 1 = Password

            System.out.println("Username: " + username + " | Password: " + password);
        }

        /*
        by function i tried

        // Example: Read Username and Password from row 2
          String username = excel.getCellData(2, 0);
          String password = excel.getCellData(2, 1);
           System.out.println("Username: " + username);
           System.out.println("Password: " + password);

       */


    }
}



