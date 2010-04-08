<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Home</title>
		<link rel="stylesheet" type="text/css" href="<s:url value="/"/>main.css">
    </head>
    <body>
		<div class="content">
			<s:include value="/header.jsp" />
			<div class="sectionHeader">
				<p class="sectionHeaderText">Secure user home</p>
			</div>
			User: <s:property value="#session.user.userName"/><br />
			Admin: <s:property value="#session.user.admin" /><br />

			Listing of products goes here?
		</div>
    </body>
</html>
