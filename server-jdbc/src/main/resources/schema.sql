create TABLE PROPERTIES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  APPLICATION VARCHAR(250) DEFAULT 'application' NOT NULL,
  PROFILE VARCHAR(250) DEFAULT 'default' NOT NULL,
  LABEL VARCHAR(250) DEFAULT 'master' NOT NULL,
  MY_KEY VARCHAR(250) NOT NULL,
  MY_VALUE VARCHAR(250) NOT NULL
);