package cn.mldn.dibmp.dao;


import java.util.List;
import java.util.Map;
import java.util.Set;


import cn.mldn.dibmp.vo.StorageApply;
public interface IStorageApplyDAO { 
	
	public List<StorageApply> findAll();
	
	public boolean doCreate(StorageApply vo) ;
	
	public List<StorageApply> findSplit(Map<String,Object> params) ;
	/**
	 * 对于审核历史页面进行的分页显示方法
	 * @param paramsToMap
	 * @return
	 */
	public List<StorageApply> findSplitHistory(Map<String, Object> paramsToMap);
	/**
	 * 获取经过查询之后的所有的记录数
	 * @param params 传入的封装好的map集合，key:column&&keyWord
	 * @return 返回查询后的记录行数
	 */
	public Long getAllCount(Map<String,String> params);
	/**
	 * 获取所有审核过的记录数
	 * @param map2
	 * @return
	 */
	public Long getAllHistoryCount(Map<String, String> map2);
	/**
	 * 根据said获取申请单的对象
	 * @param said 申请单的id
	 * @return 返回申请单的vo对象
	 */
	public StorageApply findBySaid(Long said);
	/**
	 * 将状态更新为2，表示已经经过审核
	 * @param said 审核单编号
	 * @return 返回进过更新之后的布尔值
	 */
	public boolean updateAuditStatus(Map<String,Object> map);

	

	
	

}
