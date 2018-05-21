package com.resi.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
public class ApiError implements Serializable {
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Integer status;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String error;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String message;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String exception;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String path;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private LocalDateTime timestamp;
}
