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
	 * 审核历史的分页显示业务方法
	 * @param column
	 * @param keyWord
	 * @param currentPage
	 * @param lineSize
	 * @return
	 */
	public Map<String,Object> listSplitStorageHistory(String column, String keyWord,Long currentPage, Integer lineSize);
	
	/**
	 * 提交审核申请
	 * @param said 提交的申请单编号
	 * @param mid 当前用户
	 * @param status 审核状态
	 * @param note 审核信息
	 * @return 审核通过返回true,审核失败返回false
	 */
	public boolean submitApply(Long said,String auditmid,Integer auditstatus,String auditnote);
	
	/**
	 * 根据用户ID获得一个涉及Member表，Level表，Dept表的信息
	 * @param mid 用户的ID信息
	 * @return 返回信息如下
	 * 1.key="selMember" value=用户的员工信息
	 * 2.key="selDept" value=用户的部门信息
	 * 3.key="selLevel" value=用户的等级信息
	 */
	public Map<String, Object> getMemberInfo(String mid);
	
	/**
	 * 根据仓库编号ID获得仓库的具体信息，具体设计warehouse表，以及witem表格
	 * @param wid 仓库编号
	 * @return 返回如下
	 * 1.key=selWarehouse   value=仓库的详细信息
	 * 2.key=selWitem  value=货物类型
	 */
	public Map<String , Object> getWarehouseInfo(Long wid);
	/**
	 * 根据申请表ID获得一个关于数张表的map集合
	 * @param said 申请表编号
	 * @return 返回结果如下
	 * 1.key=selectstorageapply  value=商品申请表的查询信息
	 * 2.key=selectwitem value=商品种类查询信息
	 * 3.key=selectwarehouse value= 仓库表查询信息
	 * 4.key=selectstorageapplydetails value=根据申请表编号查出来的所有详细表信息
	 * 5.key=selectgoods value=根据前面查询的所有gid 来查处所有商品的信息
	 */
	public Map<String, Object> getStorageDetailsInfo(Long said,Long currentPage,Long LineSize);
	
}
