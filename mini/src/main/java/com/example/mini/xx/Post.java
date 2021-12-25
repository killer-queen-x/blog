package com.example.mini.xx;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@TableName("post")
public class Post {
    @TableId(type = IdType.AUTO)
    private Integer pid;
    private String title;
    private String content;
    private String image;
    private String time;
    private Integer uid;
    private List<Comment> comments;
    private List<Reply> replies;
    private List<User> users;
    private List<Good> goods;
}

