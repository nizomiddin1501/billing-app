package zeroone.developers.billingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zeroone.developers.billingapp.entity.PurchaseRequest;
import zeroone.developers.billingapp.service.PurchaseService;

import java.util.Arrays;

/**
 * REST controller for managing purchase operations.
 * Provides an endpoint for completing a purchase transaction.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    /**
     * Completes a purchase transaction with the provided purchase request details.
     *
     * @param purchaseRequest the request containing transaction, items, and payment details for completing the purchase
     * @return a ResponseEntity with a success message indicating that the purchase has been completed
     */
    @Operation(summary = "Complete a Purchase", description = "Complete a purchase transaction by providing transaction, items, and payment details.")
    @ApiResponse(responseCode = "200", description = "Purchase completed successfully.")
    @PostMapping("/complete")
    public ResponseEntity<String> completePurchase(@RequestBody PurchaseRequest purchaseRequest) {
        purchaseService.completePurchase(purchaseRequest.getTransaction(),
                Arrays.asList(purchaseRequest.getItems()),
                purchaseRequest.getPayment());
        return ResponseEntity.ok("Purchase completed successfully");
    }
}
