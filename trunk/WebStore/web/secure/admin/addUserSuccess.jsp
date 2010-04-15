<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Successful</title>
		<link rel="stylesheet" type="text/css" href="<s:url value="/"/>main.css">
    </head>
    <body>
		<div class="content">
			<s:include value="/header.jsp" />
			<div class="sectionHeader">
				<s:if test="%{userId}">
					<p class="sectionHeaderText">Successfully Updated <i><s:property value="username"/></i></p>
				</s:if>
				<s:else>
					<p class="sectionHeaderText">Successfully Added <i><s:property value="username"/></i></p>
				</s:else>
			</div>
			<pre>
<b>Name</b>: <s:property value="firstName"/> <s:property value="lastName"/>
<b>Email</b>: <s:property value="email"/>
<b>Admin</b>: <s:property value="admin"/>
			</pre>
		</div>
    </body>
</html>
