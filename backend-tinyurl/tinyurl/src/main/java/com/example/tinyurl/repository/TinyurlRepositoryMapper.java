package com.example.tinyurl.repository;

import com.example.tinyurl.dto.TinyurlDto;
import com.example.tinyurl.dto.TinyurlRequestDto;
import com.example.tinyurl.model.TinyurlEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TinyurlRepositoryMapper {

    TinyurlEntity getUrlInfo(@Param("requestDto") TinyurlRequestDto tinyurlRequestDto);

    Integer getKeyCount(@Param("key") String key);

    void insertUrlInfo(@Param("dto") TinyurlDto dtoToSave);

    TinyurlEntity getLongUrl(@Param("key") String key);

    Integer deleteUrl(@Param("key") String key);
}
