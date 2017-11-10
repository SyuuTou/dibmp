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
	
//	public List<StorageApplyDetails> findSplit(Map<String,Object> params) ;
}
