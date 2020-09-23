DROP SCHEMA IF EXISTS hrdb;
CREATE SCHEMA hrdb AUTHORIZATION sa;

DROP TABLE IF EXISTS department;
CREATE TABLE department (
  deptno        VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS employee;
CREATE TABLE IF NOT EXISTS employee (
  empid         VARCHAR(50) NOT NULL,
  deptno        VARCHAR(50) NOT NULL
);

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
