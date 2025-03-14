package com.cl.entity.view;

import com.cl.entity.YonghufankuiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 用户反馈
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-04-16 11:22:10
 */
@TableName("yonghufankui")
public class YonghufankuiView  extends YonghufankuiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public YonghufankuiView(){
	}
 
 	public YonghufankuiView(YonghufankuiEntity yonghufankuiEntity){
 	try {
			BeanUtils.copyProperties(this, yonghufankuiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
