<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Edit User <s:property value="user.userName"/></title>
		<link rel="stylesheet" type="text/css" href="<s:url value="/main.css"/>"/>
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
				<p class="sectionHeaderText">Edit User <i><s:property value="user.userName"/></i></p>
			</div>
		<s:form action="editUser">
			<s:hidden  name="username" label="username" value="%{user.userName}" />
			<s:textfield name="email" label="E-Mail" value="%{user.email}" maxLength="%{@ws.utils.Constants@LEN_USER_EMAIL}" />
			<s:password name="password" label="New Password" />
			<s:password name="passwordCheck" label="New Password Again" />
			<s:textfield name="firstName" label="First Name" value="%{user.firstName}"  maxLength="%{@ws.utils.Constants@LEN_USER_FIRSTNAME}" />
			<s:textfield name="lastName" label="Last Name"  value="%{user.lastName}"  maxLength="%{@ws.utils.Constants@LEN_USER_LASTNAME}" />
			<s:textfield name="address" label="Address"  value="%{user.address}" maxLength="%{@ws.utils.Constants@LEN_USER_ADDRESS}" />
			<s:textfield name="phone" label="Phone Number"  value="%{user.phone}" maxLength="%{@ws.utils.Constants@LEN_USER_PHONE}" />
			<s:checkbox name="admin" label="Administrator"  value="%{user.admin}"  style="width: auto;" />
			<s:hidden name="userId" value="%{userId}"/>
			<s:hidden name="submit" value="true" />
			<s:submit style="width: auto;" />
		</s:form>
			</div>
		</div>
    </body>
</html>
