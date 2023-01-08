select users.id, users.is_blocked, users.username, users.password, users.user_role, profiles.firstname, profiles.surname, profiles.phone, profiles.email, profiles.birthdate 
from users
join profiles
on users.id = profiles.id;