package com.noah.dianping.service.Impl;

import com.noah.dianping.dal.UserModelMapper;
import com.noah.dianping.model.UserModel;
import com.noah.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author yanghaiqiang
 * @Date 2020/12/14 21:47
 * @ClassName dianping
 * @Description 用户服务层
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserModelMapper userModelMapper;

    @Override
    public UserModel getUser(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }
}
