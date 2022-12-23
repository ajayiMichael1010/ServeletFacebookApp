package com.facebook.dao.dto;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class commentDto  {
    private int id;
    private int post_id;
    private  int user_id;
    private  String comment;
    private  String commentedBy;
    private  String created_at;

}
