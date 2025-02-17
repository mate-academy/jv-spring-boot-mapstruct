`# Implement Mappers

### Requirements
In this repository, you will find an almost working application. The only task remaining is to **implement mappers** using the MapStruct library (all required imports are present in the `pom.xml` file).

As a result of completing this task, you should have a fully functioning application. You should be able to send POST and GET requests to any available controller, and all existing tests should pass.

#### !!! Important: Remember to provide your credentials in the `application.properties` file during testing. However, DO NOT PUSH THEM in the PR.

### Domain
In this repository, we have three models: Student, Group, and Subject. These classes simply represent the most common entities in a University app.

A student can be in one group, hence:
```java
    @ManyToOne
    private Group group;
```

Additionally, a student can study several subjects:
```java
    @ManyToMany
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;
```