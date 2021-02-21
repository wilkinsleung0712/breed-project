package com.breedsproject.api.service;

import com.breedsproject.api.web.response.BreedResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BreedService {
    Optional<BreedResponse> getDogBreedById(UUID id);
    Optional<List<BreedResponse>> getDogBreedByNames(List<String> dogNames);
    Optional<BreedResponse> createDogBreedRecord();
    Optional<BreedResponse> deleteDogBreedById(UUID id);
}
