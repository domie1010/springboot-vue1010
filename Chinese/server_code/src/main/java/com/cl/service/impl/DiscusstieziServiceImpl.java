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


import com.cl.dao.DiscusstieziDao;
import com.cl.entity.DiscusstieziEntity;
import com.cl.service.DiscusstieziService;
import com.cl.entity.view.DiscusstieziView;

@Service("discusstieziService")
public class DiscusstieziServiceImpl extends ServiceImpl<DiscusstieziDao, DiscusstieziEntity> implements DiscusstieziService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusstieziEntity> page = this.selectPage(
                new Query<DiscusstieziEntity>(params).getPage(),
                new EntityWrapper<DiscusstieziEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusstieziEntity> wrapper) {
		  Page<DiscusstieziView> page =new Query<DiscusstieziView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<DiscusstieziView> selectListView(Wrapper<DiscusstieziEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusstieziView selectView(Wrapper<DiscusstieziEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
