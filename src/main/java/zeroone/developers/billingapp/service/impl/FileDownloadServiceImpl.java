package zeroone.developers.billingapp.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import zeroone.developers.billingapp.entity.Transaction;
import zeroone.developers.billingapp.entity.UserPaymentTransaction;
import zeroone.developers.billingapp.repository.TransactionRepository;
import zeroone.developers.billingapp.repository.UserPaymentTransactionRepository;
import zeroone.developers.billingapp.service.FileDownloadService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileDownloadServiceImpl implements FileDownloadService {

    private final TransactionRepository transactionRepository;
    private final UserPaymentTransactionRepository userPaymentTransactionRepository;

    @Override
    public void generatePDF(Long userId, HttpServletResponse response) throws IOException, DocumentException {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        List<UserPaymentTransaction> payments = userPaymentTransactionRepository.findByUserId(userId);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"user_transactions.pdf\"");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Transaction data
        document.add(new Paragraph("Transactions"));
        document.add(new Paragraph(" "));
        for (Transaction transaction : transactions) {
            document.add(new Paragraph("Transaction ID: " + transaction.getId()));
            document.add(new Paragraph("User ID: " + transaction.getUser().getId()));
            document.add(new Paragraph("Total Amount: " + transaction.getTotalAmount()));
            document.add(new Paragraph("Date: " + transaction.getDate()));
            document.add(new Paragraph(" "));
        }

        // User payment transaction data
        document.add(new Paragraph("User Payments"));
        document.add(new Paragraph(" "));
        for (UserPaymentTransaction payment : payments) {
            document.add(new Paragraph("Payment Transaction ID: " + payment.getId()));
            document.add(new Paragraph("User ID: " + payment.getUser().getId()));
            document.add(new Paragraph("Payment Amount: " + payment.getAmount()));
            document.add(new Paragraph("Date: " + payment.getDate()));
            document.add(new Paragraph(" "));
        }

        document.close();
    }


    @Override
    public void generateExcel(Long userId, HttpServletResponse response) throws IOException {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        List<UserPaymentTransaction> payments = userPaymentTransactionRepository.findByUserId(userId);

        Workbook workbook = new XSSFWorkbook();
        Sheet transactionSheet = workbook.createSheet("Transactions");
        Sheet paymentSheet = workbook.createSheet("Payments");

        Row transactionHeader = transactionSheet.createRow(0);
        transactionHeader.createCell(0).setCellValue("Transaction ID");
        transactionHeader.createCell(1).setCellValue("User ID");
        transactionHeader.createCell(2).setCellValue("Total Amount");
        transactionHeader.createCell(3).setCellValue("Date");

        int transactionRowNum = 1;
        for (Transaction transaction : transactions) {
            Row row = transactionSheet.createRow(transactionRowNum++);
            row.createCell(0).setCellValue(transaction.getId());
            row.createCell(1).setCellValue(transaction.getUser().getId());
            row.createCell(2).setCellValue(transaction.getTotalAmount().doubleValue());
            row.createCell(3).setCellValue(transaction.getDate().toString());
        }

        Row paymentHeader = paymentSheet.createRow(0);
        paymentHeader.createCell(0).setCellValue("Payment Transaction ID");
        paymentHeader.createCell(1).setCellValue("User ID");
        paymentHeader.createCell(2).setCellValue("Payment Amount");
        paymentHeader.createCell(3).setCellValue("Date");

        int paymentRowNum = 1;
        for (UserPaymentTransaction payment : payments) {
            Row row = paymentSheet.createRow(paymentRowNum++);
            row.createCell(0).setCellValue(payment.getId());
            row.createCell(1).setCellValue(payment.getUser().getId());
            row.createCell(2).setCellValue(payment.getAmount().doubleValue());
            row.createCell(3).setCellValue(payment.getDate().toString());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"user_transactions.xlsx\"");

        workbook.write(response.getOutputStream());
        workbook.close();
    }


    @Override
    public void generateCSV(Long userId, HttpServletResponse response) throws IOException {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        List<UserPaymentTransaction> payments = userPaymentTransactionRepository.findByUserId(userId);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"user_transactions.csv\"");

        PrintWriter writer = response.getWriter();

        writer.println("Transaction ID, User ID, Total Amount, Date");

        for (Transaction transaction : transactions) {
            writer.println(transaction.getId() + "," + transaction.getUser().getId() + "," +
                    transaction.getTotalAmount() + "," + transaction.getDate());
        }

        writer.println("\nPayment Transaction ID, User ID, Payment Amount, Date");

        for (UserPaymentTransaction payment : payments) {
            writer.println(payment.getId() + "," + payment.getUser().getId() + "," +
                    payment.getAmount() + "," + payment.getDate());
        }

        writer.flush();
        writer.close();
    }
}
