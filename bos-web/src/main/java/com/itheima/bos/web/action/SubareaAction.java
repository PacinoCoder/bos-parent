package com.itheima.bos.web.action;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Subarea;
import com.itheima.bos.service.ISubareaService;
import com.itheima.bos.utils.FileUtils;
import com.itheima.bos.web.action.base.BaseAction;
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	//注入service
	@Autowired
	private ISubareaService subareaSerivce;
//=============================方法区=======================>>>>>>>>>>
	/**
	 * 保存分区
	 */
	public String save() throws Exception {
		subareaSerivce.save(model);
		return LIST;
	}
	/**
	 * 分页查询(带过滤条件)
	 */
	public String pageQuery() throws Exception{
		/*
		 * 对离线查询对象完成封装
		 * 	1.获取到pageBean中的离线查询对象
		 * 	2.对前台传过来的参数进行判断,如果不为空,则添加查询条件
		 */
		DetachedCriteria dc = pageBean.getDetachedCriteria();
		//得到4个前台传过来的查询条件参数
		String addresskey = model.getAddresskey();
		//进行非空判断,避免空指针异常
		String province = null;
		String city = null;
		String district = null;
		if (model.getRegion()!=null) {
			province = model.getRegion().getProvince();
			city = model.getRegion().getCity();
			district = model.getRegion().getDistrict();
		}
		//判断并添加条件
		//关键字,为单表属性.直接添加查询
		if (StringUtils.isNotBlank(addresskey)) {
			dc.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
		}
		/*
		 * province,city,district这三个属性涉及到另一张表,即关联查询.使用为关联的属性起别名的方式实现
		 * 	参数1:分区对象中关联的区域对象属性名称
		 *  参数2:别名，可以任意
		 */
		dc.createAlias("region", "r");
		//动态添加省市区的模糊查询条件
		if (StringUtils.isNotBlank(province)) {
			dc.add(Restrictions.like("r.province", "%"+province+"%"));
		}
		if (StringUtils.isNotBlank(city)) {
			dc.add(Restrictions.like("r.city", "%"+city+"%"));
		}
		if (StringUtils.isNotBlank(district)) {
			dc.add(Restrictions.like("r.district", "%"+district+"%"));
		}
		//调用service方法完成对pageBean的封装
		subareaSerivce.pageQuery(pageBean);
		//把pageBean转化成json回写给页面
		this.Bean2Json(pageBean, new String[]{"currentPage","pageSize","detachedCriteria","decidedzone","subareas"});
		return NONE;
	}
	
	/**
	 * 分区导出
	 */
	public String exportSubarea() throws Exception{
		/*
		 * 将分区信息导出到Excel步骤
		 * 	1.去数据库查询出所有的分区
		 * 	2.通过POI把数据写到Excel
		 * 	3.使用输出流下载文件
		 */
		List<Subarea> list =  subareaSerivce.findAll();
		
		//在内存中创建一个Excel
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个标签页
		HSSFSheet sheet = workbook.createSheet("分区数据");
		//在这个标签页中创建单元格,第一行为标题
		HSSFRow titleRow = sheet.createRow(0);
		//为第一行中的单元格添加数据
		titleRow.createCell(0).setCellValue("分区编号");
		titleRow.createCell(1).setCellValue("省市区");
		titleRow.createCell(2).setCellValue("街道信息");
		titleRow.createCell(3).setCellValue("起始编号");
		titleRow.createCell(4).setCellValue("结束编号");
		titleRow.createCell(5).setCellValue("关键字");
		//循环为单元格添加数据
		for (Subarea subarea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getRegion().getName());
			dataRow.createCell(2).setCellValue(subarea.getPosition());
			dataRow.createCell(3).setCellValue(subarea.getStartnum());
			dataRow.createCell(4).setCellValue(subarea.getEndnum());
			dataRow.createCell(5).setCellValue(subarea.getAddresskey());
		}
		
		//提供文件下载:一个流,两个头
		//设置输出流
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		//设置两个消息头:contentType 和 content-disposition(attachment,filename)
		//动态获取下载文件的MIME类型
		String filename="分区数据.xls";
		String type = ServletActionContext.getServletContext().getMimeType(filename);
		//设置contentType
		ServletActionContext.getResponse().setContentType(type);
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		//获取客户端类型,用于通过工具类对文件名的处理...解决中文不能显示的问题
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		//设置content-disposition(attachment,filename)信息头
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
		//设置完成后,向页面写出流数据
		workbook.write(out);
		return NONE;
	}
	/**
	 * 查询所有没关联定区的分区
	 */
	public String listajax(){
		//得到数据集合 
		List<Subarea> list = subareaSerivce.findSubareaNotAssociate();
		//转为json
		this.List2Json(list, new String[]{"decidedzone","region","startnum","endnum","single"});
		return NONE;
	}
}
