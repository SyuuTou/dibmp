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
	public boolean addStorageRecord(StorageRecord vo) ;
	
	
	/**
	 * 返回StorageApply的查询后的分页数据
	 * @return allRecords：返回经过（模糊）查询后的分页数据，调用本类的listStorageApply方法
	 * allCounts:经过查询(附带模糊查询)之后的所有记录数
	 */
	public Map<String,Object> listSplitStorageApply(String column, String keyWord,Long currentPage, Integer lineSize);

}
