package com.cloud.friend.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zbl
 * @Date: 18:02 2019/10/26
 * @Description:
 */
@FeignClient("tensquare-user")
@RequestMapping("/user")
public interface UserFeign {

    /**
     *更新好友粉丝 和 用户关注数
     */
    @PutMapping("/{userId}/{friendId}/{x}")
    public void updateFransCountAndFollowCount(@PathVariable("userId") String userId,
                                               @PathVariable("friendId") String friendId,
                                               @PathVariable("x") int x);


}
