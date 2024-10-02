package com.zac.sh.service;

import com.zac.sh.dao.TemperatureMapper;
import com.zac.sh.pojo.Temperature;
import com.zac.sh.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class TemperatureService {
    public Temperature queryTemp(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession(false);
        TemperatureMapper mapper = sqlSession.getMapper(TemperatureMapper.class);
        Temperature temperature = mapper.queryTemp();
        sqlSession.close();
        return temperature;
    }
}
