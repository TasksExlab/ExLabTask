INSERT INTO roles(role, description) VALUES ('ADMIN', 'Administrator');
INSERT INTO roles(role, description) VALUES ('PM', 'Project manager');
INSERT INTO roles(role, description) VALUES ('LEAD', 'Team lead');
INSERT INTO roles(role, description) VALUES ('USER', 'Project member');

INSERT INTO users(name, surname, email, password, role_id) VALUES ('Admin', 'Adminov', 'admin.exlab@gmail.com', '{bcrypt}$2a$10$2Il/sRkN70.1LHkL46rqju9tieea5lALI05c0UVVh2D4Xtar3Hoh2', (SELECT id FROM roles WHERE role = 'ADMIN'));