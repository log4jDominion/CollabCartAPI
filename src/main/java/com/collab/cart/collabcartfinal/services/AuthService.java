package com.collab.cart.collabcartfinal.services;

import com.collab.cart.collabcartfinal.models.UserModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class AuthService {

    Random random = new Random();
    private static final String FILE_PATH = "users.json";
    public Map<String, List<String>> map = new HashMap<>();
    ObjectMapper mapper = new ObjectMapper();

    public AuthService() {
        map.put("Fashion", new ArrayList<>(Arrays.asList(
                "Styling everyday looks with a touch of bold.",
                "Bringing streetwear and runway together—one outfit at a time.",
                "Chasing trends, creating timeless style.",
                "Wardrobe magician. Closet chaos turned into daily inspiration.",
                "Curating chic on a budget. Fashion is for everyone.",
                "Color, confidence, and couture—my daily essentials.",
                "Serving looks, not limits. Redefining modern fashion.",
                "Minimalist soul with a maximalist wardrobe.",
                "Elevating basics, embracing statements.",
                "Style speaks louder than words—here’s my voice."
        )));
        map.put("Tech", new ArrayList<>(Arrays.asList("Exploring the future of AI, one project at a time.",
                "Tech reviews, developer tips, and startup life decoded.",
                "Making complex code look simple. Sharing the how & why.",
                "From backend to blockchain—navigating the tech world.",
                "Breaking down tech trends for the curious mind.",
                "Engineer by day, content creator by night.",
                "Turning bugs into breakthroughs. Let's build smarter.",
                "Chronicling my journey in software, systems, and silicon.",
                "Teaching tech that actually works in the real world.",
                "Empowering the next-gen devs with open source and coffee.")));
        map.put("Finance", new ArrayList<>(Arrays.asList(
                "Breaking down money moves for millennials.",
                "Investing smart, living free. Teaching you how.",
                "Simplifying stocks, crypto, and wealth-building.",
                "From broke to budget boss—let's grow together.",
                "Finance tips with no fluff. Real talk, real results.",
                "Helping you understand credit, debt, and financial freedom.",
                "Making money management less intimidating.",
                "Helping you retire earlier, richer, and wiser.",
                "Personal finance doesn't have to be boring.",
                "Sharing lessons from Wall Street to your wallet."
        )));
    }

    private List<UserModel> loadUsers() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            return mapper.readValue(file, new TypeReference<>() {});
        } else {
            return new ArrayList<>();
        }
    }

    public boolean registerUser(UserModel newUser) throws IOException {
        List<UserModel> users = loadUsers();

        // Check if user with same username exists
        boolean exists = users.stream()
                .anyMatch(u -> u.getEmailId().equalsIgnoreCase(newUser.getEmailId()));

        if (exists) {
            return false;
        }

        if (StringUtils.containsIgnoreCase(newUser.getType(), "INFLUENCER")) {
            newUser.setFollowers((int) (Math.random()*10000000));
            newUser.setAverageViews((int) (Math.random() * 1000000000));
            String type = "";

            if(StringUtils.containsIgnoreCase(newUser.getDescription(), "fashion")) {
                type = "Fashion";
            } else if(StringUtils.containsIgnoreCase(newUser.getDescription(), "tech")
                    || StringUtils.containsIgnoreCase(newUser.getDescription(), "technology")) {
                type = "Tech";

            } else if(StringUtils.containsIgnoreCase(newUser.getDescription(), "finance")) {
                type = "Finance";

            }
            List<String> list = map.get(type);
            Collections.shuffle(list);
            newUser.setCategory(type);
            newUser.setInsights(list.subList(0,2));
        }

        // Append and save
        users.add(newUser);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), users);
        return true;
    }

    public UserModel validateLogin(String username, String password) {
        try {
            InputStream inputStream = new FileInputStream(FILE_PATH);
            List<UserModel> users = mapper.readValue(inputStream, new TypeReference<>() {});
            Optional<UserModel> optional =  users.stream()
                    .filter(u -> u.getEmailId().equals(username) && u.getPassword().equals(password))
                    .findFirst();

            return optional.orElse(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
