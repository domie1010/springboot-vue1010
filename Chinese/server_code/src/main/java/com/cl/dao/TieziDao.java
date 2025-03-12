package com.cl.dao;

import com.cl.entity.TieziEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.TieziView;


/**
 * 帖子
 * 
 * @author 
 * @email 
 * @date 2024-04-16 11:22:09
 */
public interface TieziDao extends BaseMapper<TieziEntity> {
	
	List<TieziView> selectListView(@Param("ew") Wrapper<TieziEntity> wrapper);

	List<TieziView> selectListView(Pagination page,@Param("ew") Wrapper<TieziEntity> wrapper);
	
	TieziView selectView(@Param("ew") Wrapper<TieziEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<TieziEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<TieziEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<TieziEntity> wrapper);



}
