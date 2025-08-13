package com.elementoj.auth.config;

// 导入nimbus-jose-jwt库中的JWK相关类，用于JWT签名验证

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.UUID;

/**
 * OAuth2 授权服务器配置类
 * <p>
 * 该配置类负责配置 OAuth2 授权服务器的核心组件，包括：
 * 1. 安全过滤链配置
 * 2. 客户端信息存储
 * 3. 令牌生成和解析
 * 4. JWT 编码和解码
 * 5. 授权信息存储
 */
@Configuration
@EnableWebSecurity
public class AuthorizationServerConfig {

    /**
     * 配置授权服务器的安全过滤链
     * <p>
     * 这是 OAuth2 授权服务器的核心安全配置，定义了：
     * - 哪些端点需要认证
     * - CSRF 保护策略
     * - 异常处理方式
     * - JWT 资源服务器配置
     * <p>
     * 示例：
     * 访问 /oauth2/token 端点时会应用此配置
     *
     * @return 配置好的 SecurityFilterChain
     * @throws Exception 配置异常
     */
//    @Resource
//    public UserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //
//
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> userTokenEnhancer() {
        return context -> {
            // 获取客户端ID
            String clientId = context.getRegisteredClient().getClientId();

            // 添加自定义信息
            context.getClaims().claim("client_id", clientId);

            // 如果需要添加更多信息，可以继续添加其他claims
            // Map<String, Object> additionalInfo = new HashMap<>();
            // additionalInfo.put("additional_key", "additional_value");
            // context.getClaims().claims(claims -> claims.putAll(additionalInfo));
        };
    }

    //
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 1) // 较低优先级
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(login -> login
                                .loginPage("/login-view")   // 指定登录页面URL
                                .loginProcessingUrl("/login")
                                .permitAll()
                )
//                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().authenticated()
                );
                // 启用纯安全过滤器登录页（不经过MVC）


        return http.build();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(
            HttpSecurity http, OAuth2TokenGenerator<OAuth2Token> tokenGenerator) throws Exception {

        // 创建OAuth2授权服务器配置器实例
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
                new OAuth2AuthorizationServerConfigurer();

        // 获取OAuth2授权服务器端点的请求匹配器
        // 这个匹配器会匹配所有OAuth2相关的端点，如/oauth2/token, /oauth2/authorize等
        RequestMatcher endpointsMatcher = authorizationServerConfigurer
                .getEndpointsMatcher();
        // 配置HTTP安全策略
        http
                .securityMatcher(endpointsMatcher)
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
//                        .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
                .formLogin(AbstractHttpConfigurer::disable) // 禁用所有表单登录处理
                .exceptionHandling(exceptions ->
                        exceptions.authenticationEntryPoint(
                                new LoginUrlAuthenticationEntryPoint("/login-view")
                        )
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(Customizer.withDefaults()))
                .with(authorizationServerConfigurer, Customizer.withDefaults());

        authorizationServerConfigurer
                .tokenGenerator(tokenGenerator)
                .oidc(Customizer.withDefaults());    // Enable OpenID Connect 1.0

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
//

    /**
     * 配置 OAuth2 授权信息服务
     * <p>
     * 用于存储和检索授权信息（如授权码、访问令牌等）
     * 使用 JDBC 存储，将数据保存到数据库中
     * <p>
     * 示例数据表结构：
     * oauth2_authorization (
     * id varchar(100) PRIMARY KEY,
     * registered_client_id varchar(100),
     * principal_name varchar(200),
     * authorization_grant_type varchar(100),
     * authorized_scopes varchar(1000),
     * attributes text,
     * state text,
     * authorization_code_value text,
     * authorization_code_issued_at timestamp,
     * ...
     * )
     *
     * @param jdbcTemplate               JDBC 模板用于数据库操作
     * @param registeredClientRepository 客户端信息仓库
     * @return JdbcOAuth2AuthorizationService 实例
     */
    @Bean
    public JdbcOAuth2AuthorizationService authorizationService(
            JdbcTemplate jdbcTemplate,
            RegisteredClientRepository registeredClientRepository) {
        // 创建基于JDBC的OAuth2授权服务实例
        // 当用户授权后，授权信息（如授权码、访问令牌等）会被存储到数据库中
        // 这样在分布式环境下，多个授权服务器实例可以共享授权信息
        return new JdbcOAuth2AuthorizationService(
                jdbcTemplate,
                registeredClientRepository
        );
    }

    /**
     * 配置客户端信息仓库
     * <p>
     * 用于存储和检索 OAuth2 客户端信息（如客户端ID、密钥、授权类型等）
     * 使用 JDBC 存储，从数据库中读取客户端信息
     * <p>
     * 示例数据表结构：
     * oauth2_registered_client (
     * id varchar(100) PRIMARY KEY,
     * client_id varchar(100),
     * client_id_issued_at timestamp,
     * client_secret varchar(200),
     * client_secret_expires_at timestamp,
     * client_name varchar(200),
     * client_authentication_methods varchar(1000),
     * authorization_grant_types varchar(1000),
     * redirect_uris varchar(1000),
     * scopes varchar(1000),
     * client_settings varchar(2000),
     * token_settings varchar(2000)
     * )
     *
     * @param jdbcTemplate JDBC 模板用于数据库操作
     *                     //     * @param passwordEncoder 密码编码器
     * @return RegisteredClientRepository 实例
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository(
            JdbcTemplate jdbcTemplate) {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }

    /**
     * 配置 JWK (JSON Web Key) 源
     * <p>
     * 用于 JWT 签名和验证的密钥源
     * 生成 RSA 密钥对并封装为 JWK 格式
     * <p>
     * 示例 JWK Set 结构：
     * {
     * "keys": [
     * {
     * "kty": "RSA",
     * "e": "AQAB",
     * "kid": "abc123",
     * "n": "xxxxx..."
     * }
     * ]
     * }
     *
     * @return JWKSource 实例
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        // 生成RSA密钥对
        // 每次应用重启都会生成新的密钥对，实际生产环境中应该使用固定的密钥
        KeyPair keyPair = generateRsaKey();
        // 获取公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 获取私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 构建RSA密钥对象，包含公钥、私钥和唯一标识符
        // kid用于在验证JWT时找到正确的公钥
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        // 创建JWK集
        JWKSet jwkSet = new JWKSet(rsaKey);
        // 返回不可变的JWK源
        // 其他服务可以通过/.well-known/jwks.json端点获取公钥来验证JWT
        return new ImmutableJWKSet<>(jwkSet);
    }

    /**
     * 配置 JWT 编码器
     * <p>
     * 用于将 OAuth2Token 编码为 JWT 格式的访问令牌
     * <p>
     * 示例 JWT 结构：
     * Header:
     * {
     * "alg": "RS256",
     * "kid": "abc123"
     * }
     * Payload:
     * {
     * "sub": "user123",
     * "aud": "client456",
     * "scope": ["read", "write"],
     * "exp": 1234567890,
     * "iat": 1234567890
     * }
     *
     * @param jwkSource JWK 源用于签名
     * @return JwtEncoder 实例
     */
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        // 创建Nimbus库的JWT编码器实例
        // 当需要生成JWT令牌时，会使用私钥对令牌进行签名
        return new NimbusJwtEncoder(jwkSource);
    }

    /**
     * 配置 JWT 解码器
     * <p>
     * 用于解码和验证 JWT 格式的访问令牌
     * <p>
     * 示例验证过程：
     * 1. 检查 JWT 签名是否有效
     * 2. 检查令牌是否过期
     * 3. 验证发行者是否正确
     *
     * @param jwkSource JWK 源用于验证签名
     * @return JwtDecoder 实例
     */
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        // 使用OAuth2授权服务器配置工具创建JWT解码器
        // 当资源服务器收到JWT令牌时，会使用公钥验证签名并解析令牌内容
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    /**
     * 配置 OAuth2 令牌生成器
     * <p>
     * 负责生成各种类型的 OAuth2 令牌（访问令牌、刷新令牌等）
     * 支持 JWT 和普通令牌格式
     * <p>
     * 示例令牌生成：
     * 1. JWT 访问令牌（包含用户信息和权限）
     * 2. 刷新令牌（用于获取新的访问令牌）
     * 3. 授权码（用于授权码流程）
     *
     * @param jwtEncoder JWT 编码器
     * @return OAuth2TokenGenerator 实例
     */
    @Bean
    public OAuth2TokenGenerator<OAuth2Token> tokenGenerator(
            JwtEncoder jwtEncoder) {
        // 创建JWT生成器
        // 用于生成JWT格式的访问令牌
        JwtGenerator jwtGenerator = new JwtGenerator(jwtEncoder);
        // 设置JWT自定义器（增强器）
        // 可以在JWT中添加额外的用户信息或自定义声明
        jwtGenerator.setJwtCustomizer(userTokenEnhancer());

        // 返回委托令牌生成器，支持JWT、访问令牌和刷新令牌生成
        // 按顺序尝试生成令牌，如果JwtGenerator不能处理，则尝试OAuth2AccessTokenGenerator
        return new DelegatingOAuth2TokenGenerator(
                jwtGenerator,
                new OAuth2AccessTokenGenerator(),
                new OAuth2RefreshTokenGenerator()
        );
    }

    /**
     * 配置授权服务器设置
     * <p>
     * 定义授权服务器的基本设置，如发行者 URL
     * <p>
     * 示例配置：
     * issuer: "http://localhost:9000"  // 用于构建 JWT 中的 iss 字段
     *
     * @return AuthorizationServerSettings 实例
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        // 构建并返回授权服务器设置实例
        return AuthorizationServerSettings.builder()
                // 设置JWT发行者地址
                // 这个URL会作为JWT中的iss字段值，资源服务器会验证该字段
                .issuer("http://elementoj-auth")
                .build();
    }

    /**
     * 生成 RSA 密钥对
     * <p>
     * 用于 JWT 签名的密钥对生成
     * <p>
     * 示例密钥对：
     * 公钥: 用于验证 JWT 签名
     * 私钥: 用于签署 JWT
     *
     * @return 生成的 RSA 密钥对
     */
    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            // 获取RSA密钥对生成器实例
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            // 初始化密钥长度为2048位
            // 2048位是目前推荐的最小安全长度
            keyPairGenerator.initialize(2048);
            // 生成密钥对
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            // 如果发生异常，抛出非法状态异常
            throw new IllegalStateException(ex);
        }
        // 返回生成的密钥对
        return keyPair;
    }
}