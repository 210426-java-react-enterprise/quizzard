drop table if exists study_set_cards;
drop table if exists flashcards;
drop table if exists categories;
drop table if exists study_sets;
drop table if exists users;

create table users(
    user_id serial,
    username varchar(20) unique not null,
    password varchar(255) not null,
    email varchar(255) unique not null,
    first_name varchar(25) not null,
    last_name varchar(25) not null,
    age int check (age > 0),

    constraint pk_users
        primary key (user_id)
);

create table categories(
    category_id serial primary key,
    name varchar(20) unique not null
);

create table flashcards(
    flashcard_id serial primary key,
    question varchar not null,
    answer varchar not null,
    category_id int,

    constraint fk_flashcard_category
        foreign key (category_id)
            references categories
);

create table study_sets(
    study_set_id serial primary key,
    name varchar(20) not null,
    owner_id int,

    constraint fk_study_set_owner
        foreign key (owner_id)
            references users
);

create table study_set_cards(
    study_set_id int not null,
    flashcard_id int not null,

    constraint pk_study_set_cards
        primary key (study_set_id, flashcard_id),

    constraint fk_study_set
        foreign key (study_set_id)
            references study_sets on delete cascade,

    constraint fk_flashcard
        foreign key (flashcard_id)
            references flashcards on delete cascade);

insert into users (username, password, email, first_name, last_name, age)
values ('wsingleton', 'revature', 'wezley.singleton@revature.com', 'Wezley', 'Singleton', 30),
       ('aanderson', 'password', 'alice.anderson@revature.com', 'Alice', 'Anderson', 23);

insert into categories (name)
values ('Java'),
       ('SQL'),
       ('JDBC'),
       ('Spring'),
       ('HTML'),
       ('CSS'),
       ('JavaScript'),
       ('React');

insert into flashcards (question, answer, category_id)
values ('question-1', 'answer-1', 1),
       ('question-2', 'answer-2', 1),
       ('question-3', 'answer-3', 2),
       ('question-4', 'answer-4', 2),
       ('question-5', 'answer-5', 3),
       ('question-6', 'answer-6', 3);

insert into study_sets (name, owner_id)
values ('Wezley''s Study Set', 1),
       ('Alice''s Study Set', 2);

insert into study_set_cards (study_set_id, flashcard_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 4);






