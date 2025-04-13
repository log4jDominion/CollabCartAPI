package com.collab.cart.collabcartfinal.services;

import com.collab.cart.collabcartfinal.models.InfluencerProfile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InfluencerProfileService {

    private static final String FILE_PATH = "influencerProfile.json";
    private final ObjectMapper mapper = new ObjectMapper();

    // Load all company profiles from JSON
    private List<InfluencerProfile> loadProfiles() throws IOException {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            file.createNewFile();
            mapper.writeValue(file, new ArrayList<InfluencerProfile>());
            return new ArrayList<>();
        }

        if (file.length() == 0) {
            return new ArrayList<>();
        }

        return mapper.readValue(file, new TypeReference<>() {});
    }

    // Save a new company profile
    public boolean addCompanyProfile(InfluencerProfile profile) throws IOException {
        List<InfluencerProfile> profiles = loadProfiles();
        profiles.add(profile);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), profiles);
        return true;
    }
}
