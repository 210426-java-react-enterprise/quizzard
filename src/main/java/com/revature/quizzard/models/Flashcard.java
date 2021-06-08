package com.revature.quizzard.models;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "flashcards")
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private AppUser creator;

    @Column(nullable = false)
    private Category category;

    public Flashcard() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public AppUser getCreator() {
        return creator;
    }

    public void setCreator(AppUser creator) {
        this.creator = creator;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flashcard flashcard = (Flashcard) o;
        return id == flashcard.id && Objects.equals(question, flashcard.question) && Objects.equals(answer, flashcard.answer) && category == flashcard.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, answer, category);
    }

    @Override
    public String toString() {
        return "Flashcard{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", creatorId=" + creator.getId() +
                ", category='" + category + '\'' +
                '}';
    }

    public enum Category {

        CORE_JAVA("Core Java"), JAVA_THREADS("Java Threads"), JAVA_REFLECTION("Java Reflection"),
        JAVA_COLLECTIONS("Java Collections"), JAVA_STREAMS("Java Streams"), ORACLE_SQL("Oracle SQL"),
        POSTGRE_SQL("PostgreSQL"), ORACLE_PLSQL("Oracle PL/SQL"), POSTGRE_PLPGSQL("PostGre PL/pgSQL"),
        JDBC("JDBC"), HTML("HTML"), CSS("CSS"), CORE_JS("Core JavaScript"),
        JS_DOM_MANIPULATION("JS DOM Manipulation"), AJAX("AJAX"), FETCH_API("Fetch API"), AXIOS("Axios"),
        JAVA_SERVLETS("Java Servlets"), TYPESCRIPT("TypeScript"), NODE_JS("Node.js"),
        ANGULAR("Angular"), REACT("React"), REDUX("Redux"), EXPRESS("Express"), AWS_CLOUD("AWS Cloud"),
        MS_AZURE_CLOUD("MS Azure Cloud"), DEVOPS_PRINCIPLES("DevOps Principles"), JENKINS("Jenkins"),
        CONTAINERIZATION("Containerization"), CONTAINER_ORCHESTRATION("Container Orchestration"),
        HIBERNATE("Hibernate"), CORE_SPRING("Core Spring Framework"), SPRING_BOOT("Spring Boot"),
        SPRING_DATA("Spring Data"), SOA("Service Oriented Architecture"), REST_WS("REST Web Services"),
        SOAP_WS("SOAP Web Services"), MICROSERVICES("Microservice Architecture"),
        MESSAGING_QUEUE("Messaging Queue"), OTHER("Other");

        private String name;

        Category(String name) {
            this.name = name;
        }

        @Override
        @JsonValue
        public String toString() {
            return name;
        }

    }

}
