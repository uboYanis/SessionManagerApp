<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page session="true"%>
<html>
<head>
<title>Welcome</title>
</head>
<body>
	<h2>
		Bonjour,
		<%=session.getAttribute("username")%>
		!
	</h2>
	<h3>
		Vous vous êtes connecté(e)
		<%=session.getAttribute("sessionCount")%>
		fois durant la session portant l'ID
		<%=session.getAttribute("sessionId")%>.
	</h3>

	<h4>
		Nombre total d'utilisations de la servlet :
		<%=application.getAttribute("usageCount")%>
	</h4>
	<h4>
		Dernier appel à la servlet :
		<%=application.getAttribute("lastAccessTime")%>
	</h4>

	<a href="logout">Logout</a>
</body>
</html>
