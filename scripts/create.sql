create table comment
(
    id      bigint       not null auto_increment,
    body    varchar(255) not null,
    email   varchar(255) not null,
    name    varchar(255) not null,
    post_id bigint       not null,
    primary key (id)
) engine = InnoDB;
create table post
(
    id          bigint       not null auto_increment,
    content     varchar(255) not null,
    description varchar(255) not null,
    title       varchar(255) not null,
    primary key (id)
) engine = InnoDB;
alter table post
    add constraint UK2jm25hjrq6iv4w8y1dhi0d9p4 unique (title);
alter table comment
    add constraint FKs1slvnkuemjsq2kj4h3vhx7i1 foreign key (post_id) references post (id);
