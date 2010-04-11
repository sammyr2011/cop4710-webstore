<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Generic title</title>
		<link rel="stylesheet" type="text/css" href="<s:url value="/main.css"/>"/>
		<style type="text/css">
			table.transactionTable {
				padding: 5px;
			}
			tr.transactionTableRow {

			}
			td.transactionTableColLabel {
				text-align: right;
				font-weight: bold;
			}
			td.transactionTableColData {

			}
		</style>
    </head>

    <body>
		<div class="content">
			<s:include value="/header.jsp" />
			<div class="sectionHeader">
				<p class="sectionHeaderText">Product Details</p>
			</div>

			<s:if test="!#session.user.admin">
				<s:form action="user/buyProduct">
					<s:hidden name="productId" value="%{product.id}"/>
					<s:submit style="width: auto;" value="Buy product" />
				</s:form>
				<s:form action="user/reviewProduct">
					<s:hidden name="productId" value="%{product.id}"/>
					<s:submit style="width: auto;" value="Review product" />
				</s:form>
			</s:if>

			<img width="300" src="<s:url value="%{product.image}"/>" alt="Image goes here"/>
			<table class="transactionTable">
				<tr class="transactionTableRow">
					<td class="transactionTableColLabel">Product name: </td>
					<td class="transactionTableColData"><s:property value="%{product.name}"/></td>
				</tr>
				<tr class="transactionTableRow">
					<td class="transactionTableColLabel">Description: </td>
					<td class="transactionTableColData"><s:property value="%{product.description}"/></td>
				</tr>
				<tr class="transactionTableRow">
					<td class="transactionTableColLabel">Price:</td>
					<td class="transactionTableColData">$<s:property value="%{product.price}"/> (Shipping: TODO)</td>
				</tr>
				<tr class="transactionTableRow">
					<td class="transactionTableColLabel">Stock:</td>
					<td class="transactionTableColData"><s:property value="%{product.stock}"/></td>
				</tr>
				<tr class="transactionTableRow">
					<td class="transactionTableColLabel">Manufacturer:</td>
					<td class="transactionTableColData"><s:property value="%{manufacturerName}"/></td>
				</tr>
			</table>
		</div>
    </body>
</html>
