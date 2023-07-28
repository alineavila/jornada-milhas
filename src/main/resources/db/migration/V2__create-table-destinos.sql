CREATE TABLE DESTINOS
(
    id            bigint       not null auto_increment,
    foto          LONGBLOB not null,
    nome          varchar(100) not null unique,
    preco         DECIMAL(10,2) not null,

    primary key (id)

);