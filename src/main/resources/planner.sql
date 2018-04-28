CREATE TABLE IF NOT EXISTS planner (
  id int AUTO_INCREMENT PRIMARY KEY,
  plannerName VARCHAR(255),
  milestones VARCHAR (255),
  cUser VARCHAR(255),
);

CREATE TABLE IF NOT EXISTS milestone (
  id int (100) PRIMARY KEY,
  title VARCHAR (255),
  description VARCHAR (255),
  plannerId INTEGER (100),
  dueDate VARCHAR (100),
  compDate VARCHAR(100),
);


