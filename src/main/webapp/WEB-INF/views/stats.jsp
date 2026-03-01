<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%
    String ctx = request.getContextPath();
    int total   = (Integer) request.getAttribute("totalRounds");
    int wins    = (Integer) request.getAttribute("totalWins");
    int losses  = (Integer) request.getAttribute("totalLosses");
    int ties    = (Integer) request.getAttribute("totalTies");
    String winPct = (String) request.getAttribute("winPct");
    Map<String, Integer> moves = (Map<String, Integer>) request.getAttribute("moveCounts");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Stats</title>
    <link rel="stylesheet" href="<%= ctx %>/css/style.css"/>
</head>
<body>

<div class="page" style="align-items:flex-start; padding:30px 20px;">
<div class="card">

    <nav>
        <span class="brand">RPS Game</span>
        <div class="nav-links">
            <a href="<%= ctx %>/game">Play</a>
            <a href="<%= ctx %>/history">History</a>
            <a href="<%= ctx %>/stats" class="active">Stats</a>
            <a href="<%= ctx %>/leaderboard">Leaderboard</a>
            <a href="<%= ctx %>/logout" class="btn btn-sm btn-danger">Logout</a>
        </div>
    </nav>

    <h1 class="title">Your Stats</h1>

    <div class="stats-grid">

        <div class="stat-card">
            <div class="stat-num"><%= total %></div>
            <div class="stat-label">Total Rounds</div>
        </div>

        <div class="stat-card">
            <div class="stat-num" style="color:var(--green)"><%= wins %></div>
            <div class="stat-label">Wins</div>
        </div>

        <div class="stat-card">
            <div class="stat-num" style="color:var(--red)"><%= losses %></div>
            <div class="stat-label">Losses</div>
        </div>

        <div class="stat-card">
            <div class="stat-num" style="color:var(--muted)"><%= ties %></div>
            <div class="stat-label">Ties</div>
        </div>

        <div class="stat-card">
            <div class="stat-num"><%= winPct %>%</div>
            <div class="stat-label">Win Rate</div>
        </div>

    </div>

    <% if(total > 0){ %>

        <p style="font-size:12px; color:var(--muted); text-transform:uppercase;
                  letter-spacing:.5px; margin-bottom:14px;">
            Your Favourite Moves
        </p>

        <% for(Map.Entry<String, Integer> e : moves.entrySet()){
               int pct = (int)(e.getValue() * 100.0 / total);
        %>
            <div style="margin-bottom:14px;">

                <div style="display:flex; justify-content:space-between;
                            font-size:14px; margin-bottom:5px;">
                    <span>
                        <img src="<%= ctx %>/Rps/<%= e.getKey().toLowerCase() %>-emoji.png"
                             style="width:18px; vertical-align:middle; margin-right:6px;"/>
                        <%= e.getKey() %>
                    </span>
                    <span style="color:var(--muted)">
                        <%= e.getValue() %> times (<%= pct %>%)
                    </span>
                </div>

                <div style="background:rgba(255,255,255,.08); border-radius:999px;
                            height:8px; overflow:hidden;">
                    <div style="background:linear-gradient(90deg,#a0a8ff,#6cf);
                                width:<%= pct %>%;
                                height:100%;
                                border-radius:999px;">
                    </div>
                </div>

            </div>
        <% } %>

    <% } else { %>
        <p style="color:var(--muted); text-align:center; margin-top:20px;">
            Play some rounds first to see your stats!
        </p>
    <% } %>

</div>
</div>

</body>
</html>