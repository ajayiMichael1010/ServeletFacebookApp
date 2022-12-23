package com.facebook.util.models;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Post {
    private int id;
    private int userId;
    private  String postDetails;
    private String postedBy;
    private  String createdAt;


}
