package com.breedsproject.api.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.breedsproject.api.model.DogBreeds;
import com.breedsproject.api.service.ImageService;
import com.breedsproject.api.web.response.BreedImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${amazon.s3.bucket.name:}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3;

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

        var resourceUrl = saveImage(imageResponse, key);

        return DogBreeds.builder().breedName(name).resourceUrl(resourceUrl).build();
    }

    private String saveImage(byte[] data, String key) {
        InputStream is = new ByteArrayInputStream(data);
        amazonS3.putObject(bucketName, key, is, OBJECT_META_DATA);
        URL url = amazonS3.getUrl(bucketName, key);
        return url.toString();
    }

}
