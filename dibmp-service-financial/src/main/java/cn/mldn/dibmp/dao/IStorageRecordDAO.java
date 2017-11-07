package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.dibmp.vo.StorageRecord;
public interface IStorageRecordDAO { 

	public List<StorageRecord> findAll();
	
	public boolean doCreate(StorageRecord vo) ;
	
//	public List<StorageRecord> findSplit(Map<String,Object> params) ;
}
