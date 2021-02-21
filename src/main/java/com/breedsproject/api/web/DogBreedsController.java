package com.breedsproject.api.web;

import com.breedsproject.api.service.DogBreedsService;
import com.breedsproject.api.web.response.DataResponse;
import com.breedsproject.api.web.response.DogBreedRecord;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/breeds/dog", produces = MediaType.APPLICATION_JSON_VALUE)
public class DogBreedsController {

  @Autowired private DogBreedsService dogBreedsService;

  @PostMapping("/create")
  @Operation(
      summary = "Generate a new dog breed record",
      description =
          "When called, the endpoint will retrieve a dog breed picture from the above endpoint.")
  @ApiResponse(
      responseCode = "200",
      description = "success to return the generated a new dog breed record")
  public DataResponse<DogBreedRecord> createDogBreedRecord() {
    return new DataResponse(dogBreedsService.createDogBreedRecord());
  }

  @GetMapping("/{id}")
  @Operation(
      summary = "Retrieve dog breed record by id",
      description =
          "When called, the endpoint will retrieve the record from the database with the given\n"
              + "id.")
  @ApiResponse(responseCode = "200", description = "success to return the dog breed record by id")
  public DataResponse<DogBreedRecord> getDogBreedRecord(@PathVariable UUID id) {
    return new DataResponse(dogBreedsService.getDogBreedById(id));
  }

  @DeleteMapping("/{id}")
  @Operation(
      summary = "Remove dog breed record by given id",
      description =
          "When called, the endpoint will remove the record from the database with the given\n"
              + "id, and also remove the stored image from S3.")
  @ApiResponse(responseCode = "200", description = "success to remove a dog breed record")
  public void deleteDogBreedRecord(@PathVariable UUID id) {
    dogBreedsService.deleteDogBreedById(id);
  }

  @GetMapping("/search")
  @Operation(
      summary = "List all dog breed records contains given breedName",
      description =
          "When called, the endpoint will retrieve any records from the database with the given\n"
              + "dog breed name.")
  @ApiResponse(
      responseCode = "200",
      description = "success to list all dog breed records contains given breedName")
  public DataResponse<List<DogBreedRecord>> searchByBreedName(@RequestParam String breedName) {
    return new DataResponse(dogBreedsService.getDogBreedByName(breedName));
  }

  @GetMapping("/names")
  @Operation(
      summary = "List all the distinct breed name",
      description =
          "When called, the endpoint will retrieve a list of dog breeds stored in the system\n"
              + "(returning the dog breed names only.")
  @ApiResponse(
      responseCode = "200",
      description = "success to return the all the distinct breed name")
  public DataResponse<List<String>> findBreedNames() {
    return new DataResponse<>(dogBreedsService.getAllDogBreeds());
  }
}
