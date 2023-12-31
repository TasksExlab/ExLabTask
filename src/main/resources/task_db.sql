/*DROP TABLE IF EXISTS invites;
DROP TABLE IF EXISTS permissions;
DROP SEQUENCE IF EXISTS workspace_id;
DROP TABLE IF EXISTS user_workspace;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS workspaces;
DROP TABLE IF EXISTS roles;

CREATE TABLE roles
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    role        VARCHAR(50)  NOT NULL,
    description VARCHAR(256) NOT NULL
);

CREATE TABLE users
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name     VARCHAR(30)  NOT NULL,
    surname  VARCHAR(30)  NOT NULL,
    email    VARCHAR(60)  NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    role_id  BIGINT       NOT NULL REFERENCES roles (id)
);

CREATE TABLE workspaces
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    workspace_name VARCHAR(50)  NOT NULL,
    desc_workspace VARCHAR(500) NOT NULL
);

CREATE TABLE invites
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email                 VARCHAR(60) UNIQUE,
    unique_identifier     VARCHAR(256) NOT NULL,
    date_of_expire_invite TIMESTAMP    NOT NULL,
    is_activated          BOOLEAN      NOT NULL,
    role_id               BIGINT       NOT NULL REFERENCES roles (id),
    workspace_id          BIGINT       NOT NULL REFERENCES workspaces (id)
);

CREATE TABLE permissions
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    permission  VARCHAR(50)  NOT NULL,
    description VARCHAR(256) NULL
);

CREATE TABLE user_workspace
(
    user_id      BIGINT NOT NULL REFERENCES users (id),
    workspace_id BIGINT NOT NULL REFERENCES workspaces (id),
    PRIMARY KEY (user_id, workspace_id)
);

CREATE SEQUENCE workspace_id START 1000000000;

CREATE OR REPLACE FUNCTION generate_id()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.id := nextval('workspace_id')::bigint;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER my_table_generate_id_trigger
    BEFORE INSERT
    ON workspaces
    FOR EACH ROW
EXECUTE FUNCTION generate_id();*/

INSERT INTO roles(role, description)
VALUES ('ADMIN', 'Administrator');
INSERT INTO roles(role, description)
VALUES ('PM', 'Project manager');
INSERT INTO roles(role, description)
VALUES ('LEAD', 'Team lead');
INSERT INTO roles(role, description)
VALUES ('USER', 'Project member');

INSERT INTO users(name, surname, email, password, role_id) VALUES ('Ivan', 'Kuliba', 'ivan_kuliba_exLab@gmail.com', '{bcrypt}$2a$10$2Il/sRkN70.1LHkL46rqju9tieea5lALI05c0UVVh2D4Xtar3Hoh2', '1');
INSERT INTO users(name, surname, email, password, role_id) VALUES ('User', 'UserUser', 'user@gmail.com', '{bcrypt}$2a$12$JyahHBgr41dbrIExOX3e6e5zJPotDGRkN19DYPf1yJFZ8Hpsj3i2u', '4');

INSERT INTO workspaces(workspace_name, desc_workspace)
VALUES ('Workspace#2', 'lead workspace');

INSERT INTO invites(email, unique_identifier, date_of_expire_invite, is_activated, role_id, workspace_id)
VALUES ('ivan_kuliba_exLab@gmail.com', 'wenfiwkjebsdviueohdjnvkln', '2023-10-09', true, 1, 1000000000);

INSERT INTO user_workspace (user_id, workspace_id) VALUES (
1, 1000000000
);









