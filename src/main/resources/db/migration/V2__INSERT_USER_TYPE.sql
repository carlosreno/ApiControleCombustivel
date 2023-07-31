-- Verifica se a migração já foi aplicada antes de inserir os dados
INSERT INTO company (name, cnpj, address, phone, email)
SELECT '1', '85.387.658/0001-50', 'address', 'phone', 'email'
WHERE NOT EXISTS (SELECT 1 FROM flyway_schema_history WHERE version = '2.0.0');

INSERT INTO user_type (id, name)
SELECT 1, 'master' UNION
SELECT 2, 'authorizing' UNION
SELECT 3, 'Reports'
WHERE NOT EXISTS (SELECT 1 FROM flyway_schema_history WHERE version = '2.0.0');

INSERT INTO user (name, company_id, user_type)
SELECT 'davi', 1, 1 UNION
SELECT 'vivi', 1, 2 UNION
SELECT 'pingo', 1, 3
WHERE NOT EXISTS (SELECT 1 FROM flyway_schema_history WHERE version = '2.0.0');

INSERT INTO type_vehicles(id, name) (
    SELECT null,'automotive'
    WHERE NOT EXISTS(SELECT 1 FROM flyway_schema_history WHERE version = '2.0.0')
);