package com.example.skyscope.Mapper;

import java.util.Date;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.skyscope.Dto.Trend.TrendResponseDto;
import com.example.skyscope.Dto.Trend.TrendTopicDto;
import com.example.skyscope.Entity.Trend;
import com.example.skyscope.Enum.TrendStatus;

@Mapper(componentModel = "spring")
public interface TrendMapper {

    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "status", expression = "java(TrendStatus.PENDING)")
    @Mapping(target = "progress", expression = "java(0)")
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "summary", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "topic", source = "topic")
    Trend toTrend(TrendTopicDto trendTopicDto);

    @Mapping(target = "modifiedAt", source = "modifiedAt")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "progress", source = "progress")
    @Mapping(target = "userName", ignore = true)
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "summary", source = "summary")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "topic", source = "topic")
    TrendResponseDto toResponseDto(Trend trend);

} 