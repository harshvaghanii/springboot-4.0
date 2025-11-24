# 🎂 Week 1 Homework - Spring Boot 4.0

Hello Everyone! 👋  

This is my **Week 1 homework** for the **Coding Shuttle Spring Boot 4.0** class.

---

## 🌟 Overview

The assignment provided a main class named `CakeBaker`. For my submission, I used the main class name:

```java
HomeworkWeek1Application
```

The functionality is the same as `CakeBaker`:

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
Frosting Type: Chocolate
Syrup Type: Maple
```

or for Strawberry Cake:

```
--------Baking the cake------
Frosting Type: STRAWBERRY
 Syrup Type: STRAWBERRY
-----------Finished Baking the Cake, Enjoy!!!---------------
```

---

## 💡 Notes

- The main class is `HomeworkWeek1Application` instead of `CakeBaker`  
- Cake type is configurable via Spring properties  
- Demonstrates Spring Boot's **Dependency Injection** and **conditional beans**

---

## 📝 Feedback

I’d love to hear your thoughts and suggestions on this project!  

Thanks! 🙏  
— [Harsh Vaghani -LinkedIn](https://www.linkedin.com/in/harshvaghanii/)

