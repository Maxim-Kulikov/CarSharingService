package org.example.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.photoDTO.PhotoRespDto;
import org.example.dto.photoDTO.SavePhotoDto;
import org.example.mapper.PhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@ComponentScan("org.example")
public class PhotoProducer {
    private final PhotoMapper photoMapper;
    private static final String TOPIC = "photo_save";
    private final KafkaTemplate<String, String> kafkaTemplate;
    PhotoProducer(@Autowired KafkaTemplate<String, String> kafkaTemplate,
                    @Autowired PhotoMapper photoMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.photoMapper = photoMapper;
    }

    public void send(Integer idCar, MultipartFile multipartFile) throws IOException {
        SavePhotoDto dto = photoMapper.toSavePhoto(idCar, multipartFile);
        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(TOPIC, json);
        System.out.println("photo_save");
    }
}
