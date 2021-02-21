package com.breedsproject.api.web.response;

import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
public class BreedImageResponse {
  public final String message;
  public final String status;
}
