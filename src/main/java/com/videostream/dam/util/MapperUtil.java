package com.videostream.dam.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;

public class MapperUtil {
    private static ModelMapper MAPPER = null;

    public static ModelMapper getInstance() {
        if(MAPPER == null) {
            MAPPER = new ModelMapper();
            MAPPER.getConfiguration()
                  .setMatchingStrategy(MatchingStrategies.LOOSE);
            MAPPER.getConfiguration()
                  .setSourceNameTokenizer(NameTokenizers.CAMEL_CASE)
                  .setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);
        }
        return MAPPER;
    }
}
