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
				<p class="sectionHeaderText">Review product <i><s:property value="%{product.name}"/></i></p>
			</div>

			<pre>
Item: <s:property value="%{product.name}"/>
Description: <s:property value="%{product.description"/>
Price: <s:property value="%{product.price}"/>
Shipping: TODO
			</pre>
			<s:form>
				<s:textfield name="comment" />
				<s:select name="rating" list="{1,2,3,4,5,6,7,8,9,10}" label="asdf" />
				<s:hidden name="submit" value="true" />
				<s:hidden name="productId" value="%{product.id}"/>
				<s:submit style="width: auto;" value="Confirm Purchase" />
			</s:form>
		</div>
    </body>
</html>