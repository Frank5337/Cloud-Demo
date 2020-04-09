package com.cloud.qa.client;

import com.cloud.qa.client.Impl.BaseClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zbl
 * @Date: 11:35 2019/10/6
 * @Description:
 */
//如果调用失败 会执行BaseClientImpl
@FeignClient(value = "tensquare-base", fallback = BaseClientImpl.class)
public interface BaseClient {

    @GetMapping("/label/{labelId}")
    public Result findByIdt(@PathVariable("labelId") String labelId);
}
