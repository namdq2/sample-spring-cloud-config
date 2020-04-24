insert into PROPERTIES (APPLICATION, PROFILE, LABEL, MY_KEY, MY_VALUE) values
  ('application', 'default', 'master', 'global', 'This is global config on JDBC backend.'),
  ('client1', 'default', 'master', 'message', 'Hello world!'),
  ('client1', 'default', 'master', 'name', 'Client 1'),
  ('client1', 'default', 'master', 'environment', 'default-cloud'),
  ('client1', 'dev', 'master', 'message', 'Hello world!'),
  ('client1', 'dev', 'master', 'name', 'Client 1'),
  ('client1', 'dev', 'master', 'environment', 'dev'),
  ('client2', 'default', 'master', 'message', 'Hello world!'),
  ('client2', 'default', 'master', 'name', 'Client 2'),
  ('client2', 'default', 'master', 'environment', 'default-cloud'),
  ('client2', 'prod', 'master', 'message', 'Hello world!'),
  ('client2', 'prod', 'master', 'name', 'Client 2'),
  ('client2', 'prod', 'master', 'environment', 'prod');