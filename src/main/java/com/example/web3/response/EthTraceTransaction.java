package com.example.web3.response;

import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.response.Transaction;

import java.io.IOException;
import java.util.Optional;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectReader;

import org.web3j.protocol.ObjectMapperFactory;
public class EthTraceTransaction extends Response<TraceTransaction> {

    public Optional<TraceTransaction> getTransaction() {
        return Optional.ofNullable(getResult());
    }

    public static class ResponseDeserialiser extends JsonDeserializer<TraceTransaction> {

        private ObjectReader objectReader = ObjectMapperFactory.getObjectReader();

        @Override
        public TraceTransaction deserialize(
                JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                return objectReader.readValue(jsonParser, TraceTransaction.class);
            } else {
                return null; // null is wrapped by Optional in above getter
            }
        }
    }
}
