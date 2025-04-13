package com.collab.cart.collabcartfinal.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.collab.cart.collabcartfinal.models.Campaign;
import com.collab.cart.collabcartfinal.models.CompanyProfile;
import com.collab.cart.collabcartfinal.services.CampaignService;
import com.collab.cart.collabcartfinal.services.CompanyProfileService;
import com.collab.cart.collabcartfinal.services.CreateCampaignService;

@RequestMapping("/campaign")
public class CampaignController {

    @PostMapping("/create")
    public ResponseEntity<Boolean> createCampaign(@RequestBody Campaign newCampaign) throws IOException {
        CreateCampaignService createCampaignService = new CreateCampaignService();
        boolean isCreated = createCampaignService.createCampaign(newCampaign);
        return ResponseEntity.ok(isCreated);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Campaign>> getAllCampaigns() throws IOException {
        CampaignService campaignService = new CampaignService();
        List<Campaign> campaigns = campaignService.getAllCampaigns();
        return ResponseEntity.ok(campaigns);
    }
    
    @PostMapping("/profile")
    public ResponseEntity<Boolean> createCompanyProfile(@RequestBody CompanyProfile profile) throws IOException{
        CompanyProfileService companyProfileService = new CompanyProfileService();
        boolean isCreated = companyProfileService.addCompanyProfile(profile);
        return ResponseEntity.ok(isCreated);
        }
    }
    

