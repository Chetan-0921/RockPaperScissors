# Rock Paper Scissors - Full Stack Java Web App

A full-stack web application where users can register, login, play Rock Paper Scissors against the computer, view match history, check personal stats, and compete on a global leaderboard.

---

## Live Demo

> Run locally following the steps below

---

## Features

- User Registration and Login with SHA-256 password hashing
- Play Rock Paper Scissors against Computer
- Every game session saved as a Match
- Every throw saved as a Round
- Match History with round by round breakdown
- Personal Stats with win rate and favourite move chart
- Global Leaderboard showing top players
- Fully responsive dark glass UI

---

## Tools and Technologies Used

| Category | Technology |
|---|---|
| Frontend | HTML, CSS, JSP |
| Backend | Java Servlets |
| Database Access | JDBC, DAO Pattern |
| Database | MySQL 8 |
| Build Tool | Maven |
| Server | Apache Tomcat 9 |
| IDE | Eclipse Enterprise Edition |
| Version Control | Git and GitHub |
| Password Security | SHA-256 Hashing |

---

## Project Structure
```
RockPaperScissors/
  ├── src/
  │    └── main/
  │         ├── java/com/rps/
  │         │    ├── model/
  │         │    │    ├── User.java
  │         │    │    ├── Match.java
  │         │    │    ├── Round.java
  │         │    │    └── LeaderboardEntry.java
  │         │    ├── dao/
  │         │    │    ├── UserDAO.java
  │         │    │    ├── MatchDAO.java
  │         │    │    ├── RoundDAO.java
  │         │    │    └── LeaderboardDAO.java
  │         │    ├── servlet/
  │         │    │    ├── LoginServlet.java
  │         │    │    ├── RegisterServlet.java
  │         │    │    ├── GameServlet.java
  │         │    │    ├── HistoryServlet.java
  │         │    │    ├── StatsServlet.java
  │         │    │    ├── LeaderboardServlet.java
  │         │    │    └── LogoutServlet.java
  │         │    └── util/
  │         │         ├── DBConnection.java
  │         │         └── PasswordUtil.java
  │         ├── resources/
  │         │    └── db.properties        (not uploaded - see db.properties.example)
  │         └── webapp/
  │              ├── css/
  │              │    └── style.css
  │              ├── Rps/
  │              │    ├── rock-emoji.png
  │              │    ├── paper-emoji.png
  │              │    └── scissors-emoji.png
  │              ├── index.jsp
  │              └── WEB-INF/
  │                   ├── web.xml
  │                   └── views/
  │                        ├── login.jsp
  │                        ├── register.jsp
  │                        ├── game.jsp
  │                        ├── history.jsp
  │                        ├── stats.jsp
  │                        └── leaderboard.jsp
  ├── pom.xml
  ├── .gitignore
  └── database.sql
```

---

## Prerequisites

Make sure you install all of these before running the project:

### 1. Java JDK 11 or 17
- Go to `https://adoptium.net`
- Download JDK 17 for Windows
- Run the installer and click Next through everything
- Open Command Prompt and verify:
```
java -version
```
- You should see: openjdk version 17.x.x

### 2. MySQL 8
- Go to `https://dev.mysql.com/downloads/installer`
- Download MySQL Installer for Windows
- Choose Developer Default during installation
- Set a root password during installation
- Remember this password - you will need it later
- MySQL Workbench also installs automatically

### 3. Apache Tomcat 9
- Go to `https://tomcat.apache.org`
- Click Tomcat 9 on the left side
- Download the Windows zip file under Binary Distributions
- Unzip to a simple folder like:
```
C:\apache-tomcat-9.0.x
```
- No installer needed - just unzip

### 4. Eclipse IDE Enterprise Edition
- Go to `https://www.eclipse.org/downloads`
- Download Eclipse IDE for Enterprise Java and Web Developers
- This version is required - not the basic Java edition
- Run installer and click through everything

### 5. Git
- Go to `https://git-scm.com`
- Download Git for Windows
- Run installer and click Next through everything
- Verify in Command Prompt:
```
git --version
```

---

## How to Run This Project From Scratch

### Step 1 - Clone the Repository

Open Git Bash or Command Prompt and run:
```
git clone https://github.com/YourUsername/RockPaperScissors.git
```

Or download ZIP from GitHub and extract it.

### Step 2 - Set Up the Database

1. Open MySQL Workbench
2. Connect with your root password
3. Click File and Open SQL Script
4. Select the `database.sql` file from the project folder
5. Press Ctrl + Shift + Enter to run everything
6. You should see rps_game database created with 3 tables

### Step 3 - Configure Database Password

1. Go to `src/main/resources/`
2. Copy `db.properties.example` and rename it to `db.properties`
3. Open `db.properties` and change the password:
```
db.url=jdbc:mysql://localhost:3306/rps_game?useSSL=false&serverTimezone=Asia/Kolkata&allowPublicKeyRetrieval=true
db.user=root
db.password=YOUR_ACTUAL_MYSQL_PASSWORD
```

### Step 4 - Import Project in Eclipse

1. Open Eclipse
2. Click File
3. Click Import
4. Select Maven and then Existing Maven Projects
5. Click Next
6. Click Browse and select the project folder
7. Click Finish
8. Wait for Maven to download all dependencies

### Step 5 - Add Tomcat to Eclipse

1. Click Window in top menu
2. Click Preferences
3. Expand Server
4. Click Runtime Environments
5. Click Add
6. Select Apache Tomcat v9.0
7. Click Next
8. Click Browse and select your Tomcat folder like C:\apache-tomcat-9.0.x
9. Click Finish
10. Click Apply and Close

### Step 6 - Run the Project

1. Right-click the project in Eclipse
2. Click Run As
3. Click Run on Server
4. Select Tomcat v9.0 Server at localhost
5. Click Finish
6. Wait for Tomcat to start

### Step 7 - Open in Browser

Open any browser and go to:
```
http://localhost:8080/RockPaperScissors/
```

You should see the Login page.

---

## How to Use the App

1. Click Create one to register a new account
2. Enter username, email and password
3. Login with your credentials
4. Choose Rock, Paper or Scissors to play
5. Click New Match to start a fresh game session
6. Click History to see all your past matches
7. Click Stats to see your win rate and favourite moves
8. Click Leaderboard to see top players

---

## Database Schema
```sql
users
  - id, username, password (SHA-256 hashed), email, created_at

matches
  - id, user_id, played_at

rounds  
  - id, match_id, player_move, computer_move, result, played_at

leaderboard (view)
  - username, total_rounds, wins, losses, ties, win_pct
```

---

## Security

- Passwords are never stored as plain text
- All passwords are hashed using SHA-256 before saving to database
- Database credentials are stored in db.properties which is in .gitignore
- db.properties is never uploaded to GitHub
- Sessions expire after 30 minutes of inactivity

---

## Common Errors and Fixes

| Error | Fix |
|---|---|
| db.properties not found | Make sure file is in src/main/resources folder |
| Access denied for user root | Check your password in db.properties |
| 404 Page not found | Make sure packaging is war in pom.xml |
| Port 8080 already in use | Stop other Tomcat instances running |
| Images not showing | Make sure PNG files are in src/main/webapp/Rps folder |

---

## Author

- Name: Your Name
- GitHub: github.com/YourUsername
- LinkedIn: linkedin.com/in/YourUsername

---

## License

This project is open source and available for learning purposes.
```
