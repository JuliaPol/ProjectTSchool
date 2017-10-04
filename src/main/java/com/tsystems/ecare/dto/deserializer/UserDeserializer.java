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

public class UserDeserializer extends JsonDeserializer<UserDTO> {
    @Override
    public UserDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        UserDTO user = new UserDTO();
        JsonNode jsonNode = jsonParser.readValueAsTree();
        user.setFirstName(jsonNode.get("firstName").asText());
        user.setLastName(jsonNode.get("lastName").asText());
        user.setEmail(jsonNode.get("email").asText());
        user.setLogin(jsonNode.get("login").asText());
        user.setBirthDate(jsonNode.get("birthDate").asText());
        user.setPassportNumber(jsonNode.get("passportNumber").asText());
        user.setPassportIssuedWhen(jsonNode.get("passportIssuedWhen").asText());
        user.setPassportIssuedByWhom(jsonNode.get("passportIssuedByWhom").asText());
        ObjectMapper objectMapper = new ObjectMapper();
        user.setAddress(objectMapper.readValue(jsonNode.get("address").toString(), AddressDTO.class));
        return user;
    }
}
