package com.breedsproject.api.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

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
    @Column(name = "breedName")
    private String breedName;
    @Column(name = "resourceUrl")
    private String resourceUrl;

}
