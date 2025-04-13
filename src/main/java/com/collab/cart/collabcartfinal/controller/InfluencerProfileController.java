package com.collab.cart.collabcartfinal.controller;

import com.collab.cart.collabcartfinal.models.InfluencerProfile;
import com.collab.cart.collabcartfinal.services.InfluencerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/influencer")
public class InfluencerProfileController {

    @Autowired
    private InfluencerProfileService influencerProfileService;

    @PostMapping("/profile")
    public ResponseEntity<?> createCompanyProfile(@RequestBody InfluencerProfile profile) {
        try {
            boolean isCreated = influencerProfileService.addCompanyProfile(profile);
            return ResponseEntity.ok(isCreated);
        } catch (Exception e) {
            e.printStackTrace(); // Logs the exact error to the console
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
