--required data
insert into authority (title) values
('Client'),
('Manager'),
('Master');

insert into repair_request_status (title) values
('Accepted'),
('In Progress'),
('Completed');

insert into device (title) values
('Phone'),
('PC'),
('Laptop'),
('Tablet');

--test data
insert into user (full_name, password, phone_number, username, authority_id) values
('testClient', '$2a$10$IW2txxc1.YmOYRCnW8SQv.Jn1DbHKTo1KFu0Eq4PTneQqynFyaPWu', '88005553535', 'user', 1),
('testManager', '$2a$10$IW2txxc1.YmOYRCnW8SQv.Jn1DbHKTo1KFu0Eq4PTneQqynFyaPWu', '88005553535', 'manager', 2),
('testMaster', '$2a$10$IW2txxc1.YmOYRCnW8SQv.Jn1DbHKTo1KFu0Eq4PTneQqynFyaPWu', '88005553535', 'master', 3),
('Alex', '$2a$10$IW2txxc1.YmOYRCnW8SQv.Jn1DbHKTo1KFu0Eq4PTneQqynFyaPWu', '88005553535', 'master1', 3),
('Not assigned', '$2a$10$IW2txxc1.YmOYRCnW8SQv.Jn1DbHKTo1KFu0Eq4PTneQqynFyaPWu', '0', 'notassigned', 3);

--
insert into repair_request (summary, creation_date, description, assignee_master_id, request_creator_id, device_id, status_id) values
('summary1', parsedatetime('17-09-2012 18:00:00', 'dd-MM-yyyy hh:mm:ss'),'description1',3,1,1,1),
('summary2', parsedatetime('17-09-2012 18:00:00', 'dd-MM-yyyy hh:mm:ss'),'description2',3,1,2,1),
('summary3', parsedatetime('17-09-2012 18:00:00', 'dd-MM-yyyy hh:mm:ss'),'description3',3,1,1,1);

insert into master_device (user_id, device_id) values
(3,1),
(4,2);
