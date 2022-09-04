package com.github.shuaidd.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * @author ddshuai
 * date 2022-08-31 15:49
 **/
public class Long2DateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        long time = jsonParser.getValueAsLong();
        if (time > 0L) {
            return new Date(time * 1000);
        }

        return null;
    }
}
