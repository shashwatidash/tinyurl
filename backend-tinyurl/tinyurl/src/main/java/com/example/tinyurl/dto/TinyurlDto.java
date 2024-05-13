package com.example.tinyurl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TinyurlDto {

    String keyValue;

    String longUrl;

    String shortUrl;
}
