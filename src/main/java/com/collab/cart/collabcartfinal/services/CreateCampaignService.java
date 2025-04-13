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
public class CreateCampaignService {

    private static final String FILE_PATH = "campaign.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Load all campaigns from the JSON file
    private List<Campaign> loadCampaigns() throws IOException {
        File file = new File(FILE_PATH);

        // If the file doesn't exist, create it with an empty JSON array
        if (!file.exists()) {
            file.createNewFile();
            objectMapper.writeValue(file, new ArrayList<Campaign>());
            return new ArrayList<>();
        }

        // If the file is empty, return an empty list
        if (file.length() == 0) {
            return new ArrayList<>();
        }

        // Otherwise, read the existing list from the file
        return objectMapper.readValue(file, new TypeReference<>() {});
    }

    // Save a new campaign to the JSON file
    public boolean createCampaign(Campaign newCampaign) throws IOException {
        List<Campaign> campaigns = loadCampaigns();
        campaigns.add(newCampaign);

        System.out.println("Saving campaign to: " + new File(FILE_PATH).getAbsolutePath());
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), campaigns);

        return true;
    }
}
