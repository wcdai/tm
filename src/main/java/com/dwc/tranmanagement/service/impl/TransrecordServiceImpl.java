package com.dwc.tranmanagement.service.impl;

import com.dwc.tranmanagement.dao.PositionDao;
import com.dwc.tranmanagement.entity.Position;
import com.dwc.tranmanagement.entity.Transrecord;
import com.dwc.tranmanagement.dao.TransrecordDao;
import com.dwc.tranmanagement.service.TransrecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Transrecord)表服务实现类
 *
 * @author makejava
 * @since 2020-04-29 09:27:16
 */
@Service("transrecordService")
public class TransrecordServiceImpl implements TransrecordService {
    @Resource
    private TransrecordDao transrecordDao;

    @Resource
    private PositionDao positionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param transactionid 主键
     * @return 实例对象
     */
    @Override
    public Transrecord queryById(Integer transactionid) {
        return this.transrecordDao.queryById(transactionid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Transrecord> queryAllByLimit(int offset, int limit) {
        return this.transrecordDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param transrecord 实例对象
     * @return 实例对象
     */
    @Override
    public Transrecord insert(Transrecord transrecord) {
        this.transrecordDao.insert(transrecord);
        return transrecord;
    }

    /**
     * 修改数据
     *
     * @param transrecord 实例对象
     * @return 实例对象
     */
    @Override
    public Transrecord update(Transrecord transrecord) {
        this.transrecordDao.update(transrecord);
        return this.queryById(transrecord.getTransactionid());
    }

    /**
     * 通过主键删除数据
     *
     * @param transactionid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer transactionid) {
        return this.transrecordDao.deleteById(transactionid) > 0;
    }


    @Override
    @Transactional
    public Position deal(Transrecord transrecord) {
        Integer maxVersion = transrecordDao.findMaxVersion(transrecord.getTradeid());
        if (null == maxVersion) {
            transrecord.setOperation("INSERT");
            transrecord.setVersion(1);
        }else {
            transrecord.setOperation("UPDATE");
            transrecord.setVersion(maxVersion + 1);
        }
        this.transrecordDao.insert(transrecord);
        Position position = this.positionDao.queryById(transrecord.getTradeid());
        if ("Buy".equals(transrecord.getDeal())) {
            switch (transrecord.getSecuritycode()) {
                case "REL":
                    position.setRel(position.getRel()+transrecord.getQuantity());
                    break;
                case "ITC":
                    position.setItc(position.getItc()+transrecord.getQuantity());
                    break;
                case "INF":
                    position.setInf(position.getInf()+transrecord.getQuantity());
                    break;
            }
        }
        if ("Sell".equals(transrecord.getDeal())) {
            switch (transrecord.getSecuritycode()) {
                case "REL":
                    position.setRel(position.getRel()-transrecord.getQuantity());
                    break;
                case "ITC":
                    position.setItc(position.getItc()-transrecord.getQuantity());
                    break;
                case "INF":
                    position.setInf(position.getInf()-transrecord.getQuantity());
                    break;
            }
        }
        this.positionDao.update(position);
        return position;
    }

    @Override
    public Position cancel(Transrecord transrecord) {
        Integer maxVersion = transrecordDao.findMaxVersion(transrecord.getTradeid());
        transrecord.setVersion(maxVersion + 1);
        this.transrecordDao.insert(transrecord);
        Position position = this.positionDao.queryById(transrecord.getTradeid());
        return position;
    }

    @Override
    public Transrecord newest(Integer tradeid) {
        Integer maxVersion = transrecordDao.findMaxVersion(tradeid);
        Example example = new Example(Transrecord.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("tradeid", tradeid);
        criteria.andEqualTo("version", maxVersion);
        List<Transrecord> transrecords = transrecordDao.selectByExample(example);
        return transrecords.get(0);
    }

    @Override
    public List<Transrecord> findAllByTradeId(Integer tradeid) {
        Example example = new Example(Transrecord.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("tradeid", tradeid);
        example.setOrderByClause("version");
        List<Transrecord> transrecordList = transrecordDao.selectByExample(example);
        return transrecordList;
    }
}