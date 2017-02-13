package com.xd.service.impl;

import com.xd.domain.User;
import com.xd.mapper.UserMapper;
import com.xd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * user xhc
 * datetime 2017/2/9 15:23
 * description
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    public int insert(User user) {
        user.setUpdateTime(new Date());
        return userMapper.insert(user);
    }
    /**
     * 1.默认值 。当前方法必须在Transaction中运行。如果存在已经定义的Transaction，则该方法在已定义的Transaction中运行；
     * 如果不存在已经定义的Transaction，则该方法新开一个Transaction并在其中运行。
     *
     * @param user
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insertRequired(User user) {
        user.setRemark("@Transactional(propagation = Propagation.REQUIRED)");
        return insert(user);
    }

    /**
     * 2.当前方法必须在新开的Transaction中运行。如果存在已经定义的Transaction，则该已定义的Transaction暂停直至新开的Transaction执行完毕。
     *
     * @param user
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int insertRequiredNew(User user) {
        user.setRemark("@Transactional(propagation = Propagation.REQUIRES_NEW)");
        return insert(user);
    }

    /**
     * 3.当前方法不需要在Transaction中运行，但如果存在已经定义的Transaction，则该方法也可以在Transaction中正常执行。
     *
     * @param user
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int insertSupports(User user) {
        user.setRemark("@Transactional(propagation = Propagation.SUPPORTS)");
        return insert(user);
    }

    /**
     * 4.当前方法不应在Transaction中运行，如果存在已经定义的Transaction，则该Transaction暂停(挂起)直至该方法运行完毕。
     *
     * @param user
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public int insertNotSupported(User user) {
        user.setRemark("@Transactional(propagation = Propagation.NOT_SUPPORTED)");
        return insert(user);
    }

    /**
     * 5.当前方法必须在已经定义的Transaction中运行，如果没有已定义的Transaction则抛出异常。
     *
     * @param user
     * @return
     */
    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public int insertMandatory(User user) {
        user.setRemark("@Transactional(propagation = Propagation.MANDATORY)");
        return insert(user);
    }

    /**
     * 6.当前方法不应在Transaction中运行，如果存在已经定义的Transaction则抛出异常。
     *org.springframework.transaction.IllegalTransactionStateException: Existing transaction found for transaction marked with propagation 'never'
     * @param user
     * @return
     */
    @Transactional(propagation = Propagation.NEVER)
    @Override
    public int insertNever(User user) {
        insertRequired(user);
        user.setRemark("@Transactional(propagation = Propagation.NEVER)");
        return insert(user);
    }

    /**
     * 7.如果没有已定义的Transaction，当前方法新开一个Transaction并在该Transaction中运行。
     * 如果存在已定义的Transaction，当前方法在嵌套事务(Nested Transaction)中运行 — 嵌套事务中可以定义储存点，
     * 因此可以独立于外部的Transaction而进行rollback。
     * PS 要设置 transactionManager 的 nestedTransactionAllowed 属性为 true, 注意, 此属性默认为 false!!!
     * @param user
     * @return
     */
    @Transactional(propagation = Propagation.NESTED)
    @Override
    public int insertNested(User user) {
        user.setRemark("@Transactional(propagation = Propagation.NESTED)");
         insert(user);
         throw new RuntimeException("手动报异常");
    }
}
