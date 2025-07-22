package com.elementoj.auth.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

//@Configuration
public class JwtConfiguration {
//    @Bean("jwtAccessTokenConverter")
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setKeyPair(keyPair());//配置JWT使用的秘钥
//        converter.setSigningKey("elementojauth");
//        return converter;
//    }

//    @Bean
//    public TokenStore tokenStore(@Qualifier("jwtAccessTokenConverter") JwtAccessTokenConverter converter) {
//        return new JwtTokenStore(converter);
//    }

//    @Bean
//    public KeyPair keyPair() {
//        //从classpath下的证书中获取秘钥对
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("key.jks"),
//                "qq106987632".toCharArray());
//        return keyStoreKeyFactory.getKeyPair("key", "qq106987632".toCharArray());
//    }


}
