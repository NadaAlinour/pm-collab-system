

INSERT INTO public.tenant
(name) VALUES ('Dunder Mifflin');

INSERT INTO public.project
(name, tenant_id)
VALUES ('Sample Project 1', 1),
       ('Sample Project 2', 1),
       ('Sample Project 3', 1);

INSERT INTO public.users
(email, firstname, lastname, password, role_id, tenant_id)
VALUES ('jim@office.com', 'Jim', 'Halpert', 'theoffice', 1, 1),
 ('pam@office.com', 'Pam', 'Beesly', 'theoffice', 1, 1),
 ('michael@office.com', 'Michael', 'Scott', 'theoffice', 1, 1);

INSERT INTO public.status
(name, value)
VALUES ('To do', 'TO_DO'),
       ('In Progress', 'IN_PROGRESS'),
('Done', 'DONE');


INSERT INTO public.category
(name)
VALUES ('Management'),
       ('Warehouse'),
       ('Accounting'),
       ('HR'),
       ('Sales');




