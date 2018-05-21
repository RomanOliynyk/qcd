package com.resi.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface AuthHandler {
  Mono<ServerResponse> login(ServerRequest request);

  Mono<ServerResponse> refreshToken(ServerRequest request);

  Mono<ServerResponse> logoff(ServerRequest request);
}
