package com.popular.welove.service.impl;

import com.popular.welove.entity.User;
import com.popular.welove.mapper.UserMapper;
import com.popular.welove.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuhang
 * @since 2020-07-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
