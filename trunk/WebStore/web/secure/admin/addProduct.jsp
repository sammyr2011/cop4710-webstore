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
				<p class="sectionHeaderText">Add Product</p>
			</div>
			<s:form>
				<s:textfield name="name" label="Name" maxLength="%{@ws.utils.Constants@LEN_PRODUCT_NAME}"/>
				<s:textfield name="manufacturerId" label="Manufacturer ID" maxLength="" />
				<s:textfield name="price" label="Price" maxLength="" />
				<s:textfield name="stock" label="Stock" maxLength="" />
				<s:textfield name="image" label="Image Path" maxLength="%{@ws.utils.Constants@LEN_PRODUCT_IMAGE}" />
				<s:textfield name="description" label="Description" maxLength="" />
				<s:hidden name="submit" value="true"  />
				<s:submit style="width: auto;"  />
			</s:form>
		</div>
    </body>
</html>
