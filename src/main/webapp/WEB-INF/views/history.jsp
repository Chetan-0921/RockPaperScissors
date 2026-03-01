<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, com.rps.model.*" %>
<%
    String ctx = request.getContextPath();
    List<Match> matches = (List<Match>) request.getAttribute("matches");
    List<Round> rounds = (List<Round>) request.getAttribute("rounds");
    Integer selId = (Integer) request.getAttribute("matchId");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>History</title>
    <link rel="stylesheet" href="<%= ctx %>/css/style.css"/>
</head>
<body>

<div class="page" style="align-items:flex-start; padding:30px 20px;">
<div class="card">

    <nav>
        <span class="brand">RPS Game</span>
        <div class="nav-links">
            <a href="<%= ctx %>/game">Play</a>
            <a href="<%= ctx %>/history" class="active">History</a>
            <a href="<%= ctx %>/stats">Stats</a>
            <a href="<%= ctx %>/leaderboard">Leaderboard</a>
            <a href="<%= ctx %>/logout" class="btn btn-sm btn-danger">Logout</a>
        </div>
    </nav>

    <h1 class="title">Match History</h1>

    <div style="display:grid; grid-template-columns:220px 1fr; gap:20px;">

        <div>
            <p style="font-size:12px; color:var(--muted); text-transform:uppercase; margin-bottom:10px;">
                Your Matches
            </p>

            <% if(matches == null || matches.isEmpty()){ %>
                <p style="color:var(--muted); font-size:14px;">No matches yet.</p>
            <% } else {
                int n = matches.size();
                for(Match m : matches){
                    boolean selected = selId != null && selId == m.getId();
            %>
                <a href="<%= ctx %>/history?matchId=<%= m.getId() %>"
                   style="display:block;
                          padding:10px 14px;
                          border-radius:10px;
                          margin-bottom:6px;
                          text-decoration:none;
                          border:1px solid <%= selected ? "rgba(160,168,255,.5)" : "var(--glass-br)" %>;
                          background:<%= selected ? "rgba(120,120,255,.18)" : "rgba(0,0,0,.2)" %>;">
                    <span style="color:#fff; font-weight:600; font-size:14px;">
                        Match #<%= n-- %>
                    </span>
                    <br/>
                    <span style="color:var(--muted); font-size:12px;">
                        <%= m.getPlayedAt() %>
                    </span>
                </a>
            <% }} %>
        </div>

        <div>
            <% if(rounds != null && !rounds.isEmpty()){ %>

                <p style="font-size:12px; color:var(--muted); text-transform:uppercase; margin-bottom:10px;">
                    Rounds in Match #<%= selId %>
                </p>

                <table>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Your Move</th>
                            <th>Computer</th>
                            <th>Result</th>
                        </tr>
                    </thead>
                    <tbody>
                    <% int rn = 1;
                       for(Round r : rounds){
                           String badge = "Win".equals(r.getResult()) ? "badge-win"
                                        : "Loss".equals(r.getResult()) ? "badge-loss"
                                        : "badge-tie";
                    %>
                        <tr>
                            <td style="color:var(--muted)"><%= rn++ %></td>
                            <td>
                                <img src="<%= ctx %>/Rps/<%= r.getPlayerMove().toLowerCase() %>-emoji.png"
                                     style="width:20px; vertical-align:middle; margin-right:5px;"/>
                                <%= r.getPlayerMove() %>
                            </td>
                            <td>
                                <img src="<%= ctx %>/Rps/<%= r.getComputerMove().toLowerCase() %>-emoji.png"
                                     style="width:20px; vertical-align:middle; margin-right:5px;"/>
                                <%= r.getComputerMove() %>
                            </td>
                            <td>
                                <span class="badge <%= badge %>"><%= r.getResult() %></span>
                            </td>
                        </tr>
                    <% } %>
                    </tbody>
                </table>

            <% } else { %>
                <p style="color:var(--muted); margin-top:20px;">
                    Select a match on the left to see its rounds
                </p>
            <% } %>
        </div>

    </div>

</div>
</div>

</body>
</html>