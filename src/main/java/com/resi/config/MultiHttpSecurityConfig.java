package com.resi.config;

import com.resi.handlers.AuthHandler;
import com.resi.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_XML;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MultiHttpSecurityConfig {

//  @Value("${russmann.ldap.url}")
//  private String ldapUrl;
//
//  @Value("${russmann.ldap.domain}")
//  private String ldapDomain;

  @Autowired
  private AuthHandler authHandler;

//  @Bean
//  public DefaultSpringSecurityContextSource contextSource() {
//    return new DefaultSpringSecurityContextSource(ldapUrl);
//  }
//
//  @Bean
//  public UserDetailsContextMapper userDetailsContextMapper() {
//    return new LdapUserDetailsContextMapper();
//  }
//
//  @Bean
//  public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
//    ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(ldapDomain,
//      ldapUrl);
//    provider.setConvertSubErrorCodesToExceptions(true);
//    provider.setUseAuthenticationRequestCredentials(true);
//    provider.setUserDetailsContextMapper(userDetailsContextMapper());
//
//    return provider;
//  }

  @Bean
  public RouterFunction routerFunction() throws Exception {
    return nest(path(Constants.API_ENDPOINT),
      nest(accept(APPLICATION_XML),
        route(GET("/b2b/user/session"), authHandler::login).
          andRoute(POST("/b2b/user/session"), authHandler::login)
          .andRoute(POST("/auth/login"), authHandler::refreshToken)
          .andRoute(POST("/auth/users/{role}"), authHandler::logoff)
      ));
  }

//  @Bean
//  public AuthenticationManager authenticationManager() {
//    return new ProviderManager(Arrays.asList(activeDirectoryLdapAuthenticationProvider()));
//  }
}
