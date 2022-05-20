package com.bip.blockchainadapter.models.messaging;

import com.bip.blockchainadapter.models.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPayload {

    private String name;
    private String email;
    private String walletAddress;
    private String phoneNumber;
    private Address cityAddress;
    private RoleType role;
}