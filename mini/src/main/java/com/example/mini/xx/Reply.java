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
@TableName("reply")
public class Reply {
    @TableId(type = IdType.AUTO)
    private Integer rid;
    private Integer pid;
    private Integer cid;
    private Integer cuid;
    private Integer uid;
    private String reply;
    private String rtime;
    private List<User> rusers;
    private List<Post> posts;
    private List<Comment> comments;
}
