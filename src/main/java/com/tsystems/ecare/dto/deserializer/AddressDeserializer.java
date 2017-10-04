package com.tsystems.ecare.dto.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.ecare.dto.AddressDTO;
import com.tsystems.ecare.dto.UserDTO;

import java.io.IOException;

public class AddressDeserializer extends JsonDeserializer<AddressDTO> {
    @Override
    public AddressDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        AddressDTO address = new AddressDTO();
        JsonNode jsonNode = jsonParser.readValueAsTree();
        address.setCountry(jsonNode.get("country").asText());
        address.setCity(jsonNode.get("city").asText());
        address.setStreet(jsonNode.get("street").asText());
        address.setHouseNumber(jsonNode.get("houseNumber").asText());
        address.setZipcode(jsonNode.get("zipcode").asText());
        return address;
    }
}
