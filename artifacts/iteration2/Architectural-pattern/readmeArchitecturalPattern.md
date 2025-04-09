## Benefits of Layered Architecture in Our Case

1. **Separation of Concerns**  
   - The system is divided into Presentation, Domain, and Data Source layers.
   - This keeps the UI, business logic, and database handling separate, making the code cleaner and easier to manage.

2. **Supports Multiple User Roles**  
   - Different users (Admins, Experts, Clients) interact with the system differently.
   - The architecture ensures each role accesses only relevant functionalities while keeping the core logic centralized.

3. **Scalability and Maintainability**  
   - Future changes (like adding new features or switching to a different database) can be made without affecting the entire system.

4. **Encapsulation**  
   - The Domain Layer ensures that important rules are handled in one place.
   - This avoids spreading logic across different parts of the system, making debugging easier.
  
## Table Data Gateway (TDG)
- Applied in the Data Source layer.
- Each table in the database has a corresponding gateway class that handles CRUD operations.
- This pattern provides a single point of access to each table which keeps SQL logic isolated.

## Data Mapper
- Sits between the Domain and Data Source layers.
- Maps data between in-memory objects (domain models) and database records.
- Keeps the domain models independent of the database schema.
















