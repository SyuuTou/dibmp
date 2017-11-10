package cn.mldn.dibmp.web.action.back;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
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
		SplitPageUtil spu = new SplitPageUtil("申请标题:title|提交状态0/1:status", super.getPage("storage.audit.list.prepare.action")) ;
		String column = spu.getColumn();
		String keyWord = spu.getKeyWord();
		long currentPage = spu.getCurrentPage();
		int lineSize = spu.getLineSize();
		Map<String, Object> map = this.financialService.listSplitStorageApply(column, keyWord, currentPage, lineSize);
		ModelAndView mav = new ModelAndView(super.getPage("storage.audit.list.prepare.page"));
		mav.addAllObjects(map);
		return mav;
	}
	@RequestMapping("edit_pre") 
	public ModelAndView editPre() {
		ModelAndView mav = new ModelAndView(super.getPage("storage.audit.edit.page"));
		Long said=0L;
		try {
			 said = Long.parseLong(super.getRequest().getParameter("said"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> map=this.financialService.listStorageApplyDetails(said);
//		System.err.println(map);
		mav.addAllObjects(map);
		mav.addObject("said",said);
		return mav;
	}
	@RequestMapping("do_edit")
	public ModelAndView doEdit(Long said,String note,Integer audit) {
		Integer status=audit;
		System.err.println(said+"-->"+note+"-->"+audit);
		String mid = (String)SecurityUtils.getSubject().getSession().getAttribute("mid");
		System.err.println(mid);
		System.err.println(this.financialService.submitApply(said, mid, status, note));
		
		//审核成功返回true,审核失败返回false
		ModelAndView mav=new ModelAndView(super.getPage("forward.page"));
		if(this.financialService.submitApply(said, mid, status, note)) {
			super.setMsgAndUrl(mav, "storage.audit.list.history.action", "vo.add.success", TITLE);
		}else {
			mav.addObject("url", super.getPage("storage.audit.edit.prepare.action")+"?said="+said);
			mav.addObject("msg", super.getMessage("vo.add.failure", TITLE));
		}
		return mav;
	}
	@RequestMapping("list_history") 
	public ModelAndView listMyself() {
		SplitPageUtil spu = new SplitPageUtil("入库单编号:said|审核状态0/1:status", super.getPage("storage.audit.list.history.action")) ;
		String column = spu.getColumn();
		String keyWord = spu.getKeyWord();
		long currentPage = spu.getCurrentPage();
		int lineSize = spu.getLineSize();
		System.err.println(column+"----"+keyWord);
		Map<String, Object> map = this.financialService.listSplitStorageRecord(column, keyWord, currentPage, lineSize);
		ModelAndView mav = new ModelAndView(super.getPage("storage.audit.list.history.page"));
		mav.addAllObjects(map);
		return mav;
	}
}
