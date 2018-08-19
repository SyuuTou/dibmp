package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.dibmp.vo.StorageApplyDetails;
public interface IStorageApplyDetailsDAO {

	public Set<StorageApplyDetails> findAll();
	
	public boolean doCreate(StorageApplyDetails vo) ;
	
	/**
	 * 
	 * @param said 申请单编号
	 * @return 申请单编号对应的商品总数
	 */
	public Long getStorageApplysGoodsAmount(Long said);
	
	/**
	 * 
	 * @param said 申请单编号
	 * @return 申请单编号对应的商品总价
	 */
	public Double getStorageApplysTotalPrice(Long said);
	/**
	 * 根据申请单编号获得该申请单的详情信息
	 * @param said 申请单编号
	 * @return
	 */
	public List<StorageApplyDetails> findDetailsBySaid(Long said);
	
	
	/**
	 * 根据申请单详情表编号来分页显示查询到所需要的信息
	 * @param sadid 申请单详情表编号  
	 * @return 返回StorageApplyDetails表的详细信息
	 */
	public List<StorageApplyDetails> findSplitBySaid(Map<String, Object> pageMap);
	/**
	 * 获得商品申请单对应的商品总数
	 * @param said 传入的申请单的said
	 * @return
	 */
	public Long findDetailsCount(Long said);
	
}
