# Environment Configuration Guide

## Overview
All sensitive and environment-specific configurations have been externalized to environment variables.

## Setting Up Environment Variables

### Option 1: Using `.env` file (Local Development)
1. Copy `.env.example` to `.env`:
   ```bash
   cp .env.example .env
   ```
2. Update `.env` with your local values
3. The application will automatically pick up these variables

### Option 2: System Environment Variables (Production/Docker)
Set environment variables before running the application:
```bash
export DB_URL="jdbc:mysql://db-server:3306/prod_db"
export DB_USERNAME="db_user"
export DB_PASSWORD="secure_password"
export JPA_DDL_AUTO="update"
export SERVER_PORT="8080"
```

### Option 3: Docker Environment
In `docker-compose.yml` or Docker run command:
```bash
docker run -e DB_URL=jdbc:mysql://mysql:3306/test1 \
           -e DB_USERNAME=root \
           -e DB_PASSWORD=root \
           enterprise-crud-app
```

### Option 4: IDE Configuration (IntelliJ IDEA)
1. Run → Edit Configurations
2. Set Environment variables:
   ```
   DB_URL=jdbc:mysql://localhost:3306/test1;DB_USERNAME=root;DB_PASSWORD=root;JPA_DDL_AUTO=create-drop
   ```

## Environment Variables Reference

| Variable | Description | Default | Example |
|----------|-------------|---------|---------|
| `DB_URL` | JDBC connection URL | `jdbc:mysql://localhost:3306/test1` | `jdbc:mysql://prod-db:3306/app_db` |
| `DB_USERNAME` | Database user | `root` | `app_user` |
| `DB_PASSWORD` | Database password | `root` | `P@ssw0rd123` |
| `DB_DRIVER` | JDBC Driver class | `com.mysql.cj.jdbc.Driver` | (keep default) |
| `JPA_DDL_AUTO` | Hibernate DDL strategy | `create-drop` | `update`, `validate` |
| `JPA_SHOW_SQL` | Show SQL in logs | `false` | `true` (dev only) |
| `HIBERNATE_DIALECT` | Hibernate dialect | `org.hibernate.dialect.MySQLDialect` | (keep default) |
| `SWAGGER_UI_PATH` | Swagger UI path | `/swagger-ui.html` | (keep default) |
| `API_DOCS_PATH` | API docs path | `/v3/api-docs` | (keep default) |
| `SERVER_PORT` | Application port | `8080` | `9090` |
| `APP_NAME` | Application name | `enterprise-crud-app` | (keep default) |
| `LOG_LEVEL` | Root logging level | `INFO` | `DEBUG`, `WARN`, `ERROR` |
| `APP_LOG_LEVEL` | Application logging level | `DEBUG` | `INFO`, `DEBUG` |

## Security Best Practices

✅ **DO:**
- Store sensitive values in environment variables or secure vaults (AWS Secrets Manager, HashiCorp Vault)
- Use strong passwords in production
- Never commit `.env` files to version control
- Use `JPA_DDL_AUTO=validate` or `update` in production (not `create-drop`)
- Rotate database credentials regularly

❌ **DON'T:**
- Commit `.env` files to Git
- Use default credentials in production
- Share credentials in logs (JPA_SHOW_SQL=true only in dev)
- Use `create-drop` in production (data loss risk)

## Running the Application

### Local Development
```bash
# Copy .env.example to .env
cp .env.example .env

# Run with Maven
mvn spring-boot:run

# Or with Docker Compose
docker-compose up
```

### Production
```bash
# Build JAR
mvn clean package

# Run with environment variables
java -jar target/enterprise-crud-app-1.0.jar
```

## Verification

After setting environment variables, verify they are loaded:
```bash
# Check logs for configuration confirmation
curl http://localhost:8080/actuator/configprops
```

## Migration from Hardcoded Values

The following have been externalized:
- ✅ Database URL
- ✅ Database username
- ✅ Database password
- ✅ JPA DDL strategy
- ✅ Swagger UI path
- ✅ Server port
- ✅ Logging levels

All configurations now support environment variable overrides with sensible defaults for local development.
