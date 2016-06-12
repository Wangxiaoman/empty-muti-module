package com.three.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.three.domain.Goods;
import com.three.service.GoodsService;

@Controller
public class GoodsController {
	@Resource
	private GoodsService goodsService;
	
	@RequestMapping("/test")
	@ResponseBody
	public List<Goods> getList(){
		return goodsService.getList();
	}
}
