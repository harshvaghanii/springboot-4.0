# 🎂 Week 1 Homework - Spring Boot 4.0

Hello Everyone! 👋  

This is my **Week 1 homework** for the **Coding Shuttle Spring Boot 4.0** class.

---

## 🌟 Overview

The main class of the application is:

```java
HomeworkWeek1Application
```

This calls the `CakeBaker` method as following:

- Calls the `bakeCake()` method  
- Uses **Dependency Injection** to bake either a **🍓 Strawberry Cake** or **🍫 Chocolate Cake**  

This project demonstrates:

- Basic Spring Boot application structure  
- Dependency Injection  
- Conditional bean creation based on cake type  

---

## 🚀 How to Run

1. Clone the repository  
2. Ensure you have **Java 21** installed  
3. Build the project using Maven:

```bash
mvn clean install
```

4. Run the application:

```bash
mvn spring-boot:run
```

You should see output similar to:

```
--------Baking the cake------
Frosting Type: STRAWBERRY
 Syrup Type: STRAWBERRY
-----------Finished Baking the Cake, Enjoy!!!---------------
```

---

## 💡 Notes

- Cake type is configurable via Spring properties or environment variables in `Intellij`  
- Demonstrates Spring Boot's **Dependency Injection** and **conditional beans**

---

## 📝 Feedback

I’d love to hear your thoughts and suggestions on this project!  

Thanks! 🙏  
— [Harsh Vaghani -LinkedIn](https://www.linkedin.com/in/harshvaghanii/)

