create table tbl_ai_keyword (
            keyword_id          bigint generated always as identity primary key,
            member_id           bigint,
            search_word         varchar(255),
            created_datetime    timestamp default now(),
            updated_datetime    timestamp default now()
);