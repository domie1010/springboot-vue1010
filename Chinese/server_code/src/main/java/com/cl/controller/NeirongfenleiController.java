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

import com.cl.entity.NeirongfenleiEntity;
import com.cl.entity.view.NeirongfenleiView;

import com.cl.service.NeirongfenleiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 内容分类
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-16 11:22:10
 */
@RestController
@RequestMapping("/neirongfenlei")
public class NeirongfenleiController {
    @Autowired
    private NeirongfenleiService neirongfenleiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,NeirongfenleiEntity neirongfenlei,
		HttpServletRequest request){
        EntityWrapper<NeirongfenleiEntity> ew = new EntityWrapper<NeirongfenleiEntity>();

		PageUtils page = neirongfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, neirongfenlei), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,NeirongfenleiEntity neirongfenlei, 
		HttpServletRequest request){
        EntityWrapper<NeirongfenleiEntity> ew = new EntityWrapper<NeirongfenleiEntity>();

		PageUtils page = neirongfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, neirongfenlei), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( NeirongfenleiEntity neirongfenlei){
       	EntityWrapper<NeirongfenleiEntity> ew = new EntityWrapper<NeirongfenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( neirongfenlei, "neirongfenlei")); 
        return R.ok().put("data", neirongfenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(NeirongfenleiEntity neirongfenlei){
        EntityWrapper< NeirongfenleiEntity> ew = new EntityWrapper< NeirongfenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( neirongfenlei, "neirongfenlei")); 
		NeirongfenleiView neirongfenleiView =  neirongfenleiService.selectView(ew);
		return R.ok("查询内容分类成功").put("data", neirongfenleiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        NeirongfenleiEntity neirongfenlei = neirongfenleiService.selectById(id);
		neirongfenlei = neirongfenleiService.selectView(new EntityWrapper<NeirongfenleiEntity>().eq("id", id));
        return R.ok().put("data", neirongfenlei);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        NeirongfenleiEntity neirongfenlei = neirongfenleiService.selectById(id);
		neirongfenlei = neirongfenleiService.selectView(new EntityWrapper<NeirongfenleiEntity>().eq("id", id));
        return R.ok().put("data", neirongfenlei);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody NeirongfenleiEntity neirongfenlei, HttpServletRequest request){
    	neirongfenlei.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(neirongfenlei);
        neirongfenleiService.insert(neirongfenlei);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody NeirongfenleiEntity neirongfenlei, HttpServletRequest request){
    	neirongfenlei.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(neirongfenlei);
        neirongfenleiService.insert(neirongfenlei);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody NeirongfenleiEntity neirongfenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(neirongfenlei);
        neirongfenleiService.updateById(neirongfenlei);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        neirongfenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
