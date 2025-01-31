# University Management
This project is designed to help manage user groups, assign grades and for a university management system. This Java-based implementation focuses on providing modular and maintainable code with a menu-driven interface for user interaction.

## How to use
This project requires a PostgreSQL database to be set up locally.

### 1. Setup the Database
You need to manually create a PostgreSQL database on your local machine:

### 2. Configure the Application
Inside the resources folder, there is a database.properties.example file that provides an example configuration. You need to copy this file and update it with your local database credentials.

```bash
db.url=jdbc:postgresql://localhost:5432/university
db.username=your_username
db.password=your_password
db.ssl=false
db.file_path=create_tables.sql
db.insert_into_tables=inserts.sql
```

Edit database.properties according to your properties.

## Technologies Used:
JDK 23

## Contributors:
- [Angel Stoynov](https://github.com/StoynovAngel)
