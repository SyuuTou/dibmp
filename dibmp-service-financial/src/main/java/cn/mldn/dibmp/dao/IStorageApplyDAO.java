package cn.mldn.dibmp.dao;


import java.util.List;
import java.util.Map;
import java.util.Set;


import cn.mldn.dibmp.vo.StorageApply;
public interface IStorageApplyDAO { 
	
	public Set<StorageApply> findAll();
	
	public boolean doCreate(StorageApply vo) ;
	
	public List<StorageApply> findSplit(Map<String,Object> params) ;
}
