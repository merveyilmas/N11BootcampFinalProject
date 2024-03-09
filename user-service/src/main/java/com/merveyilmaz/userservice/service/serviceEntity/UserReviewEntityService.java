package com.merveyilmaz.userservice.service.serviceEntity;

import com.merveyilmaz.userservice.dao.UserReviewRepository;
import com.merveyilmaz.userservice.entitiy.UserReview;
import com.merveyilmaz.userservice.general.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class UserReviewEntityService extends BaseEntityService<UserReview, UserReviewRepository> {

    protected UserReviewEntityService(UserReviewRepository repository) {
        super(repository);
    }

    /*public List<UserReview> findAllByProductId(Long productId){
        return getRepository().findAllByProductId(productId);
    }

    public List<UserReview> findAllByUserId(Long userId){
        return getRepository().findAllByUserId(userId);
    }*/
}
