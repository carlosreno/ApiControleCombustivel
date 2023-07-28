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
    active_availability_id INT,
    status ENUM('active','disabled')
);

CREATE TABLE IF NOT EXISTS sector (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL,
                                      company_id INT
);
CREATE TABLE IF NOT EXISTS availability (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      car_id INT,
                                      sector_id INT,
                                      status ENUM('active','disable')
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
                          comments TEXT,
                          supplier_id INT
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

CREATE TABLE IF NOT EXISTS requests (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        request_date DATE NOT NULL,
                                        fuel_amount DECIMAL(10, 2) NOT NULL,
                                        comments TEXT,
                                        status ENUM('Pending', 'Approved', 'Rejected', 'Completed') NOT NULL,
                                        car_id INT NOT NULL,
                                        requester_id INT NOT NULL,
                                        contract_id INT NOT NULL
);

CREATE TABLE IF NOT EXISTS fueling (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       fuel_date DATE NOT NULL,
                                       fuel_amount DECIMAL(10, 2) NOT NULL,
                                       price_per_unit DECIMAL(10, 2) NOT NULL,
                                       total_cost DECIMAL(10, 2) NOT NULL,
                                       comments TEXT,
                                       car_id INT NOT NULL,
                                       request_id INT,
                                       contract_id INT NOT NULL
);

ALTER TABLE cars
    ADD CONSTRAINT fk_activate_availability_id
    FOREIGN KEY (active_availability_id) references availability(id),

    ADD CONSTRAINT fk1_company_id
    FOREIGN KEY (company_id) references company(id);


ALTER TABLE contract
    ADD CONSTRAINT fk_supplier_id
    FOREIGN KEY (supplier_id) references supplier(id);

ALTER TABLE availability
    ADD CONSTRAINT fk_sector_id FOREIGN KEY (sector_id) references sector(id),
    ADD CONSTRAINT car_id FOREIGN KEY (car_id) references cars(id);

ALTER TABLE user
    ADD CONSTRAINT fk_user_type_id
    FOREIGN KEY (user_type) references user_type(id),
    ADD CONSTRAINT fk_company_id
    FOREIGN KEY (company_id) references company(id);

ALTER TABLE sector
    ADD CONSTRAINT fk2_company_id
    FOREIGN KEY (company_id) references company(id);

ALTER TABLE fueling
      ADD CONSTRAINT fk_fueling_car_id FOREIGN KEY (car_id) REFERENCES cars(id),
      ADD CONSTRAINT fk1_contract_id FOREIGN KEY (contract_id) REFERENCES contract(id),
      ADD CONSTRAINT fk_request_id FOREIGN KEY (request_id) REFERENCES requests(id);

ALTER TABLE requests
        ADD CONSTRAINT fk_car_id FOREIGN KEY (car_id) REFERENCES cars(id),
        ADD CONSTRAINT fk_requester_id FOREIGN KEY (requester_id) REFERENCES user(id),
        ADD CONSTRAINT fk1_contract_id FOREIGN KEY (contract_id) REFERENCES contract(id);
