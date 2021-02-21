package com.breedsproject.api.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.breedsproject.api.model.DogBreeds;
import com.breedsproject.api.service.ImageService;
import com.breedsproject.api.web.response.BreedImageResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImageServiceImpl implements ImageService {

  @Autowired private RestTemplate restTemplate;

  @Value("${amazon.s3.bucket.name:}")
  private String bucketName;

  @Autowired private AmazonS3 amazonS3;

  @Value("${breeds.image.endpoint:}")
  private String imageUrl;

  private static final ObjectMetadata OBJECT_META_DATA = new ObjectMetadata();

  public DogBreeds getDogBreedRecordFromEndpoint() {
    var randomImageResponse = restTemplate.getForObject(imageUrl, BreedImageResponse.class);
    var url = randomImageResponse.message;
    var data = randomImageResponse.message.split("/");

    var key = data[data.length - 1];
    var name = data[data.length - 2];

    var imageResponse = restTemplate.getForObject(url, byte[].class);

    saveImage(imageResponse, key);

    return DogBreeds.builder()
        .breedName(name)
        .resourceUrl(getImageUrl(key))
        .uploadTime(getImageUpdateTimestamp(key))
        .build();
  }

  private void saveImage(byte[] data, String key) {
    InputStream is = new ByteArrayInputStream(data);
    amazonS3.putObject(bucketName, key, is, OBJECT_META_DATA);
  }

  private String getImageUrl(String key) {
    return amazonS3.getUrl(bucketName, key).toString();
  }

  private Date getImageUpdateTimestamp(String key) {
    return amazonS3.getObjectMetadata(bucketName, key).getLastModified();
  }
}
