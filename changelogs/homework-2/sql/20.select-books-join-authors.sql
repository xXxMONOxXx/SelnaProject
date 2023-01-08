select books
from books
join book_authors
on book_authors.fk_book_id = books.id
where book_authors.fk_author_id = 1;