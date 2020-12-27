package utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Excel {

    static String filePath = "D:\\Laptop backup\\Interview\\DemoRoundTripBooking-master\\RestAssuredPOM\\src\\main\\data\\userdata.xlsx";
    static Workbook book;
    static Sheet sheet;


    public static Object[][] readData(String sheetName) {

        try {
            FileInputStream file = new FileInputStream(filePath);
            book = WorkbookFactory.create(file);
            sheet = book.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for(int i=0;i<sheet.getLastRowNum();i++){
            for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
            {
                  data[i][j]=sheet.getRow(i+1).getCell(j).toString();
            }
        }
        return data;
    }
}
