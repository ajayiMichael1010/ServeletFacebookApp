package com.facebook.dao.dto;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PostDto {
    private int id;
    private int userId;
    private  String postDetails;
    private String postedBy;
    private  String createdAt;
}
