package com.example.skyscope.Dto.Trend;

import java.util.Date;

import com.example.skyscope.Enum.TrendStatus;

import lombok.Data;

@Data
public class TrendResponseDto {

    private Long id;
    private String topic;
    private String summary;
    private Long userId;
    private String userName;
    private TrendStatus status;
    private Integer progress;
    private Date createdAt;
    private Date modifiedAt;

}
