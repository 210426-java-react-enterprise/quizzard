package com.revature.quizzard.flashcards;

import com.fasterxml.jackson.annotation.JsonValue;
import com.revature.quizzard.users.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;

@Data @NoArgsConstructor
@Entity @Table(name = "flashcards")
public class Flashcard {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Flashcard(String question, String answer, int creatorId, String category) {
        this.question = question;
        this.answer = answer;
        this.creator = new AppUser(creatorId);
        this.category = Category.valueOf(category);
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

        public static Category mapName(String categoryName) {
            return Arrays.stream(Category.values())
                         .filter(role -> role.toString().equals(categoryName))
                         .findFirst()
                         .orElse(OTHER);
        }

    }

}
