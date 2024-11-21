package zeroone.developers.billingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zeroone.developers.billingapp.exceptions.TransactionException;
import zeroone.developers.billingapp.payload.CustomApiResponse;
import zeroone.developers.billingapp.payload.TransactionDto;
import zeroone.developers.billingapp.service.TransactionService;

/**
 * REST controller for managing transactions, offering endpoints for
 * creating, updating, retrieving, and deleting transaction records.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;


    /**
     * Retrieve a paginated list of transactions.
     *
     * @param page the page number to retrieve (default is 0)
     * @param size the number of transactions per page (default is 10)
     * @return a ResponseEntity containing a CustomApiResponse with the paginated TransactionDto list
     */
    @Operation(summary = "Get all Transactions with Pagination", description = "Retrieve a paginated list of all transactions.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of transactions.")
    @GetMapping
    public ResponseEntity<CustomApiResponse<Page<TransactionDto>>> getAllTransactions(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<TransactionDto> transactionDtos = transactionService.getAllTransactions(page, size);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Successfully retrieved the list of transactions.",
                true,
                transactionDtos), HttpStatus.OK);
    }


    /**
     * Retrieve a transaction by their unique ID using the provided TransactionDto.
     *
     * @param id the ID of the transaction to retrieve
     * @return a ResponseEntity containing a CustomApiResponse with the TransactionDto and
     * an HTTP status of OK
     */
    @Operation(summary = "Get Transaction by ID", description = "Retrieve a transaction by their unique identifier.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the transaction.")
    @ApiResponse(responseCode = "404", description = "Transaction not found.")
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<TransactionDto>> getTransactionById(@PathVariable Long id) {
        TransactionDto transactionDto = transactionService.getTransactionById(id)
                .orElseThrow(() -> new TransactionException("Transaction not found"));
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Successfully retrieved the transaction.",
                true,
                transactionDto), HttpStatus.OK);
    }


    /**
     * Creates a new transaction.
     *
     * @param transactionDto the DTO containing the transaction information to be saved
     * @return a ResponseEntity containing a CustomApiResponse with the saved transaction data
     */
    @Operation(summary = "Create a new Transaction", description = "Create a new transaction record.")
    @ApiResponse(responseCode = "201", description = "Transaction created successfully.")
    @PostMapping
    public ResponseEntity<CustomApiResponse<TransactionDto>> createTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        TransactionDto savedTransaction = transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Transaction created successfully",
                true,
                savedTransaction), HttpStatus.CREATED);
    }


    /**
     * Update the details of an existing transaction using the provided TransactionDto.
     *
     * @param id      the ID of the transaction to be updated
     * @param transactionDto the DTO containing updated transaction details
     * @return a ResponseEntity containing a CustomApiResponse with the updated TransactionDto
     */
    @Operation(summary = "Update transaction", description = "Update the details of an existing transaction.")
    @ApiResponse(responseCode = "200", description = "Transaction updated successfully")
    @ApiResponse(responseCode = "404", description = "Transaction not found")
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<TransactionDto>> updateTransaction(
            @PathVariable Long id,
            @RequestBody TransactionDto transactionDto) {
        TransactionDto updatedTransaction = transactionService.updateTransaction(id, transactionDto);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Transaction updated successfully",
                true,
                updatedTransaction), HttpStatus.OK);
    }


    /**
     * Delete a transaction by their ID.
     *
     * @param id the ID of the transaction to delete
     * @return a ResponseEntity containing a CustomApiResponse with the status of the operation
     */
    @Operation(summary = "Delete Transaction", description = "Delete a transaction by its ID.")
    @ApiResponse(responseCode = "204", description = "Transaction deleted successfully.")
    @ApiResponse(responseCode = "404", description = "Transaction not found.")
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Transaction deleted successfully.",
                true,
                null), HttpStatus.NO_CONTENT);
    }
}
