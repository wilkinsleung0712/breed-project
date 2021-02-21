package com.breedsproject.api.web.request;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.ToString;

@ToString
public class BreedRequest {
  public final String breedName;
  public final String resourceUrl;
  public final LocalDateTime imageUpdateTime;

  @Builder(toBuilder = true)
  public BreedRequest(String breedName, String resourceUrl, LocalDateTime imageUpdateTime) {
    this.breedName = breedName;
    this.resourceUrl = resourceUrl;
    this.imageUpdateTime = imageUpdateTime;
  }
}
