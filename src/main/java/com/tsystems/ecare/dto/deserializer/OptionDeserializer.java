package com.tsystems.ecare.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.ecare.dto.OptionDTO;

import java.io.IOException;

public class OptionDeserializer extends JsonDeserializer<OptionDTO> {
    @Override
    public OptionDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        OptionDTO option = new OptionDTO();
        JsonNode jsonNode = jsonParser.readValueAsTree();
        option.setName(jsonNode.get("name").asText());
        option.setCost(jsonNode.get("cost") == null ? null : Integer.valueOf(jsonNode.get("cost").asText()));
        option.setCost(jsonNode.get("costOfConnection") == null ? null : Integer.valueOf(jsonNode.get("costOfConnection").asText()));
        option.setDescription(jsonNode.get("description").asText());
        ObjectMapper objectMapper = new ObjectMapper();
        option.setIncompatibleOption(objectMapper.readValue(jsonNode.get("incompatibleOption").toString(), OptionDTO.class));
        option.setCompatibleOption(objectMapper.readValue(jsonNode.get("compatibleOption").toString(), OptionDTO.class));
        return option;
    }
}
