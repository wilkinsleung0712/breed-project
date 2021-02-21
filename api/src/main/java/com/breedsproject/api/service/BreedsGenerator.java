package com.breedsproject.api.service;

public interface BreedsGenerator<T> {

  T generateBreeds();

  void removeBreed(String resourceUrl);
}
