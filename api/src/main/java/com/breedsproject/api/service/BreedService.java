package com.breedsproject.api.service;

import com.breedsporject.api.web.response.BreedResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BreedService {
    Optional<BreedResponse> getDogBreedById(UUID id);
    Optional<List<BreedResponse>> getDogBreedByNames(List<String> dogNames);
    Optional<BreedResponse> createDogBreedRecord(String breedName, String resourceUrl, LocalDateTime imageUpdateTime);
    Optional<BreedResponse> deleteDogBreedById(UUID id);
}
