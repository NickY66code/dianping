package com.noah.dianping.recommend;

import com.noah.dianping.dal.RecommendDOMapper;
import com.noah.dianping.model.RecommendDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author yanghaiqiang
 * @Date 2021/03/30 15:43
 * @ClassName dianping
 * @Description
 **/

@Service
public class RecommendService implements Serializable {

    @Autowired
    private RecommendDOMapper recommendDOMapper;

    //召回数据，根据userid 召回shopIdList
    public List<Integer> recall(Integer userId){
        RecommendDO recommendDO=recommendDOMapper.selectByPrimaryKey(userId);
        if(recommendDO == null){
            recommendDO =recommendDOMapper.selectByPrimaryKey(999999);
        }
        String[] shopIdArr =recommendDO.getRecommend().split(",");
        List<Integer> shopIdList= new ArrayList<>();
        for(int i =0;i<shopIdArr.length;i++){
            shopIdList.add(Integer.valueOf(shopIdArr[i]));
        }
        return shopIdList;
    }
}
