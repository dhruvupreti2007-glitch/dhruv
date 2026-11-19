<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Log in</title><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css"><script src="${pageContext.request.contextPath}/assets/app.js" defer></script></head>
<body><main class="container"><h1>Log in</h1>
<% if (request.getParameter("registered") != null) { %><p class="success">Account created. Please log in.</p><% } %>
<form action="${pageContext.request.contextPath}/login" method="post">
<label for="email">Email</label><input id="email" name="email" type="email" autocomplete="email" required>
<label for="password">Password</label><input id="password" name="password" type="password" autocomplete="current-password" required>
<button type="submit">Log in</button></form><p>New here? <a href="${pageContext.request.contextPath}/register.jsp">Create an account</a></p></main></body></html>
