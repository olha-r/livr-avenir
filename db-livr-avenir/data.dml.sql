DELETE FROM book_items;
DELETE FROM book_authors;
DELETE FROM book_languages;
DELETE FROM books;
DELETE FROM conditions;
DELETE FROM categories;
DELETE FROM publishers;
DELETE FROM languages;
DELETE FROM authors;
DELETE FROM orders;
DELETE FROM order_status;
DELETE FROM users;
DELETE FROM roles;
DELETE FROM addresses;
DELETE FROM cities;

INSERT INTO roles
	(code_role, role_name)
	VALUES
    ('ROLE_ADMIN', 'ADMIN'),  ('ROLE_USER', 'USER');
    
INSERT INTO users
	(user_name, first_name, last_name, email, password, registration_date, points_number, role_id)
	VALUES
    ('olha.raulet', 'Olha', 'Raulet', 'raulet.olha@gmail.com', '$2a$11$mTbgXh6GL/hWlZgzYMB55OrswkrWOC0squWLwJjSVBMp1Lmfbqdk6', now(), 5, (SELECT r.id FROM roles r WHERE r.code_role = 'ROLE_ADMIN')),
	('diane_mallet', 'Diane', 'Mallet', 'diane.mallet@hotmail.fr', '$2a$11$HZfRa03aYXGReZWnrZvo0eEvC5cr5MmkqrHGURv5GDC3xDjw8hO.K', now(), 5, (SELECT r.id FROM roles r WHERE r.code_role = 'ROLE_USER')),
	('n-le-boyer', 'Nathalie', 'Le Boyer', 'natalie.le.boyer@hotmail.fr', '$2a$11$HZfRa03aYXGReZWnrZvo0eEvC5cr5MmkqrHGURv5GDC3xDjw8hO.K', now(), 5, (SELECT r.id FROM roles r WHERE r.code_role = 'ROLE_USER')),
	('edouarde.froncois', 'Édouard', 'Francois', 'edouard.francois@hotmail.fr', '$2a$11$HZfRa03aYXGReZWnrZvo0eEvC5cr5MmkqrHGURv5GDC3xDjw8hO.K', now(), 5, (SELECT r.id FROM roles r WHERE r.code_role = 'ROLE_USER')),
	('joseph.b', 'Joseph', 'Bazin', 'joseph.bazin@hotmail.fr', '$2a$11$HZfRa03aYXGReZWnrZvo0eEvC5cr5MmkqrHGURv5GDC3xDjw8hO.K', now(), 5, (SELECT r.id FROM roles r WHERE r.code_role = 'ROLE_USER')),
	('michelle-g', 'Michelle', 'Gautier', 'michelle.gautier@hotmail.fr', '$2a$11$HZfRa03aYXGReZWnrZvo0eEvC5cr5MmkqrHGURv5GDC3xDjw8hO.K', now(), 5, (SELECT r.id FROM roles r WHERE r.code_role = 'ROLE_USER')),
	('tom-cat', 'Tom', 'Cat', 'tom.cat@gmail.com', '$2a$11$HZfRa03aYXGReZWnrZvo0eEvC5cr5MmkqrHGURv5GDC3xDjw8hO.K', now(), 5, (SELECT r.id FROM roles r WHERE r.code_role = 'ROLE_USER'));

INSERT INTO order_status 
	(status_code, status_label)
	VALUES 
	('En cours', 'in_progress'), ('Terminée', 'completed');

INSERT INTO languages 
	(language_name, code_iso, logical_order)
	VALUES
	('Français', 'fr', 1), ('Anglais', 'en', 2), ('Autre', '..', 3);

INSERT INTO categories 
	(category_code, category_name)
	VALUES 
	('literature', 'Littérature'), ('children&teens', 'Jeunesse'), ('comics', 'Bandes dessinées'), ('leisure-nature-travel','Loisir - Nature - Voyage'), ('art-music-cinema','Art - Musique - Cinéma'), ('other','Autre');

INSERT INTO publishers 
	(publisher_code, publisher_name)
	VALUES 
	('essai', 'Essai');

INSERT INTO authors 
	(author_code, first_name, last_name)
	VALUES 
	('guillaume-durand', 'Guillaume', 'Durand');

INSERT INTO books
	(isbn, title, publication_year, cover_image_url, page_count, summary, publisher_id, category_id, user_id)
	VALUES
    ('978-2-38292-066-4', 'Déjeunons sur lherbe', '2010',  'dejeunons-sur-l-herbe.jpg', '250', 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Mollitia inventore animi quidem, tempore molestiae facilis voluptas veniam nostrum, temporibus iste pariatur fugit laborum in quis et repellat hic esse harum.', (SELECT p.id FROM publishers p WHERE p.publisher_code = 'essai'), (SELECT c.id FROM categories c WHERE c.category_code = 'literature'), (SELECT u.id FROM users u WHERE u.email = 'raulet.olha@gmail.com'));

INSERT INTO conditions 
	(condition_code, condition_name, logical_order)
	VALUES
	('as_new', 'Comme neuf', 1), ('very_good', 'Très bon', 2), ('good', 'Bon', 3), ('satisfying', 'Satisfaisant', '4');

INSERT INTO book_items
	(item_code, description, points_price, added_at, condition_id, order_id, book_id, user_id)
	VALUES
    ('2376292836729', 'Description', 2, now(), (SELECT c.id FROM conditions c WHERE c.condition_code = 'good'), null, (SELECT b.id FROM books b WHERE b.isbn = '978-2-38292-066-4'), (SELECT u.id FROM users u WHERE u.email = 'tom.cat@gmail.com')),
    ('2372292836727', 'Description', 4, now(), (SELECT c.id FROM conditions c WHERE c.condition_code = 'very_good'), null, (SELECT b.id FROM books b WHERE b.isbn = '978-2-38292-066-4'), (SELECT u.id FROM users u WHERE u.email = 'michelle.gautier@hotmail.fr')) ;

INSERT INTO book_languages
    (book_id, language_id)
    VALUES
    ((SELECT b.id FROM books b WHERE b.isbn = '978-2-38292-066-4'), (SELECT l.id FROM languages l WHERE l.code_iso = 'fr'));

INSERT INTO book_authors
    (book_id, author_id)
    VALUES
    ((SELECT b.id FROM books b WHERE b.isbn = '978-2-38292-066-4'), (SELECT a.id FROM authors a WHERE a.author_code = 'guillaume-durand'));