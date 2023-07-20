package com.kvtsoft.fileuploader.controller;

import com.kvtsoft.fileuploader.model.RecoveryModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

@RestController(value = "service")
public class ServiceController {

    private String fileLocation;

    @PostMapping("/process-excel")
    public String processExcel(@RequestBody() String file) throws IOException {
        System.out.println("process-excel...");
//        InputStream in = file.getInputStream();
//        File currDir = new File(".");
//        String path = currDir.getAbsolutePath();
//        fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
//        FileOutputStream f = new FileOutputStream(fileLocation);
//        int ch = 0;
//        while ((ch = in.read()) != -1) {
//            f.write(ch);
//        }
//        f.flush();
//        f.close();
////        model.addAttribute("message", "File: " + file.getOriginalFilename()
////                + " has been uploaded successfully!");

        try {
            // Decoding the encoded content "uploaded file"
            byte[] base64decodedBytes = Base64.getDecoder().decode(file);
            InputStream inputStream = new ByteArrayInputStream(base64decodedBytes);

            // Create Workbook instance holding reference to .xlsx file
            // XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get first/desired sheet from the workbook
            // XSSFSheet sheet = workbook.getSheetAt(0);

            String filePath = "D:\\dev-workspace\\testfile.xlsx";
            // FileInputStream fs = new FileInputStream(inputStream);

            //Creating a workbook
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

            //HSSFWorkbook workbook = new HSSFWorkbook(fs);
            // HSSFSheet sheet = workbook.getSheetAt(0);

            XSSFSheet sheet = workbook.getSheetAt(0);

            //ArrayList<ColumnNames> candidateList = new ArrayList<>();
            ArrayList<RecoveryModel> recoveryList = new ArrayList<>();

            // get row0 from the sheet
            Row row0 = sheet.getRow(0);

            // get cell values from 0-4 cell from the row0
            Cell ceZero = row0.getCell(0);
            Cell ceOne = row0.getCell(1);
            Cell ceTwo = row0.getCell(2);
            Cell ceThree = row0.getCell(3);
            Cell ceFour = row0.getCell(4);
            Cell ceFive = row0.getCell(5);
            Cell ceSix = row0.getCell(6);

            String ceZeroString = ceZero.getStringCellValue().toString().trim();
            String ceOneString = ceOne.getStringCellValue().toString().trim();
            String ceTwoString = ceTwo.getStringCellValue().toString().trim();
            String ceThreeString = ceThree.getStringCellValue().toString().trim();
            String ceFourString = ceFour.getStringCellValue().toString().trim();
            String ceFiveString = ceFive.getStringCellValue().toString().trim();
            String ceSixString = ceSix.getStringCellValue().toString().trim();

            // compare the header for verifying file format
            // need to take care of extra spacing
            if (ceZeroString.equalsIgnoreCase("Xml Id") && ceOneString.equalsIgnoreCase("Permanent Link") && ceTwoString.equalsIgnoreCase("Successor")
                    && ceThreeString.equalsIgnoreCase("Predecessor") && ceFourString.equalsIgnoreCase("Generation Id") && ceFiveString.equalsIgnoreCase("Valid To") && ceSixString.equalsIgnoreCase("Valid From")) {

                // ignoring header for that initializing loop from +1 (row1)
                for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
                    RecoveryModel model = new RecoveryModel();
                    Row row = sheet.getRow(i);

                    for (int cellno = row.getFirstCellNum(); cellno <= row.getLastCellNum(); cellno++) {
                        Cell cell = row.getCell(cellno);

                        if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                            if (cellno == 0) {
                                model.setXmlId(cell.getStringCellValue());
                            }

                            if (cellno == 1) {
                                model.setPermanentLink(cell.getStringCellValue());
                            }

                            if (cellno == 2) {
                                model.setSuccessor(cell.getStringCellValue());
                            }

                            // Generation Id
                            if (cellno == 3) {
                                model.setPredecessor(cell.getStringCellValue());
                            }

                            if (cellno == 4) {
                                model.setGenerationId(cell.getStringCellValue());
                            }

                            if (cellno == 5) {
                                Date validTo = cell.getDateCellValue();
                                model.setValidTo(validTo.toString());
                            }

                            if (cellno == 6) {
                                Date validFrom = cell.getDateCellValue();
                                model.setValidFrom(validFrom.toString());

                            }
                        }
                    }
                    recoveryList.add(model);
                }
                System.out.println(recoveryList);

                //update the missing columns in db
                // mapping (setter) values into document
//                Document document = new Document();
//                List<Document> data = new ArrayList<>();
//                for (RecoveryModel can : recoveryList) {
//                    document = new Document();
//                    document.put("name", can.getName());
//                    document.put("email", can.getEmailId());
//                    document.put("mobile", can.getMobile());
//                    document.put("vendor", can.getVendor());
//
//                    data.add(document);
//
//                }
//
//                // check for null objects and rows
//                JSONObject object = new JSONObject();
//                object.put("data", data);
//                JSONArray dataArray = object.optJSONArray("data");
//                logger.log("dataArraySize " + dataArray.length());
//
//                boolean rowLength = false;
//                for (int i = 0; i < dataArray.length(); i++) {
//                    JSONObject inputIndexObject = dataArray.optJSONObject(i);
//                    if (inputIndexObject.length() == 0)
//                        continue;
//
//                    rowLength = inputIndexObject.length() == 4;
//                    if (rowLength == false)
//                        break;
//                }
//
//                logger.log("rowLengthState " + rowLength);


            } else {
                System.out.println("Header parameters missing");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception: " + e.getMessage());
        }

        return "File has been processed !!";
    }
}
