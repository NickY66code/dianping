package com.noah.dianping.service.Impl;

import com.noah.dianping.common.BusinessException;
import com.noah.dianping.common.EmBusinessError;
import com.noah.dianping.dal.UserModelMapper;
import com.noah.dianping.model.UserModel;
import com.noah.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

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

    @Override
    @Transactional
    public UserModel register(UserModel registerUser) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        registerUser.setPassword(encodeByMD5(registerUser.getPassword()));
        registerUser.setCreatedAt(new Date());
        registerUser.setUpdatedAt(new Date());

        try{
            userModelMapper.insertSelective(registerUser);
        }catch (DuplicateKeyException e){
            throw new BusinessException(EmBusinessError.REGISTER_DUP_FAIL);
        }

        return getUser(registerUser.getId());
    }

    @Override
    public UserModel login(String telphone, String password) throws BusinessException {
        UserModel userModel=userModelMapper.selectByTelphoneAndPassword(telphone,password);
        if(null == userModel){
            throw new BusinessException(EmBusinessError.LOGIN_FALL);
        }
        return userModel;
    }

    private String encodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法MD5
        MessageDigest messageDigest =MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder=new BASE64Encoder();
        return base64Encoder.encode(messageDigest.digest(str.getBytes("utf-8")));
    }
}
