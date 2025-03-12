package com.cl.dao;

import com.cl.entity.NeirongfenleiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.NeirongfenleiView;


/**
 * 内容分类
 * 
 * @author 
 * @email 
 * @date 2024-04-16 11:22:10
 */
public interface NeirongfenleiDao extends BaseMapper<NeirongfenleiEntity> {
	
	List<NeirongfenleiView> selectListView(@Param("ew") Wrapper<NeirongfenleiEntity> wrapper);

	List<NeirongfenleiView> selectListView(Pagination page,@Param("ew") Wrapper<NeirongfenleiEntity> wrapper);
	
	NeirongfenleiView selectView(@Param("ew") Wrapper<NeirongfenleiEntity> wrapper);
	

}
