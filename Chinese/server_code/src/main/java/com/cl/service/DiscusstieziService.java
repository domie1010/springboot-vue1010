package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscusstieziEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusstieziView;


/**
 * 帖子评论表
 *
 * @author 
 * @email 
 * @date 2024-04-16 11:22:10
 */
public interface DiscusstieziService extends IService<DiscusstieziEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusstieziView> selectListView(Wrapper<DiscusstieziEntity> wrapper);
   	
   	DiscusstieziView selectView(@Param("ew") Wrapper<DiscusstieziEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusstieziEntity> wrapper);
   	

}

