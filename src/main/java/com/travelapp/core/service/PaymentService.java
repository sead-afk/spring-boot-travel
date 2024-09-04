package com.travelapp.core.service;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.Payment;
import com.travelapp.core.model.User;
import com.travelapp.core.model.enums.DestinationType;
import com.travelapp.core.model.enums.PaymentType;
import com.travelapp.core.repository.PaymentRepository;
import com.travelapp.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getCurrentUserPayments() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);
        return paymentRepository.findByUserId(user.getId());
    }

    public Payment getPaymentById(String paymentId) throws Exception {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        if(payment.isEmpty())
            throw new Exception("Cannot find payment with provided payload");

        return payment.get();
    }

    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    public Payment updatePayment(String paymentId, Payment payload) throws Exception {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        if(payment.isEmpty())
            throw new Exception("Cannot find user with provided payload");

        payment.get().setPaymentType(payload.getPaymentType());
        payment.get().setAmount(payload.getAmount());
        payment.get().setCurrency(payload.getCurrency());
        payment.get().setStatus(payload.getStatus());
        payment.get().setDateAndTime(payload.getDateAndTime());


        paymentRepository.save(payment.get());
        return payment.get();
    }
    public List<Payment> filter(PaymentType paymentType) {
        return paymentRepository.findAllByPaymentType(paymentType);
    }

    public void deletePayment(Payment payload) {
        Optional<Payment> paymentOptional = paymentRepository.findById(payload.getId());
        if (!paymentOptional.isPresent()) {
            throw new IllegalStateException("Payment does not exist");
        }
        paymentRepository.deleteById(payload.getId());
    }
}
