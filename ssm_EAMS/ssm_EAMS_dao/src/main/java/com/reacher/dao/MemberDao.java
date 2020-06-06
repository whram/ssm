package com.reacher.dao;

import com.reacher.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberDao {

    //根据id查找member信息
    @Select("select * from member where id = #{id}")
    Member findById(String id) throws Exception;

}
