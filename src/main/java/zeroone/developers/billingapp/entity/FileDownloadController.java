package zeroone.developers.billingapp.entity;

import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zeroone.developers.billingapp.payload.CustomApiResponse;
import zeroone.developers.billingapp.service.FileDownloadService;

import java.io.IOException;

/**
 * Controller for handling file download operations.
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotel/files")
public class FileDownloadController {


    private final FileDownloadService fileDownloadService;


    /**
     * Download a PDF file.
     *
     * @param userId the ID of the hotel for the report
     * @param response to write the PDF file to
     * @return ResponseEntity with the operation status
     * @throws IOException if an error occurs during PDF generation
     */
    @GetMapping("/download/pdf/{userId}")
    public ResponseEntity<CustomApiResponse<Void>> downloadPDF(
            @PathVariable Long userId,
            HttpServletResponse response) throws IOException, DocumentException {
        fileDownloadService.generatePDF(userId, response);
        return ResponseEntity.ok(new CustomApiResponse<>(
                "PDF file generated successfully.",
                true,
                null));
    }

    /**
     * Download an Excel file.
     *
     * @param userId the ID of the hotel for the report
     * @param response to write the Excel file to
     * @return ResponseEntity with the operation status
     */
    @GetMapping("/download/excel/{userId}")
    public ResponseEntity<CustomApiResponse<Void>> downloadExcel(
            @PathVariable Long userId,
            HttpServletResponse response) throws IOException {
        fileDownloadService.generateExcel(userId, response);
        return ResponseEntity.ok(new CustomApiResponse<>(
                "Excel file generated successfully.",
                true,
                null));
    }

    /**
     * Download a CSV file.
     *
     * @param userId the ID of the hotel for the report
     * @param response to write the CSV file to
     * @return ResponseEntity with the operation status
     */
    @GetMapping("/download/csv/{userId}")
    public ResponseEntity<CustomApiResponse<Void>> downloadCSV(
            @PathVariable Long userId,
            HttpServletResponse response) throws IOException {
        fileDownloadService.generateCSV(userId, response);
        return ResponseEntity.ok(new CustomApiResponse<>(
                "CSV file generated successfully.",
                true,
                null));
    }




}
