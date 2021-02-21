package com.breedsproject.api.service.impl;

import com.breedsproject.api.model.DogBreeds;
import com.breedsproject.api.repository.DogBreedsMapper;
import com.breedsproject.api.repository.DogBreedsRepository;
import com.breedsproject.api.service.BreedService;
import com.breedsproject.api.web.response.BreedRecord;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BreedServiceImpl implements BreedService {

  @Autowired private DogBreedsProcessor dogBreedsProcessor;

  @Autowired private DogBreedsRepository dogBreedsRepository;

  @Override
  public Optional<BreedRecord> getDogBreedById(UUID id) {
    return Optional.of(
        DogBreedsMapper.INSTANCE.dogBreedDtoToBreedRecord(dogBreedsRepository.findById(id).get()));
  }

  @Override
  public Optional<List<BreedRecord>> getDogBreedByName(String dogName) {
    return Optional.of(
        dogBreedsRepository.findByBreedName(dogName).stream()
            .map(DogBreedsMapper.INSTANCE::dogBreedDtoToBreedRecord)
            .collect(Collectors.toUnmodifiableList()));
  }

  @Override
  public Optional<BreedRecord> createDogBreedRecord() {
    DogBreeds dogBreeds = dogBreedsProcessor.generateBreeds();
    DogBreeds save = dogBreedsRepository.save(dogBreeds);
    return Optional.of(DogBreedsMapper.INSTANCE.dogBreedDtoToBreedRecord(save));
  }

  @Override
  public Optional<BreedRecord> deleteDogBreedById(UUID id) {
    Optional<DogBreeds> record = dogBreedsRepository.findById(id);
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
