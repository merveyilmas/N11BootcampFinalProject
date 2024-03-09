package com.merveyilmaz.userservice.dao;

import com.merveyilmaz.userservice.entitiy.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {

}
