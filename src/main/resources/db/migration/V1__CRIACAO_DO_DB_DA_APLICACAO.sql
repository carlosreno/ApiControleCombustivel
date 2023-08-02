CREATE TABLE IF NOT EXISTS company (
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
                                    marca VARCHAR(50),
                                    brand VARCHAR(50),
                                    year INT,
                                    color VARCHAR(30),
                                    fuel VARCHAR(30),
                                    mileage DOUBLE,
                                    fabrication_date DATE,
                                    type_vehicles_id INT NOT NULL ,
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
                          company_id INT
);
CREATE TABLE IF NOT EXISTS fuel (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(100) NOT NULL,
                                    unit VARCHAR(20) NOT NULL,
                                    price_per_unit DECIMAL(10, 2) NOT NULL
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

    ADD CONSTRAINT fk_type_vehicles_id
        FOREIGN KEY (type_vehicles_id) references type_vehicles(id),

    ADD CONSTRAINT fk_company_id
        FOREIGN KEY (company_id) references company(id);


ALTER TABLE contract
    ADD CONSTRAINT fk1_company_id
    FOREIGN KEY (company_id) references company(id);

ALTER TABLE availability
    ADD CONSTRAINT fk_sector_id FOREIGN KEY (sector_id) references sector(id),
    ADD CONSTRAINT car_id FOREIGN KEY (car_id) references cars(id);

ALTER TABLE user
    ADD CONSTRAINT fk_user_type_id
    FOREIGN KEY (user_type) references user_type(id),
    ADD CONSTRAINT fk2_company_id
    FOREIGN KEY (company_id) references company(id);

ALTER TABLE user_credential
    ADD CONSTRAINT fk_user_id
    FOREIGN KEY (user_id) references user(id);

ALTER TABLE sector
    ADD CONSTRAINT fk3_company_id
    FOREIGN KEY (company_id) references company(id);

ALTER TABLE fueling
      ADD CONSTRAINT fk_fueling_car_id FOREIGN KEY (car_id) REFERENCES cars(id),
      ADD CONSTRAINT fk1_contract_id FOREIGN KEY (contract_id) REFERENCES contract(id),
      ADD CONSTRAINT fk_request_id FOREIGN KEY (request_id) REFERENCES requests(id);

ALTER TABLE requests
        ADD CONSTRAINT fk_car_id FOREIGN KEY (car_id) REFERENCES cars(id),
        ADD CONSTRAINT fk_requester_id FOREIGN KEY (requester_id) REFERENCES user(id),
        ADD CONSTRAINT fk2_contract_id FOREIGN KEY (contract_id) REFERENCES contract(id);

ALTER TABLE address
        ADD CONSTRAINT fk2_user_id FOREIGN KEY (user_id) REFERENCES user(id),
        ADD CONSTRAINT fk4_company_id FOREIGN KEY (company_id) REFERENCES company(id);

ALTER TABLE phone
        ADD CONSTRAINT fk3_user_id FOREIGN KEY (user_id) REFERENCES user(id),
        ADD CONSTRAINT fk5_company_id FOREIGN KEY (company_id) REFERENCES company(id)
