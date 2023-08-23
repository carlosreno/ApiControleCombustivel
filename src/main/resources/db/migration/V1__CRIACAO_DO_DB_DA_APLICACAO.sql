CREATE TABLE IF NOT EXISTS companies (
     id INT AUTO_INCREMENT PRIMARY KEY,
     razao_social VARCHAR(100) NOT NULL,
     nome_fantasia VARCHAR(100),
     cnpj VARCHAR(18) NOT NULL,
     company_type ENUM('supplier', 'manageable') NOT NULL
);
CREATE TABLE IF NOT EXISTS phone (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        ddi VARCHAR(3),
                                        ddd VARCHAR(3),
                                        number VARCHAR(9) NOT NULL,
                                        type ENUM('phone', 'residential', 'commercial', 'other') NOT NULL,
                                        company_id INT,
                                        user_id INT
);

CREATE TABLE IF NOT EXISTS address (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          cep VARCHAR(10) NOT NULL,
                          logradouro VARCHAR(200) NOT NULL,
                          complemento VARCHAR(100),
                          bairro VARCHAR(100) NOT NULL,
                          localidade VARCHAR(100) NOT NULL,
                          uf CHAR(2) NOT NULL,
                          address_principal BOOLEAN NOT NULL DEFAULT 0,
                          company_id INT,
                          user_id INT
);

CREATE TABLE IF NOT EXISTS type_vehicles(
                                            id INT AUTO_INCREMENT PRIMARY KEY ,
                                            name VARCHAR(150) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS cars (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    renavam VARCHAR(11) NOT NULL,
                                    placa VARCHAR(100) NOT NULL,
                                    brand VARCHAR(50),
                                    year INT,
                                    color VARCHAR(30),
                                    mileage DOUBLE,
                                    fabrication_date DATE,
                                    type_vehicles_id INT NOT NULL ,
                                    comments TEXT,
                                    company_id INT,
                                    active_availability_id INT,
                                    status ENUM('active','disabled')
);

CREATE TABLE IF NOT EXISTS sectors (
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
CREATE TABLE IF NOT EXISTS user_type (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         name VARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS user (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL,
                                      company_id INT,
                                      user_type INT
);
CREATE TABLE IF NOT EXISTS user_credential (
                                               id INT AUTO_INCREMENT PRIMARY KEY,
                                               user_id INT NOT NULL,
                                               username VARCHAR(50) NOT NULL UNIQUE,
                                               password_hash VARCHAR(100) NOT NULL,
                                               status ENUM('active','disable')
);

CREATE TABLE IF NOT EXISTS contracts (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          number_contract VARCHAR(50) NOT NULL,
                          object TEXT NOT NULL,
                          date_initial DATE,
                          date_final DATE,
                          value DECIMAL(10, 2),
                          status ENUM('Active', 'Closed', 'Canceled', 'Suspended'),
                          comments TEXT,
                          customer_id INT
);
CREATE TABLE IF NOT EXISTS contract_addendum (
                                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                                 contract_id INT NOT NULL,
                                                 type ENUM('term','value','amount'),
                                                 addendum_date DATE NOT NULL,
                                                 FOREIGN KEY (contract_id) REFERENCES contracts(id)
);
CREATE TABLE IF NOT EXISTS addendum_contract_items (
                                       addendum_id INT NOT NULL,
                                       contract_item_id INT NOT NULL,
                                       PRIMARY KEY (addendum_id, contract_item_id),
                                       new_quantity DECIMAL(10, 2),
                                       new_value DECIMAL(10, 2)
);

CREATE TABLE IF NOT EXISTS fuels_contract_items(
    fuel_id INT NOT NULL ,
    contract_id INT NOT NULL ,
    primary key(contract_id,fuel_id),
    amount INT NOT NULL ,
    price_per_unit DECIMAL(10, 2) NOT NULL,
    total_cost DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS fuels (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(100) NOT NULL,
                                    unit VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_sector (
                               user_id INT NOT NULL,
                               sector_id INT NOT NULL,
                               PRIMARY KEY (user_id, sector_id),
                               FOREIGN KEY (user_id) REFERENCES user(id),
                               FOREIGN KEY (sector_id) REFERENCES sectors (id)
);

CREATE TABLE IF NOT EXISTS requests (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        request_date DATE NOT NULL,
                                        fuels_contract INT NOT NULL ,
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
                                       fuels_contract INT NOT NULL ,
                                       fuel_amount DECIMAL(10, 2) NOT NULL,
                                       price_per_unit DECIMAL(10, 2) NOT NULL,
                                       total_cost DECIMAL(10, 2) NOT NULL,
                                       mileage FLOAT,
                                       comments TEXT,
                                       car_id INT NOT NULL,
                                       request_id INT,
                                       contract_id INT NOT NULL
);
CREATE TABLE IF NOT EXISTS cars_fuels (
                                          car_id INT NOT NULL,
                                          fuel_id INT NOT NULL,
                                          PRIMARY KEY (car_id, fuel_id),
                                          FOREIGN KEY (car_id) REFERENCES user(id),
                                          FOREIGN KEY (fuel_id) REFERENCES sectors (id)
);
ALTER TABLE cars
    ADD CONSTRAINT fk_activate_availability_id
        FOREIGN KEY (active_availability_id) references availability(id),

    ADD CONSTRAINT fk_type_vehicles_id
        FOREIGN KEY (type_vehicles_id) references type_vehicles(id),

    ADD CONSTRAINT fk_company_id
        FOREIGN KEY (company_id) references companies(id);


ALTER TABLE contracts
    ADD CONSTRAINT fk1_company_id
    FOREIGN KEY (customer_id) references companies(id);

ALTER TABLE fuels_contract_items
    ADD CONSTRAINT fk_fuel_id
        FOREIGN KEY (fuel_id) references fuels (id),
    ADD CONSTRAINT fk6_contract_id
        FOREIGN KEY (contract_id) references contracts(id);

ALTER TABLE availability
    ADD CONSTRAINT fk_sector_id FOREIGN KEY (sector_id) references sectors(id),
    ADD CONSTRAINT car_id FOREIGN KEY (car_id) references cars(id);

ALTER TABLE user
    ADD CONSTRAINT fk_user_type_id
    FOREIGN KEY (user_type) references user_type(id),
    ADD CONSTRAINT fk2_company_id
    FOREIGN KEY (company_id) references companies(id);

ALTER TABLE user_credential
    ADD CONSTRAINT fk_user_id
    FOREIGN KEY (user_id) references user(id);

ALTER TABLE sectors
    ADD CONSTRAINT fk3_company_id
    FOREIGN KEY (company_id) references companies(id);

ALTER TABLE fueling
      ADD CONSTRAINT fk_fueling_car_id FOREIGN KEY (car_id) REFERENCES cars(id),
      ADD CONSTRAINT fk1_item_contract_id FOREIGN KEY (fuels_contract) REFERENCES fuels_contract_items(fuel_id),
      ADD CONSTRAINT fk1_contract_id FOREIGN KEY (contract_id) REFERENCES contracts (id),
      ADD CONSTRAINT fk_request_id FOREIGN KEY (request_id) REFERENCES requests(id);

ALTER TABLE requests
        ADD CONSTRAINT fk_car_id FOREIGN KEY (car_id) REFERENCES cars(id),
        ADD CONSTRAINT fk2_item_contract_id FOREIGN KEY (fuels_contract) REFERENCES fuels_contract_items(fuel_id),
        ADD CONSTRAINT fk_requester_id FOREIGN KEY (requester_id) REFERENCES user(id),
        ADD CONSTRAINT fk2_contract_id FOREIGN KEY (contract_id) REFERENCES contracts (id);

ALTER TABLE address
        ADD CONSTRAINT fk2_user_id FOREIGN KEY (user_id) REFERENCES user(id),
        ADD CONSTRAINT fk4_company_id FOREIGN KEY (company_id) REFERENCES companies(id);

ALTER TABLE phone
        ADD CONSTRAINT fk3_user_id FOREIGN KEY (user_id) REFERENCES user(id),
        ADD CONSTRAINT fk5_company_id FOREIGN KEY (company_id) REFERENCES companies(id);

ALTER TABLE addendum_contract_items
    ADD CONSTRAINT fk_addendum_id FOREIGN KEY (addendum_id) REFERENCES contract_addendum(id),
    ADD CONSTRAINT fk_contract_item_id
        FOREIGN KEY (addendum_id,contract_item_id) REFERENCES fuels_contract_items(fuel_id,contract_id)