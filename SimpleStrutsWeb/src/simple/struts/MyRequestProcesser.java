package simple.struts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RequestProcessor;

public class MyRequestProcesser extends RequestProcessor {
    protected ActionForward processActionPerform(HttpServletRequest request, HttpServletResponse response, Action action, ActionForm form, ActionMapping mapping) throws IOException, ServletException {
    	try {
    		System.out.println("before...");
            ActionForward forward = action.execute(mapping, form, request, response);
            System.out.println("after...");
            return forward;
        } catch (Exception e) {
            return (processException(request, response, e, form, mapping));
        }
    }
}
