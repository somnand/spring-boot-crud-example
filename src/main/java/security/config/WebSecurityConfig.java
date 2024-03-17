package security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig
{
    private static final int STRENGTH = 11;
    private static final String[] WHITE_LIST_URLS = {"/welcome"};  

    @Bean
    public PasswordEncoder passwordEncoder()
    {
	return new BCryptPasswordEncoder(STRENGTH);
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception
    {
	httpSecurity.cors().and().csrf().disable().authorizeHttpRequests().antMatchers(WHITE_LIST_URLS).permitAll();
	return httpSecurity.build();
	
    }

}
