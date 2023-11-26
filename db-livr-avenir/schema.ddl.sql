DROP TABLE IF EXISTS book_items;
DROP TABLE IF EXISTS book_authors;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS conditions;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS publishers;
DROP TABLE IF EXISTS languages;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_status;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS cities;

CREATE TABLE cities (
    id SERIAL,
    zip_code VARCHAR(6) not null,
    city_name VARCHAR(100) not null,
    constraint pk_city_id primary key(id)
);

CREATE TABLE addresses (
    id SERIAL,
    address_code VARCHAR(300) NOT NULL,
    number VARCHAR(6) not null,
    repetition_index VARCHAR(100) not null,
    street VARCHAR(100) not null,
	city_id INTEGER NOT null,
	constraint pk_address_id primary key(id),
	constraint fk_city_id 
			foreign key (city_id)
			references cities(id)
);

CREATE TABLE roles (
	id SERIAL,
	code_role VARCHAR(300) UNIQUE NOT NULL,
	role_name VARCHAR(300) UNIQUE NOT null,
	constraint pk_role_id primary key(id)
);

CREATE TABLE users (
	id SERIAL,
	first_name VARCHAR(300) NOT NULL,
	last_name VARCHAR(300) NOT NULL,
	email VARCHAR(300) UNIQUE NOT NULL,
	password VARCHAR(1000) NOT NULL,
    registration_date TIMESTAMP NOT NULL,
    points_number INTEGER,
	role_id INTEGER NOT NULL,
	constraint pk_user_id primary key(id),
	constraint fk_role_id 
			foreign key (role_id)
			references roles(id)
);

CREATE TABLE order_status (
	id SERIAL,
	status_code VARCHAR(30) UNIQUE NOT NULL,
    status_label VARCHAR(30) UNIQUE NOT null,
    constraint pk_order_status_id primary key(id)
);

CREATE TABLE orders (
	id SERIAL,
	order_number VARCHAR(30) UNIQUE NOT NULL,
    ordered_at TIMESTAMP NOT NULL,
	address_id INTEGER NOT NULL,
	status_id INTEGER NOT NULL,
	user_id INTEGER NOT NULL,
	constraint pk_order_id primary key(id),
	constraint fk_address_id 
			foreign key (address_id)
			references addresses(id),
	constraint fk_status_id
			foreign key (status_id)
			references order_status(id),
	constraint fk_user_id
			foreign key (user_id)
			references users(id)
			);

CREATE TABLE authors (
	id SERIAL,
    author_code VARCHAR(300) NOT NULL,
	first_name VARCHAR(300) NOT NULL,
	last_name VARCHAR(300) NOT null,
	constraint pk_author_id primary key(id)
);

CREATE TABLE languages (
	id SERIAL,
	language_name VARCHAR(30) UNIQUE NOT NULL,
	code_iso VARCHAR(30) UNIQUE NOT NULL,
	logical_order SMALLINT NOT NULL,
	constraint pk_language_id primary key(id)
);

CREATE TABLE publishers (
	id SERIAL,
    publisher_code VARCHAR(100) NOT null,
	publisher_name VARCHAR(100) UNIQUE NOT null,
	constraint pk_publisher_id primary key(id)
);

CREATE TABLE categories (
	id SERIAL,
    category_code VARCHAR(100) NOT null,
	category_name VARCHAR(100) UNIQUE NOT null,
	constraint pk_category_id primary key(id)
);

CREATE TABLE books (
    id SERIAL,
    isbn VARCHAR(13) UNIQUE NOT NULL, 
    title VARCHAR(200) NOT NULL,
    publication_year VARCHAR(4) NOT NULL,
    cover_image_url VARCHAR(300) NOT NULL,
    page_count VARCHAR(5) NOT NULL,
    summary VARCHAR(1000) NOT NULL,
    publisher_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    language_id INTEGER NOT NULL,
    CONSTRAINT pk_book_id primary key(id),
	CONSTRAINT fk_publisher_id FOREIGN KEY (publisher_id) REFERENCES publishers(id),
	CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES categories(id),
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id),
	CONSTRAINT fk_language_id FOREIGN KEY (language_id) REFERENCES languages(id)
);

CREATE TABLE conditions (
	id SERIAL,
    condition_code VARCHAR(30) NOT null,
	condition_name VARCHAR(30) UNIQUE NOT NULL,
	logical_order SMALLINT NOT null,
	constraint pk_condition_id primary key(id)
);

CREATE TABLE book_items (
    id SERIAL,
    item_code VARCHAR(30) NOT null,
    description VARCHAR(1000) NOT NULL,
    points_price INTEGER NOT NULL,
    added_at TIMESTAMP NOT NULL,
    condition_id INTEGER NOT NULL,
    order_id INTEGER,
    book_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    constraint pk_book_item_id primary key(id),
    constraint fk_condition_id
			foreign key (condition_id)
			references conditions(id),
	constraint fk_order_id
			foreign key (order_id)
			references orders(id),
	constraint fk_book_id
			foreign key (book_id)
			references books(id),
	constraint fk_user_id
			foreign key (user_id)
			references users(id)
);


CREATE TABLE book_authors (
id SERIAL,
book_id INTEGER not null,
author_id INTEGER not null,
constraint pk_book_authors_id primary key(id),
constraint fk_book_id
			foreign key (book_id)
			references books(id),
constraint fk_author_id
			foreign key (author_id)
			references authors(id)
);


