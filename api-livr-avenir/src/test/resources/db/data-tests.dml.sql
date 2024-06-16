TRUNCATE TABLE book_items, book_authors, books, conditions, categories, publishers, languages, authors, orders, order_status,verification_tokens, users, roles, addresses, cities;

INSERT INTO roles
	(code_role, role_name)
	VALUES
    ('ROLE_ADMIN', 'ADMIN'),  ('ROLE_USER', 'USER');
    
INSERT INTO users
	(first_name, last_name, email, password, registration_date, points_number, role_id, is_enabled)
	VALUES
    ( 'Testadmin', 'Admin', 'admin@admin.com', '$2a$11$mTbgXh6GL/hWlZgzYMB55OrswkrWOC0squWLwJjSVBMp1Lmfbqdk6', now(), 5, (SELECT r.id FROM roles r WHERE r.code_role = 'ROLE_ADMIN'), true),
	( 'John', 'Doe', 'john.doe@test.com', '$2a$11$HZfRa03aYXGReZWnrZvo0eEvC5cr5MmkqrHGURv5GDC3xDjw8hO.K', now(), 5, (SELECT r.id FROM roles r WHERE r.code_role = 'ROLE_USER'), true);

INSERT INTO languages 
	(language_name, iso_code, logical_order)
	VALUES
	('Autre', 'N/a', 1),
    ('Français', 'fr', 2);

INSERT INTO categories 
	(category_code, category_name)
	VALUES 
	('literature', 'Littérature'), ('children-teens', 'Jeunesse'), ('comics', 'Bandes dessinées'), ('leisure-nature-travel','Loisir - Nature - Voyage'), ('art-music-cinema','Art - Musique - Cinéma'),('self-help', 'Développement personnel'), ('it','Informatique'),('other','Autre');

INSERT INTO publishers 
	(publisher_code, publisher_name)
	VALUES 
	('test_publisher', 'Test Publisher');


INSERT INTO authors 
	(author_code, first_name, last_name)
	VALUES 
	('test-author', 'Test', 'Author'),
    ('second-author', 'Second', 'TestAuthor');

INSERT INTO books
	(isbn, title, publication_year, cover_image_url, page_count, summary, publisher_id, category_id, user_id, language_id)
	VALUES
    ('0000000000', 'Test title', '2023',  'dejeunons-sur-l-herbe.jpg', '250',
    'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Mollitia inventore animi quidem, tempore molestiae facilis voluptas veniam nostrum, temporibus iste pariatur fugit laborum in quis et repellat hic esse harum.',
    (SELECT p.id FROM publishers p WHERE p.publisher_code = 'test_publisher'),
    (SELECT c.id FROM categories c WHERE c.category_code = 'literature'),
    (SELECT u.id FROM users u WHERE u.email = 'admin@admin.com'),
    (SELECT l.id FROM languages l WHERE l.iso_code = 'fr')),
    ('1111111111111', 'Second test title', '2023',  'nos-mondes-perdus.jpg', '208',
    'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Mollitia inventore animi quidem, tempore molestiae facilis voluptas veniam nostrum, temporibus iste pariatur fugit laborum in quis et repellat hic esse harum.',
    (SELECT p.id FROM publishers p WHERE p.publisher_code = 'test_publisher'),
    (SELECT c.id FROM categories c WHERE c.category_code = 'comics'),
    (SELECT u.id FROM users u WHERE u.email = 'admin@admin.com'),
    (SELECT l.id FROM languages l WHERE l.iso_code = 'fr'));
    

INSERT INTO conditions 
	(condition_code, condition_name, logical_order)
	VALUES
	('as_new', 'Comme neuf', 1), ('very_good', 'Très bon', 2), ('good', 'Bon', 3), ('satisfying', 'Satisfaisant', 4);

INSERT INTO book_items
	(item_code, description, points_price, added_at, condition_id, order_id, book_id, user_id)
	VALUES
    ('0000000000', 'Test Description', 2, now(), (SELECT c.id FROM conditions c WHERE c.condition_code = 'good'), null, (SELECT b.id FROM books b WHERE b.isbn = '9782382920664'), (SELECT u.id FROM users u WHERE u.email = 'john.doe@test.com')),
    ('1111111111111', 'Test Description', 4, now(), (SELECT c.id FROM conditions c WHERE c.condition_code = 'very_good'), null, (SELECT b.id FROM books b WHERE b.isbn = '9782382920664'), (SELECT u.id FROM users u WHERE u.email = 'john.doe@test.com')) ;

INSERT INTO book_authors
    (book_id, author_id)
    VALUES
    ((SELECT b.id FROM books b WHERE b.isbn = '0000000000'),(SELECT a.id FROM authors a WHERE a.author_code = 'test-author')),
    ((SELECT b.id FROM books b WHERE b.isbn = '1111111111111'),(SELECT a.id FROM authors a WHERE a.author_code = 'second-author'));