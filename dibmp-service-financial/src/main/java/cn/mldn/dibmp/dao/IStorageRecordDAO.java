package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.dibmp.vo.StorageRecord;
public interface IStorageRecordDAO { 

	public List<StorageRecord> findAll();
	/**
	 * 用于实现单条记录的添加操作
	 * @param vo单条记录的vo对象
	 * @return 返回值可以是布尔值，可以是影响的行数，也可以自己设置为主键自增长后的值
	 */
	public Long doCreate(StorageRecord vo) ;
	
	public List<StorageRecord> findSplit(Map<String,Object> params) ;
	/**
	 * 获取胫骨查询之后的所有的记录数
	 * @param params 传入的封装好的map集合，key:column&&keyWord
	 * @return 返回查询后的记录行数
	 */
	public Long getAllCount(Map<String,String> params);
	/**
	 * 返回已经审核过的申请单对应的商品总数
	 * @param said 申请单编号
	 * @return 审核过的申请单编号对应的商品总数
	 */
	public Long getCheckedStorageApplysGoodsAmount(Long said);
	/**
	 * 返回已经审核过的申请单对应的商品总价
	 * @param said 申请单单号
	 * @return 审核过的申请单编号对应的商品总价
	 */
	public Double getCheckedStorageApplysTotalPrice(Long said);
	/**
	 * 用于实现批量数据的添加
	 * @param storageRecordBatch用于进行添加的List
	 */
	public boolean addBatch(List<StorageRecord> storageRecordBatch);
	
	
}
