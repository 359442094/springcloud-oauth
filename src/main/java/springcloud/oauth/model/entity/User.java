package springcloud.oauth.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

/**
 * @author CJ
 * @date 2020/3/9
 */
@ToString
@Getter
@Setter
@TableName(value = "user")
public class User {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "pwd")
    private String pwd;
    @TableField(exist = false)
    private String[] roles;

}
