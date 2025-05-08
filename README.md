# Implement Mappers

### Requirements
In this repository, you will find an almost working application. The only task remaining is to **implement mappers** 
using the MapStruct library (all required imports are present in the `pom.xml` file).
### Вимоги
У цьому репозиторії ви знайдете майже робочу програму. Єдине завдання, що залишилося, це **впровадити картографи**
за допомогою бібліотеки MapStruct (усі необхідні імпортовані дані містяться у файлі `pom.xml`).

As a result of completing this task, you should have a fully functioning application. You should be able to send POST and GET
requests to any available controller, and all existing tests should pass.

У результаті виконання цього завдання у вас має бути повноцінна працездатна програма. Ви повинні мати можливість надсилати запити 
POST і GET на будь-який доступний контролер, і всі існуючі тести мають пройти. 

#### !!! Important: Remember to provide your credentials in the `application.properties` file during testing. However, DO NOT PUSH THEM in the PR.
#### !!! Важливо: не забудьте надати свої облікові дані у файлі `application.properties` під час тестування. Проте НЕ ВИПІХАЙТЕ ЇХ у піар.

### Domain
In this repository, we have three models: Student, Group, and Subject. These classes simply represent the most common entities in a University app.
### Домен 
У цьому репозиторії ми маємо три моделі: студент, група та предмет. Ці класи просто представляють найпоширеніші сутності в програмі університету.

A student can be in one group, hence:
Студент може бути в одній групі, отже:
```java
    @ManyToOne
    private Group group;
```

Additionally, a student can study several subjects:
Додатково студент може вивчати кілька предметів:
```java
    @ManyToMany
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;
```