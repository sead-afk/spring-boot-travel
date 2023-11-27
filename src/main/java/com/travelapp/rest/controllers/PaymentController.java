package com.travelapp.rest.controllers;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.Payment;
import com.travelapp.core.model.enums.DestinationType;
import com.travelapp.core.model.enums.PaymentType;
import com.travelapp.core.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(path = "/add")
    public Payment addDestination(@RequestBody Payment payment){
        return paymentService.addPayment(payment);
    }

    @GetMapping(path = "/")
    public List<Payment> getAll(){
        return paymentService.getAll();
    }

    @GetMapping(path = "/filter")
    public List<Payment> filterPayments(
            @RequestParam("type") PaymentType paymentType
    ){
        return paymentService.filter(paymentType);
    }
}