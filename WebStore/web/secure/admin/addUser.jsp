<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add User</title>
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
				<s:if test="userAdded">
					<div class="sectionHeader">
						<p class="sectionHeaderText">Added user "<s:property value="username"/>"</p>
					</div>
					<pre style="text-align:left;">
Name: <s:property value="firstName"/> <s:property value="lastName"/>
Email: <s:property value="email"/>
Admin: <s:property value="admin"/>
					</pre>
				</s:if>
				<div class="sectionHeader">
					<p class="sectionHeaderText">Add User</p>
				</div>
				<s:form>
					<s:textfield name="username" label="Username" maxLength="32"/>
					<s:textfield name="email" label="E-Mail"  maxLength="64" />
					<s:password name="password" label="Password" maxLength="32" />
					<s:password name="passwordCheck" label="Password Again" maxLength="32"  />
					<s:textfield name="firstName" label="First Name" maxLength="32" />
					<s:textfield name="lastName" label="Last Name" maxLength="32" />
					<s:textfield name="Address" label="Address" maxLength="256" />
					<s:textfield name="phone" label="Phone Number" maxLength="16" />
					<s:checkbox name="admin" label="Administrator" style="width: auto;" />
					<s:hidden name="submit" value="true"  />
					<s:submit style="width: auto;"  />
				</s:form>
			</div>
		</div>
    </body>
</html>
