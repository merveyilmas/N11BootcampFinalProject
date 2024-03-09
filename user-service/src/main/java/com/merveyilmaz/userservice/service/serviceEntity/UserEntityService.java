package com.merveyilmaz.userservice.service.serviceEntity;

import com.merveyilmaz.userservice.dao.UserRepository;
import com.merveyilmaz.userservice.entitiy.User;
import com.merveyilmaz.userservice.general.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService extends BaseEntityService<User, UserRepository> {

    protected UserEntityService(UserRepository repository) {
        super(repository);
    }
}
