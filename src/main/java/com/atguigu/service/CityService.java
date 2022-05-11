package com.atguigu.service;

import com.atguigu.admin.bean.City;
import com.atguigu.admin.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {


    @Autowired
    CityMapper cityMapper;

    public City getCityById(Long id) {
        City city = cityMapper.getById(id);
        return city;
    }

    public City saveCity(City city){
        cityMapper.insert(city);
        return  city;
    }


}
