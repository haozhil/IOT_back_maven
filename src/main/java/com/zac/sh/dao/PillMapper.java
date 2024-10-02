package com.zac.sh.dao;

import com.zac.sh.pojo.Pill;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PillMapper {
    @Select("SELECT * from pill")
    Pill[] queryPillById();

    @Update("UPDATE pill SET name = #{name}, initial_quantity = #{initialQuantity}, dose_times = #{doseTimes}, dose_amount = #{doseAmount} WHERE id = #{id}")
    int updatePill(
            @Param("id") int id,
            @Param("name") String name,
            @Param("initialQuantity") int initialQuantity,  // Java 参数名
            @Param("doseTimes") String doseTimes,       // JSON 格式字符串
            @Param("doseAmount") double doseAmount
    );
}
