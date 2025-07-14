package UtilityPackage;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtility
{
    FileInputStream readfile ;
    XSSFWorkbook wb ;
    XSSFSheet sheet;
    XSSFRow row ;
    XSSFCell cell ;
  //  String fpath;   if function use


    // using constructor
  public ExcelUtility(String filepath ) // Constructor created to load the Excel file and sheet
  {
      try
      {
          readfile = new FileInputStream(filepath);       // Load the Excel file
          wb = new XSSFWorkbook(readfile);              // Create workbook from the file
          sheet =wb.getSheetAt(0) ;               // Get the desired sheet

           //File file = new File("filepath");
          // writefile = new FileOutputStream(file); //Configure fileoutputstream only after Configuration of sheet  if we writing after readin in excel
      }

      catch (IOException e)
      {
          e.printStackTrace();
      }
  }


    public  String getCellData(int r,int c)   // Method to get cell data
    {
        return sheet.getRow(r).getCell(c).toString();
    }


    public int getRowCount()             // Optional: Get total row count
    {
        return sheet.getLastRowNum() ;  // gives index of last row (starts at 0).
       // return sheet.getPhysicalNumberOfRows();  // Count rows
    }


    public int getColumnCount()     // Optional: Get total column count in first row
    {
        return sheet.getRow(0).getLastCellNum();
    }

    // Close workbook
    public void closeWorkbook() throws IOException
    {
        wb.close();
        readfile.close();
    }

}








/*  using function
public  void  excelData(String fpath ,String sheetname ) throws Exception
{
    this.fpath = fpath ;
    readfile = new FileInputStream(fpath);
    wb = new XSSFWorkbook(readfile);
    sheet = wb.getSheet(sheetname) ;
}
 */


/*
    public void setCellData(int r, int c, String data) throws Exception
    {
        row = sheet.getRow(r);
        if (row == null) row = sheet.createRow(r);
        cell = row.getCell(c);
        if (cell == null) cell = row.createCell(c);
        cell.setCellValue(data);

        writefile = new FileOutputStream(path);
        wb.write( writefile);
        writefile.close();
    }


 */