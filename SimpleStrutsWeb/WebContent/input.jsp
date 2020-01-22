<%@ page pageEncoding = "Shift_JIS" %>
<%@ page contentType="text/html; charset=Shift_JIS" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>

<html><body>
<B>Input Form</B><BR><BR>

<html:form action="/test" method="post">
<html:submit property="dispatch">dispatchTestA</html:submit>
<html:submit property="dispatch">dispatchTestB</html:submit>
</html:form>

</body></html>