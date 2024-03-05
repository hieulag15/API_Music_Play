package com.example.api_music_play.Repository;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;

public class CloudinaryConfig {

    private String cloudName = "duq7r7iuo";

    private String apiKey = "338818468669422";

    private String apiSecret = "eyvS9ai5yFRbXioc6MhRnMF-y4I";

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }
}
