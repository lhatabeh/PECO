package com.progressSoft.FXDeal.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Deal_Id;
    @Column
    @NotNull(message = "Ordering_Currency may not be null")
    @NotBlank(message = "Ordering_Currency may not be null")
    private String Ordering_Currency;

    @Column
    @NotNull(message = "To_Currency_ISO_Code may not be null")
    @NotBlank(message = "To_Currency_ISO_Code may not be null")
    private String To_Currency_ISO_Code;

    @Column(nullable = false, updatable = false, name = "Deal_Timestamp", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime Deal_Timestamp;

    @Column
    @NotNull(message = "Deal_Amount_In_Ordering_Currency may not be null")
    private Double Deal_Amount_In_Ordering_Currency;

    @Column(unique = true)
    private String UUID;


    public long getDeal_Id() {
        return Deal_Id;
    }

    public void setDeal_Id(long deal_Id) {
        Deal_Id = deal_Id;
    }

    public String getOrdering_Currency() {
        return Ordering_Currency;
    }

    public void setOrdering_Currency(String ordering_Currency) {
        Ordering_Currency = ordering_Currency;
    }

    public String getTo_Currency_ISO_Code() {
        return To_Currency_ISO_Code;
    }

    public void setTo_Currency_ISO_Code(String to_Currency_ISO_Code) {
        To_Currency_ISO_Code = to_Currency_ISO_Code;
    }

    public LocalDateTime getDeal_Timestamp() {
        return Deal_Timestamp;
    }

    public void setDeal_Timestamp(LocalDateTime deal_Timestamp) {
        Deal_Timestamp = deal_Timestamp;
    }

    public Double getDeal_Amount_In_Ordering_Currency() {
        return Deal_Amount_In_Ordering_Currency;
    }

    public void setDeal_Amount_In_Ordering_Currency(Double deal_Amount_In_Ordering_Currency) {
        Deal_Amount_In_Ordering_Currency = deal_Amount_In_Ordering_Currency;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
