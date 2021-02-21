package com.breedsproject.api.service;

import java.util.Date;

public interface ImageService {
  String saveBreedsImageToStorage(final String url);

  Date getBreedsImageLastUpdateTime(final String url);

  void deleteImageFromStorage(final String url);
}
