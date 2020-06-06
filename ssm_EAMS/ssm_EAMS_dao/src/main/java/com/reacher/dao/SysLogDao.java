package com.reacher.dao;

import com.reacher.domain.SysLog;
import org.apache.ibatis.annotations.Insert;

public interface SysLogDao {

    @Insert("insert into sysLog(id,visitTime,username,ip,url,executionTime,method) values(replace(uuid(),'-',''),#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog) throws Exception;

}
