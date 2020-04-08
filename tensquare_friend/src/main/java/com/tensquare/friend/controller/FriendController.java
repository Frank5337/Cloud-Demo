package com.tensquare.friend.controller;

import com.tensquare.friend.feign.UserFeign;
import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zbl
 * @Date: 8:56 2019/10/13
 * @Description:
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserFeign userFeign;

    /**
     * 添加好友或非好友
     * @return
     */
    @PutMapping("/like/{friendId}/{type}")
    public Result addFriend(@PathVariable String friendId, @PathVariable String type){
        //验证是否登录, 并且拿到当前登录的用户id
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null){
            //说明当前用户没有user角色
            return new Result(false, StatusCode.LOGINERROR, "权限不足");
        }
        //得到当前用户的Id
        String userId = claims.getId();
        //判断是添加好友 还是添加非好友
        if (type != null){
            if ("1".equals(type)){
                //添加好友
                int flag = friendService.addFriend(userId, friendId);
                if (flag == 0){
                    return new Result(false, StatusCode.ERROR, "不能重复添加好友");
                }
                if (flag == 1){
                    userFeign.updateFransCountAndFollowCount(userId, friendId, 1);
                    return new Result(true, StatusCode.OK, "添加成功");
                }
            } else if ("2".equals(type)){
                //添加非好友
                int flag = friendService.addNoFriend(userId, friendId);
                if (flag == 0){
                    return new Result(false, StatusCode.ERROR, "不能重复添加非好友");
                }
                if (flag == 1){
                    return new Result(true, StatusCode.OK, "添加成功");
                }
            }
            return new Result(false, StatusCode.ERROR, "参数异常");
        } else {
            return new Result(false, StatusCode.ERROR, "参数异常");
        }

    }

    @DeleteMapping("/{friendId}")
    public Result deleteFriend(@PathVariable String friendId){
        //验证是否登录, 并且拿到当前登录的用户id
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null){
            //说明当前用户没有user角色
            return new Result(false, StatusCode.LOGINERROR, "权限不足");
        }
        //得到当前用户的Id
        String userId = claims.getId();
        friendService.deleteFriend(userId, friendId);
        userFeign.updateFransCountAndFollowCount(userId, friendId, -1);
        return new Result(true, StatusCode.OK, "del success");

    }
}
