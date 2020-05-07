package com.dwc.tranmanagement.service;

import com.dwc.tranmanagement.entity.Position;
import com.dwc.tranmanagement.entity.Transrecord;
import java.util.List;

/**
 * (Transrecord)表服务接口
 *
 * @author makejava
 * @since 2020-04-29 09:27:15
 */
public interface TransrecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param transactionid 主键
     * @return 实例对象
     */
    Transrecord queryById(Integer transactionid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Transrecord> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param transrecord 实例对象
     * @return 实例对象
     */
    Transrecord insert(Transrecord transrecord);

    /**
     * 修改数据
     *
     * @param transrecord 实例对象
     * @return 实例对象
     */
    Transrecord update(Transrecord transrecord);

    /**
     * 通过主键删除数据
     *
     * @param transactionid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer transactionid);

    Position deal(Transrecord transrecord);

    Position cancel(Transrecord transrecord);

    Transrecord newest(Integer tradeid);

    List<Transrecord> findAllByTradeId(Integer tradeid);
}