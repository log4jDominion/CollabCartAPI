package com.collab.cart.collabcartfinal.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.collab.cart.collabcartfinal.models.Campaign;
import com.collab.cart.collabcartfinal.models.CompanyProfile;
import com.collab.cart.collabcartfinal.services.CampaignService;
import com.collab.cart.collabcartfinal.services.CompanyProfileService;
import com.collab.cart.collabcartfinal.services.CreateCampaignService;

@RestController
@CrossOrigin
@RequestMapping("/campaign")
public class CampaignController {

    @Autowired
    private CreateCampaignService createCampaignService;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CompanyProfileService companyProfileService;

    @PostMapping("/create")
    public ResponseEntity<Boolean> createCampaign(@RequestBody Campaign newCampaign) throws IOException {
        boolean isCreated = createCampaignService.createCampaign(newCampaign);
        return ResponseEntity.ok(isCreated);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Campaign>> getAllCampaigns() throws IOException {
        List<Campaign> campaigns = campaignService.getAllCampaigns();
        return ResponseEntity.ok(campaigns);
    }

    @PostMapping("/profile")
    public ResponseEntity<Boolean> createCompanyProfile(@RequestBody CompanyProfile profile) throws IOException {
        boolean isCreated = companyProfileService.addCompanyProfile(profile);
        return ResponseEntity.ok(isCreated);
    }
}
