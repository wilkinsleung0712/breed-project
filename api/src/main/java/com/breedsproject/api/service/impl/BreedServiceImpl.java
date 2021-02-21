package com.breedsproject.api.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.breedsproject.api.repository.DogBreedsMapper;
import com.breedsproject.api.repository.DogBreedsRepository;
import com.breedsproject.api.service.ImageService;
import com.breedsproject.api.web.response.BreedResponse;
import com.breedsproject.api.service.BreedService;
import com.breedsproject.api.model.DogBreeds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BreedServiceImpl implements BreedService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private ImageService imageService;

    @Autowired
    private DogBreedsRepository dogBreedsRepository;

    @Override
    public Optional<BreedResponse> getDogBreedById(UUID id) {
        return Optional.of(DogBreedsMapper.INSTANCE.dogBreedDtoToBreedResponse(dogBreedsRepository.findById(id).get()));
    }

    @Override
    public Optional<List<BreedResponse>> getDogBreedByNames(List<String> dogNames) {
       return Optional.of(dogBreedsRepository.findAllByBreedName(dogNames).stream().map(DogBreedsMapper.INSTANCE::dogBreedDtoToBreedResponse).collect(Collectors.toUnmodifiableList()));
    }

    @Override
    public Optional<BreedResponse> createDogBreedRecord() {
        var id = UUID.randomUUID();
        var dogBreeds = imageService.getDogBreedRecordFromEndpoint();

        DogBreeds save = dogBreedsRepository.save(dogBreeds);
        return Optional.of(DogBreedsMapper.INSTANCE.dogBreedDtoToBreedResponse(save));
    }

    @Override
    public Optional<BreedResponse> deleteDogBreedById(UUID id) {
        return Optional.empty();
    }
}
