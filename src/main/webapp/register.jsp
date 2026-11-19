<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Register</title><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css"><script src="${pageContext.request.contextPath}/assets/app.js" defer></script></head>
<body><main class="container"><h1>Create account</h1>
<form action="${pageContext.request.contextPath}/register" method="post">
<label for="name">Full name</label><input id="name" name="name" autocomplete="name" minlength="2" required>
<label for="email">Email</label><input id="email" name="email" type="email" autocomplete="email" required>
<label for="password">Password (at least 8 characters)</label><input id="password" name="password" type="password" autocomplete="new-password" minlength="8" required>
<button type="submit">Register</button></form><p>Already registered? <a href="${pageContext.request.contextPath}/login">Log in</a></p></main></body></html>
