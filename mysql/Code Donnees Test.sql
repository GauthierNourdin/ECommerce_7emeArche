adresse﻿INSERT INTO `ecommerce_7emearche`.`article` (titre,resume,prix_ht) VALUES
("Archives du musée du cinéma de Los Angeles : Charlie Chaplin", "Recueil de photographies, de témoignages et de notes, autour du parcours de Charlie Chaplin en tant qu'acteur et réalisateur.", 1895),
("Musée des Arts et Métiers, Livre d'exposition : Les premiers pas du cinéma", "Livre de l'exposition temporaire début 2015 du musée des Arts et Métiers. Sujet centré sur le matériel et les techniques de tournage et de projection au début du cinéma à 1920", 1421),
("Bollywood, simple copie de l'Occident ? Avec préface de l'auteur", "Analyse complète du cinéma bollywoodien contemporain et comparaison avec le cinéma américain et européen.", 2179),
("Archives publiques, 1914-1918 : reportages de guerre", "Recueil d'extraits de pellicules tournés sur les théâtres d'opérations de la première guerre modiale avec notes associées et commentaires.", 2843),
("Dans les coulises du tournage de Mission Impossible, 2e édition", "Recueil de photographies et de témoignages autour des différents tournages de la série de films Mission Impossible.", 3222),
("Georges Lucas et Star Wars, derrière la saga, édition de luxe", "Bibliographie illustrée de Georges Lucas avec une interview autour de son travail autour de Star Wars.", 3791),
("1944-2019 : soixante-quinze ans de films de super-héros, 1ère édition", "Analyse de l'évolution des films de super-héros depuis 1944. Résumés et illustrations d'une cinquante de films représentatifs.", 3032),
("Films de guerre : raconter l'histoire des soldats, 4e édition", "Analyse de films de guerre de différentes époques et pays avec un regard particulier sur les personnages de soldat.", 2369);
 
INSERT INTO `ecommerce_7emearche`.`adresse` (numero_rue,nom_rue,code_postal,ville,pays,complement) VALUES
("24 bis", "Rue d'Indochine", "35200", "Rennes", "France", ""),
("18", "Avenue des Frères Lumière", "69003", "Lyon", "France", ""),
("374", "Rue du Maréchal Foch", "59160", "Lille", "France", "3e étage"),
("14", "Stirling Street", "EH10", "Edinburgh", "Ecosse", ""),
("4 ter", "Allée du 7ème Art", "33400", "Talence", "France", "Appartement 326"),
("117", "Avenue de Stalingrad", "75013", "Paris", "France", ""),
("280", "Grand place", "1050", "Bruxelles", "Belgique", ""),
("68", "Avenue du Général de Gaulle", "67000", "Strasbourg", "France", "");

INSERT INTO `ecommerce_7emearche`.`editeur` (nom,adresse_id) VALUES
("Editions 7ème Art", 1),
("Editions Philosophie et Arts", 2),
("Groupe Argonaute", 2),
("Historicae", 3);

INSERT INTO `ecommerce_7emearche`.`livre` (isbn13,titre_livre,format,lien_image,nb_pages,date_depot_legal,article_id,editeur_id) VALUES
("978-2-15857-713-5","Charlie Chaplin",".pdf","Charlie_Chaplin_Ed7emeArt.png",98,DATE("2014-10-18"),1,1),
("978-2-15857-804-2","Les premiers pas du cinéma",".pdf","Les_premiers_pas_du_cinema_Ed7emeArt.png",74,DATE("2015-01-24"),2,1),
("978-2-17172-074-0","Bollywood, simple copie de l'Occident ?",".pdf","Bollywood_simple_copie_de_l_Occident_EdPhiloEtArt.png",388,DATE("2019-06-04"),3,2),
("978-2-32724-014-8","1914-1918 : reportages de guerre",".pdf","1914_1918_reportages_de_guerre_Historicae.png",254,DATE("2008-11-11"),4,4),
("978-2-15857-954-7","Dans les coulises du tournage de Mission Impossible","Grand carré à couverture rigide","Dans_les_coulises_du_tournage_de_Mission Impossible_Ed7emeArt.png",186,DATE("2020-08-02"),5,1),
("978-2-27508-154-7","Georges Lucas et Star Wars, derrière la saga","A4 à couverture rigide","Georges_Lucas_et_Star_Wars_derriere_la_saga_GpArgo.png",238,DATE("2016-11-13"),6,3),
("978-2-27508-207-3","1944-2019 : soixante-quinze ans de films de super-héros","A5 à couverture rigide","1944_2019_soixante_quinze_ans_de_films_de_super_heros_GpArgo.png",314,DATE("2020-03-17"),7,3),
("978-2-32724-089-9","Films de guerre : raconter l'histoire des soldats","Poche à dos carré collé","Films_de_guerre_raconter_l_histoire_des_soldats_Historicae.png",360,DATE("2017-06-15"),8,4);

INSERT INTO `ecommerce_7emearche`.`personne` (nom,prenom) VALUES
("Picard","Bernard"),
("Shoeffer","Edouard"),
("Kurkov","Alexey"),
("Ryan","Lyrol"),
("Labbe","Antoine"),
("Sancta","Maria"),
("Finan","Didier"),
("Hillfort","William E."),
("Lamour","Mathilde"),
("Renard","Marguerite");

INSERT INTO `ecommerce_7emearche`.`auteur` (personne_id) VALUES
(1),
(2),
(3),
(4),
(5),
(6);

INSERT INTO `ecommerce_7emearche`.`client` (email,mot_de_passe,numero_carte,date_validite,cvc,adresse_facturation_id,adresse_livraison_id,personne_id) VALUES
("didier.finan@gmail.com","SpyFilmsAreTheBest","1565-4961-1787-1857","08/22","975",4,4,7),
("william.hillfort@protonmail.com","AlainDelon","1765-8273-2745-5818","04/23","170",1,5,8),
("mathile@lamour.com","LeCinemaCestLaVie","","","",6,6,9),
("marg.renard@gmail.com","ViveLe7eArt","4354-1961-9711-0489","01/21","178",8,7,10);

INSERT INTO `ecommerce_7emearche`.`commande` (numero,status,date,client_id) VALUES
("210422173401-00000001","Expédiée",'2021-04-22 17:34:01',1),
("210502071157-00000001","Expédiée",'2021-05-02 07:11:57',1),
("210505140247-00000002","Expédiée",'2021-05-05 14:02:47',2),
("210509222724-00000003","Payée",'2021-05-09 22:27:24',3);

INSERT INTO `ecommerce_7emearche`.`ligne_commande` (quantite_commandee,prix_ht,prix_ttc,article_id,commande_numero) VALUES
(1,1895,1999,1,"210422173401-00000001"),
(2,3222,3399,5,"210422173401-00000001"),
(3,3791,3999,6,"210422173401-00000001"),
(1,1421,1499,2,"210502071157-00000001"),
(1,2179,2299,3,"210502071157-00000001"),
(1,3222,3399,5,"210505140247-00000002"),
(2,3032,3199,7,"210505140247-00000002"),
(1,3222,3399,5,"210509222724-00000003");

INSERT INTO `ecommerce_7emearche`.`facture` (numero,status,commande_numero) VALUES
("210422173401-00000001-01","Réglée","210422173401-00000001"),
("210502071157-00000001-01","Réglée","210502071157-00000001"),
("210505140247-00000002-01","Réglée","210505140247-00000002"),
("210509222724-00000003-01","Réglée","210509222724-00000003");

INSERT INTO `ecommerce_7emearche`.`consultation` (date,article_id,client_id) VALUES
('2021-04-22 17:24:05',1,1),
('2021-04-22 17:26:41',3,1),
('2021-04-22 17:29:04',6,1),
('2021-04-22 17:32:17',3,1),
('2021-05-02 06:55:05',2,1),
('2021-05-02 07:09:37',8,1),
('2021-05-05 13:49:37',5,2),
('2021-05-05 13:52:07',4,2),
('2021-05-05 13:56:50',7,2),
('2021-05-09 22:07:34',5,3),
('2021-05-10 05:18:56',5,NULL),
('2021-05-10 12:24:14',6,NULL);

INSERT INTO `ecommerce_7emearche`.`livre_numerique` (espace,unite,livre_isbn13) VALUES
(275.4,"Mo","978-2-15857-713-5"),
(115.8,"Mo","978-2-15857-804-2"),
(44.2,"Mo","978-2-17172-074-0"),
(872.5,"Mo","978-2-32724-014-8");

INSERT INTO `ecommerce_7emearche`.`livre_imprime` (quantite_stock,date_fin_tirage,date_reimpression,poids,unite_poids,longueur,largeur,epaisseur,unite_longueur,livre_isbn13) VALUES
(4,DATE("2020-08-05"),DATE("2020-12-28"),359.4,"g",21.0,21.0,1.63,"cm","978-2-15857-954-7"),
(1,DATE("2016-11-28"),DATE("2018-04-30"),645.5,"g",29.7,21.0,2.07,"cm","978-2-27508-154-7"),
(3,DATE("2020-04-17"),NULL,422.7,"g",21.0,14.8,2.72,"cm","978-2-27508-207-3"),
(5,DATE("2017-06-18"),DATE("2019-02-14"),290.8,"g",17.0,11.0,3.11,"cm","978-2-32724-089-9");

INSERT INTO `ecommerce_7emearche`.`genre` (nom) VALUES
("Américain"),
("Biographie"),
("Exposition"),
("Historique"),
("Asiatique"),
("Analyse"),
("Documentaire"),
("");

INSERT INTO `ecommerce_7emearche`.`auteur_livre` (auteur_id,livre_isbn13) VALUES
(1,"978-2-15857-713-5"),
(1,"978-2-15857-954-7"),
(1,"978-2-27508-154-7"),
(2,"978-2-15857-804-2"),
(2,"978-2-27508-154-7"),
(3,"978-2-17172-074-0"),
(3,"978-2-32724-014-8"),
(4,"978-2-27508-207-3"),
(5,"978-2-32724-089-9"),
(6,"978-2-15857-954-7"),
(6,"978-2-32724-089-9");

INSERT INTO `ecommerce_7emearche`.`livre_genre` (genre_id,livre_isbn13) VALUES
(1,"978-2-15857-713-5"),
(2,"978-2-15857-713-5"),
(3,"978-2-15857-804-2"),
(4,"978-2-15857-804-2"),
(5,"978-2-17172-074-0"),
(6,"978-2-17172-074-0"),
(4,"978-2-32724-014-8"),
(7,"978-2-32724-014-8"),
(1,"978-2-15857-954-7"),
(7,"978-2-15857-954-7"),
(1,"978-2-27508-154-7"),
(2,"978-2-27508-154-7"),
(7,"978-2-27508-154-7"),
(6,"978-2-27508-207-3"),
(4,"978-2-32724-089-9"),
(7,"978-2-32724-089-9");