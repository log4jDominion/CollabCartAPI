package com.collab.cart.collabcartfinal.controller;

import com.collab.cart.collabcartfinal.models.CompanyProfile;
import com.collab.cart.collabcartfinal.services.CompanyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/company")
public class CompanyProfileController {

    @Autowired
    private CompanyProfileService companyProfileService;

    @PostMapping("/profile")
    public ResponseEntity<?> createCompanyProfile(@RequestBody CompanyProfile profile) {
        try {
            boolean isCreated = companyProfileService.addCompanyProfile(profile);
            return ResponseEntity.ok(isCreated);
        } catch (Exception e) {
            e.printStackTrace(); // Logs the exact error to the console
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
