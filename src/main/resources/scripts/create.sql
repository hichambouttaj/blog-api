
    create table category (
       id bigint not null auto_increment,
        description varchar(255) not null,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table comment (
       id bigint not null auto_increment,
        body varchar(255) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        post_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table post (
       id bigint not null auto_increment,
        content varchar(255) not null,
        description varchar(255) not null,
        title varchar(255) not null,
        category_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table role (
       id bigint not null auto_increment,
        role_name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table user (
       id bigint not null auto_increment,
        email varchar(255) not null,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    create table users_roles (
       user_id bigint not null,
        role_id bigint not null,
        primary key (user_id, role_id)
    ) engine=InnoDB;

    alter table category 
       add constraint UK46ccwnsi9409t36lurvtyljak unique (name);

    alter table post 
       add constraint UK2jm25hjrq6iv4w8y1dhi0d9p4 unique (title);

    alter table role 
       add constraint UK_iubw515ff0ugtm28p8g3myt0h unique (role_name);

    alter table user 
       add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table user 
       add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);

    alter table comment 
       add constraint FKs1slvnkuemjsq2kj4h3vhx7i1 
       foreign key (post_id) 
       references post (id);

    alter table post 
       add constraint FKg6l1ydp1pwkmyj166teiuov1b 
       foreign key (category_id) 
       references category (id);

    alter table users_roles 
       add constraint FKt4v0rrweyk393bdgt107vdx0x 
       foreign key (role_id) 
       references role (id);

    alter table users_roles 
       add constraint FKgd3iendaoyh04b95ykqise6qh 
       foreign key (user_id) 
       references user (id);
