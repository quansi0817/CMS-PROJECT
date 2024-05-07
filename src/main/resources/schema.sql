

CREATE TABLE STAGES (
  stageId INT PRIMARY KEY AUTO_INCREMENT,
  stage_name VARCHAR(50)
);


CREATE TABLE sec_user (
  userId            BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  email             VARCHAR(75) NOT NULL UNIQUE,
  encryptedPassword VARCHAR(255) NOT NULL,
  enabled           BIT NOT NULL 
);

CREATE TABLE CUSTOMER(
customerId BIGINT PRIMARY KEY AUTO_INCREMENT,
userId BIGINT,
name VARCHAR(255),
phone VARCHAR(50),
email VARCHAR(255),
FOREIGN KEY(userId) REFERENCES sec_user(userId)
);

CREATE TABLE LEADS (
leadsId BIGINT PRIMARY KEY AUTO_INCREMENT,
customerId BIGINT,
projectName VARCHAR(255),
products  VARCHAR(255),
unitPrice DECIMAL(10, 2),
quantity INT NOT NULL,
currentStage VARCHAR(50),
currentStageId BIGINT,
FOREIGN KEY(customerId) REFERENCES CUSTOMER(customerId),
FOREIGN KEY(currentStageId) REFERENCES STAGES(stageId)
);


CREATE TABLE sec_role(
  roleId   BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  roleName VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE user_role
(
  id     BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  userId BIGINT NOT NULL,
  roleId BIGINT NOT NULL,
   FOREIGN KEY (userId) REFERENCES sec_user (userId),
  FOREIGN KEY (roleId) REFERENCES sec_role (roleId)
);

ALTER TABLE user_role
  ADD CONSTRAINT user_role_uk UNIQUE (userId, roleId);

ALTER TABLE user_role
  ADD CONSTRAINT user_role_fk1 FOREIGN KEY (userId)
  REFERENCES sec_user (userId);
 
ALTER TABLE user_role
  ADD CONSTRAINT user_role_fk2 FOREIGN KEY (roleId)
  REFERENCES sec_role (roleId);