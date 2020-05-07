package com.dwc.tranmanagement.controller;

import com.dwc.tranmanagement.entity.Position;
import com.dwc.tranmanagement.entity.Result;
import com.dwc.tranmanagement.entity.StatusCode;
import com.dwc.tranmanagement.entity.Transrecord;
import com.dwc.tranmanagement.service.TransrecordService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Transrecord)表控制层
 *
 * @author makejava
 * @since 2020-04-29 09:27:16
 */
@RestController
@RequestMapping("transrecord")
public class TransrecordController {
    /**
     * 服务对象
     */
    @Resource
    private TransrecordService transrecordService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Result<Transrecord> selectOne(Integer id) {
        Transrecord transrecord = this.transrecordService.queryById(id);
        return new Result<Transrecord>(true, StatusCode.OK,"查询成功",transrecord);
    }

    /**
     * 交易员进行股票交易
     *  ①生成交易记录，状态为inster/update,操作类型为buy/sell
     *  ②同步更新股票寸头
     * @param transrecord
     * @return
     */
    @PostMapping(path = "deal",produces = "application/json;charset=utf-8")
    public Result<Position> deal(@RequestBody Transrecord transrecord) {
        Position deal = transrecordService.deal(transrecord);
        return new Result<Position>(true,StatusCode.OK,"交易成功",deal);
    }

    /**
     * 交易员进行交易记录删除，状态为cancel
     * @param transrecord
     * @return
     */
    @PostMapping("cancel")
    public Result<Position> cancel(@RequestBody Transrecord transrecord) {
        if (!"CANCEL".equals(transrecord.getOperation())) {
            return new Result<>(false, StatusCode.ERROR, "参数校验失败");
        }
        Position deal = transrecordService.cancel(transrecord);
        return new Result<Position>(true,StatusCode.OK,"交易成功",deal);
    }

    @GetMapping("newest")
    public Result<Transrecord> newest(Integer tradeid) {
        if (StringUtils.isEmpty(tradeid)) {
            return new Result<>(false, StatusCode.ERROR, "参数校验失败");
        }
        Transrecord transrecord = transrecordService.newest(tradeid);
        return new Result<Transrecord>(true,StatusCode.OK,"交易成功",transrecord);
    }

    @GetMapping("findAll")
    public Result<List<Transrecord>>findAll(Integer tradeid) {
        if (StringUtils.isEmpty(tradeid)) {
            return new Result<>(false, StatusCode.ERROR, "参数校验失败");
        }
        List<Transrecord> transrecordList = transrecordService.findAllByTradeId(tradeid);
        return new Result<List<Transrecord>>(true,StatusCode.OK,"交易成功",transrecordList);
    }

}