-- Create Cars Table
CREATE TABLE cars (
    cars_id SERIAL PRIMARY KEY,
    brand VARCHAR(100),
    model VARCHAR(100),
    year INTEGER,
    price DECIMAL(10, 2),
    available BOOLEAN,
    category_id INTEGER,
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);

-- Create Category Table
CREATE TABLE category (
    category_id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

-- Create Car_Category Table
CREATE TABLE cars_category (
    cars_id INTEGER,
    category_id INTEGER,
    PRIMARY KEY (cars_id, category_id),
    FOREIGN KEY (cars_id) REFERENCES cars(cars_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);