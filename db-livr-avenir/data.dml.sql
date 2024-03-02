TRUNCATE TABLE book_items, book_authors, books, conditions, categories, publishers, languages, authors, orders, order_status, users, roles, addresses, cities;

INSERT INTO roles
	(code_role, role_name)
	VALUES
    ('ROLE_ADMIN', 'ADMIN'),  ('ROLE_USER', 'USER');
    
INSERT INTO users
	(first_name, last_name, email, password, registration_date, points_number, role_id)
	VALUES
    ( 'Olha', 'Raulet', 'raulet.olha@gmail.com', '$2a$11$mTbgXh6GL/hWlZgzYMB55OrswkrWOC0squWLwJjSVBMp1Lmfbqdk6', now(), 5, (SELECT r.id FROM roles r WHERE r.code_role = 'ROLE_ADMIN')),
    ( 'Olha', 'Admin', 'admin@admin.com', '$2a$11$mTbgXh6GL/hWlZgzYMB55OrswkrWOC0squWLwJjSVBMp1Lmfbqdk6', now(), 5, (SELECT r.id FROM roles r WHERE r.code_role = 'ROLE_ADMIN')),
	( 'Diane', 'Mallet', 'diane.mallet@hotmail.fr', '$2a$11$HZfRa03aYXGReZWnrZvo0eEvC5cr5MmkqrHGURv5GDC3xDjw8hO.K', now(), 5, (SELECT r.id FROM roles r WHERE r.code_role = 'ROLE_USER')),
    ( 'Test', 'User', 'testuser@gmail.com', '$2a$11$mDvDEbWiKeBWlbK/Y6n.uenZ9S6VDoPFj6w15EBOEPqQh7whZKXOC', now(), 5, (SELECT r.id FROM roles r WHERE r.code_role = 'ROLE_USER'));

INSERT INTO languages 
	(language_name, code_iso, logical_order)
	VALUES
	('Autre', 'N/a', 1),
    ('Français', 'fr', 2),
    ('Anglais', 'en', 3),
    ('Arabe', 'ar', 4),
    ('Bulgar', 'bg', 5),
    ('Tchèque', 'cs', 8), 
    ('Danois', 'da', 9), 
    ('Allemand', 'de', 10), 
    ('Grec', 'el', 11), 
    ('Espagnol', 'es', 12), 
    ('Estonien', 'et', 13),
    ('Basque', 'eu', 14),
    ('Finlandais', 'fi', 15),
    ('Hongrois', 'hu', 16),
    ('Arménien', 'hy', 17),
    ('Italien', 'it', 18),
    ('Japonais', 'ja', 19),
    ('Géorgien', 'ka', 20),
    ('Coréen', 'ko', 21),
    ('Latin', 'la', 22),
    ('Lituanien', 'lt', 23),
    ('Letton', 'lv', 24),
    ('Norvégien', 'no', 25),
    ('Polonais', 'pl', 26),
    ('Portugais', 'pt', 27),
    ('Slovaque', 'sk', 28),
    ('Slovène', 'sl', 29),
    ('Albanais', 'sq', 30),
    ('Serbe', 'sr', 31),
    ('Suédois', 'sv', 32),
    ('Turc', 'tr', 33),
    ('Ukrainien', 'uk', 34),
    ('Vietnamien', 'vi', 35),
    ('Chinois', 'zh', 36);

INSERT INTO categories 
	(category_code, category_name)
	VALUES 
	('literature', 'Littérature'), ('children-teens', 'Jeunesse'), ('comics', 'Bandes dessinées'), ('leisure-nature-travel','Loisir - Nature - Voyage'), ('art-music-cinema','Art - Musique - Cinéma'), ('other','Autre');

INSERT INTO publishers 
	(publisher_code, publisher_name)
	VALUES 
	('essai', 'Essai'),
    ('dargaud', 'Dargaud'),
    ('gallimard', 'Gallimard'),
    ('gallimard-jeunesse', 'Gallimard Jeunesse'),
    ('lgf', 'Lgf'),
    ('pocket', 'Pocket');

INSERT INTO authors 
	(author_code, first_name, last_name)
	VALUES 
	('guillaume-durand', 'Guillaume', 'Durand'),
    ('albert-camus', 'Albert', 'Camus'),
    ('alexandre-dumas', 'Alexandre', 'Dumas'),
    ('edgar-allan-poe', 'Edgar Allan', 'Poe'),
    ('victor-hugo', 'Victor', 'Hugo'),
    ('virginie-grimaldi', 'Virginie', 'Grimaldi'),
    ('marion-montaigne', 'Marion', 'Montaigne'),
    ('muriel-barbery', 'Muriel', 'Barbery'),
    ('j-k-rowling', 'J.K.', 'Rowling'),
    ('sylvain-tesson', 'Sylvain', 'Tesson'),
    ('gustave-flaubert', 'Gustave', 'Flaubert'),
    ('danielle-steel', 'Danielle', 'Steel'),
    ('hal-elrod', 'Hal', 'Elrod');

INSERT INTO books
	(isbn, title, publication_year, cover_image_url, page_count, summary, publisher_id, category_id, user_id, language_id)
	VALUES
    ('9782382920664', 'Déjeunons sur l''herbe', '2023',  'dejeunons-sur-l-herbe.jpg', '250',
    'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Mollitia inventore animi quidem, tempore molestiae facilis voluptas veniam nostrum, temporibus iste pariatur fugit laborum in quis et repellat hic esse harum.',
    (SELECT p.id FROM publishers p WHERE p.publisher_code = 'essai'),
    (SELECT c.id FROM categories c WHERE c.category_code = 'literature'),
    (SELECT u.id FROM users u WHERE u.email = 'raulet.olha@gmail.com'),
    (SELECT l.id FROM languages l WHERE l.code_iso = 'fr')),
    ('2205208098', 'Nos mondes perdus', '2023',  'nos-mondes-perdus.jpg', '208',
    '1993, sortie en salles de "Jurassic Park" et traumatisme total pour la jeune Marion Montaigne, alors âgée de 13 ans. De cette fascination pour ces terribles reptiles d''un âge oublié va naître une obsession pour les fossiles, la science en général et le dessin anatomique... ainsi que quelques angoisses existentielles. Alors pour exorciser ses démons, rien de tel que la méthode Montaigne : recherches à fond dans les livres et les musées, humour décapant et interrogations bien senties. Une plongée dans la paléontologie, l''histoire des sciences et finalement, l''histoire de l''Histoire.',
    (SELECT p.id FROM publishers p WHERE p.publisher_code = 'dargaud'),
    (SELECT c.id FROM categories c WHERE c.category_code = 'comics'),
    (SELECT u.id FROM users u WHERE u.email = 'raulet.olha@gmail.com'),
    (SELECT l.id FROM languages l WHERE l.code_iso = 'fr')),
    ('9782070464326', 'L''Élégance du hérisson', '2006',  'lelegance-du-herisson.jpg', '413',
    'Ce roman explore les vies entrelacées d''une concierge parisienne autodidacte et d''une jeune surdouée. À travers leurs réflexions sur l''art, la philosophie et la vie, l''histoire révèle la beauté cachée dans les petites choses. ',
    (SELECT p.id FROM publishers p WHERE p.publisher_code = 'gallimard'),
    (SELECT c.id FROM categories c WHERE c.category_code = 'literature'),
    (SELECT u.id FROM users u WHERE u.email = 'raulet.olha@gmail.com'),
    (SELECT l.id FROM languages l WHERE l.code_iso = 'fr')),
    ('9782075187541', 'Harry Potter à l''école des sorciers',
    '1998',  'harry-potter.jpg', '320',
    'Suivez les aventures de Harry Potter, un jeune sorcier, lors de sa première année à l''école de sorcellerie Poudlard. Avec ses nouveaux amis Ron et Hermione, il découvre les mystères de la magie tout en faisant face à des forces obscures. Un classique de la littérature jeunesse empreint de magie et d''amitié.',
    (SELECT p.id FROM publishers p WHERE p.publisher_code = 'gallimard-jeunesse'),
    (SELECT c.id FROM categories c WHERE c.category_code = 'children-teens'),
    (SELECT u.id FROM users u WHERE u.email = 'raulet.olha@gmail.com'),
    (SELECT l.id FROM languages l WHERE l.code_iso = 'fr')),
    ('9782072836800', 'Dans les forêts de Sibérie', '2011', 
    'dans-les-forets-de-siberie.jpg', '272',
    'Sylvain Tesson relate son expérience de retraite dans une cabane au bord du lac Baïkal en Sibérie. À travers ce récit introspectif, mêlant nature, solitude et découvertes, l''auteur nous transporte dans une aventure captivante au cœur de la taïga, offrant une méditation sur la vie, la liberté et la beauté sauvage.',
    (SELECT p.id FROM publishers p WHERE p.publisher_code = 'gallimard'),
    (SELECT c.id FROM categories c WHERE c.category_code = 'leisure-nature-travel'),
    (SELECT u.id FROM users u WHERE u.email = 'raulet.olha@gmail.com'),
    (SELECT l.id FROM languages l WHERE l.code_iso = 'fr')),
    ('2266332120', 'Sans retour', '2023', 'sans-retour.jpg', '336',
    'Certains événements changent une vie à jamais... Bill dirige l''unité de traumatologie des urgences de l''Hôpital général de San Francisco et consacre sa vie à son travail depuis sa séparation. Étoile montante de son hôpital universitaire, Stephanie ne compte pas ses heures de travail, aux dépens de sa famille. Wendy, brillant médecin, est piégée dans une relation sans avenir avec un chirurgien marié. Quant à Tom, sa popularité auprès des femmes n''a d''égal que ses succès au bloc opératoire. Quand ces quatre experts sont invités à Paris pour échanger avec des confrères sur leurs pratiques, c''est une vraie bouffée d''oxygène. Mais une prise d''otages dans une école va bientôt marquer un tournant décisif dans leurs existences, et pourrait bien souder leurs destins à jamais...',
    (SELECT p.id FROM publishers p WHERE p.publisher_code = 'pocket'),
    (SELECT c.id FROM categories c WHERE c.category_code = 'literature'),
    (SELECT u.id FROM users u WHERE u.email = 'raulet.olha@gmail.com'),
    (SELECT l.id FROM languages l WHERE l.code_iso = 'fr')),
    ('2266268554', 'Miracle morning', '2017', 'miracle-morning.jpg', '272',
    'SE LEVER PLUS TÔT POUR GAGNER UN SUPPLÉMENT DE VIE... Et si la clef du bonheur et de la réussite se trouvait dans cette nouvelle résolution ? C''est la découverte qui a changé la vie d''Hal Elrod ainsi que celle de milliers de lecteurs. Démarrez votre journée par un moment rien qu''à vous, profitez de ce moment de calme pour méditer, faire du sport, lire et préparer votre journée, comme une nouvelle aventure à entamer chaque matin. Et faites de votre quotidien un miracle ! " On y gagne un supplément de vie, efficacité et moral d''acier !',
    (SELECT p.id FROM publishers p WHERE p.publisher_code = 'pocket'),
    (SELECT c.id FROM categories c WHERE c.category_code = 'leisure-nature-travel'),
    (SELECT u.id FROM users u WHERE u.email = 'raulet.olha@gmail.com'),
    (SELECT l.id FROM languages l WHERE l.code_iso = 'fr'));

INSERT INTO conditions 
	(condition_code, condition_name, logical_order)
	VALUES
	('as_new', 'Comme neuf', 1), ('very_good', 'Très bon', 2), ('good', 'Bon', 3), ('satisfying', 'Satisfaisant', 4);

INSERT INTO book_items
	(item_code, description, points_price, added_at, condition_id, order_id, book_id, user_id)
	VALUES
    ('2376292836729', 'Description', 2, now(), (SELECT c.id FROM conditions c WHERE c.condition_code = 'good'), null, (SELECT b.id FROM books b WHERE b.isbn = '9782382920664'), (SELECT u.id FROM users u WHERE u.email = 'diane.mallet@hotmail.fr')),
    ('2372292836727', 'Description', 4, now(), (SELECT c.id FROM conditions c WHERE c.condition_code = 'very_good'), null, (SELECT b.id FROM books b WHERE b.isbn = '9782382920664'), (SELECT u.id FROM users u WHERE u.email = 'diane.mallet@hotmail.fr')) ;

INSERT INTO book_authors
    (book_id, author_id)
    VALUES
    ((SELECT b.id FROM books b WHERE b.isbn = '9782382920664'),(SELECT a.id FROM authors a WHERE a.author_code = 'guillaume-durand')),
    ((SELECT b.id FROM books b WHERE b.isbn = '2205208098'),(SELECT a.id FROM authors a WHERE a.author_code = 'marion-montaigne')),
    ((SELECT b.id FROM books b WHERE b.isbn = '9782070464326'),(SELECT a.id FROM authors a WHERE a.author_code = 'muriel-barbery')),
    ((SELECT b.id FROM books b WHERE b.isbn = '9782075187541'),(SELECT a.id FROM authors a WHERE a.author_code = 'j-k-rowling')),
    ((SELECT b.id FROM books b WHERE b.isbn = '9782072836800'),(SELECT a.id FROM authors a WHERE a.author_code = 'sylvain-tesson')),
    ((SELECT b.id FROM books b WHERE b.isbn = '2266332120'),(SELECT a.id FROM authors a WHERE a.author_code = 'danielle-steel')),
    ((SELECT b.id FROM books b WHERE b.isbn = '2266268554'),(SELECT a.id FROM authors a WHERE a.author_code = 'hal-elrod'));