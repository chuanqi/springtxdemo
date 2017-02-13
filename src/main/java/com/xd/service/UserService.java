package com.xd.service;

import com.xd.domain.User;
import org.springframework.stereotype.Service;

/**
 * user xhc
 * datetime 2017/2/9 15:23
 * description
 */
public interface UserService {
    int insert(User user);

    int insertRequired(User user);

    int insertRequiredNew(User user);

    int insertSupports(User user);

    int insertNotSupported(User user);

    int insertMandatory(User user);

    int insertNever(User user);

    int insertNested(User user);
}
