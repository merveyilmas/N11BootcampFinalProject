package com.merveyilmaz.userservice.mapper;

import com.merveyilmaz.userservice.dto.UserReviewDTO;
import com.merveyilmaz.userservice.entitiy.UserReview;
import com.merveyilmaz.userservice.request.UserReviewSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserReviewMapper {

    UserReviewMapper INSTANCE = Mappers.getMapper(UserReviewMapper.class);
    UserReview convertToUserReview(UserReviewSaveRequest request);
    UserReviewDTO convertToUserReviewDTO(UserReview userReview);
    List<UserReviewDTO> convertToUserReviewDTOs(List<UserReview> userReviews);

}
