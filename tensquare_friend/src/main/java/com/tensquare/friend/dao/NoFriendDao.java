package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
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
