package com.travelapp.core.model;

import com.travelapp.core.model.enums.PaymentStatus;
import com.travelapp.core.model.enums.PaymentType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Currency;

@Document
public class Payment {
    @Id
    private String id;
    private String userId; //should be foreign key
    private float amount;
    private PaymentType paymentType;
    private Timestamp dateAndTime;
    private Currency currency;
    private PaymentStatus status;


}
