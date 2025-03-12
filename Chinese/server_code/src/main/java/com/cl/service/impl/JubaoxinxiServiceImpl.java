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


import com.cl.dao.JubaoxinxiDao;
import com.cl.entity.JubaoxinxiEntity;
import com.cl.service.JubaoxinxiService;
import com.cl.entity.view.JubaoxinxiView;

@Service("jubaoxinxiService")
public class JubaoxinxiServiceImpl extends ServiceImpl<JubaoxinxiDao, JubaoxinxiEntity> implements JubaoxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JubaoxinxiEntity> page = this.selectPage(
                new Query<JubaoxinxiEntity>(params).getPage(),
                new EntityWrapper<JubaoxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JubaoxinxiEntity> wrapper) {
		  Page<JubaoxinxiView> page =new Query<JubaoxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<JubaoxinxiView> selectListView(Wrapper<JubaoxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JubaoxinxiView selectView(Wrapper<JubaoxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
