package zeroone.developers.billingapp.service;

import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface FileDownloadService {


    void generatePDF(Long hotelId, HttpServletResponse response) throws IOException, DocumentException;

    void generateExcel(Long hotelId, HttpServletResponse response) throws IOException;

    void generateCSV(Long hotelId, HttpServletResponse response) throws IOException;






}
