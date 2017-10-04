package com.tsystems.ecare.dto.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tsystems.ecare.dto.RateDTO;

import java.io.IOException;

public class RateSerializer extends JsonSerializer<RateDTO> {
    @Override
    public void serialize(RateDTO rateDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", rateDTO.getName());
        jsonGenerator.writeNumberField("cost", rateDTO.getCost());
        jsonGenerator.writeNumberField("calls", rateDTO.getCalls());
        jsonGenerator.writeNumberField("sms", rateDTO.getSms());
        jsonGenerator.writeNumberField("internet", rateDTO.getInternet());
        jsonGenerator.writeStringField("description", rateDTO.getDescription());
        jsonGenerator.writeObjectField("options",rateDTO.getOptionDTOList());
        jsonGenerator.writeEndObject();
    }
}
