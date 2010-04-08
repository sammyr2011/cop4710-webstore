<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit Account Information</title>
		<link rel="stylesheet" type="text/css" href="<s:url value="/"/>main.css">
		<style type="text/css">
			input{
				width: 150px;
			}
			.tdLabel{
				text-align: right;
			}
		</style>
    </head>
    <body>
		<div class="content">
			<s:include value="/header.jsp" />
			<div align="center">
			<div class="sectionHeader">
				<p class="sectionHeaderText">Your account information</p>
			</div>
		<s:form action="editAccount">
			<s:textfield name="email" label="E-Mail" value="%{user.email}"/>
			<s:password name="password" label="New Password" />
			<s:password name="passwordCheck" label="New Password Again" />
			<s:textfield name="firstName" label="First Name" value="%{user.firstName}" />
			<s:textfield name="lastName" label="Last Name"  value="%{user.lastName}" />
			<s:textfield name="address" label="Address"  value="%{user.address}" />
			<s:textfield name="phone" label="Phone Number"  value="%{user.phone}" />
			<s:hidden name="userId" value="%{userId}"/>
			<s:hidden name="submit" value="true" />
			<s:submit style="width: auto;" />
		</s:form>
			</div>
		</div>
    </body>
</html>
