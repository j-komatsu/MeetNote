package com.meetnote.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // すべてのエンドポイントにCORSを適用
                .allowedOrigins("http://localhost:3001") // フロントエンドのURL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 許可するHTTPメソッド
                .allowedHeaders("*") // すべてのヘッダーを許可
                .allowCredentials(true); // クッキーを使った認証を許可する場合
    }
}
