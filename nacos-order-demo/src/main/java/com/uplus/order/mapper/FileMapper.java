package com.uplus.order.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileMapper {

    //查询文章栏目所属视图
    String getAppendix(@Param("id") Long id);

}
