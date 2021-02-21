package com.breedsproject.api.service;

/**
 * Interface for generic breed generation
 *
 * @param <T>
 */
public interface BreedsGenerator<T> {

  /**
   * Generate type of breed
   *
   * @return
   */
  T generateBreed();

  void removeBreed(String resourceUrl);
}
