package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.NeirongfenleiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.NeirongfenleiView;


/**
 * 内容分类
 *
 * @author 
 * @email 
 * @date 2024-04-16 11:22:10
 */
public interface NeirongfenleiService extends IService<NeirongfenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<NeirongfenleiView> selectListView(Wrapper<NeirongfenleiEntity> wrapper);
   	
   	NeirongfenleiView selectView(@Param("ew") Wrapper<NeirongfenleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<NeirongfenleiEntity> wrapper);
   	

}

