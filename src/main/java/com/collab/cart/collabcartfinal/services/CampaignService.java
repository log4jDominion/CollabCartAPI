package com.collab.cart.collabcartfinal.services;

import com.collab.cart.collabcartfinal.models.Campaign;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignService {

    private static final String FILE_PATH = "campaign.json";
    private final ObjectMapper mapper = new ObjectMapper();

    // Load all campaigns from JSON
    private List<Campaign> loadCampaigns() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            return mapper.readValue(file, new TypeReference<>() {});
        } else {
            return new ArrayList<>();
        }
    }
    // Return all existing campaigns
    public List<Campaign> getAllCampaigns() throws IOException {
        return loadCampaigns();
    }
}
