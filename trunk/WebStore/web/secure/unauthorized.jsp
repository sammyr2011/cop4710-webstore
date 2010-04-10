<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Unauthorized</title>
<!--
		<s:if test="forward">
			<meta http-equiv="refresh" content="1;<s:property value="forward"/>" />
		</s:if>
		<s:else>
			<meta http-equiv="refresh" content="1;<s:url action="home" value="/home" />" />
		</s:else>
  -->

		<link rel="stylesheet" type="text/css" href="<s:url value="/main.css"/>"/>
    </head>
    <body>
		<div class="content">
			<s:include value="/header.jsp" />
			<div class="sectionHeaderError">
				<p class="sectionHeaderText">You may not perform this action</p>
			</div>
		<s:actionerror />
		<s:actionmessage  />
		</div>
    </body>
</html>
