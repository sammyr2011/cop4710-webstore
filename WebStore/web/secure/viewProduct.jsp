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
			td.transactionTableColLabel {
				text-align: right;
				font-weight: bold;
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
				<s:if test="product.stock > 0">
					<s:form action="user/buyProduct">
						<s:hidden name="productId" value="%{product.id}"/>
						<s:submit style="width: auto;" value="Buy product" />
					</s:form>
				</s:if>
				<s:else>
					<b>Sold out</b>
				</s:else>
				<s:form action="user/reviewProduct">
					<s:hidden name="productId" value="%{product.id}"/>
					<s:submit style="width: auto;" value="Review product" />
				</s:form>
			</s:if>

			<table width="100%">
				<tr>
					<td width="50%">
						<div class="leftProductView">
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
									<td class="transactionTableColData">
										<s:text name="format.currency">
											<s:param value="%{product.price}"/>
										</s:text>
									</td>
								</tr>
								<tr class="transactionTableRow">
									<td class="transactionTableColLabel">Shipping:</td>
									<td class="transactionTableColData">TODO</td>
								</tr>
								<tr class="transactionTableRow">
									<td class="transactionTableColLabel">Stock:</td>
									<td class="transactionTableColData"><s:property value="%{product.stock}"/></td>
								</tr>
								<tr class="transactionTableRow">
									<td class="transactionTableColLabel">Manufacturer:</td>
									<td class="transactionTableColData"><s:property value="%{product.manufacturerName}"/></td>
								</tr>
							</table>
						</div>
					</td>
					<td width="50%" valign="top">
						<div class="rightProductView">
							<div class="viewIdeasIdea">
								(TODO: fix null check)<br/>
								<b>Average Rating</b>: 
								<s:if test="%{product.averageRating} == null">
									Not available
								</s:if>
								<s:else>
									<s:text name="format.percent">
										<s:param value="product.averageRating" />
									</s:text>
								</s:else>
								<s:iterator value="product.reviews" var="review">
									<ul>
										<li>
											<b><s:property value="#review.userName" /></b> - <s:property value="#review.rating"/> / 10<br/>
											<s:property value="#review.comment"/>
										</li>
									</ul>
								</s:iterator>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
