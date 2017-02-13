package com.xd.tx;

import com.xd.domain.User;
import com.xd.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * user xhc
 * datetime 2017/2/9 15:48
 * description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringTxTest {
    @Autowired
    UserService userService;
    @Test
    public void insertRequired(){
        User user=new User();
        user.setName("insertRequired");
        userService.insertRequired(user);
    }
    @Test
    public void insertRequiredNew(){
        User user=new User();
        user.setName("insertRequiredNew");
        userService.insertRequiredNew(user);
    }
    @Test
    public void insertMandatory(){
        User user=new User();
        user.setName("insertMandatory");
        //会报异常org.springframework.transaction.IllegalTransactionStateException: No existing transaction found for transaction marked with propagation 'mandatory'
        //如果当前方法加可用事务注解,则不会报如上异常,可以对比insertNever1()
        userService.insertMandatory(user);
    }
    @Test
    public void insertNever(){
        User user=new User();
        user.setName("insertNever");
        //数据 不会保存
        userService.insertNever(user);
    }
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertNever1(){
        //会报异常 org.springframework.transaction.IllegalTransactionStateException: Existing transaction found for transaction marked with propagation 'never'
        //如果当前方法不加可用事务注解,则不会报如上异常,可以对比  insertMandatory()
        User user=new User();
        user.setName("insertNever1");
        userService.insertNever(user);
    }
    @Test
    //@Transactional(propagation = Propagation.REQUIRED) 此测试方法上的事务注解,无法使下面调用产品事务提交
    // 因为userService.insertNotSupported(user);不支持事务
    public void insertNotSupported(){
        User user=new User();
        user.setName("insertNotSupported");
        userService.insertNotSupported(user);
    }
    @Test
    public void insertNested(){
        User user=new User();
        user.setName("insertNested");
        userService.insertNested(user);
    }
    @Test
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertNested1(){
        User user=new User();
        try {
            user.setName("insertNested11");
            userService.insertNested(user);
        }catch (Exception e){
            user.setName("Nested1Required");
            userService.insertRequiredNew(user);
        }

     }

}
