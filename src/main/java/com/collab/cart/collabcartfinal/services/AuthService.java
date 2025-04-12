package com.collab.cart.collabcartfinal.services;

import com.collab.cart.collabcartfinal.models.InfluencerModel;
import com.collab.cart.collabcartfinal.models.UserModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private static final String FILE_PATH = "users.json";
    ObjectMapper mapper = new ObjectMapper();

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

        // Append and save
        users.add(newUser);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), users);
        return true;
    }

    public UserModel validateLogin(String username, String password) {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/users.json");
            List<UserModel> users = mapper.readValue(inputStream, new TypeReference<>() {});
            Optional<UserModel> optional =  users.stream()
                    .filter(u -> u.getEmailId().equals(username) && u.getPassword().equals(password))
                    .findFirst();

            return optional.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
