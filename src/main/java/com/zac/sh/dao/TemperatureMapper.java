package com.zac.sh.dao;

import com.zac.sh.pojo.Temperature;
import org.apache.ibatis.annotations.Select;

public interface TemperatureMapper {
    @Select("select * from temperature where id=1")
    Temperature queryTemp();
}
