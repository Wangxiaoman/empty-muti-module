package com.three.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.three.dao.GoodsDao;
import com.three.domain.Goods;

@Service
public class GoodsServiceImpl implements GoodsService{
	@Resource
	private GoodsDao goodsDao;
	
	
	public List<Goods> getList(){
		return goodsDao.getList();
	}
}
