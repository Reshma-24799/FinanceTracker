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

## 🧪 Testing the API

### User Management

**Create a User**
`POST /api/users`
```json
{
  "username": "john_doe",
  "email": "john@example.com"
}
```

**Get User Details**
`GET /api/users/{id}`

### Expense Tracking

**Create an Expense**
`POST /api/expenses/user/{userId}`
```json
{
  "amount": 50.00,
  "category": "Food",
  "date": "2023-10-27"
}
```

**Get Usage (List Expenses)**
`GET /api/expenses/user/{userId}`

**Update an Expense**
`PUT /api/expenses/{id}`
```json
{
  "amount": 55.00,
  "category": "Food",
  "date": "2023-10-27"
}
```

**Delete an Expense**
`DELETE /api/expenses/{id}`

**Get Total by Category**
`GET /api/expenses/total/user/{userId}?category={category}`

### Budget Management

**Set a Budget**
`POST /api/budgets/user/{userId}`
```json
{
  "category": "Food",
  "amount": 500.00,
  "month": 10,
  "year": 2023
}
```

**Update a Budget**
`PUT /api/budgets/user/{userId}`
```json
{
  "category": "Food",
  "amount": 600.00,
  "month": 10,
  "year": 2023
}
```

**Delete a Budget**
`DELETE /api/budgets/user/{userId}?category={category}`

