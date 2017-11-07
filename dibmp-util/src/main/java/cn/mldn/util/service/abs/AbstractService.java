package cn.mldn.util.service.abs;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务层继承的公共父类
 * @author YOOTK
 */
public abstract class AbstractService {
	/**
	 * 将分页的请求参数转换为map集合
	 * @param column 模糊查询的列
	 * @param keyWord 模糊查询关键字
	 * @param currentPage 当前页
	 * @param lineSize 每页显示的数据条数
	 * @return 返回key值固定的map集合
	 */
	public Map<String,Object> paramsToMap(String column, String keyWord,Long currentPage, Integer lineSize) {
		Map<String,Object> map=new HashMap<>();
		map.put("column",column);
		map.put("keyWord", "%"+keyWord+"%");
		map.put("start", lineSize*(currentPage-1)); //开始索引
		map.put("lineSize",lineSize);
		return map;
	}
}
