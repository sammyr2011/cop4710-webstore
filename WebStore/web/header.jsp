<%@taglib prefix="s" uri="/struts-tags" %>
<div class="headerImage" align="center">
	<img src="<s:url value="/logo.jpg"/>" alt="Awesome logo goes here"  />
</div>
<div class="header" align="left">
	<s:if test="#session.user">
		<a href="<s:url value="/secure/viewProducts"/>">View Products</a> |
		<s:if test="!#session.user.admin">
			<a href="<s:url value="/secure/user/editAccount"/>">Edit Account</a> |
			<a href="<s:url value="/secure/user/transactionHistory"/>">Purchase History</a> |
		</s:if>
		<s:else>
			<a href="<s:url value="/secure/admin/addUser"/>">Add User</a> |
			<a href="<s:url value="/secure/admin/viewUsers"/>">Edit Users</a> |
			<a href="<s:url value="/secure/admin/addProduct"/>">Add Product</a> |
			<a href="<s:url value="/secure/admin/editProducts"/>">Edit Products</a> |
			<a href="<s:url value="/secure/admin/addManufacturer"/>">Add Manufacturer</a> |
			<a href="<s:url value="/secure/admin/viewManufacturers"/>">View Manufacturers</a> |
			<a href="<s:url value="/secure/admin/transactionHistory"/>">View Purchases</a> |
		</s:else>
		<a href="<s:url value="/logout"/>">Logout</a>
	</s:if>
	<s:else>
		<a href="<s:url value="/newAccount"/>">New Account</a> |
		<a href="<s:url value="/login"/>">Login</a>
	</s:else>
</div>
