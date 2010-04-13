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
				<p class="sectionHeaderText">Confirmation</p>
			</div>

			<pre>
Item: <s:property value="%{product.name}"/>
Description: <s:property value="%{product.description}"/>
Price: <s:property value="%{product.price}"/>
Shipping: $5.00
			</pre>
			<s:form>
				<s:textfield name="shippingAddress" value="%{#session.user.address}" maxLength="%{@ws.utils.Constants@LEN_USER_ADDRESS}" />
				<s:hidden name="submit" value="true" />
				<s:hidden name="productId" value="%{product.id}"/>
				<s:submit style="width: auto;" value="Confirm Purchase" />
			</s:form>
		</div>
    </body>
</html>
