package co.simplon.livravenir.configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class ResourceConfig {

    @Value("${livravenir.auth.secret}")
    private String secret;

    @Bean
    SecurityFilterChain securityFilterChain(
	    HttpSecurity http) throws Exception {
	http.cors(Customizer.withDefaults())
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests((authz) -> {
		    authz.requestMatchers(HttpMethod.POST,
			    "/auth/sign-in",
			    "/auth/sign-up").anonymous()
			    .requestMatchers(HttpMethod.GET,
				    "/auth/verify/**")
			    .anonymous()
			    .requestMatchers(HttpMethod.GET,
				    "/books",
				    "/books/{id}/detail")
			    .permitAll()
			    .requestMatchers(
				    HttpMethod.POST,
				    "/books", "/publishers",
				    "/authors")
			    .hasAuthority("ADMIN")
			    .requestMatchers(HttpMethod.PUT,
				    "/books/{id}")
			    .hasAuthority("ADMIN")
			    .requestMatchers(
				    HttpMethod.DELETE,
				    "/books/{id}")
			    .hasAuthority("ADMIN")
			    .requestMatchers(HttpMethod.GET,
				    "/categories",
				    "/languages",
				    "/authors",
				    "/publishers",
				    "/books/admin",
				    "/books/{id}/for-update")
			    .hasAuthority("ADMIN")
			    .requestMatchers(HttpMethod.GET,
				    "/conditions",
				    "/book_items/user",
				    "/users/profile")
			    .hasAuthority("USER")
			    .requestMatchers(
				    HttpMethod.POST,
				    "/book_items")
			    .hasAuthority("USER")
			    .requestMatchers(
				    HttpMethod.PATCH,
				    "/book_items/{id}")
			    .hasAuthority("USER")
			    .requestMatchers(
				    HttpMethod.DELETE,
				    "/book_items/{id}")
			    .hasAuthority("USER")
			    .anyRequest().authenticated();
		}

		).oauth2ResourceServer((oauth2) -> oauth2
			.jwt(Customizer.withDefaults()));
	;
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
