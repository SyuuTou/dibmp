package cn.mldn.dibmp.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.IStorageApplyDAO;
import cn.mldn.dibmp.dao.IStorageApplyDetailsDAO;
import cn.mldn.dibmp.dao.IStorageRecordDAO;
import cn.mldn.dibmp.service.IFinancialService;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageRecord;
@Service
public class FinancialServiceImpl implements IFinancialService {
	@Resource
	private IStorageApplyDetailsDAO storageApplyDetailsDAO ;
	@Resource
	private IStorageRecordDAO storageRecordDAO ;
	@Resource
	private IStorageApplyDAO storageApplyDAO ;
	
	@Override
	public Set<StorageRecord> listStorageRecords() {
		System.out.println("***StorageRecord****");
		return this.storageRecordDAO.findAll();
	}
	@Override
	public Set<StorageApply> listStorageApplys() {
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

	
	

}
