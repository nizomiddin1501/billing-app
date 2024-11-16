package zeroone.developers.billingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zeroone.developers.billingapp.exceptions.TransactionItemException;
import zeroone.developers.billingapp.payload.CustomApiResponse;
import zeroone.developers.billingapp.payload.TransactionItemDto;
import zeroone.developers.billingapp.service.TransactionItemService;

/**
 * REST controller for managing transactionItems, offering endpoints for
 * creating, updating, retrieving, and deleting transactionItem records.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactionItems")
public class TransactionItemController {

    private final TransactionItemService transactionItemService;


    /**
     * Retrieve a paginated list of transactionItems.
     *
     * @param page the page number to retrieve (default is 0)
     * @param size the number of transactionItems per page (default is 10)
     * @return a ResponseEntity containing a CustomApiResponse with the paginated TransactionItemDto list
     */
    @Operation(summary = "Get all TransactionItems with Pagination", description = "Retrieve a paginated list of all transactionItems.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of transactionItems.")
    @GetMapping
    public ResponseEntity<CustomApiResponse<Page<TransactionItemDto>>> getAllTransactionItems(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<TransactionItemDto> transactionItemDtos = transactionItemService.getAllTransactionItems(page, size);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Successfully retrieved the list of transactionItems.",
                true,
                transactionItemDtos), HttpStatus.OK);
    }


    /**
     * Retrieve a transactionItem by their unique ID using the provided TransactionItemDto.
     *
     * @param id the ID of the transactionItem to retrieve
     * @return a ResponseEntity containing a CustomApiResponse with the TransactionItemDto and
     * an HTTP status of OK
     */
    @Operation(summary = "Get TransactionItem by ID", description = "Retrieve a transactionItem by their unique identifier.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the transactionItem.")
    @ApiResponse(responseCode = "404", description = "TransactionItem not found.")
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<TransactionItemDto>> getTransactionItemById(@PathVariable Long id) {
        TransactionItemDto transactionItemDto = transactionItemService.getTransactionItemById(id)
                .orElseThrow(() -> new TransactionItemException("TransactionItem not found"));
        return new ResponseEntity<>(new CustomApiResponse<>(
                "Successfully retrieved the transactionItem.",
                true,
                transactionItemDto), HttpStatus.OK);
    }


    /**
     * Creates a new transactionItem.
     *
     * @param transactionItemDto the DTO containing the transactionItem information to be saved
     * @return a ResponseEntity containing a CustomApiResponse with the saved transactionItem data
     */
    @Operation(summary = "Create a new TransactionItem", description = "Create a new transactionItem record.")
    @ApiResponse(responseCode = "201", description = "TransactionItem created successfully.")
    @PostMapping
    public ResponseEntity<CustomApiResponse<TransactionItemDto>> createTransactionItem(@Valid @RequestBody TransactionItemDto transactionItemDto) {
        TransactionItemDto savedTransactionItem = transactionItemService.createTransactionItem(transactionItemDto);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "TransactionItem created successfully",
                true,
                savedTransactionItem), HttpStatus.CREATED);
    }


    /**
     * Update the details of an existing transactionItem using the provided TransactionItemDto.
     *
     * @param id      the ID of the transactionItem to be updated
     * @param transactionItemDto the DTO containing updated transactionItem details
     * @return a ResponseEntity containing a CustomApiResponse with the updated TransactionItemDto
     */
    @Operation(summary = "Update transactionItem", description = "Update the details of an existing transactionItem.")
    @ApiResponse(responseCode = "200", description = "TransactionItem updated successfully")
    @ApiResponse(responseCode = "404", description = "TransactionItem not found")
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<TransactionItemDto>> updateTransactionItem(
            @PathVariable Long id,
            @RequestBody TransactionItemDto transactionItemDto) {
        TransactionItemDto updatedTransactionItem = transactionItemService.updateTransactionItem(id, transactionItemDto);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "TransactionItem updated successfully",
                true,
                updatedTransactionItem), HttpStatus.OK);
    }


    /**
     * Delete a transactionItem by their ID.
     *
     * @param id the ID of the transactionItem to delete
     * @return a ResponseEntity containing a CustomApiResponse with the status of the operation
     */
    @Operation(summary = "Delete TransactionItem", description = "Delete a transactionItem by its ID.")
    @ApiResponse(responseCode = "204", description = "TransactionItem deleted successfully.")
    @ApiResponse(responseCode = "404", description = "TransactionItem not found.")
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> deleteTransactionItem(@PathVariable Long id) {
        transactionItemService.deleteTransactionItem(id);
        return new ResponseEntity<>(new CustomApiResponse<>(
                "TransactionItem deleted successfully.",
                true,
                null), HttpStatus.NO_CONTENT);
    }


}
