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
				<p class="sectionHeaderText">Editing Product <i><s:property value="%{product.name}"/></i></p>
			</div>

			<s:form>
				<s:textfield name="name" label="Name" maxLength="%{@ws.utils.Constants@LEN_PRODUCT_NAME}" value="%{product.name}"/>
				<s:textfield name="manufacturerId" label="Manufacturer ID" maxLength="" value="%{product.manufacturerId}"/>
				<s:textfield name="price" label="Price" maxLength="" value="%{product.price}" />
				<s:textfield name="stock" label="Stock" maxLength="" value="%{product.stock}" />
				<s:textfield name="image" label="Image Path" maxLength="%{@ws.utils.Constants@LEN_PRODUCT_IMAGE}" value="%{product.rawImage}" />
				<s:textfield name="description" label="Description" maxLength="" value="%{product.description}" />
				<s:hidden name="submit" value="true"  />
				<s:hidden name="productId" value="%{productId}"/>
				<s:checkbox name="delete" label="Delete Product"/>
				<s:submit style="width: auto;"  />
			</s:form>
		</div>
    </body>
</html>
