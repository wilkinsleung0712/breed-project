package com.breedsproject.model;

import lombok.Builder;
import lombok.ToString;

import java.util.UUID;

@ToString
@Builder
public class DogBreeds {
    public final UUID id;
    public final String breedName;
    public final String resourceUrl;
}
