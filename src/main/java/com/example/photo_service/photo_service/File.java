package com.example.photo_service.photo_service;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class File {
    private String name;
    private byte[] bytes;
}
