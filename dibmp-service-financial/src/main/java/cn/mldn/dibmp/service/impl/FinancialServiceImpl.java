package cn.mldn.dibmp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.mldn.dibmp.dao.IDeptDAO;
import cn.mldn.dibmp.dao.ILevelDAO;
import cn.mldn.dibmp.dao.IStorageApplyDAO;
import cn.mldn.dibmp.dao.IStorageApplyDetailsDAO;
import cn.mldn.dibmp.dao.IStorageRecordDAO;
import cn.mldn.dibmp.dao.IWarehouseDAO;
import cn.mldn.dibmp.dao.IWitemDAO;
import cn.mldn.dibmp.service.IFinancialService;
import cn.mldn.dibmp.service.IMemberService;
import cn.mldn.dibmp.vo.Dept;
import cn.mldn.dibmp.vo.Level;
import cn.mldn.dibmp.vo.Member;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.dibmp.vo.StorageRecord;
import cn.mldn.dibmp.vo.Warehouse;
import cn.mldn.dibmp.vo.Witem;
import cn.mldn.util.service.abs.AbstractService;
@Service
public class FinancialServiceImpl extends AbstractService implements IFinancialService {
	@Resource
	private IStorageApplyDetailsDAO storageApplyDetailsDAO ;
	@Resource
	private IStorageRecordDAO storageRecordDAO ;
	@Resource
	private IStorageApplyDAO storageApplyDAO ;
	@Resource
	private IWarehouseDAO warehouseDAO ;
	
	//注入member业务的业务接口对象,同时dubbo的配置文件之中还要引入IMemberService的引用
	@Resource
	private IMemberService memberService;
	@Resource
	private IWitemDAO witemDAO;
	
	/*注入storage(库存)业务的业务接口对象,同时dubbo的配置文件之中还要引入IMemberService的引用
		@Resource
		private IMemberService storageService;*/
	
	@Override
	public List<StorageRecord> listStorageRecordsAll() {
		System.err.println("***StorageRecord****");
		return this.storageRecordDAO.findAll();
	}
	@Override
	public List<StorageApply> listStorageApplysAll() {
		System.err.println("***StorageApply****");
		return this.storageApplyDAO.findAll();
	}
	@Override
	public boolean addStorageApply(StorageApply vo) {
		return this.storageApplyDAO.doCreate(vo);
	}
	@Override
	public Long addStorageRecord(StorageRecord vo) {
		Long sucessKey = this.storageRecordDAO.doCreate(vo);
		System.err.println("*******");
		System.err.println("自增长id:->"+vo.getSrid());
		return vo.getSrid();
	}
	@Override
	public StorageApply getStorageApplyBySaid(Long said) {
		return this.storageApplyDAO.findBySaid(said);
	}
	@Override
	public Map<String, Object> listSplitStorageApply(String column, String keyWord,Long currentPage, Integer lineSize) {
		//返回给Action的map:resultMap
		Map<String,Object> resultMap=new HashMap<>();
		List<StorageApply> allStorageApplys = this.storageApplyDAO.findSplit(super.paramsToMap(column, keyWord, currentPage, lineSize));
		List<Map<String,Object>> StorageApplyInfos=new ArrayList<Map<String,Object>>();
		allStorageApplys.forEach((e)->{
			//获取入库单编号
			Long said = e.getSaid();
			//获取提交人员
			String appmid = e.getAppmid();
			//获取仓库编号
			Long wid = e.getWid();
			final Map<String,Object> nestedMap=new HashMap<>();
			nestedMap.put("singleStorageApply", e);
			nestedMap.put("allAmount", this.storageApplyDetailsDAO.getStorageApplysGoodsAmount(said)==null ? 0:this.storageApplyDetailsDAO.getStorageApplysGoodsAmount(said));
			nestedMap.put("totalPrice", this.storageApplyDetailsDAO.getStorageApplysTotalPrice(said)==null ? 0:this.storageApplyDetailsDAO.getStorageApplysTotalPrice(said));
			nestedMap.put("member", this.memberService.get(appmid));
			/*调用库存业务的业务方法获得申请单对应的库存对象
			nestedMap.put("warehouse", this.storageService.get(wid));*/
			//下面直接调用自己编写的WarehouseDAO接口
			nestedMap.put("warehouse", this.warehouseDAO.findByWid(wid));
			StorageApplyInfos.add(nestedMap);
		});
		resultMap.put("StorageApplyInfos", StorageApplyInfos);
		//用于传递给DAO的map
		Map<String,String> map2=new HashMap<>();
		map2.put("column",column);
		map2.put("keyWord", "%"+keyWord+"%");
		resultMap.put("allRecorders", this.storageApplyDAO.getAllCount(map2));
		return resultMap;
	}
	@Override
	public Map<String, Object> listSplitStorageHistory(String column, String keyWord,Long currentPage, Integer lineSize) {
		//返回给Action的map:resultMap
		Map<String,Object> resultMap=new HashMap<>();
		List<StorageApply> allStorageApplys = this.storageApplyDAO.findSplitHistory(super.paramsToMap(column, keyWord, currentPage, lineSize));
		List<Map<String,Object>> StorageApplyHistorys=new ArrayList<Map<String,Object>>();
		allStorageApplys.forEach((e)->{
			//获取入库单编号
			Long said = e.getSaid();
			//获取提交人员
			String appmid = e.getAppmid();
			//获取仓库编号
			Long wid = e.getWid();
			//获取审核人员
			String auditmid = e.getAuditmid();
			final Map<String,Object> nestedMap=new HashMap<>();
			nestedMap.put("singleStorageApply", e);
			nestedMap.put("allAmount", this.storageApplyDetailsDAO.getStorageApplysGoodsAmount(said)==null ? 0:this.storageApplyDetailsDAO.getStorageApplysGoodsAmount(said));
			nestedMap.put("totalPrice", this.storageApplyDetailsDAO.getStorageApplysTotalPrice(said)==null ? 0:this.storageApplyDetailsDAO.getStorageApplysTotalPrice(said));
			nestedMap.put("appmember", this.memberService.get(appmid));
			nestedMap.put("auditmember", this.memberService.get(auditmid));
			/*调用库存业务的业务方法获得申请单对应的库存对象
			nestedMap.put("warehouse", this.storageService.get(wid));*/
			//下面直接调用自己编写的WarehouseDAO接口
			nestedMap.put("warehouse", this.warehouseDAO.findByWid(wid));
			
			StorageApplyHistorys.add(nestedMap);
		});
		resultMap.put("StorageApplyHistorys", StorageApplyHistorys);
		//用于传递给DAO的map
		Map<String,String> map2=new HashMap<>();
		map2.put("column",column);
		map2.put("keyWord", "%"+keyWord+"%");
		resultMap.put("allRecorders", this.storageApplyDAO.getAllHistoryCount(map2));
		return resultMap;
	}
	@Override
	public Map<String, Object> listSplitStorageRecord(String column, String keyWord, Long currentPage,Integer lineSize) {
		//返回给Action的map:resultMap
		Map<String,Object> resultMap=new HashMap<>();
		List<StorageRecord> allStorageRecords = this.storageRecordDAO.findSplit(super.paramsToMap(column, keyWord, currentPage, lineSize));
		List<Map<String,Object>> StorageRecordInfos=new ArrayList<Map<String,Object>>();
		allStorageRecords.forEach((e)->{
			//获取入库单编号
			Long said = e.getSaid();
			//获取提交人员
			String inMid = e.getInmid();
			final Map<String,Object> nestedMap=new HashMap<>();
			//根据said获取申请单的VO对象，
			StorageApply storageApplyVO = this.storageApplyDAO.findBySaid(said);
			nestedMap.put("singleStorageRecord", e);
			nestedMap.put("singleStorageApply", storageApplyVO);
			nestedMap.put("allAmount", this.storageRecordDAO.getCheckedStorageApplysGoodsAmount(said)==null ? 0:this.storageRecordDAO.getCheckedStorageApplysGoodsAmount(said));
			nestedMap.put("totalPrice", this.storageRecordDAO.getCheckedStorageApplysTotalPrice(said)==null ? 0:this.storageRecordDAO.getCheckedStorageApplysTotalPrice(said));
			//通过申请单的VO对象获取提交人的id，从而取得提交人VO
			Member subMember = this.memberService.get(storageApplyVO.getAppmid());
			nestedMap.put("subMember", subMember);
			nestedMap.put("checkMember", this.memberService.get(inMid));
			//调用库存业务的业务方法获得审核完成后的申请单对应的库存对象
			//nestedMap.put("warehouse", this.storageService.get(storageApplyVO.getWid()));
			
			StorageRecordInfos.add(nestedMap);
		});
		resultMap.put("StorageRecordInfos", StorageRecordInfos);
		//用于传递给DAO的map
		Map<String,String> map2=new HashMap<>();
		map2.put("column",column);
		map2.put("keyWord", "%"+keyWord+"%");
		resultMap.put("allRecorders", this.storageRecordDAO.getAllCount(map2));
		return resultMap;
	}
	@Override
	public Map<String, Object> listStorageApplyDetails(Long said) {
		Map<String,Object> map=new HashMap<>();
		List<StorageApplyDetails> allStorageApplyDetails = this.storageApplyDetailsDAO.findDetailsBySaid(said);
		StorageApply singleStorageApply = this.storageApplyDAO.findBySaid(said);
		map.put("singleStorageApply", singleStorageApply);
		map.put("allAmount", this.storageApplyDetailsDAO.getStorageApplysGoodsAmount(said)==null ? 0:this.storageApplyDetailsDAO.getStorageApplysGoodsAmount(said));
		map.put("totalPrice", this.storageApplyDetailsDAO.getStorageApplysTotalPrice(said)==null ? 0:this.storageApplyDetailsDAO.getStorageApplysTotalPrice(said));
		//调用库存业务的业务方法获得审核完成后的申请单对应的库存对象
		//map.put("warehouse", this.storageService.get(singleStorageApply.getWid()));
		//map.put("witem", this.storageService.get(singleStorageApply.getWiid()));
		map.put("subMember", this.memberService.get(singleStorageApply.getAppmid()));
		
		map.put("allStorageApplyDetails", allStorageApplyDetails);
		return map;
	}
	@Override
	@Transactional
	public boolean submitApply(Long said,String auditmid, Integer status, String auditnote) {
		/*List<StorageApplyDetails> StorageApplyDetails = this.storageApplyDetailsDAO.findDetailsBySaid(said);
		List<StorageRecord> StorageRecordBatch =new ArrayList<>();
		StorageApplyDetails.forEach((e)->{
			StorageRecord sr=new StorageRecord();
			sr.setSaid(said);
			sr.setGid(e.getGid());
			sr.setName(e.getName());
			sr.setNum(e.getNum());
			sr.setPrice(e.getPrice());
			sr.setWeight(e.getWeight());
			sr.setStatus(status);
			sr.setNote(note);
			sr.setIndate(new Date());
			sr.setInmid(mid);
			
			StorageRecordBatch.add(sr);
		});
		System.err.println(StorageRecordBatch);
		
		boolean addSuccessfulFlag;*/
		boolean statusUpdateFlag;
		Map<String,Object> map=new HashMap<>();
		map.put("said", said);
		map.put("status",status);
		map.put("auditmid",auditmid);
		map.put("auditnote",auditnote);
		map.put("checkdate", new Date());
		try {
			//进行数据记录的写入
//			addSuccessfulFlag=this.storageRecordDAO.addBatch(StorageRecordBatch);
			//进行审核申请单状态的改变
			statusUpdateFlag = this.storageApplyDAO.updateAuditStatus(map);
		}catch(Exception e) {
			return false;
		}
		return statusUpdateFlag;
	}
	
	@Resource
	private IDeptDAO deptDAO;
	@Resource
	private ILevelDAO levelDAO;
	@Override
	public Map<String,Object> getMemberInfo(String mid){
		Map<String, Object> map =new HashMap<>();
		Member selMember = this.memberService.get(mid);
		Dept selDept =this.deptDAO.findByDid(selMember.getDid());
		Level selLevel =this.levelDAO.findByLid(selMember.getLid());
		map.put("selMember",  selMember);
		map.put("selDept",  selDept);
		map.put("selLevel", selLevel);
		return map;
	}
	
	@Override
	public Map<String, Object> getWarehouseInfo(Long wid) {
		Map<String, Object> map =new HashMap<>();
		Warehouse selWarehouse =warehouseDAO.findByWid(wid);
		Witem selWitem =witemDAO.findByWiid(selWarehouse.getWiid());
		map.put("selWarehouse", selWarehouse);
		map.put("selWitem", selWitem);
		return map;
	}
	
	@Override
	public Map<String, Object> getStorageDetailsInfo(Long said,Long currentPage,Long LineSize) {
		Map<String, Object> map = new HashMap<>();   
		Map<String, Object> paramMap =new HashMap<>();
		paramMap.put("start", (currentPage-1)*LineSize);
		paramMap.put("lineSize", LineSize);
		paramMap.put("said", said);
		StorageApply selectStorageApply =storageApplyDAO.findBySaid(said);
		Witem selectWitem = witemDAO.findByWiid(selectStorageApply.getWiid());
		Warehouse selectWarehouse = warehouseDAO.findByWid(selectStorageApply.getWid());
		List<StorageApplyDetails> selectStorageApplyDetails = storageApplyDetailsDAO.findSplitBySaid(paramMap);
		map.put("selectStorageApply", selectStorageApply);
		map.put("selectWitem", selectWitem);
		map.put("selectWarehouse", selectWarehouse);
		map.put("selectStorageApplyDetails", selectStorageApplyDetails);
		map.put("allRecorders", storageApplyDetailsDAO.findDetailsCount(said));
		map.put("totalPrice", this.storageApplyDetailsDAO.getStorageApplysTotalPrice(said)==null ? 0:this.storageApplyDetailsDAO.getStorageApplysTotalPrice(said));
		return map;
	}
}
