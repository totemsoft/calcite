-- ----------------------------------------------------------------------------
-- Table salary
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS salary (
  empid         VARCHAR(50) NOT NULL,
  salary        DECIMAL(19,2) NOT NULL,
  CONSTRAINT salary_pk PRIMARY KEY (empid)
);
