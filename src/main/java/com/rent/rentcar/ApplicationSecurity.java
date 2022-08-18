package com.rent.rentcar;

import com.rent.rentcar.auth.JwtTokenFilter;
import com.rent.rentcar.exceptions.CustomAccesDeniedHandler;
import com.rent.rentcar.exceptions.LoginErrorMessage;
import com.rent.rentcar.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(
                username -> customerRepository.findByEmail(username)
                        .orElseThrow(
                                () -> new LoginErrorMessage("Bu email adresi kayıtlı değil")));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                );
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers("/customer/all", "/customer/remove-customer").hasAnyAuthority("ADMIN")
                .antMatchers("/category/all", "/category/remove-category").hasAnyAuthority("ADMIN")
                .antMatchers("/product/all", "/product/remove-product").hasAnyAuthority("ADMIN")
                .antMatchers("/order/all", "/order/remove-order").hasAnyAuthority("ADMIN")
                .antMatchers("/wishlist/all", "/wishlist/remove-wishlist").hasAnyAuthority("ADMIN")
                .antMatchers("/car-brand/all", "/car-brand/remove-car-brand").hasAnyAuthority("ADMIN")
                .antMatchers("/model-name/all", "/model-name/remove-model-name").hasAnyAuthority("ADMIN")
                .antMatchers("/customer/login", "/customer/save").permitAll()
                .anyRequest().authenticated();
        http.exceptionHandling().accessDeniedHandler(new CustomAccesDeniedHandler());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}