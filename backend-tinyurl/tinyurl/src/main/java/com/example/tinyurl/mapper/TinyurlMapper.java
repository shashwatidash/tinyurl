package com.example.tinyurl.mapper;

import com.example.tinyurl.dto.TinyurlDto;
import com.example.tinyurl.model.TinyurlEntity;

public class TinyurlMapper {

    public static TinyurlDto entityToDto(TinyurlEntity entity) {
        TinyurlDto dto = new TinyurlDto();
        dto.setKeyValue(entity.getKeyValue());
        dto.setShortUrl(entity.getShortUrl());
        dto.setLongUrl(entity.getLongUrl());

        return dto;
    }
}
