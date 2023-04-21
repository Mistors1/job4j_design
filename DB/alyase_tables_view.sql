select pp.name, p.number 
from users as pp join phone as p on pp.id_phone = p.id;

select pp.name as Имя, p.number as Номер 
from users as pp join phone as p on pp.id_phone = p.id;
