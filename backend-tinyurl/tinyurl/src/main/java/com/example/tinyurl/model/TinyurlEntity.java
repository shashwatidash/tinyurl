package com.example.tinyurl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TinyurlEntity {

    Integer urlId;

    String keyValue;

    String longUrl;

    String shortUrl;
}
