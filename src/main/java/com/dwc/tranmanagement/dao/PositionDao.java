package com.dwc.tranmanagement.dao;

import com.dwc.tranmanagement.entity.Position;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * (Position)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-29 09:27:41
 */
public interface PositionDao extends Mapper<Position> {

    /**
     * 通过ID查询单条数据
     *
     * @param tradeid 主键
     * @return 实例对象
     */
    Position queryById(Integer tradeid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Position> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param position 实例对象
     * @return 对象列表
     */
    List<Position> queryAll(Position position);

    /**
     * 新增数据
     *
     * @param position 实例对象
     * @return 影响行数
     */
    int insert(Position position);

    /**
     * 修改数据
     *
     * @param position 实例对象
     * @return 影响行数
     */
    int update(Position position);

    /**
     * 通过主键删除数据
     *
     * @param tradeid 主键
     * @return 影响行数
     */
    int deleteById(Integer tradeid);

}