package com.collab.cart.collabcartfinal.services;

import com.collab.cart.collabcartfinal.models.SocialAccounts;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SocialAccountsService {

    private static final String FILE_PATH = "socialAccounts.json";
    private final ObjectMapper mapper = new ObjectMapper();

    // Load existing social account entries
    private List<SocialAccounts> loadAccounts() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            return mapper.readValue(file, new TypeReference<>() {});
        } else {
            return new ArrayList<>();
        }
    }

    // Save a new social account entry
    public void saveSocialAccounts(SocialAccounts account) throws IOException {
        List<SocialAccounts> accounts = loadAccounts();
        accounts.add(account);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), accounts);
    }
}
