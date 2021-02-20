package com.breedsporject.api.web.request;

import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

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
