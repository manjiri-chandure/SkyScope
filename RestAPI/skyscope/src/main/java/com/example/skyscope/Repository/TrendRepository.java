package com.example.skyscope.Repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.skyscope.Entity.Trend;

@Mapper
public interface TrendRepository {

        void addTrend(Trend trend); 

        Trend getTrendById(Long id);
        
        List<Trend> getTrendByUserId(Long userId);   
}
