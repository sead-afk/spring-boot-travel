package com.travelapp.core.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@Document(collection = "seatBookings")
@AllArgsConstructor
@NoArgsConstructor
public class SeatBooking {

    private String flightId;
    private String ticketId;
    private LocalDate BookedAt;
    private LocalDate CreatedAt;

    public boolean isAvailable(LocalDate startDate)
    {
        return !(BookedAt.getYear() == startDate.getYear() &&
                BookedAt.getMonthValue() == startDate.getMonthValue() &&
                BookedAt.getDayOfMonth() == startDate.getDayOfMonth());
    }
}
