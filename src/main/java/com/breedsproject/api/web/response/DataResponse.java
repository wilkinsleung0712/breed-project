package com.breedsproject.api.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

public class DataResponse<T> {
  public final T data;

  public DataResponse(@JsonProperty("data") @NonNull T data) {
    this.data = data;
  }
}
