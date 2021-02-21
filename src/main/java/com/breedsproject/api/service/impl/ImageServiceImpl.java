package com.breedsproject.api.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.breedsproject.api.service.ImageService;
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

  private static final ObjectMetadata OBJECT_META_DATA = new ObjectMetadata();

  @Override
  public String saveBreedsImageToStorage(final String url) {
    var data = url.split("/");
    var key = data[data.length - 1];
    InputStream is = new ByteArrayInputStream(downloadImageFromUrl(url));
    amazonS3.putObject(bucketName, key, is, OBJECT_META_DATA);
    return getImageUrl(key);
  }

  @Override
  public Date getBreedsImageLastUpdateTime(final String url) {
    return amazonS3.getObjectMetadata(bucketName, getResourceKeyFromUrl(url)).getLastModified();
  }

  @Override
  public void deleteImageFromStorage(String url) {
    amazonS3.deleteObject(bucketName, getResourceKeyFromUrl(url));
  }

  private String getImageUrl(String key) {
    return amazonS3.getUrl(bucketName, key).toString();
  }

  private byte[] downloadImageFromUrl(final String url) {
    return restTemplate.getForObject(url, byte[].class);
  }

  private String getResourceKeyFromUrl(String url) {
    var data = url.split("/");
    return data[data.length - 1];
  }
}
