<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, com.rps.model.*" %>
<%
    String ctx = request.getContextPath();
    List<LeaderboardEntry> entries = (List<LeaderboardEntry>) request.getAttribute("entries");
    User me = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Leaderboard</title>
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
            <a href="<%= ctx %>/stats">Stats</a>
            <a href="<%= ctx %>/leaderboard" class="active">Leaderboard</a>
            <a href="<%= ctx %>/logout" class="btn btn-sm btn-danger">Logout</a>
        </div>
    </nav>

    <h1 class="title">Leaderboard</h1>

    <% if(entries == null || entries.isEmpty()){ %>
        <p style="color:var(--muted); text-align:center; margin-top:30px;">
            No players yet. Be the first!
        </p>
    <% } else { %>

        <table>
            <thead>
                <tr>
                    <th>Rank</th>
                    <th>Player</th>
                    <th>Rounds</th>
                    <th>Wins</th>
                    <th>Losses</th>
                    <th>Ties</th>
                    <th>Win %</th>
                </tr>
            </thead>
            <tbody>
            <% for(LeaderboardEntry e : entries){
                   String rankClass = e.getRank() == 1 ? "rank-1"
                                    : e.getRank() == 2 ? "rank-2"
                                    : e.getRank() == 3 ? "rank-3" : "";
                   String medal = e.getRank() == 1 ? "Gold"
                                : e.getRank() == 2 ? "Silver"
                                : e.getRank() == 3 ? "Bronze" : "";
                   boolean isMe = me != null && me.getUsername().equals(e.getUsername());
            %>
                <tr style="<%= isMe ? "background:rgba(120,120,255,.12);" : "" %>">

                    <td class="<%= rankClass %>">
                        <%= medal.isEmpty() ? String.valueOf(e.getRank()) : medal %>
                    </td>

                    <td>
                        <strong><%= e.getUsername() %></strong>
                        <% if(isMe){ %>
                            <span style="font-size:11px; color:#a0a8ff;">(you)</span>
                        <% } %>
                    </td>

                    <td style="color:var(--muted)"><%= e.getTotalRounds() %></td>

                    <td style="color:var(--green); font-weight:700"><%= e.getWins() %></td>

                    <td style="color:var(--red)"><%= e.getLosses() %></td>

                    <td style="color:var(--muted)"><%= e.getTies() %></td>

                    <td>
                        <strong style="color:var(--gold)"><%= e.getWinPct() %>%</strong>
                    </td>

                </tr>
            <% } %>
            </tbody>
        </table>

    <% } %>

</div>
</div>

</body>
</html>