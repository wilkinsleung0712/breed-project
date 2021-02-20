package com.breedsporject.api.web.response;

import lombok.Builder;
import lombok.ToString;

import java.util.UUID;

@ToString
@Builder
public class BreedResponse {
    public final UUID id;
    public final String breedName;
    public final String resourceUrl;
}
