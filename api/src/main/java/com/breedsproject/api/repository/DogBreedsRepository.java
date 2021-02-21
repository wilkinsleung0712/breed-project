package com.breedsproject.api.repository;

import com.breedsproject.api.model.DogBreeds;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DogBreedsRepository extends JpaRepository<DogBreeds, UUID> {

  List<DogBreeds> findByBreedName(String breedName);

  @Query("SELECT DISTINCT dog_breeds.breedName FROM DogBreeds dog_breeds")
  List<String> findAllDistinctBreedNames();
}
