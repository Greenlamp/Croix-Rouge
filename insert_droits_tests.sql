-- phpMyAdmin SQL Dump
-- version 3.4.10.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Jeu 18 Avril 2013 à 02:37
-- Version du serveur: 5.5.20
-- Version de PHP: 5.3.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `croixrouge`
--

--
-- Contenu de la table `appartenir`
--

INSERT INTO `appartenir` (`idUtilisateur`, `idGroupe`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(2, 2),
(3, 2),
(3, 3);

--
-- Contenu de la table `droits`
--

INSERT INTO `droits` (`idDroit`, `nom`, `description`) VALUES
(1, 'READ_DATA_MYSELF', 'Autorise la lecture de mes données'),
(2, 'WRITE_DATA_MYSELF', 'Autorise la modification de mes données'),
(3, 'READ_DATA_ALL', 'Autorise la lecture de toutes les données'),
(4, 'WRITE_DATA_ALL', 'Autorise la modification de toutes les données'),
(5, 'ASSIGN_RIGHT', 'Autorise l''assignation des droits à des groupes'),
(6, 'ASSIGN_GROUP', 'Autorise l''assignation des groupes à des utilisateurs'),
(7, 'CREATE_GROUP', 'Autorise la création de groupes'),
(8, 'CREATE_RIGHT', 'Autorise la création de droits'),
(9, 'CREATE_USER', 'Autorise la création d''utilisateurs'),
(10, 'EDIT_RIGHT', 'Autorise la modification des droits'),
(11, 'EDIT_GROUP', 'Autorise la modification des groupes'),
(12, 'EDIT_USER', 'Autorise la modification des utilisateurs'),
(13, 'CREATE_VOLUNTEER', 'Autorise la création de nouveaux volontaires'),
(14, 'SEE_ADMIN', 'Autorise l''acces au menu d''administration'),
(15, 'SEE_MANAGE_RIGHTS', 'Autorise l''acces au menu des droits'),
(16, 'SEE_MANAGE_GROUP', 'Autorise l''acces au menu des groupes'),
(17, 'SEE_MANAGE_USER', 'Autorise l''acces au menu des utilisateurs'),
(18, 'SEE_MANAGER_TEAM', 'Autorise l''acces au menu des équipes'),
(19, 'CREATE_TEAM', 'Autorise la création d''équipes'),
(20, 'EDIT_TEAM', 'Autorise la modification des équipes');

--
-- Contenu de la table `groupes`
--

INSERT INTO `groupes` (`idGroupe`, `nomGroupe`, `niveau`) VALUES
(1, 'Administrateur', 0),
(2, 'Utilisateur', 5),
(3, 'GestionDroits', 20),
(4, 'GestionGroupes', 21),
(5, 'GestionUtilisateurs', 22),
(6, 'GestionEquipes', 23);

--
-- Contenu de la table `possederdroit`
--

INSERT INTO `possederdroit` (`idGroupe`, `idDroit`) VALUES
(1, 3),
(1, 4),
(1, 13),
(1, 14),
(2, 1),
(2, 2),
(3, 14),
(3, 15),
(4, 14),
(4, 16),
(5, 14),
(5, 17),
(6, 18),
(6, 19),
(6, 20),
(20, 8),
(20, 10),
(21, 5),
(21, 7),
(21, 11),
(22, 6),
(22, 9),
(22, 12);

--
-- Contenu de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`idUtilisateur`, `login`, `password`, `dateDerniereConnexion`, `connected`, `prioritaire`) VALUES
(1, 'admin', 'admin', '2013-03-19 17:23:16', 0, 1),
(2, 'Greenlamp', 'azerty', '2013-03-21 00:00:00', 0, 0),
(3, 'droits', 'azerty', '2013-03-21 00:00:00', 0, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
