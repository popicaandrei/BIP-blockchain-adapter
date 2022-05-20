package com.bip.blockchainadapter.models.messaging;

import com.bip.blockchainadapter.models.enums.AuthType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventPayload implements Serializable {

    private String eventName;
    private Double reward;
    private UserPayload user;
    private String institutionWallet;
    private String institutionName;
    private AuthType eventAuthType;
    private String identificator;
    private Date timestamp;
}