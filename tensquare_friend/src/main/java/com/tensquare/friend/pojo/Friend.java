package com.tensquare.friend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: zbl
 * @Date: 17:03 2019/10/26
 * @Description:
 */
@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)
@Data
public class Friend implements Serializable {

    @Id
    private String userid;
    @Id
    private String friendid;

    private String islike;
}
