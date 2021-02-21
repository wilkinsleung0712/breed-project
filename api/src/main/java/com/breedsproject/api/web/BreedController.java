package com.breedsproject.api.web;

import com.breedsproject.api.web.request.BreedRequest;
import com.breedsproject.api.web.response.BreedResponse;
import com.breedsproject.api.web.response.DataResponse;
import com.breedsproject.api.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(
        value = "/breeds/dog", produces = MediaType.APPLICATION_JSON_VALUE
)
public class BreedController {

    @Autowired
    private BreedService breedService;

    @GetMapping("/{id}")
    public DataResponse<BreedResponse> getDogBreedRecord(@PathVariable UUID id) {
        return new DataResponse(breedService.getDogBreedById(id));
    }

    @GetMapping("/search")
    public DataResponse<List<BreedResponse>> searchDogBreedRecord(@RequestParam List<String> dogNames) {
        return new DataResponse(breedService.getDogBreedByNames(dogNames));
    }

    @DeleteMapping("/{id}")
    public DataResponse<BreedResponse> deleteDogBreedRecord(@PathVariable UUID id) {
        return null;
    }

    @PostMapping("/create")
    public DataResponse<BreedResponse> createDogBreedRecord() {
        return new DataResponse(breedService.createDogBreedRecord());
    }


}
