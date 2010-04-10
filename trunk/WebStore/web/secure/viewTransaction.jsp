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
				<p class="sectionHeaderText">Transaction details</p>
			</div>

			<table class="transactionTable">
				<s:if test="#session.user.admin">
					<tr class="transactionTableRow">
						<td class="transactionTableColLabel">User: </td>
						<td class="transactionTableColData"><s:property value="%{transactionUser.userName}"/></td>
					</tr>
				</s:if>
				<tr class="transactionTableRow">
					<td class="transactionTableColLabel">Product name: </td>
					<td class="transactionTableColData"><s:property value="%{product.name}"/></td>
				</tr>
				<tr class="transactionTableRow">
					<td class="transactionTableColLabel">Price:</td>
					<td class="transactionTableColData">$<s:property value="%{transaction.price}"/> (Shipping: $<s:property value="%{transaction.shippingPrice}"/>)</td>
				</tr>
				<tr class="transactionTableRow">
					<td class="transactionTableColLabel">Shipping Address:</td>
					<td class="transactionTableColData"><s:property value="%{transaction.shippingAddress}"/></td>
				</tr>
				<tr class="transactionTableRow">
					<td class="transactionTableColLabel">Date purchased:</td>
					<td class="transactionTableColData"><s:property value="%{transaction.date}"/></td>
				</tr>
			</table>
		</div>
    </body>
</html>
