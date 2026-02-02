# Personal Finance Tracker

A robust RESTful API for tracking personal finances, featuring expense tracking, budgeting, and detailed reporting.

## 🚀 Features

- **User Management**: Create and manage users.
- **Expense Tracking**: Log expenses with amounts, categories, and dates.
- **Budgeting**: Set monthly budgets (data model included).
- **Data Validation**: Ensures positive amounts and valid data using `@Valid`.
- **Custom Reporting**: Calculate total spending by category using custom JPQL queries.

## 🛠️ Technology Stack

- **Java 17+**
- **Spring Boot 3** (Web, Data JPA, Validation)
- **H2 Database** (In-memory database for testing)
- **Lombok** (Boilerplate reduction)
- **Maven** (Dependency management)

## 💻 Setup in IntelliJ IDEA

1. **Open the Project**:
   - Open IntelliJ IDEA.
   - Select `File > Open` and navigate to the `FinanceTracker` folder used for this project.
   - Select the `pom.xml` file and click "Open as Project".

2. **Load Maven Changes**:
   - IntelliJ should detect the `pom.xml` and ask to load the project.
   - If not, go to the **Maven** sidebar (usually on the right) and click the "Reload All Maven Projects" (refresh icon) button.
   - Wait for dependencies to download.

3. **Run the Application**:
   - Navigate to `src/main/java/com/example/financetracker/FinanceTrackerApplication.java`.
   - Right-click on the file or the green play arrow next to the `main` method.
   - Select `Run 'FinanceTrackerApplication'`.

4. **Verify it's Running**:
   - Access the H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
     - JDBC URL: `jdbc:h2:mem:testdb`
     - User Name: `sa`
     - Password: `password`

## 🧪 Testing the API

### create a User
**POST** `http://localhost:8080/api/users`
```json
{
  "username": "john_doe",
  "email": "john@example.com"
}
```

### Create an Expense
**POST** `http://localhost:8080/api/expenses/user/1`
```json
{
  "amount": 50.00,
  "category": "Food",
  "date": "2023-10-27"
}
```

### Get Total by Category
**GET** `http://localhost:8080/api/expenses/total/user/1?category=Food`
