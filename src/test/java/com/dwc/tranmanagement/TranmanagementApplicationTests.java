package com.dwc.tranmanagement;

import com.dwc.tranmanagement.controller.PositionController;
import com.dwc.tranmanagement.controller.TransrecordController;
import com.dwc.tranmanagement.dao.TransrecordDao;
import com.dwc.tranmanagement.entity.Position;
import com.dwc.tranmanagement.entity.Result;
import com.dwc.tranmanagement.entity.StatusCode;
import com.dwc.tranmanagement.entity.Transrecord;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class TranmanagementApplicationTests {

	@Autowired
	TransrecordController transrecordController;

	@Autowired
	PositionController positionController;

	@Autowired
	TransrecordDao transrecordDao;

	private static int tradeid =7;
	private static int testNum =7;



	/**
	 * (1)交易员开户操作
	 */
	@Test
	void openAccount() {
		Position position = new Position();
		position.setName("李四");
		Result<Position> result = positionController.openAccount(position);
		Integer code = result.getCode();
		Assert.assertEquals("成功", StatusCode.OK, code, 0);
		Assert.assertNotNull(result.getData().getTradeid());
	}

	/**
	 * (2)交易员进行股票交易
	 * ①生成交易记录，状态为inster/update,操作类型为buy/sell
	 * ②同步更新股票寸头
	 */
	@Test
	void deal() {
		Transrecord transrecord = new Transrecord();


		transrecord.setTradeid(tradeid);
		transrecord.setSecuritycode("REL");
		transrecord.setQuantity(testNum);
		transrecord.setDeal("Buy");
		Result<Position> positionResult = positionController.selectOne(tradeid);
		Integer rel = positionResult.getData().getRel();
		Result<Position> deal = transrecordController.deal(transrecord);
		Integer code = deal.getCode();
		Assert.assertEquals("成功", StatusCode.OK, code, 0);
		Assert.assertEquals("成功", rel + testNum, deal.getData().getRel(), 0);
	}

	/**
	 * (3)交易员进行交易记录删除，状态为cancel
	 */
	@Test
	void cancel() {
		Transrecord transrecord = new Transrecord();
		transrecord.setTradeid(tradeid);
		transrecord.setSecuritycode("REL");
		transrecord.setQuantity(testNum);
		transrecord.setDeal("Buy");
		transrecord.setOperation("CANCEL");
		Result<Position> positionResult = positionController.selectOne(tradeid);
		Integer maxVersion = transrecordDao.findMaxVersion(tradeid);
		Integer rel = positionResult.getData().getRel();
		Result<Position> result = transrecordController.cancel(transrecord);
		Result<Transrecord> newest = transrecordController.newest(tradeid);
		Integer code = result.getCode();
		Assert.assertEquals("成功", StatusCode.OK, code, 0);
		Assert.assertEquals("成功", rel, result.getData().getRel(), 0);
		Assert.assertTrue(newest.getData().getVersion() > maxVersion);
	}

	/**
	 * (4)交易员最新交易记录查询
	 */
	@Test
	void newest() {
		Result<Transrecord> newest = transrecordController.newest(tradeid);
		Integer code = newest.getCode();
		Assert.assertEquals("成功", StatusCode.OK, code, 0);
		Assert.assertNotNull(newest.getData());
	}

	/**
	 * (5)交易员所有交易记录查询
	 */
	@Test
	void findAll() {
		Result<List<Transrecord>> all = transrecordController.findAll(tradeid);
		Integer code = all.getCode();
		Assert.assertEquals("成功", StatusCode.OK, code, 0);
		Assert.assertNotNull(all.getData());
	}

	/**
	 * (6)交易员股票寸头查询
	 */
	@Test
	void posFindAll() {
		Result<Position> all = positionController.findAll(tradeid);
		Integer code = all.getCode();
		Assert.assertEquals("成功", StatusCode.OK, code, 0);
		Assert.assertNotNull(all.getData());
	}

}
