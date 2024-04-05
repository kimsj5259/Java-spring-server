package com.finance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/h2-console/**", "/user/signup**", "/user/login**"); // 이 부분이 h2-console 보여줌.
    }
    
    // @Bean
    // protected SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .authorizeHttpRequests(requests -> requests
    //             .requestMatchers("/user/signup**", "/user/signup/**").permitAll() // "/user/signup"에 대한 요청을 인증 없이 허용
    //             .anyRequest().authenticated()
    //         );
    //     return http.build();
    // }

    // @Bean
	// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	// 	http
	// 		.authorizeHttpRequests((requests) -> requests
	// 			.requestMatchers("/", "/user").permitAll()
	// 			.anyRequest().authenticated()
	// 		)
	// 		.formLogin((form) -> form
	// 			.loginPage("/login")
	// 			.permitAll()
	// 		)
	// 		.logout((logout) -> logout.permitAll());

	// 	return http.build();
	// }

    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
