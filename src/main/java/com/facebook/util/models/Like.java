package com.facebook.util.models;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Like {
    private  int id;
    private  int userId;
    private int postId;
    private  int commentId;
    private  String likedBy;
}
