package com.resi.common.handler;


import com.resi.common.model.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.function.Function;

@Slf4j
@Component
public class GlobalExceptionHandler {

  private static Function<Throwable, Mono<ServerResponse>> errorResponse =
    e -> {
      HttpStatus status = getStatus(e);
      return ServerResponse.status(status).body(Mono.just(buildError(e, status)), ApiError.class);
    };

  private static HttpStatus getStatus(final Throwable error) {
    if (AccessDeniedException.class.isAssignableFrom(error.getClass())) {
      return HttpStatus.FORBIDDEN;
    } else if (AuthenticationException.class.isAssignableFrom(error.getClass())) {
      return HttpStatus.UNAUTHORIZED;
    } else if (UnsupportedMediaTypeStatusException.class.isAssignableFrom(error.getClass())) {
      return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
    } else if (IllegalArgumentException.class.isAssignableFrom(error.getClass())) {
      return HttpStatus.BAD_REQUEST;
    } else {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
  }

  public static Mono<ServerResponse> handleException(Throwable e) {
    LOGGER.error(e.getMessage(), e);
    return errorResponse.apply(e);
  }

  public static ApiError buildError(Throwable e, HttpStatus status) {
    return ApiError.builder()
      .status(status.value())
      .error(e.getMessage())
      .message(e.getMessage())
      .timestamp(LocalDateTime.now())
      .build();
  }
}
