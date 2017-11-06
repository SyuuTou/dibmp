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
	public Set<StorageRecord>listStorageRecords() ;
	/**
	 * 获取所有的待审核清单
	 * @return 待审核清单List
	 */
	public Set<StorageApply> listStorageApplys() ;
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
	
	//public Map<String,Set<String>> getStorageApplyAndStorageRecord() ;

}
