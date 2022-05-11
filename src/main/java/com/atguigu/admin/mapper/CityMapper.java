package com.atguigu.admin.mapper;

import com.atguigu.admin.bean.City;
import org.apache.ibatis.annotations.*;

public interface CityMapper {

    @Select("select * from city where id = #{id} ")
    public City getById(@Param("id") Long id);
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into city(`name`,`state`,`state`) values(#{name},#{state},#{country})")
    public void insert(City city);
}
