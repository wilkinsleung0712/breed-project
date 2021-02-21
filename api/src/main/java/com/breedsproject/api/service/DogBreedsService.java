package com.breedsproject.api.service;

import com.breedsproject.api.web.response.DogBreedRecord;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DogBreedsService {
  Optional<DogBreedRecord> getDogBreedById(UUID id);

  Optional<List<DogBreedRecord>> getDogBreedByName(String dogName);

  Optional<DogBreedRecord> createDogBreedRecord();

  Optional<DogBreedRecord> deleteDogBreedById(UUID id);

  List<String> getAllDogBreeds();
}
