package com.espaharvesterproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Pojo for holding the project info
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Record {
    private String body;
    private Integer kodikos;
}
