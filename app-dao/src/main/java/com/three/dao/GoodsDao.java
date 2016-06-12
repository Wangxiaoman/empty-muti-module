package com.three.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.three.domain.Goods;

@Repository
public interface GoodsDao {
	@Select("select * from goods")
	public List<Goods> getList();
}
