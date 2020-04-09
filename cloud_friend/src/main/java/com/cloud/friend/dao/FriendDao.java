package com.cloud.friend.dao;

import com.cloud.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author: zbl
 * @Date: 17:02 2019/10/26
 * @Description:
 */
public interface FriendDao extends JpaRepository<Friend, String>, JpaSpecificationExecutor<Friend> {

    public Friend findByUseridAndFriendid(String userid, String friendid);

    @Modifying
    @Query(value = "UPDATE tb_friend SET islike = ? WHERE userid =? AND friendid = ?" , nativeQuery = true)
    public void updateIsLike(String islike,String userid, String friendid);

    @Modifying
    @Query(value = "delete from tb_friend where userid = ? and friendid = ? ", nativeQuery = true)
    void deleteFriend(String userId, String friendId);
}
