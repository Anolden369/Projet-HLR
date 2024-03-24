-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 23 mars 2024 à 11:06
-- Version du serveur : 8.0.31
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bddhelplerebours`
--

-- --------------------------------------------------------

--
-- Structure de la table `competence`
--

DROP TABLE IF EXISTS `competence`;
CREATE TABLE IF NOT EXISTS `competence` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_matiere` int NOT NULL,
  `id_user` int NOT NULL,
  `sous_matiere` longtext NOT NULL,
  `statut` int NOT NULL,
  `dateCreation` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user_competence` (`id_user`),
  KEY `id_matiere_competence` (`id_matiere`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `competence`
--

INSERT INTO `competence` (`id`, `id_matiere`, `id_user`, `sous_matiere`, `statut`, `dateCreation`) VALUES
(1, 1, 3, '#verbesirréguliers#date#nombres', 1, '0000-00-00'),
(2, 1, 1, '#gérondif#présent#date#nombres', 2, '0000-00-00'),
(3, 1, 5, '#nombres', 3, '0000-00-00'),
(4, 2, 1, '#contrats#régulation#organisation', 1, '0000-00-00'),
(5, 2, 5, '#structurejuridique#droit', 1, '0000-00-00'),
(6, 2, 2, '#intégration#rôleétat#environnement#facteurséconomiques', 2, '0000-00-00'),
(7, 2, 7, '#facteurséconomiques#structurejuridique#droit', 3, '0000-00-00'),
(8, 2, 6, '#contrats#régulation#organisation#intégration#rôleétat#environnement#facteurséconomiques#structurejuridique#droit', 1, '0000-00-00'),
(9, 2, 3, '#organisation#intégration', 3, '0000-00-00'),
(10, 2, 4, '#droit', 1, '0000-00-00'),
(11, 3, 7, '#orthographe#conjugaison', 1, '0000-00-00'),
(12, 3, 4, '#présent#futursimple#pronompersonnel#conjonctioncoordination#auxiliareavoir#indicatif', 3, '0000-00-00'),
(13, 3, 6, '#participepassé#présent#futursimple#pronompersonnel', 1, '0000-00-00'),
(14, 3, 3, '#auxiliareavoir#indicatif', 1, '0000-00-00'),
(15, 4, 1, '#loidepoisson#probabilités#statistiques', 1, '0000-00-00'),
(16, 4, 5, '#équations#factorisation#nombresrelatifs#intégrale#dérivée', 3, '0000-00-00'),
(17, 4, 7, '#tableaudevariation#matrice', 1, '0000-00-00'),
(18, 4, 3, '#équations#factorisation#nombresrelatifs#intégrale#dérivée#tableaudevariation#matrice#développement#loidepoisson#probabilités#statistiques', 3, '0000-00-00'),
(19, 4, 4, '#nombresrelatifs#intégrale#dérivée#tableaudevariation', 1, '0000-00-00'),
(20, 4, 2, '#factorisation', 1, '0000-00-00'),
(21, 5, 1, '#java#sql#python#php#javascript#modelosi#tcpip', 1, '0000-00-00'),
(22, 5, 5, '#windows#linux#dhcp', 1, '0000-00-00'),
(23, 5, 2, '#poo#boucles#conditions#json#api', 1, '0000-00-00'),
(24, 5, 7, '#java#sql', 3, '0000-00-00'),
(25, 5, 6, '#linux#dhcp#dns#voip#cisco', 2, '0000-00-00'),
(26, 5, 3, '#linux#dhcp#dns#voip#cisco#poo#boucles#conditions#json#api', 3, '0000-00-00'),
(27, 5, 4, '#java#sql#python#php#javascript#modelosi#tcpip#windows#linux#dhcp#dns#voip#cisco', 1, '0000-00-00'),
(28, 6, 7, '#puissanceetenjeuxmondiaux', 1, '0000-00-00'),
(29, 6, 6, '#crise1929#rÃ©gimestotalitaires#secondeguerremondiale#tiersmonde#france', 1, '0000-00-00'),
(30, 6, 3, '#secondeguerremondiale#tiersmonde#france#constructioneuropÃ©enne', 1, '0000-00-00'),
(31, 6, 4, '#tiersmonde#france', 3, '0000-00-00'),
(76, 4, 11, '#équations#factorisation#nombresrelatifs#intégrale#dérivée#tableaudevariation#matrice#développement#loidepoisson#probabilités#statistiques', 1, '2024-02-27'),
(77, 3, 11, '#orthographe#conjugaison#participepassé#présent#futursimple,#pronompersonnel,#conjonctioncoordination#auxiliareavoir#indicatif', 1, '2023-11-27'),
(84, 5, 11, '#java#sql#python#php#javascript#modelosi#tcpip#windows#linux#dhcp#dns#voip#cisco#poo#boucles#conditions#json#api', 1, '2024-03-01'),
(86, 6, 11, '#crise1929#régimestotalitaires#secondeguerremondiale#tiersmonde#france#constructioneuropéenne#puissanceetenjeuxmondiaux', 1, '2024-02-29'),
(87, 1, 11, '#verbesirréguliers#gérondif#présent#date#nombres', 1, '2024-03-01'),
(88, 2, 11, '#contrats#régulation#organisation#intégration#rôleétat#environnement#facteurséconomiques#structurejuridique#droit', 1, '2024-03-01');

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

DROP TABLE IF EXISTS `demande`;
CREATE TABLE IF NOT EXISTS `demande` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_updated` date NOT NULL,
  `date_fin_demande` date NOT NULL,
  `sous_matiere` longtext NOT NULL,
  `id_user` int NOT NULL,
  `id_matiere` int NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  KEY `id_matiere` (`id_matiere`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `demande`
--

INSERT INTO `demande` (`id`, `date_updated`, `date_fin_demande`, `sous_matiere`, `id_user`, `id_matiere`, `status`) VALUES
(1, '2023-09-25', '2024-01-12', '#tableaudevariation#matrice#développement', 3, 4, 1),
(2, '2023-09-25', '2024-01-12', '#nombresrelatifs#intégrale#loidepoisson#probabilités#statistiques', 5, 4, 1),
(3, '2023-10-06', '2024-01-12', '#java#sql#python#php', 6, 5, 1),
(4, '2023-12-20', '2024-01-12', '#conjonctioncoordination#auxiliareavoir', 6, 3, 3),
(5, '2023-12-20', '2024-01-12', '#nombres', 6, 1, 3),
(6, '2023-12-20', '2024-01-12', '#intégration#rôleétat#environnement', 6, 2, 3),
(7, '2023-10-22', '2024-01-12', '#date#nombres', 1, 1, 1),
(8, '2023-12-20', '2024-01-12', '#conjugaison#participepassé#présent', 1, 3, 3),
(9, '2023-10-15', '2024-01-12', '#windows#linux', 7, 5, 1),
(10, '2023-10-29', '2024-01-12', '#dhcp#dns#voip#cisco#poo#boucles#conditions#json#api', 7, 5, 1),
(11, '2023-10-22', '2023-10-31', '#boucles#conditions', 9, 5, 1),
(12, '2023-11-12', '2023-11-17', '#json#api#sql', 8, 5, 1),
(13, '2023-11-19', '2023-11-22', '#dhcp#dns#voip', 5, 5, 1),
(14, '2024-03-22', '2024-01-12', '#sql#python', 5, 5, 3),
(15, '2023-10-29', '2023-10-31', '#probabilités#statistiques', 7, 4, 1),
(22, '2024-03-22', '2024-01-12', '#verbesirréguliers#gérondif#date#nombres', 11, 1, 3),
(23, '2023-11-25', '2024-01-12', '#contrats#régulation#organisation', 11, 2, 1),
(24, '2023-11-25', '2024-01-12', '#équations#dérivée#probabilités', 11, 4, 3),
(25, '2023-11-25', '2023-11-25', '#crise1929#france#puissanceetenjeuxmondiaux', 5, 6, 1),
(26, '2023-11-29', '2024-01-12', '#java#sql#python#php#tcpip#windows', 11, 5, 1),
(27, '2023-11-25', '2023-12-21', '#participepassé#futursimple,#pronompersonnel,#auxiliareavoir', 11, 3, 2),
(28, '2023-11-25', '2023-12-21', '#nombres', 11, 1, 1),
(29, '2023-11-25', '2024-01-12', '#verbesirréguliers', 5, 1, 1),
(30, '2023-11-29', '2023-12-21', '#régulation#organisation#intégration', 11, 2, 3),
(31, '2023-11-29', '2024-01-12', '#gérondif', 11, 1, 1),
(32, '2023-11-29', '2023-12-21', '#verbesirréguliers', 11, 1, 2),
(33, '2023-11-29', '2023-12-21', '#java#sql#python', 11, 5, 1),
(34, '2023-11-29', '2023-12-21', '#rôleétat', 11, 2, 2),
(35, '2023-11-29', '2023-12-21', '#intégrale', 11, 4, 1),
(36, '2023-12-02', '2023-12-21', '#verbesirréguliers', 11, 1, 2),
(37, '2023-12-02', '2023-12-07', '#verbesirréguliers#présent', 5, 1, 1),
(38, '2023-12-08', '2023-12-21', '#équations#factorisation#nombresrelatifs', 11, 4, 3),
(39, '2023-12-14', '2023-12-21', '#java', 11, 5, 1),
(40, '2024-02-15', '2024-02-25', '#matrice', 11, 4, 1),
(41, '2024-02-15', '2024-02-22', '#participepassé', 11, 3, 1),
(42, '2024-02-18', '2024-02-20', '#équations#factorisation#nombresrelatifs', 3, 4, 1),
(43, '2024-02-18', '2024-02-22', '#équations#factorisation#nombresrelatifs#dérivée', 1, 4, 1),
(44, '2024-02-18', '2024-02-23', '#matrice', 2, 4, 1),
(45, '2024-03-22', '2024-03-29', '#verbesirréguliers#gérondif', 4, 1, 1),
(46, '2024-03-22', '2024-03-29', '#java#sql#python', 2, 5, 3);

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

DROP TABLE IF EXISTS `matiere`;
CREATE TABLE IF NOT EXISTS `matiere` (
  `id` int NOT NULL AUTO_INCREMENT,
  `designation` varchar(200) NOT NULL,
  `sous_matiere` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `matiere`
--

INSERT INTO `matiere` (`id`, `designation`, `sous_matiere`) VALUES
(1, 'Anglais', '#verbesirréguliers#gérondif#présent#date#nombres'),
(2, 'CEJM', '#contrats#régulation#organisation#intégration#rôleétat#environnement#facteurséconomiques#structurejuridique#droit'),
(3, 'Français', '#orthographe#conjugaison#participepassé#présent#futursimple,#pronompersonnel,#conjonctioncoordination#auxiliareavoir#indicatif'),
(4, 'Mathématiques', '#équations#factorisation#nombresrelatifs#intégrale#dérivée#tableaudevariation#matrice#développement#loidepoisson#probabilités#statistiques'),
(5, 'Informatique', '#java#sql#python#php#javascript#modelosi#tcpip#windows#linux#dhcp#dns#voip#cisco#poo#boucles#conditions#json#api'),
(6, 'Histoire', '#crise1929#régimestotalitaires#secondeguerremondiale#tiersmonde#france#constructioneuropéenne#puissanceetenjeuxmondiaux');

-- --------------------------------------------------------

--
-- Structure de la table `niveau`
--

DROP TABLE IF EXISTS `niveau`;
CREATE TABLE IF NOT EXISTS `niveau` (
  `id` int NOT NULL AUTO_INCREMENT,
  `designation` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `niveau`
--

INSERT INTO `niveau` (`id`, `designation`) VALUES
(1, 'Master 2'),
(2, 'Master 1'),
(3, 'Licence'),
(4, 'BTS 2'),
(5, 'BTS 1'),
(6, 'Terminale'),
(7, '1ère'),
(8, '2nde'),
(9, '3ème'),
(10, '4ème'),
(11, '5ème'),
(12, '6ème');

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code_salle` varchar(10) NOT NULL,
  `etage` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=853 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`id`, `code_salle`, `etage`) VALUES
(101, 'Salle 101', 1),
(102, 'Salle 102', 1),
(201, 'Salle 201', 2),
(202, 'Salle 202', 2),
(301, 'Salle 301', 3),
(302, 'Salle 302', 3),
(303, 'Salle 303', 3),
(304, 'Salle 304', 3),
(305, 'Salle 305', 3),
(306, 'Salle 306', 3),
(307, 'Salle 307', 3),
(308, 'Salle 308', 3);

--
-- Déclencheurs `salle`
--
DROP TRIGGER IF EXISTS `update_codeSalle_on_insert`;
DELIMITER $$
CREATE TRIGGER `update_codeSalle_on_insert` BEFORE INSERT ON `salle` FOR EACH ROW BEGIN
    DECLARE num_salle_str VARCHAR(255);
    DECLARE etage VARCHAR(255);

    -- Récupération du numéro de salle de la nouvelle entrée
    SET num_salle_str = NEW.id;

    -- Construction de la valeur pour la colonne étage (Salle + numéro de salle)
    SET etage = CONCAT('Salle ', num_salle_str);

	SET NEW.code_salle = etage;

END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `update_etage_on_insert`;
DELIMITER $$
CREATE TRIGGER `update_etage_on_insert` BEFORE INSERT ON `salle` FOR EACH ROW BEGIN
    DECLARE num_salle_str VARCHAR(255);
    DECLARE etage INT;

    -- Récupération du numéro de salle de la nouvelle entrée
    SET num_salle_str = NEW.id;

    -- Extraction du premier chiffre (qui représente l'étage)
    SET etage = CAST(SUBSTRING(num_salle_str, 1, 1) AS UNSIGNED);

    -- Mise à jour de la colonne étage avec la valeur extraite
    SET NEW.etage = etage;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `soutien`
--

DROP TABLE IF EXISTS `soutien`;
CREATE TABLE IF NOT EXISTS `soutien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_demande` int NOT NULL,
  `id_competence` int NOT NULL,
  `id_salle` int DEFAULT NULL,
  `date_du_soutien` date NOT NULL,
  `date_updated` date NOT NULL,
  `description` longtext NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_competence` (`id_competence`),
  KEY `id_demande` (`id_demande`),
  KEY `id_salle_soutien` (`id_salle`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `soutien`
--

INSERT INTO `soutien` (`id`, `id_demande`, `id_competence`, `id_salle`, `date_du_soutien`, `date_updated`, `description`, `status`) VALUES
(1, 14, 5, 101, '2024-03-22', '2024-03-22', '', 3),
(2, 14, 5, 101, '2024-03-22', '2024-03-22', '', 3),
(3, 8, 77, 202, '2024-01-06', '2023-12-20', 'Apporter les cours de français', 3),
(4, 5, 87, 202, '2023-12-18', '2023-12-20', '', 3),
(5, 4, 77, 301, '2023-12-15', '2023-12-20', '', 3),
(6, 6, 88, 301, '2023-12-01', '2023-12-20', '', 3),
(7, 46, 5, 202, '2024-03-22', '2024-03-22', 'Bonjour Erwan, penses a prendre ton pc.', 3);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `role` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `id_niveau` int DEFAULT NULL,
  `sexe` int NOT NULL,
  `telephone` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_niveau` (`id_niveau`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `password`, `role`, `id_niveau`, `sexe`, `telephone`) VALUES
(1, 'Castaing', 'Sabine', 'sabine.castaing@lerebours.org', 'sabcas', 'Etudiant', 7, 2, '0661010101'),
(2, 'Thouri', 'Erwan', 'erwan.thouri@lerebours.org', 'erwtho', 'Etudiant', 8, 1, '0661020202'),
(3, 'Bloch', 'Nicolas', 'nicolas.bloch@lerebours.org', 'nicblo', 'Etudiant', 6, 1, '0661030303'),
(4, 'Chittarath', 'Christophe', 'chittarath.christophe@lerebours.org', 'chrchi', 'Etudiant', 3, 1, '0661040404'),
(5, 'Buffeteau', 'Jacques', 'jacques.buffeteau@lerebours.org', 'jacbuf', 'Etudiant', 8, 1, '0661050505'),
(6, 'Pamart', 'Marie-Sophie', 'pamart.marie.sophie@lerebours.org', 'pammar', 'Etudiant', 8, 2, '0661060606'),
(7, 'Sordet', 'Evelyne', 'evelyne.sordet@lerebours.org', 'evesor', 'Etudiant', 10, 2, '0661070707'),
(8, 'Cornia', 'Alberto', 'alberto.cornia@lerebours.org', 'albcor', 'Etudiant', 11, 1, '0661080808'),
(9, 'Ioualitene', 'Fatah', 'loualitene.fatah@lerebours.org', 'fatlou', 'Etudiant', 9, 1, '0661090909'),
(10, 'Anolden', 'Siva', 'ano', '1234', 'Admin', 1, 1, '0661090909'),
(11, 'Thien', 'Lam', 'thien', '1234', 'Etudiant', 4, 1, '0661090909');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `competence`
--
ALTER TABLE `competence`
  ADD CONSTRAINT `id_matiere_competence` FOREIGN KEY (`id_matiere`) REFERENCES `matiere` (`id`),
  ADD CONSTRAINT `id_user_competence` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `demande`
--
ALTER TABLE `demande`
  ADD CONSTRAINT `id_matiere` FOREIGN KEY (`id_matiere`) REFERENCES `matiere` (`id`),
  ADD CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `soutien`
--
ALTER TABLE `soutien`
  ADD CONSTRAINT `id_competence` FOREIGN KEY (`id_competence`) REFERENCES `competence` (`id`),
  ADD CONSTRAINT `id_demande` FOREIGN KEY (`id_demande`) REFERENCES `demande` (`id`),
  ADD CONSTRAINT `id_salle_soutien` FOREIGN KEY (`id_salle`) REFERENCES `salle` (`id`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`id_niveau`) REFERENCES `niveau` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
