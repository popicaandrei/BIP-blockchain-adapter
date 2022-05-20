package com.bip.blockchainadapter.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@Component
public class WalletUtil {

    public static List<String> getMnemonics() throws IOException {
        File mnemonicsFile = ResourceUtils.getFile("classpath:data/mnemonics.txt");
        String mnemonics = new String(Files.readAllBytes(mnemonicsFile.toPath()));
        return Arrays.stream(mnemonics.split(" "))
                .map(String::trim)
                .toList();
    }
}
