<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Add Manufacturer</title>
		<link rel="stylesheet" type="text/css" href="<s:url value="/main.css"/>"/>
    </head>
    <body>
		<div class="content">
			<s:include value="/header.jsp" />
			<div class="sectionHeader">
				<p class="sectionHeaderText">Add Manufacturer</p>
			</div>

			<s:form>
				<s:textfield name="name" label="Name" maxLength="%{@ws.utils.Constants@LEN_MANUFACTURER_NAME}"/>
				<s:textfield name="website" label="Website" maxLength="%{@ws.utils.Constants@LEN_MANUFACTURER_WEBSITE}"/>
				<s:hidden name="submit" value="true"  />
				<s:submit style="width: auto;"  />
			</s:form>
		</div>
    </body>
</html>
