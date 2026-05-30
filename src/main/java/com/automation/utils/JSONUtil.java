package com.automation.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONUtil {
    private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T> T deserializeToObject(String jsonString, Class<T> clazz) {
        try {
            logger.debug("Deserializing JSON to: " + clazz.getName());
            return objectMapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            logger.error("Failed to deserialize JSON: " + jsonString, e);
            throw new RuntimeException("Deserialization failed: " + e.getMessage());
        }
    }

    public static String serializeToJSON(Object object) {
        try {
            logger.debug("Serializing object to JSON: " + object.getClass().getName());
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize object: " + object, e);
            throw new RuntimeException("Serialization failed: " + e.getMessage());
        }
    }

    public static <T> T deserializeToObjectGson(String jsonString, Class<T> clazz) {
        try {
            logger.debug("Deserializing JSON using Gson to: " + clazz.getName());
            return gson.fromJson(jsonString, clazz);
        } catch (Exception e) {
            logger.error("Failed to deserialize JSON using Gson: " + jsonString, e);
            throw new RuntimeException("Gson deserialization failed: " + e.getMessage());
        }
    }

    public static String serializeToJSONGson(Object object) {
        try {
            logger.debug("Serializing object to JSON using Gson: " + object.getClass().getName());
            return gson.toJson(object);
        } catch (Exception e) {
            logger.error("Failed to serialize object using Gson: " + object, e);
            throw new RuntimeException("Gson serialization failed: " + e.getMessage());
        }
    }

    public static String getPrettyJSON(String jsonString) {
        try {
            Object json = objectMapper.readValue(jsonString, Object.class);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (JsonProcessingException e) {
            logger.error("Failed to format JSON: " + jsonString, e);
            return jsonString;
        }
    }
}
