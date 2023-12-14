INSERT INTO roles(role, description) VALUES ('ADMIN', 'Administrator');
INSERT INTO roles(role, description) VALUES ('PM', 'Project manager');
INSERT INTO roles(role, description) VALUES ('LEAD', 'Team lead');
INSERT INTO roles(role, description) VALUES ('USER', 'Project member');

INSERT INTO users(name, surname, email, password, role_id) VALUES ('Admin', 'Admin', 'admin.exlab@gmail.com', '{bcrypt}$2a$10$2Il/sRkN70.1LHkL46rqju9tieea5lALI05c0UVVh2D4Xtar3Hoh2', (SELECT id FROM roles WHERE role = 'ADMIN'));
INSERT INTO users(name, surname, email, password, role_id) VALUES ('User', 'User', 'user.exlab@gmail.com', '{bcrypt}$2a$10$2Il/sRkN70.1LHkL46rqju9tieea5lALI05c0UVVh2D4Xtar3Hoh2', (SELECT id FROM roles WHERE role = 'USER'));
INSERT INTO users(name, surname, email, password, role_id) VALUES ('User1', 'User1', 'user1.exlab@gmail.com', '{bcrypt}$2a$10$2Il/sRkN70.1LHkL46rqju9tieea5lALI05c0UVVh2D4Xtar3Hoh2', (SELECT id FROM roles WHERE role = 'USER'));

INSERT INTO workspaces(name, description) VALUES ('Workspace', 'The main workspace');
INSERT INTO workspaces(name) VALUES ('Workspace 2');

INSERT INTO user_workspace(workspace_id, user_id) VALUES ((SELECT id FROM workspaces WHERE name = 'Workspace'), (SELECT id FROM users WHERE email = 'user.exlab@gmail.com'));
INSERT INTO user_workspace(workspace_id, user_id) VALUES ((SELECT id FROM workspaces WHERE name = 'Workspace'), (SELECT id FROM users WHERE email = 'user1.exlab@gmail.com'));

INSERT INTO invites(is_activated, date_of_expire_invite, role_id, workspace_id, email, unique_identifier) VALUES (true, now(), 4, 1, 'user.exlab@gmail.com', 'qwertyuiop123456789');
INSERT INTO invites(is_activated, date_of_expire_invite, role_id, workspace_id, email, unique_identifier) VALUES (true, now(), 4, 1, 'user1.exlab@gmail.com', 'qwertyuioo123456788');
INSERT INTO invites(is_activated, date_of_expire_invite, role_id, workspace_id, email, unique_identifier) VALUES (false, '2025-06-22 19:10:25-07', 4, 1, 'user3.exlab@gmail.com', '1234');