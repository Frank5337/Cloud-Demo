package com.tensquare.qa.client.Impl;

import com.tensquare.qa.client.BaseClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: zbl
 * @Date: 9:42 2019/12/1
 * @Description:
 */
@Component
public class BaseClientImpl implements BaseClient {

    @Override
    public Result findByIdt(@PathVariable("labelId") String labelId) {
        return new Result(false, StatusCode.ERROR, "熔断器出发了");
    }
}
