GRANT XA_RECOVER_ADMIN ON *.* TO 'calcite'@'%';

FLUSH PRIVILEGES;

INSERT INTO department
  (deptno)
VALUES
  ('IT'),
  ('HR')
;

INSERT INTO employee
  (empid, deptno)
VALUES
  ('100000001', 'IT'),
  ('100000001', 'HR'),
  ('100000002', 'IT')
;
