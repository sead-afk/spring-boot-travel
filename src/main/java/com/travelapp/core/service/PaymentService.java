package com.travelapp.core.service;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.Payment;
import com.travelapp.core.model.enums.DestinationType;
import com.travelapp.core.model.enums.PaymentType;
import com.travelapp.core.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    public List<Payment> filter(PaymentType paymentType) {
        return paymentRepository.findAllByPaymentType(paymentType);
    }
}
