select books
from books
join book_genres
on book_genres.fk_book_id = books.id
where book_genres.fk_genre_id = 9;
