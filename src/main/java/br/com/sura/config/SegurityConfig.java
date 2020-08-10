package br.com.sura.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.sura.service.CustomUserDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SegurityConfig extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers(HttpMethod.GET, "/v1/categoria").permitAll()
			.antMatchers(HttpMethod.GET, "/v1/categoria/*").permitAll()
			.antMatchers(HttpMethod.GET, "/v1/produto").permitAll()
			.antMatchers(HttpMethod.GET, "/v1/produto/*").permitAll()
			.antMatchers(HttpMethod.GET, "/v1/cliente").permitAll()
			.antMatchers(HttpMethod.GET, "/v1/cliente/*").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.headers().frameOptions().disable().and()
			.httpBasic()
			.and()
			.csrf()
			.ignoringAntMatchers("/h2-console/**")
			.disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/configuration/security",
                                   "/swagger-ui.html",
                                   "/webjars/**");
    }

	
}
