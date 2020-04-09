package com.cloud.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cloud.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{

    public List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state);//where state = ? order by create time

    public List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state);//where state != ? order by create time desc
}
