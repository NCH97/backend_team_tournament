package com.campusacademy.nch.backenddevteam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final PasswordEncoder passwordEncoder;
	
	 @Override
	    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication()
	                .withUser("visitor").password(this.passwordEncoder.encode("test")).roles("VISITOR")
	                .and()
	                .withUser("user").password(this.passwordEncoder.encode("test")).authorities("ROLE_USER")
	                .and()
	                .withUser("admin").password(this.passwordEncoder.encode("test")).roles("USER", "ADMIN");
	    }
	 
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	 http.authorizeRequests()
	 .antMatchers("/login").permitAll()
	 .antMatchers("/admin/**").hasRole("ADMIN")
	 .antMatchers("/**").hasAnyRole("ADMIN", "USER","VISITOR")
	 .antMatchers(HttpMethod.PUT).hasAnyAuthority("ROLE_ADMIN")
     .antMatchers(HttpMethod.POST).hasRole("ADMIN")
     .antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN")
     .anyRequest().authenticated()
	 .and().formLogin()
	 .and().logout().logoutSuccessUrl("/login").permitAll()
	 .and().csrf().disable();
	 
}
}
