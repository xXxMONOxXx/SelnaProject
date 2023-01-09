select items
from items
join users
on items.fk_users_id = users.id
where users.id = 1;