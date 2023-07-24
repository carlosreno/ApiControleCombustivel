CREATE TABLE IF NOT EXISTS company (
     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(100) NOT NULL,
     cnpj VARCHAR(18) NOT NULL,
     address VARCHAR(200),
     phone VARCHAR(20),
     email VARCHAR(100)
);
CREATE TABLE IF NOT EXISTS cars (
    id INT AUTO_INCREMENT PRIMARY KEY,
    renavam VARCHAR(11) NOT NULL,
    placa VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    marca VARCHAR(50),
    brand VARCHAR(50),
    year INT,
    color VARCHAR(30),
    fuel VARCHAR(30),
    mileage DOUBLE,
    fabrication_date DATE,
    comments TEXT,
    company_id INT,
    availability_id INT,
    status ENUM('active','disabled')
);

CREATE TABLE IF NOT EXISTS sector (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL,
                                      company_id INT
);
CREATE TABLE IF NOT EXISTS availability (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL,
                                      car_id INT,
                                      sector_id INT
);
CREATE TABLE IF NOT EXISTS user (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL,
                                      company_id INT,
                                      user_type INT
);
CREATE TABLE IF NOT EXISTS user_type (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS contract (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          number_contract VARCHAR(50) NOT NULL,
                          object TEXT NOT NULL,
                          date_initial DATE,
                          date_final DATE,
                          value DECIMAL(10, 2),
                          customer VARCHAR(100),
                          status ENUM('Active', 'Closed', 'Canceled', 'Suspended'),
                          supplier_id INT,
                          comments TEXT
);

CREATE TABLE IF NOT EXISTS supplier (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cnpj VARCHAR(18) NOT NULL,
    address VARCHAR(200),
    phone VARCHAR(20),
    email VARCHAR(100),
    date_register DATE,
    comments TEXT
);
CREATE TABLE IF NOT EXISTS user_sector (
                               user_id INT NOT NULL,
                               sector_id INT NOT NULL,
                               PRIMARY KEY (user_id, sector_id),
                               FOREIGN KEY (user_id) REFERENCES user(id),
                               FOREIGN KEY (sector_id) REFERENCES sector(id)
);

ALTER TABLE cars ADD CONSTRAINT company_id
    FOREIGN KEY (company_id) references company(id);

ALTER TABLE contract ADD CONSTRAINT supplier_id
    FOREIGN KEY (supplier_id) references supplier(id);

ALTER TABLE availability ADD CONSTRAINT sector_id
    FOREIGN KEY (sector_id) references sector(id);

ALTER TABLE availability ADD CONSTRAINT car_id
    FOREIGN KEY (car_id) references cars(id);

ALTER TABLE user ADD CONSTRAINT user_type_id
    FOREIGN KEY (user_type) references user_type(id);

ALTER TABLE sector ADD CONSTRAINT company_id
    FOREIGN KEY (company_id) references company(id);
