package cn.mldn.dibmp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IStorageApplyDAO;
import cn.mldn.dibmp.dao.IStorageApplyDetailsDAO;
import cn.mldn.dibmp.dao.IStorageRecordDAO;
import cn.mldn.dibmp.service.IFinancialService;
import cn.mldn.dibmp.service.IMemberService;
import cn.mldn.dibmp.vo.Member;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageApplyDetails;
import cn.mldn.dibmp.vo.StorageRecord;
import cn.mldn.util.service.abs.AbstractService;
@Service
public class FinancialServiceImpl extends AbstractService implements IFinancialService {
	@Resource
	private IStorageApplyDetailsDAO storageApplyDetailsDAO ;
	@Resource
	private IStorageRecordDAO storageRecordDAO ;
	@Resource
	private IStorageApplyDAO storageApplyDAO ;
	
	//注入member业务的业务接口对象,同时dubbo的配置文件之中还要引入IMemberService的引用
	@Resource
	private IMemberService memberService;
	
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
	public boolean submitApply(Long said,String mid, Integer status, String note) {
		List<StorageApplyDetails> StorageApplyDetails = this.storageApplyDetailsDAO.findDetailsBySaid(said);
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
		//进行数据记录的写入
		boolean addSuccessfulFlag=this.storageRecordDAO.addBatch(StorageRecordBatch);
		//进行审核申请单状态的改变
		boolean statusUpdateFlag = this.storageApplyDAO.updateStatusTo2(said);
		/*if(!(addSuccessfulFlag && statusUpdateFlag)) {
			回滚以上两个操作
			return false;
		}*/
		return addSuccessfulFlag && statusUpdateFlag;
	}
	

	
	

}
