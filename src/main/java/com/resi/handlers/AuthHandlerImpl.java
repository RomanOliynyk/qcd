package com.resi.handlers;

import com.resi.common.handler.GlobalExceptionHandler;
import com.resi.common.model.LoginUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Slf4j
@Service
public class AuthHandlerImpl implements AuthHandler {
//  @Autowired
//  ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider;
//
//  @Autowired
//  private JwtTokenUtil jwtTokenUtil;
//
//  @Autowired
//  private JwtSettings settings;
//
//  LdapTemplate ldapTemplate;
//
//  @Autowired
//  LdapContextSource contextSource;
//
//  @Autowired
//  private GlobalExceptionHandler globalExceptionHandler;

  final BiFunction<Mono<? extends Object>, ? super Class, Mono<ServerResponse>> buildResponse = (value, classType) ->
    value.flatMap(entity -> ServerResponse.ok().body(Mono.just(entity), classType));

  @Override
  public Mono<ServerResponse> login(ServerRequest request) {
    LOGGER.info("LOGGING IN");
    return request.bodyToMono(LoginUserResponse.class)
//      .map(this::)
//      .map(this::getUserWithTokens)
      .publish(userResponse -> buildResponse.apply(userResponse, Order.class))
      .onErrorResume(GlobalExceptionHandler::handleException);
  }

  @Override
  public Mono<ServerResponse> refreshToken(ServerRequest request) {
    return null;
  }

  @Override
  public Mono<ServerResponse> logoff(ServerRequest request) {
    return null;
  }

//  @Override
//  public Mono<ServerResponse> refreshToken(ServerRequest request) {
//    return Mono.fromCallable(request::headers)
//      .map(headers -> headers.header(settings.getAuthorization()))
//      .map(List::stream)
//      .map(Stream::findAny)
//      .map(optional -> optional.orElse(BLANK))
//      .map(token -> jwtTokenUtil.getUserFromToken(jwtTokenUtil.extract(token)))
//      .map(jwtTokenUtil::getJwtToken)
//      .publish(jwtResponse -> buildResponse.apply(jwtResponse, JwtToken.class))
//      .onErrorResume(globalExceptionHandler::handleException);
//  }
//
//  @Override
//  public Mono<ServerResponse> logoff(ServerRequest request) {
//    return request.bodyToMono(User.class)
//      .map(user -> getLDAPUsers(user, Role.valueOf(request.pathVariable(ParamConstants.ROLE))))
//      .publish(userResponse -> buildResponse.apply(userResponse, User.class))
//      .onErrorResume(globalExceptionHandler::handleException);
//  }
//
//  private class UserAttributesMapper implements AttributesMapper {
//    private final Role role;
//
//    public UserAttributesMapper(Role longName) {
//      this.role = longName;
//    }
//
//    @Override
//    public User mapFromAttributes(Attributes attributes) throws NamingException {
//
//      return User.builder()
//        .username(retrieveAttribute(attributes, S_AMA_CCOUNT_NAME))
//        .firstName(retrieveAttribute(attributes, GIVEN_NAME))
//        .lastName(retrieveAttribute(attributes, SN))
//        .role(role)
//        .build();
//    }
//  }

//  private Authentication getAuthentication(User user) {
//    final CurrentUser currentUser = new CurrentUser(user);
//    return (activeDirectoryLdapAuthenticationProvider.authenticate(
//      new UsernamePasswordAuthenticationToken(currentUser, currentUser.getPassword())));
//  }
//
//  private User getUserWithTokens(Authentication authentication) {
//    CurrentUser currentUserResponse = (CurrentUser) authentication.getPrincipal();
//    User userResponse = currentUserResponse.getUser();
//    userResponse.setToken(jwtTokenUtil.createJwtToken(currentUserResponse, null, settings.getTokenExpirationTime()).getToken());
//    userResponse.setRefreshToken(jwtTokenUtil.createJwtToken(currentUserResponse, UUID.randomUUID().toString(), settings.getRefreshTokenExpTime()).getToken());
//
//    return userResponse;
//  }
//
//  private String handleNaming(Attribute attribute) {
//    try {
//      return (String) attribute.get();
//    } catch (NamingException e) {
//      return BLANK;
//    }
//  }
//
//  private String retrieveAttribute(Attributes attributes, String name) throws NamingException {
//    return Optional.ofNullable(attributes.get(name))
//      .map(this::handleNaming)
//      .orElse(BLANK);
//  }
//
//  private List<User> getLDAPUsers(User user, Role role) {
//    contextSource.setUserDn(SystemConfig.getLdapUserDn() + user.getUsername());
//    contextSource.setPassword(user.getPassword());
//    ldapTemplate = new LdapTemplate(contextSource);
//
//    return ldapTemplate.search(getLdapBase(), String.format(LDAP_CN, MEMBER_OF, role.getLongName(), getLdapFilter(), getLdapBase()), new UserAttributesMapper(role));
//  }
}
