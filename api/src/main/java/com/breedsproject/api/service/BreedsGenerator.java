package com.breedsproject.api.service;

public interface BreedsGenerator<T> {

  T generateDogBreed();

  void removeBreed(String resourceUrl);
}
