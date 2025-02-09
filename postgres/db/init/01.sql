-- ----------------------------------------------------------------------------
-- psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB"
-- ----------------------------------------------------------------------------

-- ----------------------------------------------------------------------------
-- Table salary
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS salary (
  empid         VARCHAR(50) NOT NULL,
  salary        DECIMAL(19,2) NOT NULL,
  CONSTRAINT salary_pk PRIMARY KEY (empid)
);
