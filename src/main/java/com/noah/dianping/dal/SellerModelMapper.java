package com.noah.dianping.dal;

import com.noah.dianping.model.SellerModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Tue Jan 12 15:45:10 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Tue Jan 12 15:45:10 CST 2021
     */
    int insert(SellerModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Tue Jan 12 15:45:10 CST 2021
     */
    int insertSelective(SellerModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Tue Jan 12 15:45:10 CST 2021
     */
    SellerModel selectByPrimaryKey(Integer id);

    List<SellerModel> selectAll();
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Tue Jan 12 15:45:10 CST 2021
     */
    int updateByPrimaryKeySelective(SellerModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table seller
     *
     * @mbg.generated Tue Jan 12 15:45:10 CST 2021
     */
    int updateByPrimaryKey(SellerModel record);
}