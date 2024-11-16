package zeroone.developers.billingapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zeroone.developers.billingapp.entity.PurchaseRequest;
import zeroone.developers.billingapp.service.PurchaseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping("/complete")
    public ResponseEntity<String> completePurchase(@RequestBody PurchaseRequest purchaseRequest) {
        purchaseService.completePurchase(purchaseRequest.getTransaction(),
                purchaseRequest.getItems(),
                purchaseRequest.getPayment());
        return ResponseEntity.ok("Purchase completed successfully");
    }

}
