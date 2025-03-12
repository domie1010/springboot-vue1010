package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.TieziEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.TieziView;


/**
 * 帖子
 *
 * @author 
 * @email 
 * @date 2024-04-16 11:22:09
 */
public interface TieziService extends IService<TieziEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<TieziView> selectListView(Wrapper<TieziEntity> wrapper);
   	
   	TieziView selectView(@Param("ew") Wrapper<TieziEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TieziEntity> wrapper);
   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<TieziEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<TieziEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<TieziEntity> wrapper);



}

