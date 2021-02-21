package com.breedsproject.api.model;

import java.util.Date;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class DogBreeds {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private UUID id;

  @Column(name = "breed_name")
  private String breedName;

  @Column(name = "upload_time")
  private Date uploadTime;

  @Column(name = "resource_url")
  private String resourceUrl;
}
