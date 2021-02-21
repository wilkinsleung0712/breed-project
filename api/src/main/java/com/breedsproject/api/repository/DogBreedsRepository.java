package com.breedsproject.api.repository;

import com.breedsproject.api.model.DogBreeds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface DogBreedsRepository extends JpaRepository<DogBreeds, UUID> {

    @Query(value = "SELECT dog_breeds FROM DogBreeds dog_breeds WHERE dog_breeds.breedName IN :names")
    List<DogBreeds> findAllByBreedName(Collection<String> names);
}