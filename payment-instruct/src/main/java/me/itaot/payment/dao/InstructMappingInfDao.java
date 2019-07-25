package me.itaot.payment.dao;

import me.itaot.payment.common.beans.InstructMappingInf;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructMappingInfDao {

    @Select("select sys_code as sysCode,sub_sys_code as subSysCode,instruct_code as instructCode,seq_no as seqNo,src_field as srcField," +
            "dest_field as destField,type,length,must,remark,default_value as defaultValue from sp_instruct_mapping_info " +
            "where sys_code = #{sysCode} and sub_sys_code = #{subSysCode} and instruct_code = #{instructCode} order by seq_no")
    public List<InstructMappingInf> getInstructMapping(@Param("sysCode") String sysCode,
                                                       @Param("subSysCode") String subSysCode,
                                                       @Param("instructCode") String instructCode);

}