-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Feb 20, 2020 alle 15:04
-- Versione del server: 10.1.37-MariaDB
-- Versione PHP: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ospitesocial`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `abitazioni`
--

CREATE TABLE `abitazioni` (
  `id` int(11) NOT NULL,
  `proprietario` varchar(11) NOT NULL,
  `indirizzo` varchar(40) NOT NULL,
  `citta` varchar(50) NOT NULL,
  `distanzaCentro` float NOT NULL,
  `distanzaStazione` float NOT NULL,
  `tempoTermineModifiche` int(11) NOT NULL,
  `dataInizioDisponibilita` date NOT NULL,
  `dataFineDisponibilita` date NOT NULL,
  `tariffaGiornaliera` int(11) NOT NULL DEFAULT '5'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `abitazioni`
--

INSERT INTO `abitazioni` (`id`, `proprietario`, `indirizzo`, `citta`, `distanzaCentro`, `distanzaStazione`, `tempoTermineModifiche`, `dataInizioDisponibilita`, `dataFineDisponibilita`, `tariffaGiornaliera`) VALUES
(128, '1', '12', '1', 1, 11, 1, '2020-02-12', '2020-02-17', 6),
(129, '1', '121', '1', 1, 11, 1, '2020-02-20', '2020-02-24', 6),
(130, '2', '2', '2', 2, 2, 2, '2020-02-02', '2020-02-09', 6),
(132, '88', '8', '8', 1, 1, 1, '2015-03-31', '2015-03-31', 6),
(137, '88', '82', '8', 1, 1, 1, '2015-03-31', '2015-03-31', 6),
(139, '88', '83', '8', 1, 1, 1, '2015-03-31', '2015-03-31', 6),
(140, '88', '85', '8', 1, 1, 1, '2015-03-31', '2015-03-31', 6),
(145, '1', '11', '1', 1, 1, 1, '2020-02-01', '2020-02-07', 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `ospiti`
--

CREATE TABLE `ospiti` (
  `codiceprenotazione` int(11) NOT NULL,
  `cf` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `prenotazioni`
--

CREATE TABLE `prenotazioni` (
  `id` int(11) NOT NULL,
  `codiceAbitazione` int(11) NOT NULL,
  `ospitante` varchar(11) NOT NULL,
  `dataInizio` date NOT NULL,
  `dataFine` date NOT NULL,
  `checkIn` date DEFAULT NULL,
  `checkOut` date DEFAULT NULL,
  `stato` varchar(20) NOT NULL DEFAULT 'Da confermare',
  `motivazioni` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `stanze`
--

CREATE TABLE `stanze` (
  `id` int(11) NOT NULL,
  `tipologiaStanza` varchar(20) NOT NULL,
  `tipologiaPostoLetto` varchar(20) NOT NULL,
  `numeroPostiLetto` int(11) NOT NULL,
  `codiceAbitazione` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `stanze`
--

INSERT INTO `stanze` (`id`, `tipologiaStanza`, `tipologiaPostoLetto`, `numeroPostiLetto`, `codiceAbitazione`) VALUES
(48, 'Letto singolo', 'Stanza comune', 1, 128),
(49, 'Letto singolo', 'Stanza comune', 2, 128),
(50, 'Letto singolo', 'Stanza comune', 2, 129),
(51, 'Letto singolo', 'Stanza comune', 1, 129),
(52, 'Letto singolo', 'Stanza comune', 1, 129),
(53, 'Letto singolo', 'Stanza comune', 1, 129),
(54, 'Letto singolo', 'Stanza comune', 1, 129),
(55, 'Letto singolo', 'Stanza comune', 1, 129),
(56, 'Letto singolo', 'Stanza comune', 1, 129),
(57, 'Letto singolo', 'Stanza comune', 1, 129),
(58, 'Letto singolo', 'Stanza comune', 2, 130),
(61, '1', '1', 2, 140),
(62, '1', '1', 1, 140),
(63, '1', '1', 1, 140),
(64, '1', '1', 1, 140),
(65, '1', '1', 1, 140),
(66, '1', '1', 1, 140),
(67, 'Letto singolo', 'Stanza comune', 1, 145),
(69, '1', '1', 1, 140),
(70, '1', '1', 1, 140),
(128, 'Letto singolo', 'Stanza comune', 2, 130);

-- --------------------------------------------------------

--
-- Struttura della tabella `stanzeoccupate`
--

CREATE TABLE `stanzeoccupate` (
  `codicePrenotazione` int(11) NOT NULL,
  `codiceStanza` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `cf` varchar(11) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(20) NOT NULL,
  `eta` int(11) NOT NULL,
  `sesso` char(1) NOT NULL,
  `cittaResidenza` varchar(20) NOT NULL,
  `cellulare` varchar(10) NOT NULL,
  `password` varchar(50) NOT NULL,
  `credito` int(11) NOT NULL DEFAULT '20'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`cf`, `nome`, `cognome`, `eta`, `sesso`, `cittaResidenza`, `cellulare`, `password`, `credito`) VALUES
('1', '3', '1', 19, 'M', '1', '1234567890', '1', 547),
('11', '11', '11', 11, 'M', '11', '11', '11', 20),
('2', '2', '2', 2, 'M', '2', '2', '2', 20),
('61', '61', '6', 6, 'M', '6', '6', '6', 20),
('88', '88', '88', 88, 'F', '88', '88', '88', -4),
('9', '9', '9', 9, 'F', '9', '9', '9', 20),
('DBLKS', 'Kristian', 'Di blasi', 23, 'M', 'Floridia', '12', 'KRI', 5);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `abitazioni`
--
ALTER TABLE `abitazioni`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `indirizzo` (`indirizzo`),
  ADD KEY `proprietario` (`proprietario`) USING BTREE;

--
-- Indici per le tabelle `ospiti`
--
ALTER TABLE `ospiti`
  ADD UNIQUE KEY `codiceprenotazione` (`codiceprenotazione`,`cf`),
  ADD KEY `prenotato` (`cf`);

--
-- Indici per le tabelle `prenotazioni`
--
ALTER TABLE `prenotazioni`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `stanze`
--
ALTER TABLE `stanze`
  ADD PRIMARY KEY (`id`),
  ADD KEY `vincoloStanza` (`codiceAbitazione`);

--
-- Indici per le tabelle `stanzeoccupate`
--
ALTER TABLE `stanzeoccupate`
  ADD UNIQUE KEY `codicePrenotazione` (`codicePrenotazione`,`codiceStanza`),
  ADD KEY `occupata` (`codiceStanza`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`cf`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `abitazioni`
--
ALTER TABLE `abitazioni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=188;

--
-- AUTO_INCREMENT per la tabella `prenotazioni`
--
ALTER TABLE `prenotazioni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT per la tabella `stanze`
--
ALTER TABLE `stanze`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `abitazioni`
--
ALTER TABLE `abitazioni`
  ADD CONSTRAINT `utente` FOREIGN KEY (`proprietario`) REFERENCES `utenti` (`cf`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `ospiti`
--
ALTER TABLE `ospiti`
  ADD CONSTRAINT `ospiti_ibfk_1` FOREIGN KEY (`codiceprenotazione`) REFERENCES `prenotazioni` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prenotato` FOREIGN KEY (`cf`) REFERENCES `utenti` (`cf`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `stanze`
--
ALTER TABLE `stanze`
  ADD CONSTRAINT `vincoloStanza` FOREIGN KEY (`codiceAbitazione`) REFERENCES `abitazioni` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `stanzeoccupate`
--
ALTER TABLE `stanzeoccupate`
  ADD CONSTRAINT `occupata` FOREIGN KEY (`codiceStanza`) REFERENCES `stanze` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `stanzeoccupate_ibfk_1` FOREIGN KEY (`codicePrenotazione`) REFERENCES `prenotazioni` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
