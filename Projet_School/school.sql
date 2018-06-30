-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 30 juin 2018 à 20:59
-- Version du serveur :  5.7.21-log
-- Version de PHP :  5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `school`
--

-- --------------------------------------------------------

--
-- Structure de la table `absence`
--

DROP TABLE IF EXISTS `absence`;
CREATE TABLE IF NOT EXISTS `absence` (
  `idAbsence` int(11) NOT NULL AUTO_INCREMENT,
  `idEtudiant` int(11) NOT NULL,
  `nomEtudiant` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `nomEnseignant` varchar(255) NOT NULL,
  `idSeance` int(11) NOT NULL,
  PRIMARY KEY (`idAbsence`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `absence`
--

INSERT INTO `absence` (`idAbsence`, `idEtudiant`, `nomEtudiant`, `date`, `nomEnseignant`, `idSeance`) VALUES
(1, 2, 'alexis', '28/06', 'Donnadieu', 1),
(2, 10, 'aze', '28/06', 'Donnadieu', 1);

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identifiant` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fonction` varchar(255) NOT NULL,
  `actif` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `identifiant` (`identifiant`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`id`, `identifiant`, `password`, `fonction`, `actif`) VALUES
(1, 'admin', 'admin', 'administrateur', 1),
(2, 'alex', 'alex', 'etudiant', 1),
(3, 'dd', 'dd', 'enseignant', 1),
(4, 'philippe', 'philot', 'secretaire', 1),
(10, 'aze', 'aze', 'etudiant', 1),
(20, 'fgh', 'fgh', 'etudiant', 1),
(21, 'qsd', 'qsd', 'enseignant', 1);

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

DROP TABLE IF EXISTS `cours`;
CREATE TABLE IF NOT EXISTS `cours` (
  `idCours` int(11) NOT NULL AUTO_INCREMENT,
  `idEnseignant` int(11) NOT NULL,
  `nomCours` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`idCours`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`idCours`, `idEnseignant`, `nomCours`, `description`) VALUES
(1, 3, 'Anglais', 'Cours d\'anglais pour personnes bilingues');

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

DROP TABLE IF EXISTS `enseignant`;
CREATE TABLE IF NOT EXISTS `enseignant` (
  `id` int(11) NOT NULL,
  `identifiant` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `idCours` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`id`, `identifiant`, `prenom`, `nom`, `mail`, `telephone`, `idCours`) VALUES
(3, 'Damien', 'Damien', 'Donnadieu', 'd.donnadieux@gmail.com', '0667894512', 1),
(21, 'qsd', 'gfdg', 'dfg', 'fdg', 'dfg', 0);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `id` int(11) NOT NULL,
  `identifiant` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `idGroupe` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`id`, `identifiant`, `prenom`, `nom`, `mail`, `telephone`, `idGroupe`) VALUES
(2, 'alex', 'boutemy', 'alexis', 'alexis@gmail.fr', '0669954852', 2),
(10, 'aze', 'aze', 'aze', 'azefsdgsfdg', '064545465654', 2),
(20, 'fgh', 'fgh', 'fgh', 'fgh', 'gh', 0);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

DROP TABLE IF EXISTS `groupe`;
CREATE TABLE IF NOT EXISTS `groupe` (
  `idGroupe` int(11) NOT NULL AUTO_INCREMENT,
  `mention` varchar(255) NOT NULL,
  `place` int(11) NOT NULL,
  `idCours` int(11) NOT NULL,
  PRIMARY KEY (`idGroupe`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`idGroupe`, `mention`, `place`, `idCours`) VALUES
(2, 'LEA', 30, 1);

-- --------------------------------------------------------

--
-- Structure de la table `seance`
--

DROP TABLE IF EXISTS `seance`;
CREATE TABLE IF NOT EXISTS `seance` (
  `idSeance` int(11) NOT NULL AUTO_INCREMENT,
  `idCours` int(11) NOT NULL,
  `nomCours` varchar(255) NOT NULL,
  `nomEnseignant` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `salle` int(60) NOT NULL,
  `idGroupe` int(11) NOT NULL,
  PRIMARY KEY (`idSeance`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `seance`
--

INSERT INTO `seance` (`idSeance`, `idCours`, `nomCours`, `nomEnseignant`, `date`, `salle`, `idGroupe`) VALUES
(1, 1, 'Anglais', 'Donnadieu', '28/06', 5, 2),
(2, 1, 'Anglais', 'Donnadieu', '28/06', 5, 2);

-- --------------------------------------------------------

--
-- Structure de la table `secretaire`
--

DROP TABLE IF EXISTS `secretaire`;
CREATE TABLE IF NOT EXISTS `secretaire` (
  `id` int(11) NOT NULL,
  `identifiant` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `secretaire`
--

INSERT INTO `secretaire` (`id`, `identifiant`, `prenom`, `nom`, `mail`, `telephone`) VALUES
(4, 'philippe', 'Philippe', 'Perrot', 'philot@gmail.com', '0645897542');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
