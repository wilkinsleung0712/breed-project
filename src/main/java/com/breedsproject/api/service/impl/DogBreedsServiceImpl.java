package com.breedsproject.api.service.impl;

import com.breedsproject.api.model.DogBreed;
import com.breedsproject.api.repository.DogBreedsMapper;
import com.breedsproject.api.repository.DogBreedsRepository;
import com.breedsproject.api.service.DogBreedsService;
import com.breedsproject.api.web.response.DogBreedRecord;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogBreedsServiceImpl implements DogBreedsService {

  @Autowired private DogBreedsProcessor dogBreedsProcessor;

  @Autowired private DogBreedsRepository dogBreedsRepository;

  @Override
  public Optional<DogBreedRecord> getDogBreedById(UUID id) {
    return dogBreedsRepository.findById(id).map(DogBreedsMapper.INSTANCE::dogBreedDtoToBreedRecord);
  }

  @Override
  public Optional<List<DogBreedRecord>> getDogBreedByName(String dogName) {
    return Optional.of(
        dogBreedsRepository.findByBreedName(dogName).stream()
            .map(DogBreedsMapper.INSTANCE::dogBreedDtoToBreedRecord)
            .collect(Collectors.toUnmodifiableList()));
  }

  @Override
  public Optional<DogBreedRecord> createDogBreedRecord() {
    var breed = dogBreedsProcessor.generateBreed();
    var saved = dogBreedsRepository.save(breed);
    return Optional.of(DogBreedsMapper.INSTANCE.dogBreedDtoToBreedRecord(saved));
  }

  @Override
  public Optional<DogBreedRecord> deleteDogBreedById(UUID id) {
    Optional<DogBreed> record = dogBreedsRepository.findById(id);
    record.ifPresent(
        r -> {
          dogBreedsRepository.deleteById(id);
          dogBreedsProcessor.removeBreed(r.getResourceUrl());
        });
    return Optional.empty();
  }

  @Override
  public List<String> getAllDogBreeds() {
    return dogBreedsRepository.findAllDistinctBreedNames();
  }
}
