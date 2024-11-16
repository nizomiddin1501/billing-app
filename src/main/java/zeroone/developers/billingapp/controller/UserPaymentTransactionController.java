package zeroone.developers.billingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zeroone.developers.billingapp.exceptions.UserPaymentTransactionException;
import zeroone.developers.billingapp.payload.CustomApiResponse;
import zeroone.developers.billingapp.payload.UserPaymentTransactionDto;
import zeroone.developers.billingapp.service.UserPaymentTransactionService;

/**
 * REST controller for managing userPaymentTransactions, offering endpoints for
 * creating, updating, retrieving, and deleting userPaymentTransaction records.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-payment-transactions")
public class UserPaymentTransactionController {

    private final UserPaymentTransactionService userPaymentTransactionService;


    /**
     * Retrieve a paginated list of userPaymentTransactions.
     *
     * @param page the page number to retrieve (default is 0)
     * @param size the number of userPaymentTransactions per page (default is 10)
     * @return a ResponseEntity containing a CustomApiResponse with the paginated UserDto list
     */
    @Operation(summary = "Get all UserPaymentTransactions with Pagination", description = "Retrieve a paginated list of all userPaymentTransactions.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of userPaymentTransactions.")
    @GetMapping
    public ResponseEntity<CustomApiResponse<Page<UserPaymentTransactionDto>>> getAllUserPaymentTransactions(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<UserPaymentTransactionDto> userPaymentTransactionDtos = userPaymentTransactionService.getAllUserPaymentTransactions(page, size);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Successfully retrieved the list of userPaymentTransactions.",
                true,
                userPaymentTransactionDtos), HttpStatus.OK);
    }


    /**
     * Retrieve a userPaymentTransaction by their unique ID using the provided UserPaymentTransactionDto.
     *
     * @param id the ID of the userPaymentTransaction to retrieve
     * @return a ResponseEntity containing a CustomApiResponse with the UserPaymentTransactionDto and
     * an HTTP status of OK
     */
    @Operation(summary = "Get UserPaymentTransaction by ID", description = "Retrieve a userPaymentTransaction by their unique identifier.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the userPaymentTransaction.")
    @ApiResponse(responseCode = "404", description = "UserPaymentTransaction not found.")
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<UserPaymentTransactionDto>> getUserById(@PathVariable Long id) {
        UserPaymentTransactionDto userPaymentTransactionDto = userPaymentTransactionService.getUserPaymentTransactionById(id)
                .orElseThrow(() -> new UserPaymentTransactionException("UserPaymentTransaction not found"));
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Successfully retrieved the userPaymentTransaction.",
                true,
                userPaymentTransactionDto), HttpStatus.OK);
    }


    /**
     * Creates a new userPaymentTransaction.
     *
     * @param userPaymentTransactionDto the DTO containing the userPaymentTransaction information to be saved
     * @return a ResponseEntity containing a CustomApiResponse with the saved userPaymentTransaction data
     */
    @Operation(summary = "Create a new UserPaymentTransaction", description = "Create a new userPaymentTransaction record.")
    @ApiResponse(responseCode = "201", description = "UserPaymentTransaction created successfully.")
    @PostMapping
    public ResponseEntity<CustomApiResponse<UserPaymentTransactionDto>> createUserPaymentTransaction(@Valid @RequestBody UserPaymentTransactionDto userPaymentTransactionDto) {
        UserPaymentTransactionDto savedUserPaymentTransaction = userPaymentTransactionService.createUserPaymentTransaction(userPaymentTransactionDto);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "UserPaymentTransaction created successfully",
                true,
                savedUserPaymentTransaction), HttpStatus.CREATED);
    }


    /**
     * Update the details of an existing userPaymentTransaction using the provided UserPaymentTransactionDto.
     *
     * @param id      the ID of the userPaymentTransaction to be updated
     * @param userPaymentTransactionDto the DTO containing updated userPaymentTransaction details
     * @return a ResponseEntity containing a CustomApiResponse with the updated UserPaymentTransactionDto
     */
    @Operation(summary = "Update userPaymentTransaction", description = "Update the details of an existing userPaymentTransaction.")
    @ApiResponse(responseCode = "200", description = "UserPaymentTransaction updated successfully")
    @ApiResponse(responseCode = "404", description = "UserPaymentTransaction not found")
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<UserPaymentTransactionDto>> updateUserPaymentTransaction(
            @PathVariable Long id,
            @RequestBody UserPaymentTransactionDto userPaymentTransactionDto) {
        UserPaymentTransactionDto updatedUserPaymentTransaction = userPaymentTransactionService.updateUserPaymentTransaction(id, userPaymentTransactionDto);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "UserPaymentTransaction updated successfully",
                true,
                updatedUserPaymentTransaction), HttpStatus.OK);
    }


    /**
     * Delete a userPaymentTransaction by their ID.
     *
     * @param id the ID of the userPaymentTransaction to delete
     * @return a ResponseEntity containing a CustomApiResponse with the status of the operation
     */
    @Operation(summary = "Delete UserPaymentTransaction", description = "Delete a userPaymentTransaction by its ID.")
    @ApiResponse(responseCode = "204", description = "UserPaymentTransaction deleted successfully.")
    @ApiResponse(responseCode = "404", description = "UserPaymentTransaction not found.")
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> deleteUserPaymentTransaction(@PathVariable Long id) {
        userPaymentTransactionService.deleteUserPaymentTransaction(id);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "UserPaymentTransaction deleted successfully.",
                true,
                null), HttpStatus.NO_CONTENT);
    }


}
