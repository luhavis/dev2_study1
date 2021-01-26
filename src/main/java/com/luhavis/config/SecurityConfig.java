package com.luhavis.config;

import com.luhavis.filter.CustomAuthenticationFilter;
import com.luhavis.handler.CustomLogoutSuccessHandler;
import com.luhavis.service.CustomUserDetailsService;
import com.luhavis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public CustomLogoutSuccessHandler customLogoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/lib/**", "/templates/**");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/member/**").authenticated()
                .antMatchers("/infra/**").hasRole("ADMIN")
                .antMatchers("/oauth2/**").permitAll()
//                .antMatchers("/**").permitAll();
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .oauth2Login()
                .loginPage("/oauth2/login")
                .permitAll(); // 마지막에 해야 form 로그인도 사용할 수 있음.


//        http.formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/")
//                .permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessHandler(customLogoutSuccessHandler())
                .invalidateHttpSession(true);


        http.exceptionHandling()
                .accessDeniedPage("/denied");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
