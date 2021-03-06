package com.breedsproject.api.repository;

import com.breedsproject.api.model.DogBreed;
import com.breedsproject.api.web.response.DogBreedRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DogBreedsMapper {

  DogBreedsMapper INSTANCE = Mappers.getMapper(DogBreedsMapper.class);

  DogBreedRecord dogBreedDtoToBreedRecord(DogBreed dogBreed);
}
