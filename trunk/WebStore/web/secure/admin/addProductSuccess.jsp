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
				<s:if test="%{product == null}">
					<p class="sectionHeaderText">Successfully Deleted <i><s:property value="name"/></i></p>
				</s:if>
				<s:elseif test="%{productId}">
					<p class="sectionHeaderText">Successfully Updated <i><s:property value="name"/></i></p>
				</s:elseif>
				<s:else>
					<p class="sectionHeaderText">Successfully Added <i><s:property value="name"/></i></p>
				</s:else>
			</div>

			<pre style="text-align:left;">
Name: <s:property value="name"/>
Description: <s:property value="description"/>
Stock: <s:property value="stock"/>
Price: <s:property value="price"/>
Manufacturer: <s:property value="manufacturerId"/>
Iamge: <s:property value="image" />
			</pre>

			<s:if test="%{image == ''}">
				<img alt="Image" src="<s:url value="%{@ws.utils.Constants@PRODUCT_IMAGE_PATH}/missing.jpg" />" />
			</s:if>
			<s:else>
				<img alt="Image" src="<s:url value="%{@ws.utils.Constants@PRODUCT_IMAGE_PATH}/%{image}" />" />
			</s:else>
		</div>
    </body>
</html>
