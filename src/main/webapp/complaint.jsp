<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Your complaints</title><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css"><script src="${pageContext.request.contextPath}/assets/app.js" defer></script></head>
<body><main class="container wide"><header><h1>Your complaints</h1><p>Signed in as <%= org.apache.taglibs.standard.functions.Functions.escapeXml(String.valueOf(session.getAttribute("userName"))) %> · <a href="${pageContext.request.contextPath}/logout">Log out</a></p></header>
<% if (request.getParameter("submitted") != null) { %><p class="success">Your complaint was submitted.</p><% } %>
<h2>Submit a complaint</h2><form action="${pageContext.request.contextPath}/complaint" method="post">
<label for="title">Title</label><input id="title" name="title" minlength="3" required>
<label for="description">Description</label><textarea id="description" name="description" rows="5" minlength="15" required></textarea>
<button type="submit">Submit complaint</button></form>
<h2>Previous complaints</h2><%
ResultSet complaints = (ResultSet) request.getAttribute("complaints");
boolean found = false;
while (complaints != null && complaints.next()) { found = true; %>
<article class="complaint"><h3><%= org.apache.taglibs.standard.functions.Functions.escapeXml(complaints.getString("title")) %></h3>
<p><%= org.apache.taglibs.standard.functions.Functions.escapeXml(complaints.getString("description")) %></p>
<p>Status: <strong><%= org.apache.taglibs.standard.functions.Functions.escapeXml(complaints.getString("status")) %></strong> · <%= complaints.getTimestamp("created_at") %></p></article>
<% } if (!found) { %><p>No complaints yet.</p><% } %></main></body></html>
