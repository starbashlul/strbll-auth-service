package org.example.strbllauthservice.bll.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class JwtConfig {

    @Bean
    public PrivateKey privateKey(
            @Value("${jwt.private-key}") Resource resource
    ) throws Exception {

        String key = new String(
                resource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );

        key = key
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] decoded = Base64.getDecoder().decode(key);

        PKCS8EncodedKeySpec spec =
                new PKCS8EncodedKeySpec(decoded);

        KeyFactory factory =
                KeyFactory.getInstance("RSA");

        return factory.generatePrivate(spec);
    }

    @Bean
    public PublicKey publicKey(
            @Value("${jwt.public-key}") Resource resource
    ) throws Exception {

        String key = new String(
                resource.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );

        key = key
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] decoded = Base64.getDecoder().decode(key);

        X509EncodedKeySpec spec =
                new X509EncodedKeySpec(decoded);

        KeyFactory factory =
                KeyFactory.getInstance("RSA");

        return factory.generatePublic(spec);
    }
}