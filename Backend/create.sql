
    create sequence session_properties_seq start with 1 increment by 50;

    create table anon_users (
        priority integer,
        num_songs_added bigint,
        id uuid not null,
        session_id uuid,
        nick varchar(255),
        primary key (id)
    );

    create table auth_users (
        token_expiration timestamp(6),
        id uuid not null,
        access_token varchar(512),
        refresh_token varchar(512),
        spotify_display_name varchar(255),
        spotify_id varchar(255),
        primary key (id)
    );

    create table auth_users_sessions (
        auth_user_id uuid not null,
        sessions_id uuid not null unique
    );

    create table banned_genres (
        session_properties_id bigint not null,
        banned_genre varchar(255)
    );

    create table session_properties (
        ban_explicit_content boolean,
        generate_playlist boolean,
        max_guests integer,
        max_songs_per_guest integer,
        id bigint not null,
        primary key (id)
    );

    create table sessions (
        created timestamp(6),
        session_properties_id bigint unique,
        id uuid not null,
        owner_id uuid,
        invitation_code varchar(255) not null unique,
        name varchar(255),
        spotify_playlist_id varchar(255),
        primary key (id)
    );

    create table tracks_added (
        session_id uuid not null,
        spotify_track_added varchar(255)
    );

    alter table if exists anon_users
       add constraint FKofkyotanly03mt6y15qfx4fop
       foreign key (session_id)
       references sessions;

    alter table if exists auth_users_sessions
       add constraint FKni9pu5arwebh74hph8cvblnbk
       foreign key (sessions_id)
       references sessions;

    alter table if exists auth_users_sessions
       add constraint FK269qansk9n6k3d6k9dyusj1cf
       foreign key (auth_user_id)
       references auth_users;

    alter table if exists banned_genres
       add constraint FK8f1l977lhgq9v4x8e0p44fwp8
       foreign key (session_properties_id)
       references session_properties;

    alter table if exists sessions
       add constraint FKsbrp785je5xddx1runr3lfqxx
       foreign key (owner_id)
       references auth_users;

    alter table if exists sessions
       add constraint FKqofpuj2lsuapxk6vt4saah5pn
       foreign key (session_properties_id)
       references session_properties;

    alter table if exists tracks_added
       add constraint FK38htlp35ffsr41adujns20jf8
       foreign key (session_id)
       references sessions;
