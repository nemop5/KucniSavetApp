/*lozinka je ista kao i korisnicko ime*/
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');
              
INSERT INTO zgrada (id, adresa, predsednik, broj_stanova, broj_stanara) VALUES (1, 'Gogoljeva 1', 'Petar Petrović', 4, 12);
INSERT INTO zgrada (id, adresa, predsednik, broj_stanova, broj_stanara) VALUES (2, 'Balzakova 3', 'Marija Mitić', 5, 10);
INSERT INTO zgrada (id, adresa, predsednik, broj_stanova, broj_stanara) VALUES (3, 'Bul. Cara Lazara 5', 'Marija Mitić', 3, 10);

INSERT INTO poruka (id, naslov, tip, potreban_procenat, prihvacen, opis, zgrada_id) VALUES (1, 'Nestanak vode',  'obaveštenje', 0, false, '15.5. od 8h', 1);
INSERT INTO poruka (id, naslov, tip, potreban_procenat, prihvacen, opis, zgrada_id) VALUES (2, 'Zamena brave',  'obaveštenje', 0, false, '16.5. dolazi bravar', 2);
INSERT INTO poruka (id, naslov, tip, potreban_procenat, prihvacen, opis, zgrada_id) VALUES (3, 'Obnova fasade',  'predlog', 100, true, 'Da li ste za?', 3);
INSERT INTO poruka (id, naslov, tip, potreban_procenat, prihvacen, opis, zgrada_id) VALUES (4, 'Obnova fasade',  'predlog', 80, false, 'Da li ste za?', 2);
INSERT INTO poruka (id, naslov, tip, potreban_procenat, prihvacen, opis, zgrada_id) VALUES (5, 'Finansije',  'obaveštenje', 0, false, 'stanje na nuli', 1);


INSERT INTO glas (id, predlog_podrzan, komentar, poruka_id) VALUES (1, 'ZA', '', 3);
INSERT INTO glas (id, predlog_podrzan, komentar, poruka_id) VALUES (2, 'ZA', '', 3);
INSERT INTO glas (id, predlog_podrzan, komentar, poruka_id) VALUES (3, 'ZA', '', 4);
INSERT INTO glas (id, predlog_podrzan, komentar, poruka_id) VALUES (4, 'ZA', '', 4);
INSERT INTO glas (id, predlog_podrzan, komentar, poruka_id) VALUES (5, 'ZA', '', 4);

