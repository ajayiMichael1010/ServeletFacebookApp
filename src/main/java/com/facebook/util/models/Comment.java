package com.facebook.util.models;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class Comment {
    private int id;
    private int post_id;
    private  int user_id;
    private  String comment;
    private  String commentedBy;
    private  String created_at;

}
