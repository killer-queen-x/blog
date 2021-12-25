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
@TableName("comment")
public class Comment {
    @TableId(type = IdType.AUTO)
    private Integer cid;
    private Integer pid;
    private Integer puid;
    private Integer uid;
    private String comment;
    private String ctime;
    private String pictrue;
    private List<User> cusers;
    private List<Post> posts;
}
