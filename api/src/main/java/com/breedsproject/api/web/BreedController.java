package com.breedsproject.api.web;

import com.breedsproject.api.service.BreedService;
import com.breedsproject.api.web.response.BreedRecord;
import com.breedsproject.api.web.response.DataResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/breeds/dog", produces = MediaType.APPLICATION_JSON_VALUE)
public class BreedController {

  @Autowired private BreedService breedService;

  @PostMapping("/create")
  public DataResponse<BreedRecord> createDogBreedRecord() {
    return new DataResponse(breedService.createDogBreedRecord());
  }

  @GetMapping("/{id}")
  public DataResponse<BreedRecord> getDogBreedRecord(@PathVariable UUID id) {
    return new DataResponse(breedService.getDogBreedById(id));
  }

  @DeleteMapping("/{id}")
  public void deleteDogBreedRecord(@PathVariable UUID id) {
    breedService.deleteDogBreedById(id);
  }

  @GetMapping("/search")
  public DataResponse<List<BreedRecord>> searchByBreedName(@RequestParam String breedName) {
    return new DataResponse(breedService.getDogBreedByName(breedName));
  }

  @GetMapping("/names")
  public DataResponse<List<String>> findBreedNames() {
    return new DataResponse<>(breedService.getAllDogBreeds());
  }
}
