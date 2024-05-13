package com.example.tinyurl.controller;

import com.example.tinyurl.dto.TinyurlDto;
import com.example.tinyurl.dto.TinyurlRequestDto;
import com.example.tinyurl.service.TinyurlService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@Validated
public class TinyurlController {
    private TinyurlService tinyurlService;

    @PostMapping(value = "/newUrl")
    public ResponseEntity<TinyurlDto> newUrl(@Valid @RequestBody (required = true) TinyurlRequestDto requestDto) {
        TinyurlDto responseDto = tinyurlService.newUrl(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{key}")
    public ResponseEntity<Object> redirectToLongUrl(@PathVariable String key) {
        TinyurlDto dto = tinyurlService.getLongUrl(key);

        if (dto != null) {
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(dto.getLongUrl())).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("URL not found!");
        }
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<Object> deleteUrl(@PathVariable String key) {
        Integer deletedCount = tinyurlService.deleteUrl(key);
        if (deletedCount > 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no URL for the input key");
        }
    }
}
