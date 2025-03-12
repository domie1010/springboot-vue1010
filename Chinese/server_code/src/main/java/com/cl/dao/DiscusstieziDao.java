package com.cl.dao;

import com.cl.entity.DiscusstieziEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusstieziView;


/**
 * 帖子评论表
 * 
 * @author 
 * @email 
 * @date 2024-04-16 11:22:10
 */
public interface DiscusstieziDao extends BaseMapper<DiscusstieziEntity> {
	
	List<DiscusstieziView> selectListView(@Param("ew") Wrapper<DiscusstieziEntity> wrapper);

	List<DiscusstieziView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusstieziEntity> wrapper);
	
	DiscusstieziView selectView(@Param("ew") Wrapper<DiscusstieziEntity> wrapper);
	

}
