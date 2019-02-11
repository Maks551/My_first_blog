DELETE FROM users;
DELETE FROM messages;
ALTER TABLE users AUTO_INCREMENT = 1;
ALTER TABLE messages AUTO_INCREMENT = 1;

INSERT INTO users (login, password, last_name, first_name) VALUES
  ('login1', '{noop}password1', 'last name 1', 'first name 1'),
  ('login2', '{noop}password2', 'last name 2', ''),
  ('login3', '{noop}password3', 'last name 3', 'first name 3');

INSERT INTO user_roles (role, user_id) VALUES
  ('USER', 1),
  ('USER', 2),
  ('USER', 3),
  ('ADMIN', 3);

INSERT INTO messages (user_id, topic, message, date_time) VALUES
  ('1', 'Mechanic', 'Most mechanics specialize in a particular field, such as auto mechanics, truck mechanic, bicycle mechanics, motorcycle mechanics, boiler mechanics, general mechanics, industrial maintenance mechanics (millwrights), air conditioning and refrigeration mechanics, bus mechanics, aircraft mechanics,[2] diesel mechanics and tank mechanics in the armed services. Auto mechanics, for example, have many trades within. Some may specialize in the electrical aspects, while others may specialize in the mechanical aspects. Other areas include: brakes and steering, suspension, automatic or manual transmission, engine repairs or diagnosing customer complaints. An automotive technician, on the other hand, has a wide variety of topics to learn. A mechanic is typically certified by a trade association or regional government power. Mechanics may be separated into two classes based on the type of machines they work on, heavyweight and lightweight. Heavyweight work is on larger machines or heavy equipment, such as tractors and trailers, while lightweight work is on smaller items, such as automotive engines.', '2019-01-30 10:00:00'),
  ('1', 'Mechanic', 'message number 2... next text... also text...', '2019-01-30 10:00:00'),
  ('1', 'Mechanic', 'message number 3... next text... also text...', '2019-01-30 10:00:00'),
  ('1', 'Culinary', 'message number 1', '2019-01-30 11:00:00'),
  ('2', 'Sport', 'message number 1', '2019-01-30 16:00:00'),
  ('2', 'Sport', 'message number 2', '2019-01-30 18:00:00');