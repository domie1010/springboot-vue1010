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

import com.cl.entity.JubaoxinxiEntity;
import com.cl.entity.view.JubaoxinxiView;

import com.cl.service.JubaoxinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 举报信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-16 11:22:09
 */
@RestController
@RequestMapping("/jubaoxinxi")
public class JubaoxinxiController {
    @Autowired
    private JubaoxinxiService jubaoxinxiService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JubaoxinxiEntity jubaoxinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			jubaoxinxi.setJubaorenzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<JubaoxinxiEntity> ew = new EntityWrapper<JubaoxinxiEntity>();

		PageUtils page = jubaoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jubaoxinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JubaoxinxiEntity jubaoxinxi, 
		HttpServletRequest request){
        EntityWrapper<JubaoxinxiEntity> ew = new EntityWrapper<JubaoxinxiEntity>();

		PageUtils page = jubaoxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jubaoxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JubaoxinxiEntity jubaoxinxi){
       	EntityWrapper<JubaoxinxiEntity> ew = new EntityWrapper<JubaoxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jubaoxinxi, "jubaoxinxi")); 
        return R.ok().put("data", jubaoxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JubaoxinxiEntity jubaoxinxi){
        EntityWrapper< JubaoxinxiEntity> ew = new EntityWrapper< JubaoxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jubaoxinxi, "jubaoxinxi")); 
		JubaoxinxiView jubaoxinxiView =  jubaoxinxiService.selectView(ew);
		return R.ok("查询举报信息成功").put("data", jubaoxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JubaoxinxiEntity jubaoxinxi = jubaoxinxiService.selectById(id);
		jubaoxinxi = jubaoxinxiService.selectView(new EntityWrapper<JubaoxinxiEntity>().eq("id", id));
        return R.ok().put("data", jubaoxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JubaoxinxiEntity jubaoxinxi = jubaoxinxiService.selectById(id);
		jubaoxinxi = jubaoxinxiService.selectView(new EntityWrapper<JubaoxinxiEntity>().eq("id", id));
        return R.ok().put("data", jubaoxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JubaoxinxiEntity jubaoxinxi, HttpServletRequest request){
    	jubaoxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jubaoxinxi);
        jubaoxinxiService.insert(jubaoxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JubaoxinxiEntity jubaoxinxi, HttpServletRequest request){
    	jubaoxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jubaoxinxi);
        jubaoxinxiService.insert(jubaoxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JubaoxinxiEntity jubaoxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jubaoxinxi);
        jubaoxinxiService.updateById(jubaoxinxi);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<JubaoxinxiEntity> list = new ArrayList<JubaoxinxiEntity>();
        for(Long id : ids) {
            JubaoxinxiEntity jubaoxinxi = jubaoxinxiService.selectById(id);
            jubaoxinxi.setSfsh(sfsh);
            jubaoxinxi.setShhf(shhf);
            list.add(jubaoxinxi);
        }
        jubaoxinxiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jubaoxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	






    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,JubaoxinxiEntity jubaoxinxi, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("yonghu")) {
            jubaoxinxi.setJubaorenzhanghao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<JubaoxinxiEntity> ew = new EntityWrapper<JubaoxinxiEntity>();
        int count = jubaoxinxiService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jubaoxinxi), params), params));
        return R.ok().put("data", count);
    }


}
