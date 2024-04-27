## Clear Solutions (Test Assigment) by *Oleh Tsipotan*

- **TASK**: [link to the task description](https://docs.google.com/document/d/1LosRgr72sJYcNumbZKET7uiIJ3Un_ORl25Psn1Dd9hw/edit)

---

### How to run:
- **1.** Clone the repository:
```bash
git clone https://github.com/OlehTsipotan/ClearSolutionsTestAssignment.git
```

- **2.** Run the Database via [Docker](https://www.docker.com/):
```bash
docker run --name pg_clear_solution -e POSTGRES_PASSWORD=mysecretpassword -e POSTGRES_USER=myuser -e POSTGRES_DB=clearsolution -p 5432:5432 -d postgres
```

- **3.** Set up the Env. variables:
```
  SPRING_DATASOURCE_PASSWORD=mysecretpassword;
  SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/clearsolution;
  SPRING_DATASOURCE_USERNAME=myuser
```

- **4.** Run the application!
- The application is accessible via `http://localhost:8080` by default. And two endpoints are available:
  - `http://localhost:8080/api/v1/users`
  - `http://localhost:8080/api/v1/adresses`


---

## Contact Information

**Name:** Oleh Tsipotan

**Email:** tsipotan.oleh@gmail.com

**LinkedIn:** https://www.linkedin.com/in/oleh-tsipotan/
