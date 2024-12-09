package org.example.coffee.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@ConfigurationProperties(prefix = "my")
@RequiredArgsConstructor
public class PropertyConfig implements WebMvcConfigurer {
    private final String path;
    private final String upload;
    private final String home = System.getProperty("user.home");

    public String getUploadPath() {
        return home + path;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /upload/** URL로 들어오는 요청을 Desktop/pjt1/uploads 디렉토리에서 매핑
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + home + path);
    }
}
