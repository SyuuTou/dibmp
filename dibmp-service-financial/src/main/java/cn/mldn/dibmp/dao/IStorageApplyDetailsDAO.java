package cn.mldn.dibmp.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.dibmp.vo.StorageApplyDetails;
public interface IStorageApplyDetailsDAO {

	public Set<StorageApplyDetails> findAll();
	
	public boolean doCreate(StorageApplyDetails vo) ;
	
//	public List<StorageApplyDetails> findSplit(Map<String,Object> params) ;
}
