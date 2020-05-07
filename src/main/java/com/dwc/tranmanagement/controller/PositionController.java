package com.dwc.tranmanagement.controller;


import com.dwc.tranmanagement.entity.Position;
import com.dwc.tranmanagement.entity.Result;
import com.dwc.tranmanagement.entity.StatusCode;
import com.dwc.tranmanagement.service.PositionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Position)表控制层
 *
 * @author makejava
 * @since 2020-04-29 09:27:41
 */
@RestController
@RequestMapping("position")
public class PositionController {
    /**
     * 服务对象
     */
    @Resource
    private PositionService positionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Result<Position> selectOne(Integer id) {
        Position position = this.positionService.queryById(id);
        return new Result<Position>(true, StatusCode.OK,"查询成功",position);
    }

    @PostMapping("openAccount")
    public Result<Position> openAccount(@RequestBody Position position){
        position.setInf(0);
        position.setItc(0);
        position.setRel(0);
        Position insert = this.positionService.insert(position);
        insert.setUsername("");
        insert.setPassword("");
        return new Result<Position>(true,StatusCode.OK,"开户成功",insert);
    }

    @GetMapping("findAll")
    public Result<Position> findAll(Integer tradeid){
        Position position = positionService.queryById(tradeid);
        position.setUsername("");
        position.setPassword("");
        return new Result<Position>(true,StatusCode.OK,"查询成功",position);
    }


}