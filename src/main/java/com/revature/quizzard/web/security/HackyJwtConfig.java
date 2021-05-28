package com.revature.quizzard.web.security;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.Key;
import java.util.Properties;

public class HackyJwtConfig {

    private static String header;
    private static String prefix;
    private static String secret;
    private static int expiration;
    private static SignatureAlgorithm sigAlg = SignatureAlgorithm.HS512;
    private static Key signingKey;

    static {
        Properties props = new Properties();
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties"));
            header = props.getProperty("jwt.header");
            prefix = props.getProperty("jwt.prefix");
            secret = props.getProperty("jwt.secret");
            expiration = 24 * 60 * 60 * 1000;
            sigAlg = SignatureAlgorithm.HS512;

            byte[] secretBytes = DatatypeConverter.parseBase64Binary(secret);
            signingKey = new SecretKeySpec(secretBytes, sigAlg.getJcaName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHeader() {
        return header;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getSecret() {
        return secret;
    }

    public static int getExpiration() {
        return expiration;
    }

    public static SignatureAlgorithm getSigAlg() {
        return sigAlg;
    }

    public static Key getSigningKey() {
        return signingKey;
    }

}
