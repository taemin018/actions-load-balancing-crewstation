create table tbl_ai_log (
            log_id              bigint generated always as identity primary key,
            member_id           bigint,
            post_id             bigint,
            created_datetime    timestamp default now(),
            updated_datetime    timestamp default now()
);
