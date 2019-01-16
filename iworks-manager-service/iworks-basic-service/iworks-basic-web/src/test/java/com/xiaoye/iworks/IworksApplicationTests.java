package com.xiaoye.iworks;

import com.alibaba.fastjson.JSON;
import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.PageResponse;
import com.xiaoye.iworks.basic.api.AppConstantService;
import com.xiaoye.iworks.basic.api.dto.AppConstantDto;
import com.xiaoye.iworks.basic.api.input.AppConstantQueryInput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IworksApplicationTests {

	@Autowired
	private AppConstantService service;

	@Test
	public void insert() {
		AppConstantDto appConstantDto = new AppConstantDto();
		appConstantDto.setConstantKey("SM_CHANNEL_CODE");
		appConstantDto.setConstantValue("12345678");
		appConstantDto.setConstantDesc("世明渠道号");
		appConstantDto.setCreateBy("yehl");
		appConstantDto.setModifyBy("yehl");
		DataResponse<Long> response = service.insertAppConstant(appConstantDto);
		System.out.println(JSON.toJSONString(response));
	}

	@Test
	public void update() {
		AppConstantDto appConstantDto = new AppConstantDto();
		appConstantDto.setPkid(2L);
		appConstantDto.setConstantKey("SM_CHANNEL_CODE");
		appConstantDto.setConstantValue("326202020");
		appConstantDto.setConstantDesc("世明渠道号");
		appConstantDto.setCreateBy("chen");
		appConstantDto.setModifyBy("chen");
		DataResponse<Integer> response = service.updateAppConstant(appConstantDto);
		System.out.println(JSON.toJSONString(response));
	}

	@Test
	public void delete() {
		AppConstantQueryInput queryInput = new AppConstantQueryInput();
		queryInput.setPkid(1L);
		DataResponse<Integer> response = service.deleteAppConstant(queryInput);
		System.out.println(JSON.toJSONString(response));
	}

	@Test
	public void list() {
		AppConstantQueryInput queryInput = new AppConstantQueryInput();
		queryInput.setConstantKey("SM");
		PageResponse<AppConstantDto> response = service.listAppConstant(queryInput);
		System.out.println(JSON.toJSONString(response));
	}

	@Test
	public void listByPage() {
		AppConstantQueryInput queryInput = new AppConstantQueryInput();
		queryInput.setPagenation(true);
		queryInput.setOffset(0);
		queryInput.setLimit(20);
		PageResponse<AppConstantDto> response = service.listAppConstant(queryInput);
		System.out.println(JSON.toJSONString(response));
	}
}

