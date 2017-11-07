package cn.mldn.dibmp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IStorageApplyDAO;
import cn.mldn.dibmp.dao.IStorageApplyDetailsDAO;
import cn.mldn.dibmp.dao.IStorageRecordDAO;
import cn.mldn.dibmp.service.IFinancialService;
import cn.mldn.dibmp.vo.StorageApply;
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
	
	@Override
	public Set<StorageRecord> listStorageRecordsAll() {
		System.out.println("***StorageRecord****");
		return this.storageRecordDAO.findAll();
	}
	@Override
	public Set<StorageApply> listStorageApplysAll() {
		System.out.println("***StorageApply****");
		return this.storageApplyDAO.findAll();
	}
	@Override
	public boolean addStorageApply(StorageApply vo) {
		return this.storageApplyDAO.doCreate(vo);
	}
	@Override
	public boolean addStorageRecord(StorageRecord vo) {
		return this.storageRecordDAO.doCreate(vo);
	}
	@Override
	public List<StorageApply> listStorageApply(String column, String keyWord,Long currentPage, Integer lineSize) {
		Map<String, Object> map = super.paramsToMap(column, keyWord, currentPage, lineSize);
		return this.storageApplyDAO.findSplit(map);
		
	}
	@Override
	public Map<String, Object> listSplitStorageApply(String column, String keyWord,Long currentPage, Integer lineSize) {
		Map<String,Object> map=new HashMap<>();
		map.put("allStorageApplys", this.listStorageApply(column,keyWord,currentPage,lineSize));
		
		Map<String,String> map2=new HashMap<>();
		map2.put("column",column);
		map2.put("keyWord", "%"+keyWord+"%");
		map.put("allRecords", this.storageApplyDAO.getAllCount(map2));
		return map;
	}

	
	

}
