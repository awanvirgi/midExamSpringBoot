INSERT INTO Authors (Name, Description)
VALUES
('J.K. Rowling', 'British author, best known for the Harry Potter series.'),
('George R.R. Martin', 'American novelist and short story writer, author of A Song of Ice and Fire.'),
('Haruki Murakami', 'Japanese writer known for his blend of surrealism and realism.'),
('Agatha Christie', 'English writer known for her detective novels.'),
('Paulo Coelho', 'Brazilian lyricist and novelist, author of The Alchemist.'),
('Stephen King', 'American author of horror, supernatural fiction, suspense, and fantasy novels.'),
('Jane Austen', 'English novelist known for her six major novels.'),
('Dan Brown', 'American author best known for thriller novels including The Da Vinci Code.'),
('Leo Tolstoy', 'Russian writer regarded as one of the greatest authors of all time.'),
('Isabel Allende', 'Chilean-American writer known for works in magical realism.');

INSERT INTO Books (Name, Cover, ReleaseDate, IsDeleted, Author)
VALUES
('Harry Potter and the Sorcerer''s Stone', 'harry_potter_1.jpg', '1997-06-26', 0, 1),
('A Game of Thrones', 'got_cover.jpg', '1996-08-06', 0, 2),
('Kafka on the Shore', 'kafka_shore.jpg', '2002-09-12', 0, 3),
('Murder on the Orient Express', 'orient_express.jpg', '1934-01-01', 0, 4),
('The Alchemist', 'alchemist.jpg', '1988-04-15', 0, 5),
('It', 'it_cover.jpg', '1986-09-15', 0, 6),
('Pride and Prejudice', 'pride_prejudice.jpg', '1813-01-28', 0, 7),
('The Da Vinci Code', 'da_vinci_code.jpg', '2003-03-18', 0, 8),
('War and Peace', 'war_peace.jpg', '1869-01-01', 0, 9);

INSERT INTO Users (Username, Email, FirstName, LastName, BirthDate, Gender, Role, Deactivated)
VALUES
('jdoe', 'jdoe@example.com', 'John', 'Doe', '1990-05-15', 'M', 'USER', 0),
('asmith', 'asmith@example.com', 'Alice', 'Smith', '1985-08-22', 'F', 'ADMIN', 0),
('bchan', 'bchan@example.com', 'Bruce', 'Chan', '1992-11-03', 'M', 'USER', 0),
('lgarcia', 'lgarcia@example.com', 'Laura', 'Garcia', '1988-02-10', 'F', 'USER', 1),
('mkim', 'mkim@example.com', 'Michael', 'Kim', '1995-07-30', 'M', 'USER', 0),
('slee', 'slee@example.com', 'Sophie', 'Lee', '1993-12-25', 'F', 'USER', 0),
('dwang', 'dwang@example.com', 'David', 'Wang', '1980-03-18', 'M', 'ADMIN', 1),
('nroberts', 'nroberts@example.com', 'Natalie', 'Roberts', '1997-09-09', 'F', 'USER', 0),
('tnguyen', 'tnguyen@example.com', 'Tom', 'Nguyen', '1989-06-12', 'M', 'ADMIN', 0),
('ekurniawan', 'ekurniawan@example.com', 'Eka', 'Kurniawan', '1982-01-01', 'M', 'USER', 0);

INSERT INTO Highlights(OrderNumber,Book_id,AddedDate)
VALUES(1,1,'2025-11-18T12:09:41.9165025')

INSERT INTO Reviews (Title, Description, AddedDate, ReadStatus, FinishingReadingDate, ReviewDate, Rating, UserId, BookId,Deactivated)
VALUES
-- jdoe
('Great Read', 'Loved the pacing and characters.', '2025-11-01', 'FINISHED', '2025-11-05 14:00:00', '2025-11-06 10:00:00', 5, 1, 1,0),
('Not Bad', 'Interesting premise but slow middle.', '2025-10-20', 'FINISHED', '2025-10-25 18:30:00', '2025-10-26 09:00:00', 3, 1, 2,0),
(NULL, NULL, '2025-11-15', 'PLANNED', NULL, NULL, NULL, 1, 3,0),

-- bchan
('Thrilling!', 'Couldn’t put it down.', '2025-09-15', 'FINISHED', '2025-09-20 20:00:00', '2025-09-21 08:00:00', 4, 3, 3,0),
('Halfway Through', 'So far so good.', '2025-11-10', 'READING', NULL, '2025-11-11 20:00:00', NULL, 3, 2,0),

-- lgarcia (meskipun deleted = 1, tetap bisa review jika tidak dibatasi)
('Emotional', 'A touching story about family.', '2025-08-01', 'FINISHED', '2025-08-05 15:00:00', '2025-08-06 11:00:00', 5, 4, 1,0),

-- mkim
('Too Long', 'Could have been shorter.', '2025-07-10', 'FINISHED', '2025-07-20 17:00:00', '2025-07-21 09:30:00', 2, 5, 4,0),

-- slee
('Beautifully Written', 'Loved the prose and message.', '2025-06-01', 'FINISHED', '2025-06-10 13:00:00', '2025-06-11 10:00:00', 5, 6, 5,0),

-- nroberts
('Confusing Plot', 'Hard to follow at times.', '2025-05-01', 'FINISHED', '2025-05-07 19:00:00', '2025-05-08 08:00:00', 3, 8, 2,0),

-- ekurniawan
('Classic!', 'A must-read for everyone.', '2025-04-01', 'FINISHED', '2025-04-05 12:00:00', '2025-04-06 09:00:00', 5, 10, 3,0);


