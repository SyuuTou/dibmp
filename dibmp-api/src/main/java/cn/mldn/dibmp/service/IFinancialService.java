package cn.mldn.dibmp.service ;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.dibmp.vo.Member;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageRecord;


public interface IFinancialService {
	/**
	 * 获取所有的已审核过的清单
	 * @return 已审核过的清单List
	 */
	public List<StorageRecord>listStorageRecordsAll() ;
	/**
	 * 获取所有的待审核清单
	 * @return 待审核清单List
	 */
	public List<StorageApply> listStorageApplysAll() ;
	/**
	 * 实现待审核清单数据的增加
	 * @param vo 待审核清单的vo对象
	 * @return 增加之后布尔值
	 */
	public boolean addStorageApply(StorageApply vo) ;
	/**
	 * 实现已审核清单的添加
	 * @param vo 已审核清单的vo对象
	 * @return 添加之后的布尔值
	 */
	public Long addStorageRecord(StorageRecord vo) ;
	
	
	/**
	 * 返回StorageApply的查询后的分页数据
	 * @return allRecorders：返回经过（模糊）查询后的分页数据，调用本类的listStorageApply方法
	 * allCounts:经过查询(附带模糊查询)之后的所有记录数
	 */
	public Map<String,Object> listSplitStorageRecord(String column, String keyWord,Long currentPage, Integer lineSize);
	
	/**
	 * 返回StorageRecord的查询后的分页数据
	 * @return allRecorders：返回经过（模糊）查询后的分页数据，调用本类的listStorageApply方法
	 * allCounts:经过查询(附带模糊查询)之后的所有记录数
	 */
	public Map<String,Object> listSplitStorageApply(String column, String keyWord,Long currentPage, Integer lineSize);
	/**
	 * 
	 * @param said 传入的申请单的编号
	 * @return 返回申请单的vo对象
	 */
	public StorageApply getStorageApplyBySaid(Long said);
	
	/**
	 * 根据申请单编号获取所有的申请单详情信息，用于申请单的预审核页面的回显
	 * @param said 申请单编号
	 * @return 申请单对应的详情信息
	 */
	public Map<String,Object> listStorageApplyDetails(Long said);
	
	/**
	 * 提交审核申请
	 * @param said 提交的申请单编号
	 * @param mid 当前用户
	 * @param status 审核状态
	 * @param note 审核信息
	 * @return 审核通过返回true,审核失败返回false
	 */
	public boolean submitApply(Long said,String mid,Integer status,String note);
	
}
