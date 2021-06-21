package com.bjsxt.mapper;

import com.bjsxt.pojo.Airport;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AirportMapper {


    @Select("select * from airport")
    List<Airport> selAll();
}
