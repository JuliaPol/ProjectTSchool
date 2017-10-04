package com.tsystems.ecare.dto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tsystems.ecare.dto.AddressDTO;

import java.io.IOException;

public class AddressSerializer extends JsonSerializer<AddressDTO> {
    @Override
    public void serialize(AddressDTO addressDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("country", addressDTO.getCountry());
        jsonGenerator.writeStringField("city", addressDTO.getCity());
        jsonGenerator.writeStringField("street", addressDTO.getStreet());
        jsonGenerator.writeStringField("houseNumber", addressDTO.getHouseNumber());
        jsonGenerator.writeStringField("zipcode", addressDTO.getZipcode());
        jsonGenerator.writeEndObject();
    }
}
