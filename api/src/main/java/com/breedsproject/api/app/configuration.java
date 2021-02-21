package com.breedsproject.api.app;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Nullable;
import java.util.List;

@Configuration
public class configuration {

    @Autowired
    Environment env;

    @Bean
    public AmazonS3 amazonS3(){
        var profiles = List.of(env.getActiveProfiles());
        var hasTestProfile = profiles.contains("test") || profiles.contains("dev");

        if (!hasTestProfile) {
            return awsS3Client();
        } else {
            @Nullable var s3Endpoint = env.getProperty("amazon.s3.endpoint");
            @Nullable var s3Region = env.getProperty("amazon.s3.region");
            assert (s3Endpoint != null);
            assert (s3Region != null);
            return localStackS3Client(s3Endpoint, s3Region);
        }
    }




    private AmazonS3 awsS3Client() {
        return AmazonS3ClientBuilder.defaultClient();
    }


    private AmazonS3 localStackS3Client(
            @Value("${amazon.s3.endpoint}") String s3Endpoint,
            @Value("${amazon.s3.region}") String s3Region) {
        return AmazonS3Client.builder()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(s3Endpoint, s3Region))
                .withPathStyleAccessEnabled(true)
                .build();
    }

    @Bean
    public RestTemplate restTemplate(List<HttpMessageConverter<?>> messageConverters) {
        return new RestTemplate(messageConverters);
    }

    @Bean
    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
        return new ByteArrayHttpMessageConverter();
    }
}
