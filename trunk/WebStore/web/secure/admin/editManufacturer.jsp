<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Editing Manufacturer <s:property value="%{manufacturer.name}"/></title>
		<link rel="stylesheet" type="text/css" href="<s:url value="/main.css"/>"/>
    </head>
    <body>
		<div class="content">
			<s:include value="/header.jsp" />
			<div class="sectionHeader">
				<p class="sectionHeaderText">Edit Manufacturer <i><s:property value="%{manufacturer.name}"/></i></p>
			</div>

			<s:form>
				<s:textfield name="name" label="Name" maxLength="%{@ws.utils.Constants@LEN_MANUFACTURER_NAME}" value="%{manufacturer.name}"/>
				<s:textfield name="website" label="Website" maxLength="%{@ws.utils.Constants@LEN_MANUFACTURER_WEBSITE}" value="%{manufacturer.website}"/>
				<s:hidden name="submit" value="true"  />
				<s:hidden name="manufacturerId" value="%{manufacturerId}"/>
				<s:submit style="width: auto;"  />
			</s:form>
		</div>
    </body>
</html>
