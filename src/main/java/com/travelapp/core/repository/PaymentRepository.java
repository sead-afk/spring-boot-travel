package com.travelapp.core.repository;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.Payment;
import com.travelapp.core.model.enums.DestinationType;
import com.travelapp.core.model.enums.PaymentType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
    public List<Payment> findAllByPaymentType(PaymentType paymentType);
}
