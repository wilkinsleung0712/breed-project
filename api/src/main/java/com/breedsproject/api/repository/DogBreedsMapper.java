package com.breedsproject.api.repository;

import com.breedsproject.api.model.DogBreeds;
import com.breedsproject.api.web.response.BreedRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DogBreedsMapper {

  DogBreedsMapper INSTANCE = Mappers.getMapper(DogBreedsMapper.class);

  BreedRecord dogBreedDtoToBreedRecord(DogBreeds dogBreeds);
}
