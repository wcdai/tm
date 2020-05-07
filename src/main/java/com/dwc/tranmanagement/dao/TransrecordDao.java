package com.dwc.tranmanagement.dao;

import com.dwc.tranmanagement.entity.Transrecord;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * (Transrecord)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-29 09:27:15
 */
public interface TransrecordDao extends Mapper<Transrecord> {

    /**
     * 通过ID查询单条数据
     *
     * @param transactionid 主键
     * @return 实例对象
     */
    Transrecord queryById(Integer transactionid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Transrecord> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param transrecord 实例对象
     * @return 对象列表
     */
    List<Transrecord> queryAll(Transrecord transrecord);

    /**
     * 新增数据
     *
     * @param transrecord 实例对象
     * @return 影响行数
     */
    int insert(Transrecord transrecord);

    /**
     * 修改数据
     *
     * @param transrecord 实例对象
     * @return 影响行数
     */
    int update(Transrecord transrecord);

    /**
     * 通过主键删除数据
     *
     * @param transactionid 主键
     * @return 影响行数
     */
    int deleteById(Integer transactionid);

    Integer findMaxVersion(Integer transrecord);



}