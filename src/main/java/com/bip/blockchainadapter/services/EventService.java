package com.bip.blockchainadapter.services;

import com.bip.blockchainadapter.elrond.ElrondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    ElrondService elrondService;

    public void handleEvents(){

    }
}
