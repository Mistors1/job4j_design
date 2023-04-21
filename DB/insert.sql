INSERT INTO users(name) values('Test Test');
INSERT INTO users(name) values('Ivan Ivanov');

INSERT INTO item(name) values ('Bug1');
INSERT INTO item(name) values ('Bug2');
INSERT INTO item(name) values ('Bug3');
INSERT INTO item(name) values ('Bug4');

INSERT INTO comments(id_item, text) values('1','need to fix bug');
INSERT INTO comments(id_item, text) values('2','dont fix that bug');

INSERT INTO role(name) values('admin');
INSERT INTO role(name) values('user');
INSERT INTO role(name) values('guest');

INSERT INTO rules(name) values('admin_rules');
INSERT INTO rules(name) values('user_rules');
INSERT INTO rules(name) values('guest_rules');

INSERT INTO role_rules(id_role, id_rules) values('2','1');
INSERT INTO role_rules(id_role, id_rules) values('1','2');

INSERT INTO category(name) values('in prosses');
INSERT INTO category(name) values('finished');
INSERT INTO category(name) values('in treker');

INSERT INTO state(name) values('accepted');
INSERT INTO state(name) values('rejected');
INSERT INTO state(name) values('evaluating');

SELECT * FROM role;
SELECT * FROM rules;
SELECT * FROM role_rules;
SELECT * FROM category;
SELECT * FROM state;
SELECT * FROM users;