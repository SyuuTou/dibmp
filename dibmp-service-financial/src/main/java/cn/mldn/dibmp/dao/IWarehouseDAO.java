package cn.mldn.dibmp.dao;

import cn.mldn.dibmp.vo.Warehouse;

public interface IWarehouseDAO {
	/**
	 * 增加仓库的假数据，只添加了假的数据
	 * @param vo Warehouse
	 * @return 成功返回true
	 */
	public boolean add(Warehouse vo);
	/**
	 * 查询仓库的详细信息
	 * @param wid 仓库编号
	 * @return Warehouse
	 */
	public Warehouse findByWid(Long wid);

}