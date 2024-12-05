package org.example.coffee.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@ConfigurationProperties(prefix = "my")
@RequiredArgsConstructor
public class PropertyConfig {
    private final String path;
    private final String upload;
    private final String home = System.getProperty("user.home");

    public String getUploadPath() {
        return home + path;
    }
}
