package com.amilo.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;

import java.util.Collection;
import java.util.Collections;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtDecoder jwtDecoder;

    @Autowired
    public SecurityConfiguration(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Bean
    @Primary
    public Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
//        JwtGrantedAuthoritiesConverter jwtCollectionConverter = new JwtGrantedAuthoritiesConverter();
//        jwtCollectionConverter.setAuthorityPrefix("");
//        jwtCollectionConverter.setAuthoritiesClaimName("authorities");
//
//        return jwtCollectionConverter;
        return new Converter<Jwt, Collection<GrantedAuthority>>() {
            @Override
            public Collection<GrantedAuthority> convert(Jwt source) {
                return Collections.singletonList(new SimpleGrantedAuthority("DEFAULT"));
            }
        };
    }

    @Bean
    @Primary
    public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationTokenConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setPrincipalClaimName("sub");
        jwtAuthenticationConverter
                .setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
        return jwtAuthenticationConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .cors()
                .and()
                .oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer -> {
                    httpSecurityOAuth2ResourceServerConfigurer.jwt(jwtConfigurer -> {
                        jwtConfigurer.decoder(this.jwtDecoder);
                        jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationTokenConverter());
                    });
                    httpSecurityOAuth2ResourceServerConfigurer
                            .bearerTokenResolver(new DefaultBearerTokenResolver());
                })
                .headers()
                .frameOptions().sameOrigin().disable();
    }

}
