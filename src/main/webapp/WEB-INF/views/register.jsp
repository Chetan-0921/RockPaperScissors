<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>

<div class="page">
<div class="card" style="max-width:420px;">

    <h1 class="title">Create Account</h1>
    <p class="subtitle">Join and compete on the leaderboard</p>

    <% if(request.getAttribute("error") != null){ %>
        <div class="alert alert-error">${error}</div>
    <% } %>

    <form method="post" action="${pageContext.request.contextPath}/register">

        <div class="form-group">
            <label>Username</label>
            <input type="text" name="username" required autofocus/>
        </div>

        <div class="form-group">
            <label>Email</label>
            <input type="email" name="email" required/>
        </div>

        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" required minlength="6"/>
        </div>

        <div class="form-group">
            <label>Confirm Password</label>
            <input type="password" name="confirm" required/>
        </div>

        <button type="submit" class="btn btn-primary btn-full">Create Account</button>

    </form>

    <hr class="divider"/>

    <p style="text-align:center; font-size:14px; color:var(--muted);">
        Have an account?
        <a class="link" href="${pageContext.request.contextPath}/login">Log in</a>
    </p>

</div>
</div>

</body>
</html>