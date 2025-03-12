package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JubaoxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JubaoxinxiView;


/**
 * 举报信息
 *
 * @author 
 * @email 
 * @date 2024-04-16 11:22:09
 */
public interface JubaoxinxiService extends IService<JubaoxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JubaoxinxiView> selectListView(Wrapper<JubaoxinxiEntity> wrapper);
   	
   	JubaoxinxiView selectView(@Param("ew") Wrapper<JubaoxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JubaoxinxiEntity> wrapper);
   	

}

