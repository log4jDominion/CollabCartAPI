package com.collab.cart.collabcartfinal.services;

import com.collab.cart.collabcartfinal.models.CompanyProfile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyProfileService {

    private static final String FILE_PATH = "companyProfile.json";
    private final ObjectMapper mapper = new ObjectMapper();

    // Load all company profiles from JSON
    private List<CompanyProfile> loadProfiles() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            return mapper.readValue(file, new TypeReference<>() {});
        } else {
            return new ArrayList<>();
        }
    }

    // Save a new company profile
    public boolean addCompanyProfile(CompanyProfile profile) throws IOException {
        List<CompanyProfile> profiles = loadProfiles();
        profiles.add(profile);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), profiles);

        return true;
    }
}
