package com.scm1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm1.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {
//     @Bean
//  public UserDetailsService userDetailsService(){
   
//     UserDetails user1 = User
//     .withDefaultPasswordEncoder()
//     .username("shweta")
//     .password("shweta")
//     //.roles("ADMIN","USER")
//     .build();
//     UserDetails user2 = User
//     .withDefaultPasswordEncoder()
//     .username("rudra")
//     .password("rudra")
//     .roles("ADMIN","USER")
//     .build();
//    var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,user2);
//    return inMemoryUserDetailsManager;
//  }
@Autowired
private SecurityCustomUserDetailService userDetailService;
@Autowired
private OAuth handler;
//configuration of authentication
@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // user detail service ka object:
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // password encoder ka object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // configuration

        // urls configure kiay hai ki koun se public rangenge aur koun se private
        // rangenge
        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home", "/register", "/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });
httpSecurity.formLogin(formLogin->{
   
    formLogin.loginPage("/login");
    formLogin.loginProcessingUrl("/authenticate");
    formLogin.successForwardUrl("/user/profile");
    // formLogin.failureForwardUrl("/login?error=true");
    // formLogin.defaultSuccessUrl("/home");
    formLogin.usernameParameter("email");
    formLogin.passwordParameter("password");
});

httpSecurity.csrf(AbstractHttpConfigurer::disable);

// oauth configurations

httpSecurity.oauth2Login(oauth -> {
    oauth.loginPage("/login");
    oauth.successHandler(handler);
});
httpSecurity.logout(logoutForm -> {
    logoutForm.logoutUrl("/do-logout");
    logoutForm.logoutSuccessUrl("/login?logout=true");
});
    return httpSecurity.build();
}
@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}
}
