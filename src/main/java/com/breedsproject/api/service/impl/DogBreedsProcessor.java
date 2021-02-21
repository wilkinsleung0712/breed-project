package com.breedsproject.api.service.impl;

import com.breedsproject.api.model.DogBreed;
import com.breedsproject.api.service.BreedsGenerator;
import com.breedsproject.api.service.ImageService;
import com.breedsproject.api.web.response.BreedImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DogBreedsProcessor implements BreedsGenerator<DogBreed> {

  @Autowired private RestTemplate restTemplate;

  @Autowired private ImageService imageService;

  @Value("${breeds.image.endpoint:}")
  private String imageUrl;

  @Override
  public DogBreed generateBreed() {
    var randomImageResponse = restTemplate.getForObject(imageUrl, BreedImageResponse.class);
    assert randomImageResponse != null;
    var url = randomImageResponse.message;
    var data = randomImageResponse.message.split("/");
    var name = data[data.length - 2];
    var resourceUrl = imageService.saveBreedsImageToStorage(url);
    return DogBreed.builder()
        .breedName(name)
        .resourceUrl(resourceUrl)
        .uploadTime(imageService.getBreedsImageLastUpdateTime(resourceUrl))
        .build();
  }

  @Override
  public void removeBreed(String resourceUrl) {
    imageService.deleteImageFromStorage(resourceUrl);
  }
}
