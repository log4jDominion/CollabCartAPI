package com.collab.cart.collabcartfinal.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class InfluencerModel {
        public String name;
        public String platform;
        public int followers;
        public double avgViewCount;
        public String location;
        public String category;

        // Required for Jackson
        public InfluencerModel() {}

        public InfluencerModel(String name, String platform, int followers, double avgViewCount, String location, String category) {
            this.name = name;
            this.platform = platform;
            this.followers = followers;
            this.avgViewCount = avgViewCount;
            this.location = location;
            this.category = category;
        }

        @Override
        public String toString() {
            return "Influencer{" +
                    "name='" + name + '\'' +
                    ", platform='" + platform + '\'' +
                    ", followers=" + followers +
                    ", avgViewCount=" + avgViewCount +
                    ", location='" + location + '\'' +
                    ", category='" + category + '\'' +
                    '}';
        }
}
