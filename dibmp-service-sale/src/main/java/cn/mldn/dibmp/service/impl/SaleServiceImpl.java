package cn.mldn.dibmp.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.mldn.dibmp.dao.ISaleDAO;
import cn.mldn.dibmp.service.ISaleService;
import cn.mldn.dibmp.vo.Goods;
@Service
public class SaleServiceImpl implements ISaleService{
	@Resource
	private ISaleDAO saleDAO;
	
	@Resource
	private RedisTemplate<String,Goods> redisTemp;
	@Override
	public Set<Goods> getAllGoods() {
		return saleDAO.getAllGoods();
	}
	@Override
	public boolean preDelivery(String mid,Long gid) {
		try {
			Goods goods = this.saleDAO.getByGid(gid);
			this.redisTemp.opsForValue().set(mid,goods);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
}
