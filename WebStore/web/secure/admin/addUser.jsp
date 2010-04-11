<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add User</title>
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
			<div>
				<div class="sectionHeader">
					<p class="sectionHeaderText">Add User</p>
				</div>
				<s:form>
					<s:textfield name="username" label="Username" maxLength="%{@ws.utils.Constants@LEN_USER_USERNAME}"/>
					<s:textfield name="email" label="E-Mail"  maxLength="%{@ws.utils.Constants@LEN_USER_EMAIL}" />
					<s:password name="password" label="Password" maxLength="" />
					<s:password name="passwordCheck" label="Password Again" maxLength=""  />
					<s:textfield name="firstName" label="First Name" maxLength="%{@ws.utils.Constants@LEN_USER_FIRSTNAME}" />
					<s:textfield name="lastName" label="Last Name" maxLength="%{@ws.utils.Constants@LEN_USER_LASTNAME}" />
					<s:textfield name="Address" label="Address" maxLength="%{@ws.utils.Constants@LEN_USER_ADDRESS}" />
					<s:textfield name="phone" label="Phone Number" maxLength="%{@ws.utils.Constants@LEN_USER_PHONE}" />
					<s:checkbox name="admin" label="Administrator" style="width: auto;" />
					<s:hidden name="submit" value="true"  />
					<s:submit style="width: auto;"  />
				</s:form>
			</div>
		</div>
    </body>
</html>
