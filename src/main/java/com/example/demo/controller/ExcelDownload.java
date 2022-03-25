package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.dao.*;
import com.example.demo.excelDownloadDto.ExcelObjects;
@org.springframework.stereotype.Controller
public class ExcelDownload {
	@Autowired
	private Dao dao;
	
	@RequestMapping("/excelDownloadPage")
	public String excelDownloadPage() {
		
		return "excelDownload";
	}
	@GetMapping("/excelDownload")
	public void excelDownload(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String genre = req.getParameter("genre");
		List<ExcelObjects> data = dao.getExcelData(genre);
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("첫번째 시트");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("번호");
        cell = row.createCell(1);
        cell.setCellValue("노래이름");
        cell = row.createCell(2);
        cell.setCellValue("장르");
        cell = row.createCell(3);
        cell.setCellValue("공식 여부");
        
        for(int i = 0 ; i < data.size(); i ++) {
        	row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue(data.get(i).getSongNo());
            cell = row.createCell(1);
            cell.setCellValue(data.get(i).getSongName());
            cell = row.createCell(2);
            cell.setCellValue(data.get(i).getGenre());
            cell = row.createCell(3);
            cell.setCellValue(data.get(i).getSongType());
        }
       
        res.setContentType("ms-vnd/excel");
//      response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        res.setHeader("Content-Disposition", "attachment;filename="+genre+":genre taikoSongs.xlsx");

      // Excel File Output
      wb.write(res.getOutputStream());
      wb.close();

		
	}

}
