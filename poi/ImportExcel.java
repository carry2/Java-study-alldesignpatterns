package com.geely.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;

public class ImportExcel {

    @Test
    public void importExcelAndExportTXT()  {

        FileOutputStream fileOutputStream=null;
        String inputxsl="/Users/yangzepeng/Desktop/安邦/中银邮储工农字典映射(1).xls";
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(inputxsl));

            String outPutString = readExcel(fileInputStream,inputxsl);

            String pathname = "/Users/yangzepeng/Desktop/安邦/导出国籍sql.txt";
            File file = new File(pathname);
//            file.deleteOnExit();
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(outPutString.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

    private String readExcel(FileInputStream inputStream, String inputxsl) {
        String outPutString = "";
        Workbook workbook=null;
        try {
            if (inputxsl.endsWith("xlsx")){
                workbook = new XSSFWorkbook(inputStream);//Excel 2007
            }else if (inputxsl.endsWith("xls")){
                workbook = new HSSFWorkbook(inputStream);//Excel 2003

            }
            //获取表格sheet页
            Sheet sheetAt1 = workbook.getSheetAt(2);

            outPutString = resolveOutPutString(sheetAt1);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return outPutString;
    }

    private String resolveOutPutString(Sheet sheetAt) {
        StringBuffer stringBuffer = new StringBuffer();

        for (int rowNum = 90; rowNum < sheetAt.getLastRowNum(); rowNum++) {
            //HSSFRow hssfRow = hssfSheet.getRow(rowNum);
            System.out.println(rowNum);
            Row hssfRow = sheetAt.getRow(rowNum);
            if (hssfRow != null) {
                String insuValue = hssfRow.getCell(3).getStringCellValue();
                String bankValue = hssfRow.getCell(4).getStringCellValue();
//                String remarkValue = hssfRow.getCell(5).getStringCellValue();
                stringBuffer=   appendOutPutString(insuValue,bankValue,stringBuffer);
            }
        }
        return  stringBuffer.toString();
    }

    private StringBuffer appendOutPutString(String insuValue, String bankValue, StringBuffer stringBuffer) {

        stringBuffer.append("insert into `universal`.`system_dictionary_detail` ( `DICT_DETAIL_ID`, `DICT_ID`, `OPTION_KEY`, `OPTION_VALUE`, `OPTION_CLASS`, `OPTION_COLUMN1`, `OPTION_COLUMN2`, `OPTION_COLUMN3`, `OPTION_COLUMN4`, `OPTION_COLUMN5`, `OPTION_REMARK`, `CREATE_DATE`, `UPDATE_DATE`, `CREATE_ACCOUNT_ID`, `UPDATE_ACCOUNT_ID`, `STATUS`, `RCDSTS`, `option_column6`, `option_column7`, `option_column8`) values ( uuid(), '588C0A45-F521-E045-811D-76A0758FA64D', 'Mapping',");
        stringBuffer.append("'"+bankValue+"',");
        stringBuffer.append("'"+insuValue+"',");
        stringBuffer.append("'04', 'life', '17', '640',");
        stringBuffer.append("'"+bankValue+"',");
        stringBuffer.append(" '',");
        stringBuffer.append("'2019-06-05 09:59:27', '2019-06-05 09:59:27', '', '', 'A', 'A', null, null, null);\r\n");


        return stringBuffer;
    }
}
