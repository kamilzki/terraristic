package com.kamilzki.terraristic.configuration;

import com.kamilzki.terraristic.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .authorizeRequests().antMatchers("/","/index","/commodity/animal/{id}/show","/console/**"
                    , "/webjars/**").permitAll()
                .anyRequest().hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/index").permitAll()
                .and()
                .logout().permitAll();

       httpSecurity.csrf().disable();
       httpSecurity.headers().frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
    {
//        authenticationManagerBuilder
//                .inMemoryAuthentication()
////                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin").password("{bcrypt}admin").roles("ADMIN")
//                .and().withUser("user").password("{bcrypt}user").roles("USER");
        authenticationManagerBuilder
                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//
//        PasswordEncoder encoder = passwordEncoder();
//
//        final User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
//        UserDetails user = userBuilder
//                .username("user")
//                .password("user")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = userBuilder
//                .username("admin")
//                .password("admin")
//                .roles("USER","ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}