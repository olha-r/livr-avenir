package co.simplon.livravenir.configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class ResourceConfig {

    @Value("${livravenir.auth.secret}")
    private String secret;

    @Bean
    SecurityFilterChain fitChain(HttpSecurity http)
	    throws Exception {
	http.csrf().disable().authorizeRequests()
		.antMatchers("/auth/sign-in").permitAll()
		.antMatchers("/auth/sign-up").permitAll()
		.antMatchers(HttpMethod.GET, "/books/**",
			"/categories", "/conditions",
			"/formats", "/languages")
		.permitAll().antMatchers("/admin")
		.hasRole("ADMIN").antMatchers("/admin")
		.hasAuthority("ROLE_ADMIN").anyRequest()
		.authenticated().and()
		.oauth2ResourceServer().jwt();

	return http.build();
    }

    @Bean
    JwtAuthenticationConverter authenticationConverter() {
	JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
	authoritiesConverter
		.setAuthoritiesClaimName("role");
	authoritiesConverter.setAuthorityPrefix("");
	JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
	authenticationConverter
		.setJwtGrantedAuthoritiesConverter(
			authoritiesConverter);
	return authenticationConverter;
    }

    @Bean
    JwtDecoder jwtDecoder() throws Exception {
	SecretKey key = new SecretKeySpec(secret.getBytes(),
		"HmacSHA256");
	return NimbusJwtDecoder.withSecretKey(key).build();
    }
}
