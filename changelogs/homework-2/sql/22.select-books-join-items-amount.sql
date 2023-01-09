select books.title, count(*) as amount
from books
join items
on books.id = items.fk_book_id 
group by books.title;