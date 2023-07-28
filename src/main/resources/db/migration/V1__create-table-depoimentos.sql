CREATE TABLE DEPOIMENTOS
(
    id            bigint       not null auto_increment,
    foto          LONGBLOB     not null,
    depoimento    varchar(100) not null,
    autor         varchar(100) not null,

    primary key (id)

);