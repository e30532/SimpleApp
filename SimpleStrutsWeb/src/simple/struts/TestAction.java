package simple.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class TestAction extends DispatchAction {
	public ActionForward dispatchTestA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("dispatchA");
		TestForm tForm = (TestForm) form;
	    tForm.setMessage("Hello1");
		return mapping.findForward("successA");
	}

	public ActionForward dispatchTestB(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("dispatchB");
		TestForm tForm = (TestForm) form;
	    tForm.setMessage("Hello2");
		return mapping.findForward("successB");
	}
}
