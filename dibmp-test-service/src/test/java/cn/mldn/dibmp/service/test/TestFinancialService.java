package cn.mldn.dibmp.service.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.dbc.DatabaseConnection;
import cn.mldn.dibmp.service.IFinancialService;
import cn.mldn.dibmp.service.IMemberService;
import cn.mldn.dibmp.vo.Member;
import cn.mldn.dibmp.vo.StorageApply;
import cn.mldn.dibmp.vo.StorageRecord;
import junit.framework.TestCase;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)

public class TestFinancialService extends TestCase {
	@Resource
	private IFinancialService financialService;

	@Resource
	private IMemberService memberService;

	@Test
	public void testListStorageApply() {
		Member member = this.memberService.get("mldn-finance");
		System.err.println(member);
		List<StorageApply> allsa = this.financialService.listStorageApplysAll();
		Iterator<StorageApply> itersa = allsa.iterator();
		while(itersa.hasNext()) {
			System.err.println(itersa.next());
		}
	}
	@Test
	public void testSubmitApply() throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		System.out.println(conn);
		conn.setAutoCommit(false);
		
		try {
			this.financialService.submitApply(1L, "mldn-finance", 1, "note");
			conn.commit(); // 进行事务提交
		} catch (Exception e) {
			conn.rollback(); // 进行事务提交
		} finally {
			conn.close(); // 不管是否出现异常都一定要关闭数据库连接
		}
	}
	@Test
	public void testListStorageRecord() {
		List<StorageRecord> allsr = this.financialService.listStorageRecordsAll();
		Iterator<StorageRecord> itersr = allsr.iterator();
		while(itersr.hasNext()) {
			System.err.println(itersr.next());;
		}
	}
	
	@Test
	public void testAddStorageApply() {
		
		for(int i=0;i<10;i++) {
			StorageApply sa =new StorageApply();
			//sa.setSaid((long)i);
			sa.setTitle("title"+i);
			sa.setPid((long)i);
			sa.setCid((long)i);
			sa.setWiid((long)i);
			sa.setWid((long)i);
			sa.setPubdate(new Date());
			sa.setNote("note"+i);
			sa.setStatus(0);
			sa.setAppmid("appmid"+i);
			System.err.println(this.financialService.addStorageApply(sa));;
		}
	}
	
	@Test
	public void testAddStorageRecord() {
		for(int i=0;i<10;i++) {
			StorageRecord sr =new StorageRecord();
			//sa.setSrid((long)i);
			sr.setSaid((long)i);
			sr.setGid((long)1);
			sr.setName("name"+i);
			sr.setNum(i);
			sr.setPrice((double)1);
			sr.setWeight((double)i);
			sr.setNote("note"+i);
			sr.setStatus(0);
			sr.setIndate(new Date());
			sr.setInmid("inmid"+i);
			System.err.println("增长成功后返回的自增长id："+this.financialService.addStorageRecord(sr));
		}
	}
	@Test
	public void testListSplitStorageApply() {
		Map<String, Object> map = this.financialService.listSplitStorageApply(null, null, 1L, 5);
		
		Iterator<Entry<String, Object>> ite = map.entrySet().iterator();
		while(ite.hasNext()) {
			Entry<String, Object> next = ite.next();
			System.err.println(next.getKey()+" :"+next.getValue());
		}
	}
	@Test
	public void testListSplitStorageRecord() {
		Map<String, Object> map = this.financialService.listSplitStorageRecord("name","qwe", 1L, 3);
		
		Iterator<Entry<String, Object>> ite = map.entrySet().iterator();
		while(ite.hasNext()) {
			Entry<String, Object> next = ite.next();
			System.err.println(next.getKey()+" :"+next.getValue());;
		}
	}
	
	@Test
	public void testGetStorageApplyBySaid() {
		System.err.println(this.financialService.getStorageApplyBySaid(1L));
	}
}
