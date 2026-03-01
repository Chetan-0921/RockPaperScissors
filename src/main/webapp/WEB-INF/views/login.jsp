<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title>Login</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>

<div class="page">
<div class="card" style="max-width:420px;">

  <h1 class="title">RPS Game</h1>
  <p class="subtitle">Sign in to track your scores</p>

  <% if(request.getAttribute("error") != null){ %>
    <div class="alert alert-error">${error}</div>
  <% } %>

  <% if(request.getAttribute("success") != null){ %>
    <div class="alert alert-success">${success}</div>
  <% } %>

  <form method="post" action="${pageContext.request.contextPath}/login">

    <div class="form-group">
      <label>Username</label>
      <input type="text" name="username" required autofocus/>
    </div>

    <div class="form-group">
      <label>Password</label>
      <input type="password" name="password" required/>
    </div>

    <button type="submit" class="btn btn-primary btn-full">Log In</button>

  </form>

  <hr class="divider"/>

  <p style="text-align:center; font-size:14px; color:var(--muted);">
    No account?
    <a class="link" href="${pageContext.request.contextPath}/register">Create one</a>
  </p>

</div>
</div>

</body>
</html>