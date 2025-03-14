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

import com.cl.entity.YonghufankuiEntity;
import com.cl.entity.view.YonghufankuiView;

import com.cl.service.YonghufankuiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 用户反馈
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-16 11:22:10
 */
@RestController
@RequestMapping("/yonghufankui")
public class YonghufankuiController {
    @Autowired
    private YonghufankuiService yonghufankuiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YonghufankuiEntity yonghufankui,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			yonghufankui.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<YonghufankuiEntity> ew = new EntityWrapper<YonghufankuiEntity>();

		PageUtils page = yonghufankuiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonghufankui), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YonghufankuiEntity yonghufankui, 
		HttpServletRequest request){
        EntityWrapper<YonghufankuiEntity> ew = new EntityWrapper<YonghufankuiEntity>();

		PageUtils page = yonghufankuiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonghufankui), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YonghufankuiEntity yonghufankui){
       	EntityWrapper<YonghufankuiEntity> ew = new EntityWrapper<YonghufankuiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yonghufankui, "yonghufankui")); 
        return R.ok().put("data", yonghufankuiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YonghufankuiEntity yonghufankui){
        EntityWrapper< YonghufankuiEntity> ew = new EntityWrapper< YonghufankuiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yonghufankui, "yonghufankui")); 
		YonghufankuiView yonghufankuiView =  yonghufankuiService.selectView(ew);
		return R.ok("查询用户反馈成功").put("data", yonghufankuiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YonghufankuiEntity yonghufankui = yonghufankuiService.selectById(id);
		yonghufankui = yonghufankuiService.selectView(new EntityWrapper<YonghufankuiEntity>().eq("id", id));
        return R.ok().put("data", yonghufankui);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YonghufankuiEntity yonghufankui = yonghufankuiService.selectById(id);
		yonghufankui = yonghufankuiService.selectView(new EntityWrapper<YonghufankuiEntity>().eq("id", id));
        return R.ok().put("data", yonghufankui);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YonghufankuiEntity yonghufankui, HttpServletRequest request){
    	yonghufankui.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yonghufankui);
        yonghufankuiService.insert(yonghufankui);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YonghufankuiEntity yonghufankui, HttpServletRequest request){
    	yonghufankui.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(yonghufankui);
        yonghufankuiService.insert(yonghufankui);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YonghufankuiEntity yonghufankui, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yonghufankui);
        yonghufankuiService.updateById(yonghufankui);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yonghufankuiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	






    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,YonghufankuiEntity yonghufankui, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            yonghufankui.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<YonghufankuiEntity> ew = new EntityWrapper<YonghufankuiEntity>();
        int count = yonghufankuiService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yonghufankui), params), params));
        return R.ok().put("data", count);
    }


}
