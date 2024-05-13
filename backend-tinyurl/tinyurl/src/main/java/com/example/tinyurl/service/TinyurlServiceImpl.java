package com.example.tinyurl.service;

import com.example.tinyurl.dto.TinyurlDto;
import com.example.tinyurl.dto.TinyurlRequestDto;
import com.example.tinyurl.mapper.TinyurlMapper;
import com.example.tinyurl.model.TinyurlEntity;
import com.example.tinyurl.repository.TinyurlRepositoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TinyurlServiceImpl implements TinyurlService {

    private TinyurlRepositoryMapper tinyurlRepositoryMapper;

    @Transactional
    public TinyurlDto newUrl(TinyurlRequestDto requestDto) {

        final String SHORT_URL_START = "https://localhost/";

        TinyurlDto tinyurlDto;
        TinyurlEntity tinyurlEntity = tinyurlRepositoryMapper.getUrlInfo(requestDto);

        if (tinyurlEntity != null) {
            tinyurlDto = TinyurlMapper.entityToDto(tinyurlEntity);
            return tinyurlDto;
        }

        String key = generateHash(requestDto.getUrl()).substring(0, 8);
        Integer keyCount = tinyurlRepositoryMapper.getKeyCount(key);

        if (keyCount > 0) {
            String longUrl = requestDto.getUrl() + UUID.randomUUID().toString();
            key = generateHash(longUrl).substring(0, 8);
        }

        String shortUrl = SHORT_URL_START + key;

        TinyurlDto dtoToSave = new TinyurlDto();
        dtoToSave.setKeyValue(key);
        dtoToSave.setLongUrl(requestDto.getUrl());
        dtoToSave.setShortUrl(shortUrl);

        tinyurlRepositoryMapper.insertUrlInfo(dtoToSave);
        return dtoToSave;
    }

    private String generateHash(String longUrl) {
        try {
            MessageDigest hash = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = hash.digest(longUrl.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();

            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();

        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash! : " + e.getMessage());
        }
    }

    public TinyurlDto getLongUrl(String key) {
        TinyurlEntity entity;
        TinyurlDto dto = new TinyurlDto();
        entity = tinyurlRepositoryMapper.getLongUrl(key);

        if (entity != null) {
            dto.setLongUrl(entity.getLongUrl());
            return dto;
        }

        return null;
    }

    public Integer deleteUrl(String key) {
        return tinyurlRepositoryMapper.deleteUrl(key);
    }
}
