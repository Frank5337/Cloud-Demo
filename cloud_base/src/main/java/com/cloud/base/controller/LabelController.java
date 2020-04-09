package com.cloud.base.controller;

import com.cloud.base.pojo.Label;
import com.cloud.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zbl
 * @Date: 10:58 2019/10/2
 * @Description:
 */
@RestController
@RequestMapping("/label")
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService labelService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/findAll")
    public Result findAll(){
        //获取头信息
        String header = request.getHeader("Authorization");
        System.out.println(header);
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

/*    @GetMapping
    public Result findByIdo(@RequestParam String labelId){
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(labelId));
    }*/

    @GetMapping("/{labelId}")
    public Result findByIdt(@PathVariable("labelId") String labelId){
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(labelId));
    }

    @PostMapping
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping("/{labelId}")
    public Result update(@PathVariable("labelId") String labelId, @RequestBody Label label){
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{labelId}")
    public Result del(@PathVariable("labelId") String labelId){
        labelService.del(labelId);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    @PostMapping("/search")
    public Result findSearch(@RequestBody Label label){
        return new Result(true, StatusCode.OK,"查询成功" ,labelService.findSearch(label));
    }

    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label,
                            @PathVariable("page") Integer page,
                            @PathVariable("size") Integer size){
        Page<Label> pageDate = labelService.pageQuery(page, size, label);
        return new Result(true, StatusCode.OK,"查询成功" ,
                new PageResult<Label>(pageDate.getTotalElements(), pageDate.getContent()));
    }


}
