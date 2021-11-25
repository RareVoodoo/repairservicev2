package test.repairservice.repairservicev2.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
// TODO: 10/29/2021 check
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {
    Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**/*.js", "/**/*.css").permitAll()
                .antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/**").permitAll().and()
                .authorizeRequests().antMatchers("/registration").permitAll().and()
                .authorizeRequests().antMatchers("/resources/**").permitAll().and()
                .authorizeRequests().antMatchers("/login-enter").permitAll()
                .antMatchers("/user-dashboard").hasAnyAuthority("Client","Manager")
                .antMatchers("/master-dashboard").hasAnyAuthority("Master", "Manager")
                .antMatchers("/manager-dashboard").hasAnyAuthority("Manager")
                .antMatchers("/manager-dashboard-table").hasAnyAuthority("Manager")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and().csrf().disable()
                .headers().frameOptions().disable();
    }
}
