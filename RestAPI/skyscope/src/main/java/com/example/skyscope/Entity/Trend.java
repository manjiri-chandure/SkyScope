package com.example.skyscope.Entity;

import com.example.skyscope.Enum.TrendStatus;
import lombok.Data;

@Data
public class Trend {
    private Long id;
    private String topic;
    private String summary;
    private Long userId;
    private TrendStatus status;
    private Integer progress;
}
