package com.chatApp.sp.authservice.config;


import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.chatApp.sp.authservice.filter.JWTAuthenticationFilter;
import com.chatApp.sp.authservice.filter.JWTLoginFilter;
import com.chatApp.sp.service.UserService;
import com.google.common.collect.ImmutableList;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true
		)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserService userSer() {
		return new UserService();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	@Bean
	public JWTAuthenticationFilter jwtAuthenticationFilter() {
		return new JWTAuthenticationFilter();
	}
	
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception{
		
		//http.csrf().disable();
		
		http.cors();
		
		
		http.authorizeRequests().antMatchers("/*.ico","/js/*","/css/*", "/img/*","/","/login","/logout","/signup","/accessDenied").permitAll();
		
		http.authorizeRequests().anyRequest().authenticated()
								.and()
								.addFilterBefore(new JWTLoginFilter("/auth", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
								.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		http.exceptionHandling()	
	        .authenticationEntryPoint((request, response, e) -> 
	        {
	        response.setContentType("application/json;charset=UTF-8");
	        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	        response.sendRedirect("/accessDenied");
	        /*response.getWriter().write(new JSONObject() 
	                .put("timestamp", LocalDateTime.now())
	                .put("message", "Access denied")
	                .put("tester", "tester")
	                .put("errorCode", "403")
	                .toString());*/
	        });
	}
	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		UserDetailsService userDetailService = userSer();
		
		auth.userDetailsService(userDetailService).passwordEncoder(userSer().passwordEncoder());
		
	}
	
	 @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        final CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(ImmutableList.of("*"));
	        configuration.setAllowedMethods(ImmutableList.of("HEAD",
	                "GET", "POST", "PUT", "DELETE", "PATCH"));
	        // setAllowCredentials(true) is important, otherwise:
	        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
	        configuration.setAllowCredentials(true);
	        // setAllowedHeaders is important! Without it, OPTIONS preflight request
	        // will fail with 403 Invalid CORS request
	        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }

}
