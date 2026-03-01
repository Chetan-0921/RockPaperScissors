<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.rps.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    String pm = (String) request.getAttribute("playerMove");
    String cm = (String) request.getAttribute("computerMove");
    String res = (String) request.getAttribute("result");

    Integer mW = (Integer) request.getAttribute("mWins");
    Integer mL = (Integer) request.getAttribute("mLosses");
    Integer mT = (Integer) request.getAttribute("mTies");

    if(mW == null) mW = (Integer) session.getAttribute("mWins");
    if(mL == null) mL = (Integer) session.getAttribute("mLosses");
    if(mT == null) mT = (Integer) session.getAttribute("mTies");

    if(mW == null) mW = 0;
    if(mL == null) mL = 0;
    if(mT == null) mT = 0;

    String rLabel = "";
    String rClass = "";

    if("Win".equals(res)) {
        rLabel = "You Win!";
        rClass = "win";
    } else if("Loss".equals(res)) {
        rLabel = "You Lose";
        rClass = "loss";
    } else if("Tie".equals(res)) {
        rLabel = "Tie!";
        rClass = "";
    }

    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Play RPS</title>
    <link rel="stylesheet" href="<%= ctx %>/css/style.css"/>
</head>
<body>

<div class="page">
<div class="card">

    <nav>
        <span class="brand">RPS Game</span>
        <div class="nav-links">
            <a href="<%= ctx %>/game" class="active">Play</a>
            <a href="<%= ctx %>/history">History</a>
            <a href="<%= ctx %>/stats">Stats</a>
            <a href="<%= ctx %>/leaderboard">Leaderboard</a>
            <a href="<%= ctx %>/logout" class="btn btn-sm btn-danger">Logout</a>
        </div>
    </nav>

    <h1 class="title">Rock Paper Scissors</h1>
    <p class="subtitle">
        Hey <strong style="color:#fff"><%= user.getUsername() %></strong>, choose your move!
    </p>

    <form method="post" action="<%= ctx %>/game">
        <div class="choices">

            <button type="submit" name="move" value="Rock" class="choice-btn">
                <img class="choice-img" src="<%= ctx %>/Rps/rock-emoji.png" alt="Rock"/>
            </button>

            <button type="submit" name="move" value="Paper" class="choice-btn">
                <img class="choice-img" src="<%= ctx %>/Rps/paper-emoji.png" alt="Paper"/>
            </button>

            <button type="submit" name="move" value="Scissors" class="choice-btn">
                <img class="choice-img" src="<%= ctx %>/Rps/scissors-emoji.png" alt="Scissors"/>
            </button>

        </div>
    </form>

    <% if(res != null){ %>
        <p class="result-text <%= rClass %>"><%= rLabel %></p>
        <div class="battle">
            <span>You</span>
            <img class="battle-img" src="<%= ctx %>/Rps/<%= pm.toLowerCase() %>-emoji.png" alt="<%= pm %>"/>
            <span class="vs">vs</span>
            <img class="battle-img" src="<%= ctx %>/Rps/<%= cm.toLowerCase() %>-emoji.png" alt="<%= cm %>"/>
            <span>Computer</span>
        </div>
    <% } else { %>
        <p style="text-align:center; color:var(--muted); margin:16px 0;">
            Make your move above
        </p>
    <% } %>

    <div class="score-bar">
        <span class="score-pill wins">Wins: <%= mW %></span>
        <span class="score-pill losses">Losses: <%= mL %></span>
        <span class="score-pill ties">Ties: <%= mT %></span>
    </div>

    <div style="text-align:center; margin-top:20px;">
        <a href="<%= ctx %>/game" class="btn">New Match</a>
    </div>

</div>
</div>

</body>
</html>