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


import com.cl.dao.TieziDao;
import com.cl.entity.TieziEntity;
import com.cl.service.TieziService;
import com.cl.entity.view.TieziView;

@Service("tieziService")
public class TieziServiceImpl extends ServiceImpl<TieziDao, TieziEntity> implements TieziService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TieziEntity> page = this.selectPage(
                new Query<TieziEntity>(params).getPage(),
                new EntityWrapper<TieziEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<TieziEntity> wrapper) {
		  Page<TieziView> page =new Query<TieziView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<TieziView> selectListView(Wrapper<TieziEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public TieziView selectView(Wrapper<TieziEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<TieziEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<TieziEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<TieziEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
