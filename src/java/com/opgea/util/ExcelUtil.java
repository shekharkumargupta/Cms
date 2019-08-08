/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.util;

import com.opgea.cms.domain.modal.FileUploadBean;
import com.opgea.cms.web.dto.ResumeDTO;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author Shekhar
 */
public class ExcelUtil {
    
    
    public static List<ResumeDTO> getResumeDetailsFromExcel(FileUploadBean fileUploadBean){
        List<ResumeDTO> recordList = new ArrayList<ResumeDTO>();
        try{
            //FileInputStream myInput = new FileInputStream(fileName);
            POIFSFileSystem myFileSystem = new POIFSFileSystem(fileUploadBean.getFile().getInputStream());

            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);

            Iterator rowIter = mySheet.rowIterator();
            int row = 0;
            while(rowIter.hasNext()){
              HSSFRow myRow = (HSSFRow) rowIter.next();
              Iterator cellIter = myRow.cellIterator();
              if(row > 0){
                ResumeDTO resumeDTO = new ResumeDTO();
                int column  = 0;  
                while(cellIter.hasNext()){
                      HSSFCell cellValue = (HSSFCell) cellIter.next();
                      if(column == 0){
                          resumeDTO.setName(cellValue.toString());
                          System.out.print(cellValue.toString()+"\t");
                      }
                      if(column == 1){
                          String dob = DateUtil.getYYYYMMDDFromDate(cellValue.getDateCellValue(), "-");
                          resumeDTO.setDateOfBirth(dob);
                          System.out.print(dob+"\t");
                      }
                      if(column == 2){
                          resumeDTO.setEmail(cellValue.toString());
                          System.out.print(cellValue.toString()+"\t");
                      }
                      if(column == 3){
                          resumeDTO.setContactNo(cellValue.getStringCellValue());
                          System.out.print(cellValue.getStringCellValue()+"\t");
                      }
                      if(column == 4){
                          if(cellValue != null){
                            resumeDTO.setExperience(new Float(cellValue.toString()));
                            System.out.print(cellValue.toString()+"\t");
                          }
                      }
                      if(column == 5){
                          resumeDTO.setCurrentCompany(cellValue.toString());
                          System.out.print(cellValue.toString()+"\t");
                      }
                      if(column == 6){
                          resumeDTO.setCurrentLocation(cellValue.toString());
                          System.out.print(cellValue.toString()+"\t");
                      }
                      if(column == 7){
                          resumeDTO.setCurrentDesignation(cellValue.toString());
                          System.out.print(cellValue.toString()+"\t");
                      }
                      if(column == 8){
                          if(cellValue != null){
                            resumeDTO.setCurrentSalary(new Float(cellValue.toString()));
                            System.out.print(cellValue.toString()+"\t");
                          }
                      }
                      /*
                      if(column == 9){
                          resumeDTO.setKeySkills(cellValue.toString());
                          System.out.println(cellValue.toString()+"\t");
                      }*/
                      column = column+1;
                  }
                //System.out.println(resumeDTO);
                recordList.add(resumeDTO);
              }
              row = row+1;
            }
        }catch(Exception e){
            System.out.println("Exception: "+e);
        }
        return recordList;
    }
     
    private static void printCellDataToConsole(List<ResumeDTO> dataHolder) {
        for(ResumeDTO dto : dataHolder){
            System.out.println("ResumeDTO: "+dto);
        }
    }
    
    /*
    public static void main(String args[]){
        List<ResumeDTO> excelFile = getResumeDetailsFromExcel("c:/record.xls");
        printCellDataToConsole(excelFile);
    }
    */
}
