package com.tsystems.ecare.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.entities.Option;

import java.io.IOException;
import java.util.Arrays;

public class RateDeserializer extends JsonDeserializer<RateDTO> {
    @Override
    public RateDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        RateDTO rate = new RateDTO();
        JsonNode jsonNode = jsonParser.readValueAsTree();
        rate.setName(jsonNode.get("name").asText());
        rate.setCost(jsonNode.get("cost") == null ? null : Integer.valueOf(jsonNode.get("cost").asText()));
        rate.setCalls(jsonNode.get("calls") == null ? null : Integer.valueOf(jsonNode.get("calls").asText()));
        rate.setSms(jsonNode.get("sms") == null ? null : Integer.valueOf(jsonNode.get("sms").asText()));
        rate.setInternet(jsonNode.get("internet") == null ? null : Integer.valueOf(jsonNode.get("internet").asText()));
        rate.setDescription(jsonNode.get("description").asText());
        ObjectMapper objectMapper = new ObjectMapper();
        String option = jsonNode.get("options") == null ? null : jsonNode.get("options").toString();
        rate.setOptionDTOList(option == null ? null : Arrays.asList(objectMapper.readValue(option, OptionDTO[].class)));
        return rate;
    }
}
