package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
        .requestMatchers("/login").permitAll()
	      .requestMatchers("/register").permitAll()
	      .requestMatchers("/logout").permitAll()//로그인을 하지않은 익명의 사용자도 접근 허용
	    	.requestMatchers("/").permitAll();
		http.authorizeHttpRequests()
		.requestMatchers("/assets/**", "/images/**", "/path/**").permitAll();
	    	
	    	
		
		

			
		/* 메뉴별 접근제한 */
        http.authorizeHttpRequests()
		  .requestMatchers("/map/main","/map/list","/map/read").hasAnyRole("ADMIN","USER")
		  .requestMatchers("/map/register","/map/modify").hasRole("ADMIN")
		  
		  
		  .requestMatchers("/member/modify/*","/member/remove/*","/member/readMine").hasAnyRole("ADMIN","USER")
		  .requestMatchers("/member/read","/member/list").hasRole("ADMIN")
		  
		  
		  .requestMatchers("/placeboard/*").hasAnyRole("ADMIN","USER")
		  
		  .requestMatchers("/request/*").hasAnyRole("ADMIN","USER")
		  
          .requestMatchers("/interboard/*").hasAnyRole("ADMIN","USER");

	  http.formLogin();
      http.csrf().disable(); //csrf는 get을 제외하여 상태값을 위조(변경)할 수있는 post,put,delete 메소드를 막음
      http.logout(); // 로그아웃 처리
      
	    
		return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
