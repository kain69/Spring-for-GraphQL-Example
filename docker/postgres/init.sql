create table users (
                       id uuid not null primary key,
                       name varchar(255) not null
);

create table posts (
                       id uuid not null primary key,
                       title varchar(255) not null,
                       user_id uuid not null references users(id)
);

create table comments (
                          id uuid not null primary key,
                          text text not null,
                          post_id uuid not null references posts(id)
);

insert into users (id, name) values
                                 (uuid_generate_v4(), 'Alice'),
                                 (uuid_generate_v4(), 'Bob'),
                                 (uuid_generate_v4(), 'Charlie');

insert into posts (id, title, user_id) values
                                           (uuid_generate_v4(), 'Alice Post 1', (select id from users where name='Alice')),
                                           (uuid_generate_v4(), 'Alice Post 2', (select id from users where name='Alice')),
                                           (uuid_generate_v4(), 'Bob Post 1',   (select id from users where name='Bob')),
                                           (uuid_generate_v4(), 'Charlie Post 1', (select id from users where name='Charlie')),
                                           (uuid_generate_v4(), 'Charlie Post 2', (select id from users where name='Charlie')),
                                           (uuid_generate_v4(), 'Charlie Post 3', (select id from users where name='Charlie'));

insert into comments (id, text, post_id) values
                                             (uuid_generate_v4(), 'Great post, Alice!', (select id from posts where title='Alice Post 1')),
                                             (uuid_generate_v4(), 'Thanks for sharing!', (select id from posts where title='Alice Post 1')),
                                             (uuid_generate_v4(), 'Interesting read', (select id from posts where title='Alice Post 2')),

                                             (uuid_generate_v4(), 'Nice one, Bob!', (select id from posts where title='Bob Post 1')),

                                             (uuid_generate_v4(), 'First comment!', (select id from posts where title='Charlie Post 1')),
                                             (uuid_generate_v4(), 'Second comment!', (select id from posts where title='Charlie Post 1')),
                                             (uuid_generate_v4(), 'Another comment', (select id from posts where title='Charlie Post 2')),
                                             (uuid_generate_v4(), 'Yet another one', (select id from posts where title='Charlie Post 3'));