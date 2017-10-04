package com.tsystems.ecare.dto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tsystems.ecare.dto.OptionDTO;

import java.io.IOException;

public class OptionSerializer extends JsonSerializer<OptionDTO> {
    @Override
    public void serialize(OptionDTO optionDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", optionDTO.getName());
        jsonGenerator.writeNumberField("cost", optionDTO.getCost());
        jsonGenerator.writeNumberField("costOfConnection", optionDTO.getCostOfConnection());
        jsonGenerator.writeStringField("description", optionDTO.getDescription());
        jsonGenerator.writeObjectField("compatibleOption",optionDTO.getCompatibleOption());
        jsonGenerator.writeObjectField("incompatibleOption",optionDTO.getIncompatibleOption());
        jsonGenerator.writeEndObject();
    }
}
