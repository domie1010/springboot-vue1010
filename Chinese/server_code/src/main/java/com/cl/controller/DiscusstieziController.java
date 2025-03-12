package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.DiscusstieziEntity;
import com.cl.entity.view.DiscusstieziView;

import com.cl.service.DiscusstieziService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 帖子评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-16 11:22:10
 */
@RestController
@RequestMapping("/discusstiezi")
public class DiscusstieziController {
    @Autowired
    private DiscusstieziService discusstieziService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscusstieziEntity discusstiezi,
		HttpServletRequest request){
        EntityWrapper<DiscusstieziEntity> ew = new EntityWrapper<DiscusstieziEntity>();

		PageUtils page = discusstieziService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusstiezi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscusstieziEntity discusstiezi, 
		HttpServletRequest request){
        EntityWrapper<DiscusstieziEntity> ew = new EntityWrapper<DiscusstieziEntity>();

		PageUtils page = discusstieziService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusstiezi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscusstieziEntity discusstiezi){
       	EntityWrapper<DiscusstieziEntity> ew = new EntityWrapper<DiscusstieziEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discusstiezi, "discusstiezi")); 
        return R.ok().put("data", discusstieziService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscusstieziEntity discusstiezi){
        EntityWrapper< DiscusstieziEntity> ew = new EntityWrapper< DiscusstieziEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discusstiezi, "discusstiezi")); 
		DiscusstieziView discusstieziView =  discusstieziService.selectView(ew);
		return R.ok("查询帖子评论表成功").put("data", discusstieziView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscusstieziEntity discusstiezi = discusstieziService.selectById(id);
		discusstiezi = discusstieziService.selectView(new EntityWrapper<DiscusstieziEntity>().eq("id", id));
        return R.ok().put("data", discusstiezi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscusstieziEntity discusstiezi = discusstieziService.selectById(id);
		discusstiezi = discusstieziService.selectView(new EntityWrapper<DiscusstieziEntity>().eq("id", id));
        return R.ok().put("data", discusstiezi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscusstieziEntity discusstiezi, HttpServletRequest request){
    	discusstiezi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discusstiezi);
        discusstieziService.insert(discusstiezi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscusstieziEntity discusstiezi, HttpServletRequest request){
    	discusstiezi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discusstiezi);
        discusstieziService.insert(discusstiezi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscusstieziEntity discusstiezi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discusstiezi);
        discusstieziService.updateById(discusstiezi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discusstieziService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscusstieziEntity discusstiezi, HttpServletRequest request,String pre){
        EntityWrapper<DiscusstieziEntity> ew = new EntityWrapper<DiscusstieziEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = discusstieziService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusstiezi), params), params));
        return R.ok().put("data", page);
    }








}
