package com.tsystems.ecare.dto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tsystems.ecare.dto.UserDTO;

import java.io.IOException;

public class UserSerializer extends JsonSerializer<UserDTO> {
    @Override
    public void serialize(UserDTO userDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("firstName", userDTO.getFirstName());
        jsonGenerator.writeStringField("lastName", userDTO.getLastName());
        jsonGenerator.writeStringField("email", userDTO.getEmail());
        jsonGenerator.writeStringField("login", userDTO.getLogin());
        jsonGenerator.writeStringField("birthDate", userDTO.getBirthDate());
        jsonGenerator.writeStringField("passportNumber", userDTO.getPassportNumber());
        jsonGenerator.writeStringField("passportIssuedWhen", userDTO.getPassportIssuedWhen());
        jsonGenerator.writeStringField("passportIssuedByWhom", userDTO.getPassportIssuedByWhom());
        jsonGenerator.writeObjectField("address", userDTO.getAddress());
        jsonGenerator.writeEndObject();
    }
}
