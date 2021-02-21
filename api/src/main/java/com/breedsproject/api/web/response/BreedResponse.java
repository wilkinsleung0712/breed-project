package com.breedsproject.api.web.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Data
@Builder
public class BreedResponse {
  public final UUID id;
  public final String breedName;
  public final String resourceUrl;
}
