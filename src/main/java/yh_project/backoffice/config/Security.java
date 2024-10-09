package yh_project.backoffice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import yh_project.backoffice.config.oauth2.BackOfficeOAuth2Service;

@Configuration
@RequiredArgsConstructor
public class Security {

    private final BackOfficeOAuth2Service backOfficeOAuth2Service;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, OAuth2UserService oAuth2UserService) throws Exception {
        http
                .oauth2Login(oAuth2Login -> oAuth2Login
                        .loginPage("/login")
                        .userInfoEndpoint(config->config.userService(backOfficeOAuth2Service))
                        .defaultSuccessUrl("/home", true)
                )
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/myPage").hasAuthority("Member")
                        .requestMatchers("/manage").hasAuthority("Admin")
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
