package com.noah.dianping.dal;

import com.noah.dianping.model.RecommendDO;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recommend
     *
     * @mbg.generated Tue Mar 30 15:46:49 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recommend
     *
     * @mbg.generated Tue Mar 30 15:46:49 CST 2021
     */
    int insert(RecommendDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recommend
     *
     * @mbg.generated Tue Mar 30 15:46:49 CST 2021
     */
    int insertSelective(RecommendDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recommend
     *
     * @mbg.generated Tue Mar 30 15:46:49 CST 2021
     */
    RecommendDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recommend
     *
     * @mbg.generated Tue Mar 30 15:46:49 CST 2021
     */
    int updateByPrimaryKeySelective(RecommendDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table recommend
     *
     * @mbg.generated Tue Mar 30 15:46:49 CST 2021
     */
    int updateByPrimaryKey(RecommendDO record);
}