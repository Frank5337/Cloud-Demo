package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: zbl
 * @Date: 16:57 2019/10/26
 * @Description:
 */
@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;
    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriend(String userId, String friendId) {
        //先判断userId 和 friendId是否有数据, 有就是重复添加好友
        Friend friend = friendDao.findByUseridAndFriendid(userId, friendId);
        if (friend != null){
            return 0;
        }
        //直接添加好友, 让好友userId 到friendId 方向的type为0
        friend = new Friend();
        friend.setUserid(userId);
        friend.setFriendid(friendId);
        friend.setIslike("0");
        friendDao.save(friend);
        //如果有 双方状态都改为1   都喜欢 1 单喜欢0
        if (friendDao.findByUseridAndFriendid(friendId, userId) != null){
            //把双方isLike 改为1
            friendDao.updateIsLike("1", userId, friendId);
            friendDao.updateIsLike("1", friendId, userId);
        }
        return 1;
    }

    public int addNoFriend(String userId, String friendId) {
        //先判断是否已经是非好友
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userId, friendId);
        if (noFriend != null){
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendId);
        noFriendDao.save(noFriend);
        return 1;
    }

    public void deleteFriend(String userId, String friendId) {
        //删除表中userId 到friendId 这条数据
        friendDao.deleteFriend(userId, friendId);
        //更新friendId 到userId 的islike 为0
        friendDao.updateIsLike("0", userId, friendId);
        //非好友表中添加数据
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendId);
        noFriendDao.save(noFriend);
    }
}
