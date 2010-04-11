<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Generic title</title>
		<link rel="stylesheet" type="text/css" href="<s:url value="/main.css"/>"/>
    </head>
    <body>
		<div class="content">
			<s:include value="/header.jsp" />
			<div class="sectionHeader">
				<p class="sectionHeaderText">Manufacturers</p>
			</div>

			<ol style="text-align: left;">
				<s:iterator value="manufacturers" var="manufacturer">
					<li><a href="<s:url action="editManufacturer"><s:param name="manufacturerId" value="#manufacturer.id"/></s:url>"><s:property value="#manufacturer.name"/></a></li>
				</s:iterator>
			</ol>
		</div>
    </body>
</html>
