package com.travelapp.rest.controllers;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.Payment;
import com.travelapp.core.model.User;
import com.travelapp.core.model.enums.DestinationType;
import com.travelapp.core.model.enums.PaymentType;
import com.travelapp.core.service.PaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/payments")
@SecurityRequirement(name = "JWT Security")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /*@GetMapping("/my-payments")
    @PreAuthorize("hasRole('MEMBER')")
    public List<Payment> getCurrentUserPayments() {
        return paymentService.getCurrentUserPayments();
    }*/

    @PostMapping(path = "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Payment addDestination(@RequestBody Payment payment){
        return paymentService.addPayment(payment);
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Payment> getAll(){
        return paymentService.getAll();
    }

    @PutMapping(path = "/{paymentId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Payment updatePayment(@PathVariable("paymentId") String paymentId, @RequestBody Payment paymentPayload) throws Exception {
        return paymentService.updatePayment(paymentId, paymentPayload);
    }

    @DeleteMapping(path = "/{payment}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deletePayment(@RequestBody Payment payment) {
        paymentService.deletePayment(payment);
    }

    @GetMapping(path = "/filter")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Payment> filterPayments(
            @RequestParam("type") PaymentType paymentType
    ){
        return paymentService.filter(paymentType);
    }

    @GetMapping(path = "/{paymentId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Payment getPaymentById(@PathVariable String paymentId) throws Exception {
        return paymentService.getPaymentById(paymentId);
    }
}
