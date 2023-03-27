package com.example.photo_service.photo_service.document;

import com.example.photo_service.photo_service.File;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "images")
public class Photo {
    @Id
    private String id;
    private Integer idCar;
    private File file;
}
