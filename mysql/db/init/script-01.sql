SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Table department
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS department (
  deptno        VARCHAR(50) NOT NULL,
  created_date  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT department_pk PRIMARY KEY (deptno))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table employee
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS employee (
  empid         VARCHAR(50) NOT NULL,
  deptno        VARCHAR(50) NOT NULL,
  created_date  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT employee_pk PRIMARY KEY (empid, deptno),
  CONSTRAINT employee_fk1
    FOREIGN KEY (deptno)
    REFERENCES department (deptno))
ENGINE=InnoDB
DEFAULT CHARACTER SET = utf8;

SET FOREIGN_KEY_CHECKS = 1;
