package com.xiaoye.iworks;

import com.alibaba.fastjson.JSON;
import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.PageResponse;
import com.xiaoye.iworks.basic.api.DictConstantService;
import com.xiaoye.iworks.basic.api.constant.DictConstant;
import com.xiaoye.iworks.basic.api.dto.DictConstantDataDto;
import com.xiaoye.iworks.basic.api.dto.DictConstantDto;
import com.xiaoye.iworks.basic.api.input.DictConstantQueryInput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IworksApplicationTests {

	@Autowired
	private DictConstantService service;

	@Test
	public void insert() {
		DictConstantDto dto = new DictConstantDto();
		dto.setDictCode("USER_SEX");
		dto.setDictName("用户性别");
		dto.setDictDesc("用户性别");
		dto.setCreateBy("yehl");
		dto.setModifyBy("yehl");
		DataResponse<Long> response = service.insertDictConstant(dto);
		System.out.println(JSON.toJSONString(response));
	}

	@Test
	public void insertData() {
		DictConstantDataDto dto = new DictConstantDataDto();
		dto.setDictCode("USER_SEX");
		dto.setDictKey("1");
		dto.setDictValue("男");
		dto.setDictDesc("男性");
		dto.setCreateBy("yehl");
		dto.setModifyBy("yehl");
		DataResponse<Long> response = service.insertDictConstantData(dto);
		dto.setDictKey("2");
		dto.setDictValue("女");
		dto.setDictDesc("女性");
		response = service.insertDictConstantData(dto);
		System.out.println(JSON.toJSONString(response));
	}

	@Test
	public void update() {
	}

	@Test
	public void delete() {
	}

	@Test
	public void list() {
		DictConstantQueryInput input = new DictConstantQueryInput();
		input.setListDictData(DictConstant.LIST_DICT_DATA);
		PageResponse<DictConstantDto> response = service.listDictConstants(input);
		System.out.println(JSON.toJSONString(response));
	}

	@Test
	public void listByPage() {
	}
}

