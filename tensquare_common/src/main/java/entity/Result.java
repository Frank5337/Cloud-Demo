package entity;

import javax.annotation.sql.DataSourceDefinition;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zbl
 * @Date: 22:34 2019/10/1
 * @Description: 返回体
 */
@Data
public class Result implements Serializable {

    public Result() {
    }

    private Boolean flag;
    private Integer code;
    private String message;
    private Object data;

    public Result(Boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(Boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
