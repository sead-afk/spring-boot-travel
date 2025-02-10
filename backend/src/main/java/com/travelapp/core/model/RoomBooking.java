package com.travelapp.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;



@Getter
@Setter
@Document(collection = "roomBookings")
@AllArgsConstructor
@NoArgsConstructor
public class RoomBooking {

    private String hotelId;
    private String roomId;
    private String bookingId;
    private LocalDate BookedAt;
    private LocalDate BookedUntil;
    private LocalDate CreatedAt;
    public boolean isAvailable(LocalDate startDate,LocalDate endDate)
    {
        boolean overlaps = !(endDate.isBefore(BookedAt) || startDate.isAfter(BookedUntil));
        return !overlaps;
    }

}

