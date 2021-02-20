package com.breedsproject.api.service.impl;

import com.breedsporject.api.web.response.BreedResponse;
import com.breedsproject.api.service.BreedService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BreedServiceImpl implements BreedService {

    @Override
    public Optional<BreedResponse> getDogBreedById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<BreedResponse>> getDogBreedByNames(List<String> dogNames) {
        return Optional.empty();
    }

    @Override
    public Optional<BreedResponse> createDogBreedRecord(String breedName, String resourceUrl, LocalDateTime imageUpdateTime) {
        return Optional.empty();
    }

    @Override
    public Optional<BreedResponse> deleteDogBreedById(UUID id) {
        return Optional.empty();
    }
}
