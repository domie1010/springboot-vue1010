package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.NeirongfenleiDao;
import com.cl.entity.NeirongfenleiEntity;
import com.cl.service.NeirongfenleiService;
import com.cl.entity.view.NeirongfenleiView;

@Service("neirongfenleiService")
public class NeirongfenleiServiceImpl extends ServiceImpl<NeirongfenleiDao, NeirongfenleiEntity> implements NeirongfenleiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NeirongfenleiEntity> page = this.selectPage(
                new Query<NeirongfenleiEntity>(params).getPage(),
                new EntityWrapper<NeirongfenleiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<NeirongfenleiEntity> wrapper) {
		  Page<NeirongfenleiView> page =new Query<NeirongfenleiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<NeirongfenleiView> selectListView(Wrapper<NeirongfenleiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public NeirongfenleiView selectView(Wrapper<NeirongfenleiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
