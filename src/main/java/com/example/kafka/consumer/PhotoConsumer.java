package com.example.kafka.consumer;

import com.example.dto.PhotoResp;

import com.example.service.PhotoService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class PhotoConsumer {
    @Autowired
    private final PhotoService photoService;
    private static final String TOPIC = "photo_save";

    @KafkaListener(topics = TOPIC, groupId = "id-group")
    public void receive(String message){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PhotoResp photoResp = objectMapper.readValue(message, PhotoResp.class);
            photoService.save(photoResp);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
