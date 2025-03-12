package com.cl.dao;

import com.cl.entity.JubaoxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JubaoxinxiView;


/**
 * 举报信息
 * 
 * @author 
 * @email 
 * @date 2024-04-16 11:22:09
 */
public interface JubaoxinxiDao extends BaseMapper<JubaoxinxiEntity> {
	
	List<JubaoxinxiView> selectListView(@Param("ew") Wrapper<JubaoxinxiEntity> wrapper);

	List<JubaoxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<JubaoxinxiEntity> wrapper);
	
	JubaoxinxiView selectView(@Param("ew") Wrapper<JubaoxinxiEntity> wrapper);
	

}
