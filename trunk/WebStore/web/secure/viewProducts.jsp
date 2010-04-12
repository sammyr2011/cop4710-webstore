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
			div.briefProductLeftFrame{
				text-align: center;
				width: 128px;
				height: 100%;
				float: left;
			}
			div.briefProductMiddleFrame{
				width: 100%;
				height: 100%;
				padding-top: 10px;
			}
			div.briefProductRightFrame{
				width: 128px;
				height: 100%;
				float: right;
				padding-top: 10px;
			}
			img.productThumbnail {
				border: 0;
				width: 100px;
				height:100px;
			}
			ul.productList {
				text-align: left;
				list-style: none;
				margin-right: 50px;
			}
			li.productListEntry {
				border-bottom: gray;
				border-bottom-width: thin;
				border-bottom-style: dashed;
				margin-bottom: 50px;
			}
			.briefProductMiddleDescriptionText {
				text-indent: 5px;
			}
			.briefProductLeftRating {
				text-align: center;
			}

		</style>
    </head>
    <body>
		<div class="content">
			<s:include value="/header.jsp" />
			<div class="sectionHeader">
				<p class="sectionHeaderText">Products</p>
			</div>

			<ul class="productList">
				<s:iterator value="products" var="product">
					<li class="productListEntry">
						<div style="height: 130px;">
							<div class="briefProductLeftFrame">
								<div class="briefProductLeftImage">
									<a  href="<s:url action="viewProduct"><s:param name="productId" value="#product.id"/></s:url>">
										<img class="productThumbnail" alt="missing" src="<s:url namespace="/" action="%{#product.image}"/>"/>
									</a>
								</div>
								<div class="briefProductLeftRating">
									<b>Rating</b>:
									<s:if test="#product.averageRating">
										<s:text name="format.percent">
											<s:param value="%{#product.averageRating}" />
										</s:text>
									</s:if>
									<s:else>
										N/A
									</s:else>
								</div>
							</div>
							<div class="briefProductRightFrame">
								<s:text name="format.currency">
									<s:param value="#product.price"/>
								</s:text>
								<s:if test="#product.stock <= 0">
									<br/><b>Sold out</b>
								</s:if>
							</div>
							<div class="briefProductMiddleFrame">
								<div class="briefProductMiddleTitle">
									<a href="<s:url action="viewProduct"><s:param name="productId" value="#product.id"/></s:url>"><s:property value="#product.name" /></a>
								</div>
								<div class="briefProductMiddleDescription">
									<p class="briefProductMiddleDescriptionText"><s:property value="#product.description" /></p>
								</div>
							</div>

						</div>
					</li>
				</s:iterator>
			</ul>
		</div>
    </body>
</html>
