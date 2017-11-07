package cn.mldn.dibmp.web.action.back;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.dibmp.service.IFinancialService;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;



@Controller
@RequestMapping("/pages/back/admin/storageaudit/*")
public class StorageAuditActionBack extends AbstractAction {
	
	@Resource
	private IFinancialService financialService;
	
	private static final String TITLE = "入库审核" ;
	
	@RequestMapping("list_prepare") 
	public ModelAndView listDetails() {
		SplitPageUtil spu = new SplitPageUtil("申请标题:title", "storage.audit.list.prepare.action") ;
//		String column = spu.getColumn();
//		String keyWord = spu.getKeyWord();
//		long currentPage = spu.getCurrentPage();
//		int lineSize = spu.getLineSize();
//		Map<String, Object> map = this.financialService.listSplitStorageApply(column, keyWord, currentPage, lineSize);
//		
		ModelAndView mav = new ModelAndView(super.getPage("storage.audit.list.prepare.page"));
//		mav.addObject(map);
		return mav;
	}
	@RequestMapping("edit_pre") 
	public ModelAndView editPre() {
		ModelAndView mav = new ModelAndView(super.getPage("storage.audit.edit.page"));
		return mav;
	}
	@RequestMapping("list_history") 
	public ModelAndView listMyself() {
		SplitPageUtil spu = new SplitPageUtil("申请标题:title", "storage.audit.list.history.action") ;
		ModelAndView mav = new ModelAndView(super.getPage("storage.audit.list.history.page"));
		return mav;
	}
}
