package cn.mldn.dibmp.service.test;

import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
		Set<StorageApply> allsa = this.financialService.listStorageApplys();
		Iterator<StorageApply> itersa = allsa.iterator();
		while(itersa.hasNext()) {
			System.err.println(itersa.next());;
		}
	}
	
	@Test
	public void testListStorageRecord() {
		Set<StorageRecord> allsr = this.financialService.listStorageRecords();
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
			sa.setNote("note"+i);
			sa.setStatus(i);
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
			sr.setStatus(i);
			sr.setInmid("inmid"+i);
			System.err.println(this.financialService.addStorageRecord(sr));;
		}
	}
}
