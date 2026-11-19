# Community Complaint Management

A Java 17 / Jakarta Servlet web application with JSP pages and MySQL storage. The files are wired as a Maven WAR project.

## Requirements
- JDK 17+
- Maven 3.9+
- MySQL 8+
- A Jakarta Servlet 6 container (for example, Tomcat 10.1+)

## Set up the database
Run `src/main/resources/schema.sql` in MySQL. Configure the app with environment variables (defaults are `root` and an empty password):

```sh
export DB_URL='jdbc:mysql://localhost:3306/community_complaint_db?useSSL=false&serverTimezone=UTC'
export DB_USER='root'
export DB_PASSWORD='your-mysql-password'
```

## Build and run
Build the deployable WAR:

```sh
mvn clean package
```

Deploy `target/community-complaints.war` to Tomcat 10.1+, then open `http://localhost:8080/community-complaints/`. The project cannot run directly from GitHub; it must be built and deployed in an environment with the requirements above.

Registration, login, logout, and complaint submission/history are connected. Passwords are stored as salted PBKDF2 hashes. Do not expose database credentials in source control.
