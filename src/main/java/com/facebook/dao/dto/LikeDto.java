package com.facebook.dao.dto;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class LikeDto {
    private  int id;
    private  int userId;
    private int postId;
    private  int commentId;
    private  String likedBy;
}
