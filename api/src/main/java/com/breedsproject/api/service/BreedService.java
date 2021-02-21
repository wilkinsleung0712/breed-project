package com.breedsproject.api.service;

import com.breedsproject.api.web.response.BreedRecord;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BreedService {
  Optional<BreedRecord> getDogBreedById(UUID id);

  Optional<List<BreedRecord>> getDogBreedByName(String dogName);

  Optional<BreedRecord> createDogBreedRecord();

  Optional<BreedRecord> deleteDogBreedById(UUID id);

  List<String> getAllDogBreeds();
}
