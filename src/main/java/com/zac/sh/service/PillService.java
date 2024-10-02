package com.zac.sh.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac.sh.dao.PillMapper;
import com.zac.sh.pojo.Pill;
import com.zac.sh.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class PillService {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Pill[] queryAllPills() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession(false);
        PillMapper mapper = sqlSession.getMapper(PillMapper.class);
        Pill[] pill = mapper.queryPillById();
        sqlSession.close();
        return pill;
    }

    // 更新药物信息
    public boolean updatePill(Pill pill) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession(false);
        PillMapper mapper = sqlSession.getMapper(PillMapper.class);
        try {
            // 将 List<String> 转换为 JSON 字符串
            String dosageTimesJson = objectMapper.writeValueAsString(pill.getDoseTimes());

            // 调用 MyBatis 更新方法
            int updatedRows = mapper.updatePill(
                    pill.getId(),
                    pill.getName(),
                    pill.getInitialQuantity(),
                    dosageTimesJson,  // 将 JSON 字符串传给数据库
                    pill.getDoseAmount()
            );
            sqlSession.commit();  // 手动提交事务
            return updatedRows > 0;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }finally {
            sqlSession.close();  // 保证操作完成后关闭 session
        }
    }
}
