package fi.hh.BookStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import fi.hh.BookStore.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
       	.authorizeRequests().antMatchers("/api/**").permitAll() // Enable api when logged out
        .and()
        .authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out
        .and()
        .authorizeRequests()
        .antMatchers("/", "addbook", "save", "booklist", "delete/{id}").permitAll()
          .anyRequest().authenticated()
          .and()
      .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/booklist")
          .permitAll()
          .and()
      .logout()
          .permitAll();
    }

   
    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("admin").roles("ADMIN");
    }*/
    
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		
	}
}