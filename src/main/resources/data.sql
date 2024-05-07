INSERT INTO sec_user (email, encryptedPassword, enabled)
VALUES ('quansi@sheridancollege.ca', '$2y$12$xveqbLS9Gy0OxSdk1VG9TOaW6qR0fO3gymX5OFV/MQW2fUNCTmHhK', 1);

INSERT INTO sec_user (email, encryptedPassword, enabled) VALUES
('sales1@sheridancollege.ca', '$2a$12$xOTpjV9N6fJYX17088j1eOpfEqgPh/jjYUb6ZzZcvvk/cz8RLXS/a', 1),
('sales2@sheridancollege.ca', '$2a$12$xOTpjV9N6fJYX17088j1eOpfEqgPh/jjYUb6ZzZcvvk/cz8RLXS/a', 1),
('sales3@sheridancollege.ca', '$2a$12$xOTpjV9N6fJYX17088j1eOpfEqgPh/jjYUb6ZzZcvvk/cz8RLXS/a', 1);


INSERT INTO sec_role (roleName)
VALUES ('ROLE_ADMIN');
 
INSERT INTO sec_role (roleName)
VALUES ('ROLE_USER');
 

INSERT INTO user_role (userId, roleId)
VALUES (1, 1);
 
INSERT INTO user_role (userId, roleId)
VALUES (2, 2);

INSERT INTO user_role (userId, roleId)
VALUES (3, 2);

INSERT INTO user_role (userId, roleId)
VALUES (4, 2);


INSERT INTO stages (stage_name) VALUES ('New Lead'), ('Proposal'), ('Won'), ('Lost');


INSERT INTO CUSTOMER (userId, name, phone, email) VALUES
(1, 'Alice Brown', '123-456-7890', 'alice.brown@example.com'),
(2, 'Bob Smith', '234-567-8901', 'bob.smith@example.com'),
(3, 'Charlie Johnson', '345-678-9012', 'charlie.johnson@example.com'),
(4, 'Diana Miller', '456-789-0123', 'diana.miller@example.com'),
(1, 'Ethan Williams', '567-890-1234', 'ethan.williams@example.com'),
(2, 'Fiona Brown', '678-901-2345', 'fiona.brown@example.com'),
(3, 'George Wilson', '789-012-3456', 'george.wilson@example.com'),
(4, 'Hannah Moore', '890-123-4567', 'hannah.moore@example.com'),
(1, 'Ian Clark', '901-234-5678', 'ian.clark@example.com'),
(2, 'Julia Scott', '012-345-6789', 'julia.scott@example.com'),
(4, 'Yolanda King', '567-890-1234', 'yolanda.king@example.com'),
(1, 'Zachary Lee', '678-901-2345', 'zachary.lee@example.com'),
(2, 'Amelia Morgan', '789-012-3456', 'amelia.morgan@example.com'),
(3, 'Brian Nelson', '890-123-4567', 'brian.nelson@example.com'),
(1, 'David Parker', '012-345-6789', 'david.parker@example.com'),
(2, 'Evelyn Rivera', '123-456-7890', 'evelyn.rivera@example.com'),
(3, 'Frank Sanders', '234-567-8901', 'frank.sanders@example.com'),
(4, 'Grace Thompson', '345-678-9012', 'grace.thompson@example.com'),
(1, 'Harry Wilson', '456-789-0123', 'harry.wilson@example.com'),
(2, 'Isabella Young', '567-890-1234', 'isabella.young@example.com'),
(3, 'Jack Taylor', '678-901-2345', 'jack.taylor@example.com'),
(4, 'Katherine Brown', '789-012-3456', 'katherine.brown@example.com'),
(1, 'Liam White', '890-123-4567', 'liam.white@example.com'),
(2, 'Mia Johnson', '901-234-5678', 'mia.johnson@example.com');


INSERT INTO LEADS (customerId, projectName, products, unitPrice, quantity, currentStage, currentStageId) VALUES
(1, 'Project Aurora', 'Product X', 120.50, 10, 'New Lead', 1),
(2, 'Project Orion', 'Product Y', 150.00, 15, 'Proposal', 2),
(3, 'Project Nova', 'Product Z', 200.75, 5, 'Won', 3),
(4, 'Project Sirius', 'Product A', 250.00, 8, 'Lost', 4),
(1, 'Project Gemini', 'Product B', 100.00, 12, 'New Lead', 1),
(2, 'Project Phoenix', 'Product C', 130.30, 6, 'Proposal', 2),
(3, 'Project Apollo', 'Product D', 180.00, 9, 'Won', 3),
(4, 'Project Luna', 'Product E', 210.00, 7, 'Lost', 4),
(1, 'Project Atlas', 'Product F', 115.00, 14, 'New Lead', 1),
(2, 'Project Zenith', 'Product G', 140.00, 4, 'Proposal', 2),
(4, 'Project Neptune', 'Product N', 220.50, 3, 'Lost', 4),
(1, 'Project Eclipse', 'Product O', 130.00, 11, 'New Lead', 1),
(2, 'Project Comet', 'Product P', 160.75, 2, 'Proposal', 2),
(3, 'Project Vega', 'Product Q', 195.00, 13, 'Won', 3),
(4, 'Project Pulsar', 'Product R', 245.50, 1, 'Lost', 4),
(1, 'Project Pegasus', 'Product S', 135.00, 7, 'New Lead', 1),
(2, 'Project Draco', 'Product T', 165.75, 3, 'Proposal', 2),
(3, 'Project Lyra', 'Product U', 210.25, 5, 'Won', 3),
(4, 'Project Ursa', 'Product V', 225.50, 2, 'Lost', 4),
(1, 'Project Andromeda', 'Product W', 140.00, 9, 'New Lead', 1),
(2, 'Project Cygnus', 'Product X', 155.50, 4, 'Proposal', 2),
(3, 'Project Aquila', 'Product Y', 200.00, 8, 'Won', 3),
(4, 'Project Cassiopeia', 'Product Z', 235.00, 1, 'Lost', 4),
(1, 'Project Scorpius', 'Product A1', 145.00, 10, 'New Lead', 1),
(2, 'Project Carina', 'Product B1', 170.30, 6, 'Proposal', 2),
(3, 'Project Vela', 'Product K1', 195.00, 11, 'Won', 3),
(4, 'Project Lyra', 'Product L1', 220.00, 12, 'Lost', 4),
(1, 'Project Orion', 'Product M1', 130.50, 13, 'New Lead', 1),
(2, 'Project Gemini', 'Product N1', 160.75, 14, 'Proposal', 2),
(3, 'Project Leo', 'Product O1', 205.25, 15, 'Won', 3),
(4, 'Project Virgo', 'Product P1', 230.00, 16, 'Lost', 4),
(1, 'Project Libra', 'Product Q1', 125.00, 17, 'New Lead', 1),
(2, 'Project Capricorn', 'Product R1', 150.50, 18, 'Proposal', 2),
(3, 'Project Aquarius', 'Product S1', 210.75, 19, 'Won', 3),
(4, 'Project Sagittarius', 'Product T1', 245.00, 20, 'Lost', 4),
(1, 'Project Epsilon', 'Product A2', 150.00, 5, 'New Lead', 1),
(2, 'Project Zeta', 'Product B2', 175.75, 6, 'Proposal', 2),
(3, 'Project Eta', 'Product C2', 220.25, 7, 'Won', 3),
(4, 'Project Theta', 'Product D2', 235.50, 8, 'Lost', 4),
(1, 'Project Iota', 'Product E2', 140.00, 9, 'New Lead', 1),
(2, 'Project Kappa', 'Product F2', 155.50, 10, 'Proposal', 2),
(3, 'Project Lambda', 'Product G2', 200.00, 11, 'Won', 3),
(4, 'Project Mu', 'Product H2', 235.00, 12, 'Lost', 4),
(1, 'Project Nu', 'Product I2', 145.00, 13, 'New Lead', 1),
(2, 'Project Xi', 'Product J2', 170.30, 14, 'Proposal', 2),
(3, 'Project Omicron', 'Product K2', 195.00, 15, 'Won', 3),
(4, 'Project Pi', 'Product L2', 220.00, 16, 'Lost', 4),
(1, 'Project Rho', 'Product M2', 130.50, 17, 'New Lead', 1),
(2, 'Project Sigma', 'Product N2', 160.75, 18, 'Proposal', 2),
(3, 'Project Tau', 'Product O2', 205.25, 19, 'Won', 3),
(4, 'Project Upsilon', 'Product P2', 230.00, 20, 'Lost', 4),
(1, 'Project Phi', 'Product Q2', 125.00, 21, 'New Lead', 1),
(2, 'Project Chi', 'Product R2', 150.50, 22, 'Proposal', 2),
(3, 'Project Psi', 'Product S2', 210.75, 23, 'Won', 3),
(4, 'Project Omega', 'Product T2', 245.00, 24, 'Lost', 4);










