# How to Run Spring Boot Application with Maven

## Quick Start

Open your terminal and run:

```bash
cd /Users/macbookairm1/Documents/GitHub/CULTARAL-pro/backend
mvn spring-boot:run
```

## What This Does

- Compiles your Java source code
- Downloads dependencies
- Starts the embedded Tomcat server
- Runs your Spring Boot application

## Expected Output

You should see output like:

```
[INFO] Scanning for projects...
[INFO] 
[INFO] --------< com.bangla:bangla-learning-lms >--------
[INFO] Building Bangla Learning LMS 1.0.0
[INFO] --------
[INFO]
[INFO] --- spring-boot-maven-plugin:3.2.0:run (default-cli) @ bangla-learning-lms ---
[INFO] Attaching agents: []

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.0)

2025-12-16T00:50:10.123+06:00  INFO 12345 --- [           main] c.b.l.BanglaLearningLmsApplication      : Starting BanglaLearningLmsApplication v1.0.0
2025-12-16T00:50:10.456+06:00  INFO 12345 --- [           main] c.b.l.BanglaLearningLmsApplication      : No active profile set, falling back to default profiles: [default]
...
2025-12-16T00:50:15.789+06:00  INFO 12345 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http)
2025-12-16T00:50:15.890+06:00  INFO 12345 --- [           main] c.b.l.BanglaLearningLmsApplication      : Started BanglaLearningLmsApplication in 5.123 seconds
```

## Application is Ready

Once you see "Tomcat started on port(s): 8080", your application is running and you can access:

- **Main App**: http://localhost:8080
- **H2 Database Console**: http://localhost:8080/h2-console
- **API Endpoints**: http://localhost:8080/api/...

## Stop the Application

Press `Ctrl + C` in your terminal to stop the server.

## Alternative Commands

### Clean compile and run
```bash
mvn clean spring-boot:run
```

### Run with specific profiles
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod"
```

### Run in background (Linux/macOS)
```bash
nohup mvn spring-boot:run > app.log 2>&1 &
```

## Troubleshooting

If you get `ClassNotFoundException`, try:
```bash
mvn clean compile spring-boot:run
```

If port 8080 is already in use, change it:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8090"
```

## Your POM Configuration

Your `pom.xml` is already properly configured with:
- ✅ Spring Boot 3.2.0
- ✅ Java 17 compiler
- ✅ Explicit `mainClass` declaration
- ✅ Proper plugin versions
- ✅ `-parameters` compiler flag (required for Spring `@ConfigurationProperties`)

Everything is ready to go!
