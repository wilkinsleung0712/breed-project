package com.breedsproject.api.web;

import com.breedsporject.api.web.request.BreedRequest;
import com.breedsporject.api.web.response.BreedResponse;
import com.breedsporject.api.web.response.DataResponse;
import com.breedsproject.api.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(
        value = "/breeds/dog/"
)
public class BreedController {

    @Autowired
    private BreedService breedService;

    @GetMapping("/{id}")
    public DataResponse<BreedResponse> getDogBreedRecord(@PathVariable UUID id) {
        return new DataResponse(breedService.getDogBreedById(id));
    }

    @GetMapping("/dog")
    public DataResponse<List<BreedResponse>> searchDogBreedRecord(@RequestParam List<String> dogNames) {
        return new DataResponse(breedService.getDogBreedByNames(dogNames));
    }

    @DeleteMapping("/{id}")
    public DataResponse<BreedResponse> deleteDogBreedRecord(@PathVariable UUID id) {
        return null;
    }

    @PostMapping("/dog")
    public DataResponse<BreedResponse> createDogBreedRecord(@RequestBody BreedRequest request) {
        return null;
    }


}
