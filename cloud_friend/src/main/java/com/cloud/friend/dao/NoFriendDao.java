package com.cloud.friend.dao;

import com.cloud.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author: zbl
 * @Date: 17:02 2019/10/26
 * @Description:
 */
public interface NoFriendDao extends JpaRepository<NoFriend, String>, JpaSpecificationExecutor<NoFriend> {

    public NoFriend findByUseridAndFriendid(String userid, String friendid);

}
