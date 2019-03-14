package com.pby.gamstudy.dao;

import com.pby.gamstudy.bean.Cover;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CoverDao {

    @Select("select cover from cover")
    @Results({
            @Result(column = "cover", property = "text")
    })
    List<Cover> findAllCover();
}
