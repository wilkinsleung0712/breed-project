package com.breedsproject.api.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@ToString
@AllArgsConstructor
@Data
@Builder
public class BreedResponse {
    public final UUID id;
    public final String breedName;
    public final String resourceUrl;
}
