package com.smartsync.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class SearchForm {
    private String searchBy;
    private String keyword;
}
