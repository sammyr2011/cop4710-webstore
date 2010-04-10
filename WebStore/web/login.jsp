<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:if test="#session.user != null">
    <c:redirect url="/" />
</s:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="<s:url value="/main.css"/>"/>
		<style type="text/css">
			input{
				width: 150px;
			}
			.tdLabel{
				text-align: right;
			}
		</style>
    </head>
    <body>
		<div class="content">
			<div align="center">
				<s:include value="/header.jsp" />
				<div class="sectionHeader">
					<p class="sectionHeaderText">Login</p>
				</div>
				<div>
					<s:form name="inputForm" action="/login">
						<s:label name="error" />
						<s:textfield name="userName" label="Username" value="" />
						<s:password name="userPassword" label="Password" value="" />
						<s:submit align="center" value="Login" style="width: auto;" />
					</s:form>
				</div>
			</div>
		</div>
    </body>
</html>
