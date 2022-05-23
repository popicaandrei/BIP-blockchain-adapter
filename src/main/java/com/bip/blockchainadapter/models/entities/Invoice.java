package com.bip.blockchainadapter.models.entities;

import com.bip.blockchainadapter.models.enums.AuthType;
import com.bip.blockchainadapter.models.messaging.UserPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@Document(value = "invoices")
public class Invoice {

    @Id
    private String id;
    private String event;
    private Double reward;
    private UserPayload invoiceData;
    private String institution;
    private String transactionIdentifier;
    private AuthType authType;
    private String identificator;
    private Date eventDate;

}
