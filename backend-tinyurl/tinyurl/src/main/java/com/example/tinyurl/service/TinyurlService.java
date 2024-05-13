package com.example.tinyurl.service;

import com.example.tinyurl.dto.TinyurlDto;
import com.example.tinyurl.dto.TinyurlRequestDto;

public interface TinyurlService {

    TinyurlDto newUrl(TinyurlRequestDto requestDto);

    TinyurlDto getLongUrl(String key);

    Integer deleteUrl(String key);
}
