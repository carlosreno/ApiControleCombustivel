ALTER TABLE contracts add supplier_id INT;
ALTER table contracts ADD CONSTRAINT
    fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES companies(id)