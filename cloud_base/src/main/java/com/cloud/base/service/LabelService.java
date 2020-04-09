package com.cloud.base.service;

import com.cloud.base.dao.LabelDao;
import com.cloud.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zbl
 * @Date: 11:14 2019/10/2
 * @Description:
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    public void save(Label label){
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void del(String id){
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /**
             * @param root 根对象, 也就是要把条件封装到哪个对象中, where 类名= label.getId
             * @param query  封装的都是关键字查询, 比如group by order by 等
             * @param cb 用来封装条件对象的
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new 一个list集合, 来存放所有的条件
                List<Predicate> list = new ArrayList<>();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())){
                    Predicate p = cb.like(root.get("labelname").as(String.class),
                            "%" + label.getLabelname() + "%");// where labelname like %小明%
                    list.add(p);
                }
                if (label.getState() != null && !"".equals(label.getState())){
                    Predicate p = cb.equal(root.get("state").as(String.class),
                            label.getState() );// where state =
                    list.add(p);
                }
                //new 一个数组作为最终返回值的条件
                Predicate[] parr = new Predicate[list.size()];
                //把list直接转成数组 并存入数组中
                list.toArray(parr);
                return cb.and(parr);
            }
        });
    }

    public Page<Label> pageQuery(Integer page, Integer size, Label label) {
        Pageable pageable = PageRequest.of(page -1 , size);
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //new 一个list集合, 来存放所有的条件
                List<Predicate> list = new ArrayList<>();
                if (label.getLabelname() != null && !"".equals(label.getLabelname())){
                    Predicate p = cb.like(root.get("labelname").as(String.class),
                            "%" + label.getLabelname() + "%");// where labelname like %小明%
                    list.add(p);
                }

                //new 一个数组作为最终返回值的条件
                Predicate[] parr = new Predicate[list.size()];
                //把list直接转成数组 并存入数组中
                list.toArray(parr);
                return cb.and(parr);
            }
        }, pageable);
    }
}
