package com.itheima.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.Region;
import com.itheima.bos.service.IRegionService;
import com.itheima.bos.utils.PinYin4jUtils;
import com.itheima.bos.web.action.base.BaseAction;
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class RegionAction extends BaseAction<Region> {
	//注入RegionService
	@Autowired
	private IRegionService regionService;
	
	//属性驱动,接受上传的文件
	private File regionFile;
	public File getRegionFile() {
		return regionFile;
	}
	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}
	
	// 属性驱动,接收添加分区时候区域选择的过滤参数
	private String q;
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
//==================================方法区=====================>>>>>>>>>>>>
	/**
	 * 区域导入
	 * @throws Exception 
	 */
	public void importRegion() throws Exception{
		//使用poi封装一个excel文件对象
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));
		//获取第一个标签页sheet1的数据
		HSSFSheet sheet = workbook.getSheet("sheet1");
		//声明一个集合保存每个区域
		ArrayList<Region> arrayList = new ArrayList<Region>();
		//遍历标签页中的每行数据
		for (Row row : sheet) {
			//忽略第一行的标题
			int rowNum = row.getRowNum();
			if (rowNum == 0) {
				continue;
			}
			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			Region region = new Region(id, province, city, district, postcode, null, null, null);
			//对省市区进行截取,方便Pingyin4j操作
			province = province.substring(0,province.length()-1);
			city = city.substring(0,city.length()-1);
			district = district.substring(0,district.length()-1);
			//拼接字符串
			String info = province+city+district;
			//通过PinYin4J,获得首字母
			String[] headByString = PinYin4jUtils.getHeadByString(info);
			//获取区域简码
			String shortcode = StringUtils.join(headByString);
			//获取城市编码
			String citycode = PinYin4jUtils.hanziToPinyin(city, "");
			//赋值给对象
			region.setShortcode(shortcode);
			region.setCitycode(citycode);
			//保存到集合
			arrayList.add(region);
		}
		regionService.saveBatch(arrayList);
	}
	/**
	 * 分页查询所有区域
	 */
	public String pageQuery() throws Exception {
		//传递至业务层处理
		regionService.pageQuery(pageBean);
		Bean2Json(pageBean, new String[]{"currentPage","pageSize","detachedCriteria","subareas"});
		return NONE;
	}
	/**
	 * 通过ajax查询所有区域
	 */
	public String listajax() throws Exception {
		List<Region> list = null;
		if (StringUtils.isNotBlank(q)) {
			//传递了过滤的参数
			list = regionService.findListByQ(q);
		}else{
			//没有参数过滤参数
			list = regionService.findAll();
		}
		List2Json(list, new String[]{"subareas"});
		return NONE;
	}
	
}
