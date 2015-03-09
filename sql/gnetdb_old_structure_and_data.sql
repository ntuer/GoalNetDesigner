-- phpMyAdmin SQL Dump
-- version 4.2.8.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 28, 2014 at 08:50 AM
-- Server version: 5.5.40
-- PHP Version: 5.4.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `gnetdb`
--
CREATE DATABASE IF NOT EXISTS `gnetdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `gnetdb`;

-- --------------------------------------------------------

--
-- Table structure for table `arc`
--

DROP TABLE IF EXISTS `arc`;
CREATE TABLE IF NOT EXISTS `arc` (
`ID` bigint(20) unsigned NOT NULL COMMENT 'ID of an Arc',
  `GNetID` bigint(20) NOT NULL COMMENT 'GoalNetID of the goal net it belongs to.',
  `Name` varchar(50) NOT NULL COMMENT 'Name of the arc',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Description of the arc',
  `Direction` tinyint(1) NOT NULL COMMENT 'Yes means the arc is from a state to a transition, no means otherwise',
  `IsDirect` tinyint(1) NOT NULL COMMENT 'Whether the arc is a direct arc or a parallel one',
  `InputID` bigint(20) DEFAULT '0' COMMENT 'Input entity id',
  `OutputID` bigint(20) DEFAULT '0' COMMENT 'Output entity id'
) ENGINE=InnoDB AUTO_INCREMENT=11112 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arc`
--

INSERT INTO `arc` (`ID`, `GNetID`, `Name`, `Description`, `Direction`, `IsDirect`, `InputID`, `OutputID`) VALUES
(1, 28, '', '', 1, 1, 91, 1),
(2, 28, '', '', 0, 1, 1, 92),
(3, 28, '', '', 1, 1, 91, 2),
(4, 28, '', '', 0, 1, 2, 92),
(5, 28, '', '', 1, 1, 91, 3),
(6, 28, '', '', 0, 1, 3, 92),
(15, 29, '', '', 1, 0, 98, 5),
(17, 29, '', '', 1, 0, 98, 6),
(19, 29, '', '', 1, 0, 98, 7),
(21, 29, '', '', 1, 0, 98, 8),
(23, 29, '', '', 0, 0, 5, 99),
(24, 29, '', '', 0, 0, 6, 99),
(25, 29, '', '', 0, 0, 7, 99),
(26, 29, '', '', 0, 0, 8, 99),
(27, 30, '', '', 1, 1, 100, 9),
(28, 30, '', '', 0, 1, 9, 103),
(29, 30, '', '', 1, 1, 103, 10),
(30, 30, '', '', 0, 1, 10, 104),
(31, 30, '', '', 1, 1, 104, 11),
(32, 30, '', '', 0, 1, 11, 105),
(33, 30, '', '', 1, 1, 105, 12),
(34, 30, '', '', 0, 1, 12, 106),
(36, 30, '', '', 0, 1, 13, 101),
(37, 30, '', '', 1, 1, 107, 14),
(38, 30, '', '', 0, 1, 14, 109),
(43, 30, '', '', 1, 0, 109, 15),
(44, 30, '', '', 0, 0, 15, 110),
(45, 30, '', '', 1, 0, 110, 16),
(46, 30, '', '', 0, 0, 16, 108),
(47, 30, '', '', 1, 0, 109, 17),
(48, 30, '', '', 0, 0, 17, 108),
(49, 30, '', '', 1, 1, 111, 18),
(50, 30, '', '', 0, 1, 18, 112),
(52, 30, '', '', 1, 1, 106, 21),
(53, 30, '', '', 0, 1, 21, 113),
(54, 30, '', '', 1, 1, 113, 20),
(55, 30, '', '', 0, 1, 20, 114),
(56, 30, '', '', 1, 1, 114, 13),
(57, 30, '', '', 1, 1, 100, 22),
(58, 30, '', '', 0, 1, 22, 115),
(59, 30, '', '', 1, 1, 115, 23),
(60, 30, '', '', 1, 1, 115, 24),
(61, 30, '', '', 0, 1, 23, 101),
(62, 30, '', '', 0, 1, 24, 101),
(63, 30, '', '', 1, 1, 115, 25),
(64, 30, '', '', 0, 1, 25, 101),
(68, 32, '', '', 0, 1, 27, 120),
(69, 32, '', '', 1, 1, 120, 28),
(70, 32, '', '', 0, 1, 28, 121),
(71, 32, '', '', 1, 1, 121, 29),
(72, 32, '', '', 0, 1, 29, 117),
(73, 32, '', '', 1, 1, 119, 30),
(74, 32, '', '', 0, 1, 30, 119),
(76, 32, '', '', 1, 0, 116, 26),
(77, 32, '', '', 0, 0, 26, 119),
(78, 32, '', '', 1, 0, 116, 31),
(80, 32, '', '', 0, 0, 31, 123),
(81, 32, '', '', 1, 1, 124, 33),
(82, 32, '', '', 0, 1, 33, 124),
(83, 32, '', '', 1, 1, 124, 34),
(84, 32, '', '', 0, 1, 34, 125),
(102, 33, '', '', 1, 1, 146, 43),
(107, 33, '', '', 0, 1, 43, 150),
(111, 33, '', '', 1, 1, 150, 50),
(112, 33, '', '', 0, 1, 50, 150),
(118, 33, '', '', 1, 1, 133, 39),
(126, 33, '', '', 0, 1, 39, 152),
(128, 33, '', '', 1, 1, 152, 51),
(145, 34, '', '', 0, 1, 56, 157),
(146, 34, '', '', 1, 1, 157, 57),
(147, 34, '', '', 0, 1, 57, 154),
(148, 33, '', '', 1, 1, 144, 53),
(151, 33, '', '', 0, 1, 53, 164),
(152, 33, '', '', 0, 1, 53, 167),
(153, 33, '', '', 0, 1, 53, 168),
(154, 33, '', '', 0, 1, 51, 152),
(156, 33, '', '', 0, 1, 51, 169),
(157, 33, '', '', 1, 1, 169, 54),
(158, 33, '', '', 0, 1, 54, 144),
(159, 33, '', '', 1, 1, 164, 47),
(160, 33, '', '', 0, 1, 47, 134),
(161, 33, '', '', 1, 1, 167, 46),
(162, 33, '', '', 0, 1, 46, 134),
(163, 33, '', '', 1, 1, 168, 47),
(165, 35, '', '', 1, 1, 170, 62),
(167, 35, '', '', 0, 1, 62, 173),
(168, 35, '', '', 1, 1, 173, 63),
(169, 35, '', '', 0, 1, 63, 170),
(170, 34, '', '', 1, 1, 162, 56),
(171, 34, '', '', 0, 1, 56, 162),
(172, 33, '', '', 0, 1, 44, 174),
(173, 33, '', '', 1, 1, 174, 64),
(174, 33, '', '', 0, 1, 64, 177),
(175, 33, '', '', 0, 1, 64, 175),
(176, 33, '', '', 0, 1, 64, 176),
(177, 33, '', '', 1, 1, 175, 65),
(178, 33, '', '', 1, 1, 176, 65),
(179, 33, '', '', 0, 1, 65, 139),
(180, 33, '', '', 0, 1, 66, 139),
(181, 33, '', '', 1, 1, 177, 66),
(182, 33, '', '', 0, 1, 50, 178),
(183, 33, '', '', 1, 1, 178, 44),
(184, 37, '', '', 1, 1, 182, 67),
(186, 37, '', '', 1, 1, 183, 68),
(187, 37, '', '', 0, 1, 68, 184),
(188, 37, '', '', 0, 1, 67, 183),
(189, 38, '', '', 1, 1, 186, 69),
(190, 38, '', '', 0, 1, 69, 189),
(191, 38, '', '', 1, 1, 189, 70),
(192, 38, '', '', 0, 1, 70, 190),
(193, 38, '', '', 1, 1, 190, 71),
(194, 38, '', '', 0, 1, 71, 191),
(195, 38, '', '', 1, 1, 191, 72),
(196, 38, '', '', 0, 1, 72, 187),
(197, 39, '', '', 1, 1, 192, 73),
(198, 39, '', '', 0, 1, 73, 195),
(199, 39, '', '', 1, 1, 195, 74),
(200, 39, '', '', 0, 1, 74, 197),
(201, 39, '', '', 0, 1, 74, 196),
(202, 39, '', '', 1, 1, 197, 75),
(203, 39, '', '', 0, 1, 75, 193),
(204, 39, '', '', 1, 1, 198, 76),
(205, 39, '', '', 0, 1, 76, 199),
(207, 39, '', '', 1, 1, 193, 78),
(208, 39, '', '', 0, 1, 78, 193),
(209, 39, '', '', 0, 1, 78, 196),
(210, 41, '', '', 1, 1, 202, 80),
(211, 41, '', '', 0, 1, 80, 204),
(212, 41, '', '', 1, 1, 204, 81),
(213, 41, '', '', 0, 1, 81, 205),
(214, 41, '', '', 1, 1, 205, 82),
(215, 41, '', '', 0, 1, 82, 203),
(216, 41, '', '', 1, 1, 202, 83),
(217, 41, '', '', 0, 1, 83, 205),
(218, 41, '', '', 1, 1, 204, 84),
(219, 41, '', '', 0, 1, 84, 203),
(220, 41, '', '', 0, 1, 80, 202),
(221, 41, '', '', 1, 1, 207, 85),
(222, 41, '', '', 0, 1, 85, 212),
(223, 41, '', '', 1, 1, 212, 86),
(224, 41, '', '', 0, 1, 86, 210),
(225, 41, '', '', 1, 1, 208, 87),
(226, 41, '', '', 0, 1, 87, 209),
(228, 32, '', '', 0, 1, 34, 120),
(229, 32, '', '', 1, 0, 119, 27),
(230, 32, '', '', 1, 0, 123, 27),
(231, 40, '', '', 1, 1, 200, 79),
(232, 40, '', '', 0, 1, 79, 201),
(233, 40, '', '', 1, 1, 214, 88),
(234, 40, '', '', 0, 1, 88, 215),
(235, 40, '', '', 0, 1, 79, 216),
(236, 40, '', '', 1, 1, 216, 89),
(237, 40, '', '', 0, 1, 89, 201),
(238, 42, '', '', 1, 1, 217, 90),
(239, 42, '', '', 0, 1, 90, 218),
(240, 42, '', '', 1, 1, 217, 91),
(241, 44, '', '', 1, 1, 225, 92),
(242, 44, '', '', 0, 1, 92, 226),
(243, 44, '', '', 1, 1, 226, 93),
(244, 44, '', '', 0, 1, 93, 227),
(246, 45, '', '', 0, 1, 94, 232),
(249, 45, '', '', 1, 1, 232, 96),
(250, 45, '', '', 0, 1, 96, 232),
(253, 45, '', '', 1, 1, 232, 98),
(254, 45, '', '', 0, 1, 98, 233),
(255, 45, '', '', 1, 1, 233, 99),
(256, 45, '', '', 0, 1, 99, 233),
(259, 45, '', '', 1, 1, 233, 101),
(260, 45, '', '', 0, 1, 101, 236),
(261, 45, '', '', 1, 1, 236, 102),
(262, 45, '', '', 0, 1, 102, 230),
(263, 46, '', '', 1, 1, 237, 103),
(264, 46, '', '', 0, 1, 103, 240),
(265, 46, '', '', 1, 1, 240, 104),
(266, 46, '', '', 0, 1, 104, 238),
(267, 46, '', '', 1, 1, 237, 105),
(268, 46, '', '', 0, 1, 105, 241),
(269, 46, '', '', 1, 1, 240, 107),
(270, 46, '', '', 0, 1, 107, 240),
(271, 46, '', '', 1, 1, 241, 106),
(272, 46, '', '', 0, 1, 106, 238),
(273, 45, '', '', 1, 1, 234, 108),
(274, 45, '', '', 0, 1, 108, 234),
(275, 45, '', '', 1, 1, 234, 94),
(276, 47, '', '', 1, 1, 244, 109),
(277, 47, '', '', 0, 1, 109, 244),
(278, 47, '', '', 1, 1, 244, 110),
(279, 47, '', '', 0, 1, 110, 247),
(280, 47, '', '', 0, 1, 110, 244),
(281, 47, '', '', 1, 1, 247, 111),
(282, 47, '', '', 0, 1, 111, 250),
(283, 48, '', '', 1, 1, 251, 112),
(284, 48, '', '', 0, 1, 112, 251),
(285, 48, '', '', 1, 1, 251, 113),
(286, 48, '', '', 0, 1, 113, 251),
(287, 48, '', '', 0, 1, 113, 254),
(288, 48, '', '', 1, 1, 254, 114),
(291, 48, '', '', 0, 1, 114, 251),
(292, 48, '', '', 1, 1, 251, 115),
(293, 48, '', '', 0, 1, 115, 252),
(294, 49, '', '', 1, 1, 255, 116),
(295, 49, '', '', 0, 1, 116, 256),
(297, 50, '', '', 1, 1, 260, 117),
(298, 50, '', '', 1, 1, 260, 118),
(299, 50, '', '', 1, 1, 260, 119),
(300, 50, '', '', 0, 1, 117, 263),
(301, 50, '', '', 0, 1, 118, 264),
(302, 50, '', '', 0, 1, 119, 265),
(303, 50, '', '', 1, 1, 266, 120),
(304, 50, '', '', 0, 1, 120, 268),
(305, 50, '', '', 1, 1, 268, 121),
(306, 50, '', '', 0, 1, 121, 269),
(307, 50, '', '', 1, 1, 269, 122),
(308, 50, '', '', 0, 1, 122, 270),
(309, 50, '', '', 1, 1, 270, 123),
(310, 50, '', '', 0, 1, 123, 267),
(311, 50, '', '', 1, 1, 271, 124),
(312, 50, '', '', 0, 1, 124, 273),
(313, 50, '', '', 1, 1, 273, 125),
(314, 50, '', '', 0, 1, 125, 274),
(315, 50, '', '', 1, 1, 274, 126),
(316, 50, '', '', 0, 1, 126, 275),
(317, 50, '', '', 1, 1, 275, 127),
(318, 50, '', '', 0, 1, 127, 272),
(319, 50, '', '', 1, 1, 276, 128),
(320, 50, '', '', 0, 1, 128, 277),
(321, 50, '', '', 1, 1, 277, 129),
(322, 50, '', '', 0, 1, 129, 278),
(323, 50, '', '', 1, 1, 278, 130),
(324, 50, '', '', 0, 1, 130, 279),
(325, 50, '', '', 1, 1, 279, 131),
(326, 50, '', '', 0, 1, 131, 280),
(327, 50, '', '', 1, 1, 263, 132),
(328, 50, '', '', 0, 1, 132, 281),
(329, 50, '', '', 1, 1, 265, 134),
(330, 50, '', '', 0, 1, 134, 281),
(331, 50, '', '', 1, 1, 264, 133),
(332, 50, '', '', 0, 1, 133, 281),
(333, 50, '', '', 1, 1, 281, 135),
(334, 50, '', '', 0, 1, 135, 282),
(335, 50, '', '', 1, 1, 282, 136),
(336, 50, '', '', 0, 1, 136, 283),
(337, 50, '', '', 1, 1, 283, 137),
(338, 50, '', '', 0, 1, 137, 284),
(343, 50, '', '', 1, 1, 285, 140),
(344, 50, '', '', 0, 1, 140, 261),
(351, 51, '', '', 1, 1, 289, 144),
(354, 51, '', '', 1, 1, 290, 145),
(355, 51, '', '', 0, 1, 145, 291),
(356, 51, '', '', 1, 1, 291, 146),
(360, 51, '', '', 1, 1, 294, 147),
(361, 51, '', '', 0, 1, 147, 287),
(362, 52, '', '', 1, 1, 295, 148),
(363, 52, '', '', 0, 1, 148, 298),
(364, 52, '', '', 1, 1, 298, 149),
(365, 52, '', '', 0, 1, 149, 299),
(366, 52, '', '', 1, 1, 299, 150),
(367, 52, '', '', 0, 1, 150, 300),
(368, 52, '', '', 1, 1, 300, 151),
(369, 52, '', '', 0, 1, 151, 301),
(370, 52, '', '', 1, 1, 301, 152),
(375, 52, '', '', 0, 1, 152, 304),
(376, 52, '', '', 1, 1, 304, 154),
(377, 52, '', '', 0, 1, 154, 302),
(378, 52, '', '', 1, 1, 303, 155),
(379, 52, '', '', 0, 1, 155, 305),
(382, 52, '', '', 1, 1, 307, 156),
(383, 52, '', '', 1, 1, 305, 156),
(385, 52, '', '', 0, 1, 156, 296),
(386, 53, '', '', 1, 1, 308, 157),
(389, 53, '', '', 1, 1, 311, 158),
(390, 53, '', '', 0, 1, 158, 312),
(391, 53, '', '', 1, 1, 312, 159),
(392, 53, '', '', 0, 1, 159, 312),
(393, 53, '', '', 0, 1, 159, 313),
(394, 53, '', '', 1, 1, 313, 160),
(395, 53, '', '', 0, 1, 159, 315),
(396, 53, '', '', 1, 1, 315, 162),
(397, 53, '', '', 0, 1, 162, 314),
(398, 53, '', '', 1, 1, 314, 163),
(399, 53, '', '', 0, 1, 163, 309),
(405, 54, '', '', 1, 1, 317, 165),
(406, 54, '', '', 0, 1, 165, 318),
(409, 51, '', '', 1, 0, 286, 141),
(410, 51, '', '', 1, 0, 286, 142),
(411, 51, '', '', 1, 0, 286, 143),
(412, 51, '', '', 0, 0, 141, 289),
(413, 51, '', '', 0, 0, 142, 289),
(414, 51, '', '', 0, 0, 143, 289),
(415, 51, '', '', 0, 1, 144, 321),
(416, 51, '', '', 1, 1, 321, 167),
(417, 51, '', '', 0, 1, 167, 289),
(418, 51, '', '', 1, 1, 321, 168),
(419, 51, '', '', 0, 1, 168, 290),
(420, 50, '', '', 1, 0, 284, 138),
(421, 50, '', '', 1, 0, 284, 139),
(422, 50, '', '', 0, 0, 139, 285),
(423, 50, '', '', 0, 0, 138, 285),
(424, 51, '', '', 1, 1, 323, 169),
(425, 51, '', '', 0, 1, 169, 291),
(426, 51, '', '', 0, 1, 146, 323),
(427, 51, '', '', 1, 1, 323, 170),
(428, 51, '', '', 0, 1, 170, 294),
(429, 52, '', '', 1, 1, 302, 174),
(430, 52, '', '', 0, 1, 174, 324),
(431, 52, '', '', 1, 1, 324, 171),
(432, 52, '', '', 0, 1, 171, 302),
(433, 52, '', '', 1, 1, 324, 172),
(434, 52, '', '', 0, 1, 172, 303),
(435, 52, '', '', 1, 1, 324, 173),
(436, 52, '', '', 0, 1, 173, 307),
(437, 53, '', '', 0, 1, 161, 308),
(438, 53, '', '', 1, 1, 325, 175),
(439, 53, '', '', 0, 1, 175, 313),
(440, 53, '', '', 1, 1, 325, 161),
(441, 53, '', '', 1, 1, 325, 177),
(442, 53, '', '', 0, 1, 160, 325),
(443, 53, '', '', 0, 1, 177, 308),
(444, 53, '', '', 1, 1, 326, 179),
(445, 53, '', '', 0, 1, 179, 311),
(446, 53, '', '', 1, 1, 326, 178),
(447, 53, '', '', 0, 1, 178, 308),
(448, 53, '', '', 0, 1, 157, 326),
(449, 55, '', '', 1, 1, 328, 180),
(450, 55, '', '', 0, 1, 180, 331),
(451, 55, '', '', 1, 1, 331, 181),
(452, 55, '', '', 0, 1, 181, 331),
(453, 55, '', '', 1, 1, 331, 182),
(454, 55, '', '', 0, 1, 182, 328),
(455, 55, '', '', 1, 1, 331, 183),
(456, 55, '', '', 0, 1, 183, 332),
(457, 55, '', '', 1, 1, 332, 185),
(458, 55, '', '', 0, 1, 185, 332),
(459, 55, '', '', 1, 1, 332, 184),
(461, 55, '', '', 1, 1, 332, 186),
(462, 55, '', '', 0, 1, 186, 333),
(463, 55, '', '', 1, 1, 333, 187),
(464, 55, '', '', 0, 1, 187, 333),
(465, 55, '', '', 1, 1, 333, 188),
(467, 55, '', '', 1, 1, 333, 191),
(468, 55, '', '', 0, 1, 191, 329),
(471, 55, '', '', 1, 1, 329, 190),
(472, 55, '', '', 0, 1, 190, 328),
(473, 55, '', '', 0, 1, 184, 328),
(474, 55, '', '', 0, 1, 188, 328),
(475, 56, '', '', 1, 1, 334, 192),
(476, 56, '', '', 1, 1, 334, 193),
(477, 56, '', '', 0, 1, 192, 337),
(478, 56, '', '', 1, 1, 337, 194),
(479, 56, '', '', 0, 1, 194, 335),
(480, 56, '', '', 0, 1, 193, 338),
(481, 56, '', '', 1, 1, 338, 195),
(482, 56, '', '', 0, 1, 195, 335),
(483, 57, '', '', 1, 1, 339, 196),
(484, 57, '', '', 0, 1, 196, 340),
(485, 57, '', '', 1, 1, 340, 202),
(486, 57, '', '', 0, 1, 202, 339),
(487, 57, '', '', 1, 1, 339, 197),
(488, 57, '', '', 0, 1, 197, 342),
(489, 57, '', '', 1, 1, 339, 198),
(490, 57, '', '', 0, 1, 198, 343),
(491, 57, '', '', 1, 1, 339, 199),
(492, 57, '', '', 0, 1, 199, 344),
(493, 57, '', '', 1, 1, 339, 200),
(494, 57, '', '', 0, 1, 200, 345),
(495, 57, '', '', 1, 1, 339, 201),
(496, 57, '', '', 0, 1, 201, 346),
(497, 57, '', '', 1, 1, 340, 203),
(498, 57, '', '', 0, 1, 203, 342),
(499, 57, '', '', 1, 1, 340, 204),
(500, 57, '', '', 0, 1, 204, 343),
(501, 57, '', '', 1, 1, 340, 205),
(502, 57, '', '', 0, 1, 205, 344),
(503, 57, '', '', 1, 1, 340, 206),
(504, 57, '', '', 0, 1, 206, 345),
(505, 57, '', '', 1, 1, 340, 207),
(506, 57, '', '', 0, 1, 207, 346),
(507, 57, '', '', 1, 1, 342, 208),
(508, 57, '', '', 0, 1, 208, 343),
(509, 57, '', '', 1, 1, 343, 209),
(510, 57, '', '', 0, 1, 209, 344),
(511, 57, '', '', 1, 1, 344, 210),
(512, 57, '', '', 0, 1, 210, 345),
(513, 57, '', '', 1, 1, 345, 211),
(514, 57, '', '', 0, 1, 211, 346),
(515, 57, '', '', 1, 1, 346, 212),
(516, 57, '', '', 0, 1, 212, 347),
(517, 57, '', '', 1, 1, 346, 213),
(518, 57, '', '', 0, 1, 213, 348),
(519, 57, '', '', 1, 1, 348, 214),
(520, 57, '', '', 0, 1, 214, 347),
(521, 57, '', '', 1, 1, 347, 215),
(522, 57, '', '', 0, 1, 215, 348),
(523, 57, '', '', 1, 1, 349, 216),
(524, 57, '', '', 0, 1, 216, 339),
(525, 57, '', '', 1, 1, 349, 217),
(526, 57, '', '', 0, 1, 217, 340),
(527, 57, '', '', 1, 1, 348, 219),
(528, 57, '', '', 0, 1, 219, 350),
(529, 57, '', '', 1, 1, 347, 218),
(530, 57, '', '', 0, 1, 218, 350),
(10000, 1, 'sd', '', 1, 0, 1, 1),
(11111, 1, 'sd', 's', 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `function`
--

DROP TABLE IF EXISTS `function`;
CREATE TABLE IF NOT EXISTS `function` (
`ID` bigint(20) unsigned NOT NULL,
  `Alias` varchar(50) DEFAULT NULL COMMENT 'Alias of the function',
  `FileName` varchar(255) NOT NULL COMMENT 'Dll filename from which the function comes',
  `RTType` varchar(50) NOT NULL DEFAULT 'void' COMMENT 'return type of the function',
  `Name` varchar(50) NOT NULL COMMENT 'function name',
  `Params` varchar(500) DEFAULT NULL COMMENT 'parameters of the function in sequence separated by semicolons, eg int,float,string,char',
  `Values` varchar(500) DEFAULT NULL COMMENT 'Values of each parameter in the same sequence.',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Description for the function'
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `function`
--

INSERT INTO `function` (`ID`, `Alias`, `FileName`, `RTType`, `Name`, `Params`, `Values`, `Description`) VALUES
(1, 'Function 0', 'test.dll', 'void', 'EchoBOOL', NULL, NULL, 'A function from test.dll'),
(2, 'Function 1', 'test.dll', 'void', 'EchoChar', NULL, NULL, 'A function from test.dll'),
(3, 'Function 2', 'test.dll', 'void', 'EchoCharPtr', NULL, NULL, 'A function from test.dll'),
(4, 'Function 3', 'test.dll', 'void', 'EchoFloat', NULL, NULL, 'A function from test.dll'),
(5, 'Function 4', 'test.dll', 'void', 'EchoInt', NULL, NULL, 'A function from test.dll'),
(6, 'Function 5', 'test.dll', 'void', 'EchoIntPtr', NULL, NULL, 'A function from test.dll'),
(7, 'Function 6', 'test.dll', 'void', 'EchoLong', NULL, NULL, 'A function from test.dll'),
(8, 'Function 7', 'test.dll', 'void', 'EchoShort', NULL, NULL, 'A function from test.dll'),
(9, 'Function 8', 'test.dll', 'void', 'EchoString', NULL, NULL, 'A function from test.dll'),
(10, 'Function 9', 'test.dll', 'void', 'HelloWorld', NULL, NULL, 'A function from test.dll'),
(11, 'Function 10', 'test.dll', 'void', 'MinusFloat', NULL, NULL, 'A function from test.dll'),
(12, 'Function 11', 'test.dll', 'void', 'MinusInt', NULL, NULL, 'A function from test.dll'),
(13, 'Function 12', 'test.dll', 'void', 'Mul', NULL, NULL, 'A function from test.dll'),
(14, 'Function 13', 'test.dll', 'void', 'NoReturn', NULL, NULL, 'A function from test.dll'),
(15, 'AddService', 'http://155.69.221.69:8080/axis2/services/AddService?wsdl', 'OutputMessage', 'AddService', 'InputMessage', NULL, 'numerical add calculation - added by guest2'),
(16, 'Error1Service', 'http://155.69.221.69:8080/bioDemo/wsdl/Error1.wsdl', 'OutputMessage', 'Error1Service', 'InputMessage', NULL, 'correct errors'),
(17, 'Error2Service', 'http://155.69.221.69:8080/bioDemo/wsdl/Error2.wsdl', 'OutputMessage', 'Error2Service', 'InputMessage', NULL, 'correct errors'),
(18, 'Error3Service', 'http://155.69.221.69:8080/bioDemo/wsdl/Error3.wsdl', 'OutputMessage', 'Error3Service', 'InputMessage', NULL, 'correct errors'),
(19, 'Error4Service', 'http://155.69.221.69:8080/bioDemo/wsdl/Error4.wsdl', 'OutputMessage', 'Error4Service', 'InputMessage', NULL, 'correct errors'),
(20, 'Error5Service', 'http://155.69.221.69:8080/bioDemo/wsdl/Error5.wsdl', 'OutputMessage', 'Error5Service', 'InputMessage', NULL, 'correct errors'),
(21, 'Error6Service', 'http://155.69.221.69:8080/bioDemo/wsdl/Error6.wsdl', 'OutputMessage', 'Error6Service', 'InputMessage', NULL, 'correct errors'),
(22, 'Error7Service', 'http://155.69.221.69:8080/bioDemo/wsdl/Error7.wsdl', 'OutputMessage', 'Error7Service', 'InputMessage', NULL, 'correct errors'),
(23, 'Step1StartService', 'http://155.69.221.69:8080/bioDemo/wsdl/Step1Start.wsdl', 'OutputMessage', 'Step1StartService', 'InputMessage', NULL, 'update steps'),
(24, 'Step2UncompressService', 'http://155.69.221.69:8080/bioDemo/wsdl/Step2Uncompress.wsdl', 'OutputMessage', 'Step2UncompressService', 'InputMessage', NULL, 'update steps'),
(25, 'Step3changetypeService', 'http://155.69.221.69:8080/bioDemo/wsdl/Step3changetype.wsdl', 'OutputMessage', 'Step3changetypeService', 'InputMessage', NULL, 'update steps'),
(26, 'Step4combineService', 'http://155.69.221.69:8080/bioDemo/wsdl/Step4combine.wsdl', 'OutputMessage', 'Step4combineService', 'InputMessage', NULL, 'update steps'),
(27, 'Step5webService', 'http://155.69.221.69:8080/bioDemo/wsdl/Step5web.wsdl', 'OutputMessage', 'Step5webService', 'InputMessage', NULL, 'update steps'),
(28, 'Step6UncompressService', 'http://155.69.221.69:8080/bioDemo/wsdl/Step6Uncompress.wsdl', 'OutputMessage', 'Step6UncompressService', 'InputMessage', NULL, 'update steps'),
(29, 'Step7typeService', 'http://155.69.221.69:8080/bioDemo/wsdl/Step7type.wsdl', 'OutputMessage', 'Step7typeService', 'InputMessage', NULL, 'update steps'),
(30, 'Step8copyService', 'http://155.69.221.69:8080/bioDemo/wsdl/Step8copy.wsdl', 'OutputMessage', 'Step8copyService', 'InputMessage', NULL, 'update steps'),
(31, 'Step9changenameService', 'http://155.69.221.69:8080/bioDemo/wsdl/Step9changename.wsdl', 'OutputMessage', 'Step9changenameService', 'InputMessage', NULL, 'update steps'),
(32, 'ConverterService', 'http://155.69.221.69:8080/converter/wsdl/Converter.wsdl', 'OutputMessage', 'ConverterService', 'InputMessage', NULL, 'simple conversion between C and F measurements'),
(33, 'MulService', 'http://155.69.221.69:8080/axis2/services/MulService?wsdl', 'OutputMessage', 'MulService', 'InputMessage', NULL, 'numerical multiplication calculation- added by guest2'),
(34, 'SayHello', 'http://155.69.221.69:8080/axis2/services/SayHello?wsdl', 'OutputMessage', 'SayHello', 'InputMessage', NULL, 'simple echo service - added by guest2'),
(35, 'UnixCommandService', 'http://155.69.221.69:8080/axis2/services/UnixCommandService?wsdl', 'OutputMessage', 'UnixCommandService', 'InputMessage', NULL, 'command-enabled interface for remote Unix communication'),
(36, 'GetLocation124', 'aw_extra1.dll', 'char*', 'GetLocation', 'short,long', NULL, 'A function from aw_extra1.dll'),
(37, 'Function 1', 'aw_extra1.dll', 'void', 'doGesture', NULL, NULL, 'A function from aw_extra1.dll'),
(38, 'Function 2', 'aw_extra1.dll', 'void', 'initLoc', NULL, NULL, 'A function from aw_extra1.dll'),
(39, 'Function 3', 'aw_extra1.dll', 'void', 'login', NULL, NULL, 'A function from aw_extra1.dll'),
(40, 'Function 4', 'aw_extra1.dll', 'void', 'say', NULL, NULL, 'A function from aw_extra1.dll'),
(41, 'Function 5', 'aw_extra1.dll', 'void', 'turn', NULL, NULL, 'A function from aw_extra1.dll'),
(42, 'Function 6', 'aw_extra1.dll', 'void', 'walkto', NULL, NULL, 'A function from aw_extra1.dll'),
(43, 'Function 7', 'aw_extra1.dll', 'void', 'walkto1', NULL, NULL, 'A function from aw_extra1.dll'),
(44, 'Function 0', 'Copy of test111111.dll', 'void', 'EchoBOOL', 'int,short', NULL, 'A function from Copy of test111111.dll'),
(45, 'Function 1', 'Copy of test111111.dll', 'void', 'EchoChar', NULL, NULL, 'A function from Copy of test111111.dll'),
(46, 'Function 2', 'Copy of test111111.dll', 'void', 'EchoCharPtr', NULL, NULL, 'A function from Copy of test111111.dll'),
(47, 'Function 3', 'Copy of test111111.dll', 'void', 'EchoFloat', NULL, NULL, 'A function from Copy of test111111.dll'),
(48, 'Function 4', 'Copy of test111111.dll', 'void', 'EchoInt', NULL, NULL, 'A function from Copy of test111111.dll'),
(49, 'Function 5', 'Copy of test111111.dll', 'void', 'EchoIntPtr', NULL, NULL, 'A function from Copy of test111111.dll'),
(50, 'Function 6', 'Copy of test111111.dll', 'void', 'EchoLong', NULL, NULL, 'A function from Copy of test111111.dll'),
(51, 'Function 7', 'Copy of test111111.dll', 'void', 'EchoShort', NULL, NULL, 'A function from Copy of test111111.dll'),
(52, 'Function 8', 'Copy of test111111.dll', 'void', 'EchoString', NULL, NULL, 'A function from Copy of test111111.dll'),
(53, 'Function 9', 'Copy of test111111.dll', 'void', 'HelloWorld', NULL, NULL, 'A function from Copy of test111111.dll'),
(54, 'Function 10', 'Copy of test111111.dll', 'void', 'MinusFloat', NULL, NULL, 'A function from Copy of test111111.dll'),
(55, 'Function 11', 'Copy of test111111.dll', 'void', 'MinusInt', NULL, NULL, 'A function from Copy of test111111.dll'),
(56, 'Function 12', 'Copy of test111111.dll', 'void', 'Mul', NULL, NULL, 'A function from Copy of test111111.dll'),
(57, 'Function 13', 'Copy of test111111.dll', 'void', 'NoReturn', NULL, NULL, 'A function from Copy of test111111.dll'),
(58, 'Function 0', 'test11.dll', 'void', 'EchoBOOL', NULL, NULL, 'A function from test11.dll'),
(59, 'Function 1', 'test11.dll', 'void', 'EchoChar', NULL, NULL, 'A function from test11.dll'),
(60, 'Function 2', 'test11.dll', 'void', 'EchoCharPtr', NULL, NULL, 'A function from test11.dll'),
(61, 'Function 3', 'test11.dll', 'void', 'EchoFloat', NULL, NULL, 'A function from test11.dll'),
(62, 'Function 4', 'test11.dll', 'void', 'EchoInt', NULL, NULL, 'A function from test11.dll'),
(63, 'Function 5', 'test11.dll', 'void', 'EchoIntPtr', NULL, NULL, 'A function from test11.dll'),
(64, 'Function 6', 'test11.dll', 'void', 'EchoLong', NULL, NULL, 'A function from test11.dll'),
(65, 'Function 7', 'test11.dll', 'void', 'EchoShort', NULL, NULL, 'A function from test11.dll'),
(66, 'Function 8', 'test11.dll', 'void', 'EchoString', NULL, NULL, 'A function from test11.dll'),
(67, 'Function 9', 'test11.dll', 'void', 'HelloWorld', NULL, NULL, 'A function from test11.dll'),
(68, 'Function 10', 'test11.dll', 'void', 'MinusFloat', NULL, NULL, 'A function from test11.dll'),
(69, 'Function 11', 'test11.dll', 'void', 'MinusInt', NULL, NULL, 'A function from test11.dll'),
(70, 'Function 12', 'test11.dll', 'void', 'Mul', NULL, NULL, 'A function from test11.dll'),
(71, 'Function 13', 'test11.dll', 'void', 'NoReturn', NULL, NULL, 'A function from test11.dll');

-- --------------------------------------------------------

--
-- Table structure for table `gnet`
--

DROP TABLE IF EXISTS `gnet`;
CREATE TABLE IF NOT EXISTS `gnet` (
`ID` bigint(20) unsigned NOT NULL,
  `Name` varchar(50) NOT NULL COMMENT 'Name of the goal net',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Description of the goal net',
  `StateNumber` bigint(20) unsigned DEFAULT '0' COMMENT 'total number of states in the goal net',
  `TransitionNumber` bigint(20) unsigned DEFAULT '0' COMMENT 'total number of transition in the goal net.',
  `StartStateID` bigint(20) unsigned DEFAULT '0' COMMENT 'start state id',
  `EndStateID` bigint(20) unsigned DEFAULT '0' COMMENT 'end state id',
  `RootID` bigint(20) unsigned DEFAULT '0' COMMENT 'root state id',
  `GoalSelectionType` tinyint(1) DEFAULT '0' COMMENT 'True if fast goal selection is required. False to select the best goal',
  `IsOpen` tinyint(1) DEFAULT '0' COMMENT 'Mutex lock to ensure one goal net is not simultaneously being edited by more than one user. Yes means it is being edited.'
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gnet`
--

INSERT INTO `gnet` (`ID`, `Name`, `Description`, `StateNumber`, `TransitionNumber`, `StartStateID`, `EndStateID`, `RootID`, `GoalSelectionType`, `IsOpen`) VALUES
(27, 'test', 't', 0, 0, 0, 0, 0, 0, 0),
(28, 'test1', ' ', 3, 3, 91, 92, 93, 1, 0),
(29, 'Research Plan', 'A research plan upto the confirmation time (1 year)', 5, 4, 98, 99, 97, 1, 0),
(30, 'trust agent', ' ', 16, 16, 100, 101, 102, 1, 0),
(31, 'Appraisor', ' ', 0, 0, 0, 0, 0, 0, 0),
(32, 'testPF', '  ', 9, 8, 116, 117, 118, 1, 0),
(33, 'PF', 'PF paper graphs', 18, 12, 133, 134, 136, 1, 0),
(34, 'PF boy', 'boy agent', 4, 2, 162, 154, 158, 1, 1),
(35, 'Physics Agent', ' ', 4, 2, 170, 171, 172, 1, 0),
(36, 'PF police', ' ', 3, 0, 179, 180, 181, 1, 0),
(37, 'nwe', ' ', 4, 2, 182, 184, 185, 1, 0),
(38, 'NurseAvatar', ' ', 6, 4, 186, 187, 188, 1, 0),
(39, 'DoctorAvatar', ' ', 8, 5, 192, 193, 194, 1, 0),
(40, 'testing123', ' ', 6, 3, 200, 201, 213, 1, 0),
(41, 'test11', ' ', 11, 8, 202, 203, 206, 1, 0),
(42, 'sample11', ' ', 0, 0, 0, 0, 0, 0, 0),
(43, 'testtest', ' ', 0, 0, 0, 0, 0, 0, 0),
(44, 'dummy', ' ', 4, 2, 225, 227, 228, 1, 0),
(45, 'Storyline', ' ', 6, 7, 234, 230, 231, 1, 1),
(46, 'new one', ' ', 7, 5, 237, 238, 239, 1, 0),
(47, 'Teachable Agent', ' ', 3, 0, 244, 245, 246, 1, 1),
(48, 'TeachableAgent', ' ', 4, 4, 251, 252, 253, 1, 0),
(49, 'rrrr', ' ', 0, 0, 0, 0, 0, 0, 0),
(50, '3 little pigs', 'The classical story of the 3 little pigs', 26, 24, 260, 261, 262, 1, 0),
(51, '3 Little Pigs Overall', ' ', 9, 11, 286, 287, 288, 1, 0),
(52, 'Pig A', ' ', 13, 12, 295, 296, 297, 1, 0),
(53, 'Wolf', ' ', 10, 11, 308, 309, 310, 1, 0),
(54, 'demonstration', 'testing', 4, 1, 317, 319, 320, 1, 0),
(55, 'Remembrance Agent', 'RA', 6, 11, 328, 329, 330, 1, 0),
(56, 'test 100', ' ', 5, 4, 334, 335, 336, 1, 0),
(57, 'COS', 'The Goal Net design for the COS overall scenarios.', 12, 24, 349, 350, 351, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
CREATE TABLE IF NOT EXISTS `property` (
`ID` bigint(20) unsigned NOT NULL,
  `ParentID` bigint(20) NOT NULL COMMENT 'Id of the entity that the property belongs to.',
  `Type` varchar(50) NOT NULL COMMENT 'Data type',
  `Name` varchar(50) NOT NULL COMMENT 'Name of the property',
  `Val` varchar(255) DEFAULT NULL COMMENT 'Value of the property, to be converted to Type in the program',
  `ST` tinyint(1) NOT NULL COMMENT 'Yes means the parent entity is a State, no means it is a transition'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `property`
--

INSERT INTO `property` (`ID`, `ParentID`, `Type`, `Name`, `Val`, `ST`) VALUES
(1, 202, 'int', 'test', '1', 1);

-- --------------------------------------------------------

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
CREATE TABLE IF NOT EXISTS `state` (
`ID` bigint(20) unsigned NOT NULL,
  `GNetID` bigint(20) NOT NULL COMMENT 'GoalNetID of the goal net it belongs to',
  `ParentGNetID` bigint(20) DEFAULT '0' COMMENT 'Parent State ID of this state (>=1). Value may be invalid since only the start and end state of a sub goal net has a parent.',
  `Name` varchar(50) NOT NULL COMMENT 'Name of the state',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Description of the state',
  `Composite` tinyint(1) NOT NULL COMMENT 'Indicating whether the state is composite(Yes) or atomic(No)',
  `Cost` int(11) DEFAULT '0' COMMENT 'Cost of achieving the state',
  `Achievement` int(11) DEFAULT '0' COMMENT 'The benefit of achieving the state',
  `SubGNetStartID` bigint(20) DEFAULT '0' COMMENT 'State goal id if it is a composite state',
  `SubGNetEndID` bigint(20) DEFAULT '0' COMMENT 'End goal id if it is a composite state',
  `Token` bigint(20) DEFAULT '0' COMMENT 'Whether there is a token and the value of the token. no token means 0.',
  `X` int(11) NOT NULL COMMENT 'X coordinate of the state on the canvas',
  `Y` int(11) NOT NULL COMMENT 'Y coordinate of the state on the canvas'
) ENGINE=InnoDB AUTO_INCREMENT=352 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `state`
--

INSERT INTO `state` (`ID`, `GNetID`, `ParentGNetID`, `Name`, `Description`, `Composite`, `Cost`, `Achievement`, `SubGNetStartID`, `SubGNetEndID`, `Token`, `X`, `Y`) VALUES
(87, 27, -1, 'S87', 'New state', 0, 0, 0, -1, -1, 0, 236, 208),
(88, 27, -1, 'S88', 'New state', 0, 0, 0, -1, -1, 0, 395, 235),
(89, 27, -1, 'S89', 'New state', 0, 0, 0, -1, -1, 0, 610, 206),
(90, 27, -1, 'S90', 'New state', 0, 0, 0, -1, -1, 0, 620, 368),
(91, 28, 93, 'S91', 'New state', 0, 0, 0, -1, -1, 0, 196, 190),
(92, 28, 93, 'At school', 'New state', 0, 0, 0, -1, -1, 0, 482, 194),
(93, 28, -1, 'S93', 'New state', 1, 0, 0, 91, 92, 0, 331, 60),
(95, 29, 96, 'End', 'New state', 0, 0, 0, -1, -1, 0, 906, 207),
(96, 29, -1, 'S96', 'New state', 1, 0, 0, 97, 95, 0, 473, 6),
(97, 29, 96, 'Topic Proposed', 'New state', 1, 0, 0, 98, 99, 0, 66, 196),
(98, 29, 97, 'S98', 'New state', 0, 0, 0, -1, -1, 0, 11, 431),
(99, 29, 97, 'S99', 'New state', 0, 0, 0, -1, -1, 0, 395, 479),
(100, 30, 102, 'Start', 'New state', 0, 0, 0, -1, -1, 0, 41, 99),
(101, 30, 102, 'End', 'New state', 0, 0, 0, -1, -1, 0, 905, 98),
(102, 30, -1, 'BuyerAgent', 'New state', 1, 0, 0, 100, 101, 0, 449, -2),
(103, 30, -1, 'Found Sellers', 'New state', 0, 0, 0, -1, -1, 0, 199, 261),
(104, 30, -1, 'Sellers Ranked', 'New state', 1, 0, 0, 111, 112, 0, 271, 419),
(105, 30, -1, 'Seller Selected', 'New state', 0, 0, 0, -1, -1, 0, 453, 273),
(106, 30, -1, 'Transaction', 'New state', 1, 0, 0, 107, 108, 0, 572, 275),
(107, 30, 106, 'Start', 'New state', 0, 0, 0, -1, -1, 0, 389, 441),
(108, 30, 106, 'End', 'New state', 0, 0, 0, -1, -1, 0, 791, 426),
(109, 30, -1, 'Down Payment Made', 'New state', 0, 0, 0, -1, -1, 0, 483, 463),
(110, 30, -1, 'Item Received', 'New state', 0, 0, 0, -1, -1, 0, 619, 502),
(111, 30, 104, 'Start', 'New state', 0, 0, 0, -1, -1, 0, 178, 491),
(112, 30, 104, 'End', 'New state', 0, 0, 0, -1, -1, 0, 328, 492),
(113, 30, -1, 'Outcome Calculated', 'New state', 0, 0, 0, -1, -1, 0, 709, 243),
(114, 30, -1, 'Trust Belief Updated', 'New state', 0, 0, 0, -1, -1, 0, 798, 221),
(115, 30, -1, 'Target Agent Identified', 'New state', 0, 0, 0, -1, -1, 0, 380, 107),
(116, 32, 118, 'S116', 'New state', 0, 0, 0, -1, -1, 0, 52, 127),
(117, 32, 118, 'Aliya', 'New state', 0, 0, 0, -1, -1, 0, 907, 191),
(118, 32, -1, 'S118', 'New state', 1, 0, 0, 116, 117, 0, 304, 31),
(119, 32, -1, 'Check Reaching RD CRSS', 'New state', 0, 0, 0, -1, -1, 0, 341, 133),
(120, 32, -1, 'S120', 'New state', 0, 0, 0, -1, -1, 0, 573, 175),
(121, 32, -1, 'S121', 'New state', 0, 0, 0, -1, -1, 0, 703, 239),
(123, 32, -1, 'S123', 'New state', 1, 0, 0, 124, 125, 0, 270, 303),
(124, 32, 123, 'S124', 'New state', 0, 0, 0, -1, -1, 0, 134, 431),
(125, 32, 123, 'S125', 'New state', 0, 0, 0, -1, -1, 0, 412, 437),
(133, 33, -1, 'agents initialized', 'New state', 0, 0, 0, -1, -1, 0, 264, 120),
(134, 33, 136, 'end', 'New state', 0, 0, 0, -1, -1, 0, 759, 136),
(136, 33, -1, 'Scenario Controller Agent', 'New state', 1, 0, 0, 133, 134, 0, 495, 39),
(139, 33, 147, 'end', 'New state', 0, 0, 0, -1, -1, 0, 728, 459),
(144, 33, -1, 'boy started', 'New state', 0, 0, 0, -1, -1, 0, 438, 185),
(146, 33, 147, 'start', 'New state', 0, 0, 0, -1, -1, 0, 48, 457),
(147, 33, -1, 'Client Agent', 'New state', 1, 0, 0, 146, 139, 0, 394, 363),
(150, 33, -1, 'driving', 'New state', 0, 0, 0, -1, -1, 0, 177, 526),
(152, 33, -1, 'driver started', 'New state', 0, 0, 0, -1, -1, 0, 314, 174),
(154, 34, 158, 'end', 'New state', 0, 0, 0, -1, -1, 0, 463, 68),
(157, 34, -1, 'car is near', 'New state', 0, 0, 0, -1, -1, 0, 296, 68),
(158, 34, -1, 'boy', 'New state', 1, 0, 0, 162, 154, 0, 311, 11),
(162, 34, 158, 'playing ball', 'New state', 0, 0, 0, -1, -1, 0, 183, 74),
(164, 33, -1, 'collision', 'New state', 0, 0, 0, -1, -1, 0, 575, 250),
(167, 33, -1, 'safe', 'New state', 0, 0, 0, -1, -1, 0, 538, 133),
(168, 33, -1, 'near miss', 'New state', 0, 0, 0, -1, -1, 0, 572, 177),
(169, 33, -1, 'reaching junction', 'reaching juction', 0, 0, 0, -1, -1, 0, 385, 125),
(170, 35, 172, 'start', 'New state', 0, 0, 0, -1, -1, 0, 126, 218),
(171, 35, 172, 'S171', 'New state', 0, 0, 0, -1, -1, 0, 483, 224),
(172, 35, -1, 'S172', 'New state', 1, 0, 0, 170, 171, 0, 316, 86),
(173, 35, -1, 'new position computed', 'New state', 0, 0, 0, -1, -1, 0, 275, 260),
(174, 33, -1, 'car stopped', 'New state', 0, 0, 0, -1, -1, 0, 364, 526),
(175, 33, -1, 'near miss', 'New state', 0, 0, 0, -1, -1, 0, 571, 500),
(176, 33, -1, 'accident', 'New state', 0, 0, 0, -1, -1, 0, 566, 563),
(177, 33, -1, 'safe', 'New state', 0, 0, 0, -1, -1, 0, 530, 459),
(178, 33, -1, 'boy in the front', 'New state', 0, 0, 0, -1, -1, 0, 256, 528),
(179, 36, 181, 'S179', 'New state', 0, 0, 0, -1, -1, 0, 147, 254),
(180, 36, 181, 'S180', 'New state', 0, 0, 0, -1, -1, 0, 664, 256),
(181, 36, -1, 'S181', 'New state', 1, 0, 0, 179, 180, 0, 408, 104),
(182, 37, 185, 'S182', 'New state', 0, 0, 0, -1, -1, 0, 157, 196),
(183, 37, -1, 'S183', 'New state', 0, 0, 0, -1, -1, 0, 374, 249),
(184, 37, 185, 'S184', 'New state', 0, 0, 0, -1, -1, 0, 660, 247),
(185, 37, -1, 'S185', 'New state', 1, 0, 0, 182, 184, 0, 405, 70),
(186, 38, 188, 'S186', 'New state', 0, 0, 0, -1, -1, 0, 197, 215),
(187, 38, 188, 'S187', 'New state', 0, 0, 0, -1, -1, 0, 748, 214),
(188, 38, -1, 'S188', 'New state', 1, 0, 0, 186, 187, 0, 485, 62),
(189, 38, -1, 'S189', 'New state', 0, 0, 0, -1, -1, 0, 321, 257),
(190, 38, -1, 'S190', 'New state', 0, 0, 0, -1, -1, 0, 481, 274),
(191, 38, -1, 'S191', 'New state', 0, 0, 0, -1, -1, 0, 623, 277),
(192, 39, 194, 'Start', 'New state', 0, 0, 0, -1, -1, 0, 44, 142),
(193, 39, 194, 'Sitting at Desk', 'New state', 0, 0, 0, -1, -1, 0, 614, 99),
(194, 39, -1, 'DoctorAvatar', 'DoctorAvatar', 1, 0, 0, 192, 193, 0, 319, 20),
(195, 39, -1, 'At Patient 1', 'New state', 0, 0, 0, -1, -1, 0, 132, 152),
(196, 39, -1, 'Talking to Player', 'New state', 1, 0, 0, 198, 199, 0, 259, 212),
(197, 39, -1, 'No Player is Near', 'New state', 0, 0, 0, -1, -1, 0, 408, 153),
(198, 39, 196, 'Start', 'New state', 0, 0, 0, -1, -1, 0, 138, 289),
(199, 39, 196, 'End', 'New state', 0, 0, 0, -1, -1, 0, 369, 289),
(200, 40, 213, 'S200', 'New state', 0, 0, 0, -1, -1, 0, 249, 273),
(201, 40, 213, 'S201', 'New state', 0, 0, 0, -1, -1, 0, 603, 275),
(202, 41, 206, 'S202', 'New state', 0, 0, 0, -1, -1, 0, 113, 183),
(203, 41, 206, 'S203', 'New state', 0, 0, 0, -1, -1, 0, 634, 199),
(204, 41, -1, 'S204', 'New state', 0, 0, 0, -1, -1, 0, 310, 193),
(205, 41, -1, 'S205', 'New state', 0, 0, 0, -1, -1, 0, 474, 195),
(206, 41, -1, 'S206', 'New state', 1, 0, 0, 202, 203, 0, 417, 47),
(207, 41, 211, 'S207', 'New state', 0, 0, 0, -1, -1, 0, 155, 422),
(208, 41, 212, 'S208', 'New state', 0, 0, 0, -1, -1, 0, 264, 576),
(209, 41, 212, 'S209', 'New state', 0, 0, 0, -1, -1, 0, 616, 604),
(210, 41, 211, 'S210', 'New state', 0, 0, 0, -1, -1, 0, 688, 427),
(211, 41, -1, 'S211', 'New state', 1, 0, 0, 207, 210, 0, 473, 338),
(212, 41, -1, 'S212', 'New state', 1, 0, 0, 208, 209, 0, 471, 461),
(213, 40, -1, 'S213', 'New state', 1, 0, 0, 200, 201, 0, 464, 129),
(214, 40, 216, 'S214', 'New state', 0, 0, 0, -1, -1, 0, 325, 431),
(215, 40, 216, 'S215', 'New state', 0, 0, 0, -1, -1, 0, 496, 431),
(216, 40, -1, 'S216', 'New state', 1, 0, 0, 214, 215, 0, 359, 356),
(217, 42, -1, 'S0', 'New state', 0, 0, 0, -1, -1, 0, 495, 147),
(218, 42, -1, 'S0', 'New state', 0, 0, 0, -1, -1, 0, 735, 173),
(219, 42, -1, 'S0', 'New state', 1, 0, 0, 217, 218, 0, 617, 49),
(220, 43, -1, 'S220', 'New state', 0, 0, 0, -1, -1, 0, 145, 92),
(221, 43, -1, 'S221', 'New state', 0, 0, 0, -1, -1, 0, 348, 202),
(222, 43, -1, 'S222', 'New state', 0, 0, 0, -1, -1, 0, 466, 174),
(223, 43, -1, 'S223', 'New state', 0, 0, 0, -1, -1, 0, 560, 237),
(224, 43, -1, 'S224', 'New state', 0, 0, 0, -1, -1, 0, 545, 313),
(225, 44, 228, 'S225', 'New state', 0, 0, 0, -1, -1, 0, 165, 193),
(226, 44, -1, 'S226', 'New state', 0, 0, 0, -1, -1, 0, 335, 203),
(227, 44, 228, 'S227', 'New state', 0, 0, 0, -1, -1, 0, 535, 192),
(228, 44, -1, 'S228', 'New state', 1, 0, 0, 225, 227, 0, 335, 79),
(230, 45, 231, 'End', 'New state', 0, 0, 0, -1, -1, 0, 868, 256),
(231, 45, -1, 'Storyline', 'New state', 1, 0, 0, 234, 230, 0, 327, 154),
(232, 45, -1, 'Teacher Agent Activated', 'New state', 0, 0, 0, -1, -1, 0, 265, 272),
(233, 45, -1, 'Micro-world Unlocked', 'New state', 0, 0, 0, -1, -1, 0, 546, 263),
(234, 45, 231, 'Start', 'New state', 0, 0, 0, -1, -1, 0, 34, 269),
(236, 45, -1, 'Teachable Agent Activated', 'New state', 0, 0, 0, -1, -1, 0, 707, 313),
(237, 46, 239, 'S237', 'New state', 0, 0, 0, -1, -1, 0, 276, 314),
(238, 46, 239, 'S238', 'New state', 0, 0, 0, -1, -1, 0, 784, 314),
(239, 46, -1, 'S239', 'New state', 1, 0, 0, 237, 238, 0, 504, 114),
(240, 46, -1, 'S240', 'New state', 0, 0, 0, -1, -1, 0, 546, 312),
(241, 46, -1, 'S241', 'New state', 1, 0, 0, 242, 243, 0, 549, 425),
(242, 46, 241, 'S242', 'New state', 0, 0, 0, -1, -1, 0, 436, 603),
(243, 46, 241, 'S243', 'New state', 0, 0, 0, -1, -1, 0, 721, 597),
(244, 47, 246, 'S244', 'New state', 0, 0, 0, -1, -1, 0, 215, 275),
(245, 47, 246, 'S245', 'New state', 0, 0, 0, -1, -1, 0, 674, 278),
(246, 47, -1, 'S246', 'New state', 1, 0, 0, 244, 245, 0, 463, 80),
(247, 47, -1, 'S0', 'New state', 0, 0, 0, -1, -1, 0, 384, 277),
(248, 47, -1, 'S0', 'New state', 0, 0, 0, -1, -1, 0, 371, 471),
(249, 47, -1, 'S0', 'New state', 0, 0, 0, -1, -1, 0, 663, 473),
(251, 48, 253, 'Start', 'New state', 0, 0, 0, -1, -1, 0, 272, 333),
(252, 48, 253, 'End', 'New state', 0, 0, 0, -1, -1, 0, 704, 289),
(253, 48, -1, 'Teachable Agent', 'New state', 1, 0, 0, 251, 252, 0, 378, 224),
(254, 48, -1, 'Conflicting Rules Found', 'New state', 0, 0, 0, -1, -1, 0, 515, 419),
(255, 49, 257, 'S255', 'New state', 1, 0, 0, 258, 259, 0, 353, 295),
(256, 49, 257, 'S256', 'New state', 0, 0, 0, -1, -1, 0, 733, 297),
(257, 49, -1, 'S257', 'New state', 1, 0, 0, 255, 256, 0, 532, 66),
(258, 49, 255, 'S258', 'New state', 0, 0, 0, -1, -1, 0, 257, 403),
(259, 49, 255, 'S259', 'New state', 0, 0, 0, -1, -1, 0, 444, 400),
(260, 50, 262, 'Start', 'New state', 0, 0, 0, -1, -1, 0, 63, 244),
(261, 50, 262, 'Happily Ever After', 'New state', 0, 0, 0, -1, -1, 0, 1012, 234),
(262, 50, -1, '3 Little Pigs', 'New state', 1, 0, 0, 260, 261, 0, 190, 172),
(263, 50, -1, 'Build Straw House', 'New state', 1, 0, 0, 266, 267, 0, 279, 227),
(264, 50, -1, 'Build Stick House', 'New state', 1, 0, 0, 271, 272, 0, 289, 389),
(265, 50, -1, 'Build Brick House', 'New state', 1, 0, 0, 276, 280, 0, 289, 574),
(266, 50, 263, 'Begin', 'New state', 0, 0, 0, -1, -1, 0, 163, 281),
(267, 50, 263, 'Finish', 'New state', 0, 0, 0, -1, -1, 0, 568, 282),
(268, 50, -1, 'Straws Gathered', 'New state', 0, 0, 0, -1, -1, 0, 250, 283),
(269, 50, -1, 'Roof Built', 'New state', 0, 0, 0, -1, -1, 0, 375, 282),
(270, 50, -1, 'Walls Built', 'New state', 0, 0, 0, -1, -1, 0, 466, 280),
(271, 50, 264, 'Begin', 'New state', 0, 0, 0, -1, -1, 0, 171, 443),
(272, 50, 264, 'Finish', 'New state', 0, 0, 0, -1, -1, 0, 563, 448),
(273, 50, -1, 'Sticks Gathered', 'New state', 0, 0, 0, -1, -1, 0, 251, 442),
(274, 50, -1, 'Roof Built', 'New state', 0, 0, 0, -1, -1, 0, 374, 442),
(275, 50, -1, 'Walls Built', 'New state', 0, 0, 0, -1, -1, 0, 472, 443),
(276, 50, 265, 'Begin', 'New state', 0, 0, 0, -1, -1, 0, 175, 663),
(277, 50, -1, 'Bricks Gathered', 'New state', 0, 0, 0, -1, -1, 0, 259, 666),
(278, 50, -1, 'Roof Built', 'New state', 0, 0, 0, -1, -1, 0, 369, 664),
(279, 50, -1, 'Walls & Chimney Built', 'New state', 0, 0, 0, -1, -1, 0, 471, 654),
(280, 50, 265, 'Finish', 'New state', 0, 0, 0, -1, -1, 0, 587, 665),
(281, 50, -1, 'Pigs are in Houses', 'New state', 0, 0, 0, -1, -1, 0, 674, 342),
(282, 50, -1, 'Pig A is eaten', 'New state', 0, 0, 0, -1, -1, 0, 761, 305),
(283, 50, -1, 'Pig B is eaten', 'New state', 0, 0, 0, -1, -1, 0, 783, 409),
(284, 50, -1, 'Brick House withstands', 'New state', 0, 0, 0, -1, -1, 0, 791, 499),
(285, 50, -1, 'Wolf is toast', 'New state', 0, 0, 0, -1, -1, 0, 838, 643),
(286, 51, 288, 'Start', 'New state', 0, 0, 0, -1, -1, 0, 23, 162),
(287, 51, 288, 'Happily Ever Afer', 'New state', 0, 0, 0, -1, -1, 0, 840, 163),
(288, 51, -1, 'Scene Manager', 'New state', 1, 0, 0, 286, 287, 0, 171, 91),
(289, 51, -1, 'Pig Created', 'New state', 0, 0, 0, -1, -1, 0, 226, 206),
(290, 51, -1, 'All 3 Houses Finished', 'New state', 0, 0, 0, -1, -1, 0, 375, 250),
(291, 51, -1, 'Wolf Created', 'New state', 0, 0, 0, -1, -1, 0, 458, 209),
(294, 51, -1, 'Wolf Defeated', 'New state', 0, 0, 0, -1, -1, 0, 666, 208),
(295, 52, 297, 'Start', 'New state', 0, 0, 0, -1, -1, 0, 32, 257),
(296, 52, 297, 'End', 'New state', 0, 0, 0, -1, -1, 0, 1030, 206),
(297, 52, -1, 'Pig', 'New state', 1, 0, 0, 295, 296, 0, 492, 59),
(298, 52, -1, 'Materials Gathered', 'New state', 0, 0, 0, -1, -1, 0, 85, 290),
(299, 52, -1, 'Roof Built', 'New state', 0, 0, 0, -1, -1, 0, 199, 269),
(300, 52, -1, 'Walls & Chimney Built', 'New state', 0, 0, 0, -1, -1, 0, 318, 322),
(301, 52, -1, 'House Assembled', 'New state', 0, 0, 0, -1, -1, 0, 418, 224),
(302, 52, -1, 'Fire Lit', 'New state', 0, 0, 0, -1, -1, 0, 633, 282),
(303, 52, -1, 'House Blown Down', 'New state', 0, 0, 0, -1, -1, 0, 763, 284),
(304, 52, -1, 'In the House', 'New state', 0, 0, 0, -1, -1, 0, 547, 273),
(305, 52, -1, 'Reach Next House', 'New state', 0, 0, 0, -1, -1, 0, 873, 306),
(307, 52, -1, 'Wolf Defeated', 'New state', 0, 0, 0, -1, -1, 0, 844, 215),
(308, 53, 310, 'Start', 'New state', 0, 0, 0, -1, -1, 0, 74, 250),
(309, 53, 310, 'End', 'New state', 0, 0, 0, -1, -1, 0, 847, 271),
(310, 53, -1, 'Wolf', 'New state', 1, 0, 0, 308, 309, 0, 462, 31),
(311, 53, -1, 'Found Pig', 'New state', 0, 0, 0, -1, -1, 0, 255, 327),
(312, 53, -1, 'Spoken', 'New state', 0, 0, 0, -1, -1, 0, 339, 304),
(313, 53, -1, 'House Crumbles', 'New state', 0, 0, 0, -1, -1, 0, 337, 135),
(314, 53, -1, 'Burnt by Fire', 'New state', 0, 0, 0, -1, -1, 0, 710, 314),
(315, 53, -1, 'House Withstands', 'New state', 0, 0, 0, -1, -1, 0, 530, 290),
(317, 54, 320, 'S317', 'New state', 0, 0, 0, -1, -1, 0, 57, 127),
(318, 54, -1, 'S318', 'New state', 0, 0, 0, -1, -1, 0, 236, 157),
(319, 54, 320, 'S319', 'New state', 0, 0, 0, -1, -1, 0, 461, 213),
(320, 54, -1, 'S320', 'New state', 1, 0, 0, 317, 319, 0, 300, 58),
(321, 51, -1, 'Decision 1', 'New state', 0, 0, 0, -1, -1, 0, 310, 209),
(323, 51, -1, 'Decision 2', 'New state', 0, 0, 0, -1, -1, 0, 585, 223),
(324, 52, -1, 'House Status Acquired', 'New state', 0, 0, 0, -1, -1, 0, 696, 237),
(325, 53, -1, 'Decision 2', 'New state', 0, 0, 0, -1, -1, 0, 583, 135),
(326, 53, -1, 'Decision 1', 'New state', 0, 0, 0, -1, -1, 0, 190, 376),
(328, 55, 330, 'Start', 'New state', 0, 0, 0, -1, -1, 0, 40, 249),
(329, 55, 330, 'End', 'New state', 0, 0, 0, -1, -1, 0, 713, 207),
(330, 55, -1, 'Remembrance Agent', 'New state', 1, 0, 0, 328, 329, 0, 346, 195),
(331, 55, -1, 'Decision 1', 'New state', 0, 0, 0, -1, -1, 0, 219, 392),
(332, 55, -1, 'Decision 2', 'New state', 0, 0, 0, -1, -1, 0, 456, 341),
(333, 55, -1, 'Decision 3', 'New state', 0, 0, 0, -1, -1, 0, 652, 335),
(334, 56, 336, 'S334', 'New state', 0, 0, 0, -1, -1, 0, 193, 317),
(335, 56, 336, 'S335', 'New state', 0, 0, 0, -1, -1, 0, 680, 300),
(336, 56, -1, 'S336', 'New state', 1, 0, 0, 334, 335, 0, 506, 106),
(337, 56, -1, 'S337', 'New state', 0, 0, 0, -1, -1, 0, 446, 302),
(338, 56, -1, 'S338', 'New state', 0, 0, 0, -1, -1, 0, 447, 417),
(339, 57, -1, 'At Diffusion Lab', 'New state', 0, 0, 0, -1, -1, 0, 302, 111),
(340, 57, -1, 'At Osmosis Lab', 'New state', 0, 0, 0, -1, -1, 0, 607, 111),
(342, 57, -1, 'Teach Water & Salt', 'New state', 0, 0, 0, -1, -1, 0, 93, 362),
(343, 57, -1, 'Bring water & salt into root', 'New state', 0, 0, 0, -1, -1, 0, 282, 375),
(344, 57, -1, 'Bring water & salt to leaf', 'New state', 0, 0, 0, -1, -1, 0, 435, 364),
(345, 57, -1, 'Help food generation', 'New state', 0, 0, 0, -1, -1, 0, 580, 378),
(346, 57, -1, 'Bring food out of leaf', 'New state', 0, 0, 0, -1, -1, 0, 714, 367),
(347, 57, -1, 'Teach Uncle Ben', 'New state', 0, 0, 0, -1, -1, 0, 299, 598),
(348, 57, -1, 'Teach Banana Tree', 'New state', 0, 0, 0, -1, -1, 0, 594, 603),
(349, 57, 351, 'Enter', 'New state', 0, 0, 0, -1, -1, 0, 686, 21),
(350, 57, 351, 'Exit', 'New state', 0, 0, 0, -1, -1, 0, 702, 722),
(351, 57, -1, 'VS II Overall', 'New state', 1, 0, 0, 349, 350, 0, 857, 278);

-- --------------------------------------------------------

--
-- Table structure for table `state_function`
--

DROP TABLE IF EXISTS `state_function`;
CREATE TABLE IF NOT EXISTS `state_function` (
  `StateID` bigint(20) unsigned NOT NULL COMMENT 'State ID',
  `FunctionID` bigint(20) unsigned NOT NULL COMMENT 'Function ID',
  `Sequence` int(10) unsigned NOT NULL COMMENT 'The sequence number of the function in the current state, starting from 1.',
  `Arguments` varchar(500) DEFAULT NULL COMMENT 'Function arguments separated by ";"'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `state_function`
--

INSERT INTO `state_function` (`StateID`, `FunctionID`, `Sequence`, `Arguments`) VALUES
(91, 1, 1, NULL),
(119, 1, 1, NULL),
(201, 36, 1, '1;123'),
(202, 41, 1, NULL),
(237, 37, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE IF NOT EXISTS `task` (
`ID` bigint(20) unsigned NOT NULL,
  `Name` varchar(50) NOT NULL COMMENT 'Task name',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Task description',
  `Composite` tinyint(1) DEFAULT '0' COMMENT 'whether this task is composed of other tasks',
  `ClassName` varchar(50) DEFAULT NULL COMMENT 'For a non-composite task, the class name must be given so that process unit knows whether to load this task.',
  `ChildrenTaskNumber` int(11) DEFAULT '0' COMMENT 'total number of children task for a composite task',
  `ChildTaskID` bigint(20) DEFAULT '0' COMMENT 'the child task ID',
  `Cost` int(11) DEFAULT '0' COMMENT 'Cost of execuing the task.',
  `Achievement` int(11) DEFAULT '0' COMMENT 'Achievement after executing the task.'
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`ID`, `Name`, `Description`, `Composite`, `ClassName`, `ChildrenTaskNumber`, `ChildTaskID`, `Cost`, `Achievement`) VALUES
(1, 'New Task1', 'New Task', 0, NULL, 0, 0, 0, 0),
(2, 'New Task2', 'New Task', 0, NULL, 0, 0, 0, 0),
(3, 'New Task', 'New Task', 0, NULL, 0, 0, 0, 0),
(4, 'New Task4', 'New Task', 0, NULL, 0, 0, 0, 0),
(5, 'New Task', 'New Task', 0, NULL, 0, 0, 0, 0),
(6, 'New Task6', 'New Task', 0, NULL, 0, 0, 0, 0),
(7, 'New Task', 'New Task', 0, NULL, 0, 0, 0, 0),
(8, 'New Task', 'New Task', 0, NULL, 0, 0, 0, 0),
(9, 'task1', 'New Task', 0, NULL, 0, 0, 0, 0),
(10, 'task2', 'New Task', 0, NULL, 0, 0, 0, 0),
(11, 'New Task', 'New Task', 0, NULL, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tasklist`
--

DROP TABLE IF EXISTS `tasklist`;
CREATE TABLE IF NOT EXISTS `tasklist` (
`ID` bigint(20) unsigned NOT NULL,
  `Name` varchar(50) NOT NULL COMMENT 'Tasklist name',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Tasklist Description'
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tasklist`
--

INSERT INTO `tasklist` (`ID`, `Name`, `Description`) VALUES
(1, 'TaskList1', ''),
(2, 'TaskList', ''),
(3, 'TaskList', ''),
(4, 'TaskList', ''),
(5, 'TaskList', ''),
(6, 'TaskList', ''),
(7, 'TaskList', ''),
(8, 'TaskList1', ''),
(9, 'TaskList', '');

-- --------------------------------------------------------

--
-- Table structure for table `tasklist_task`
--

DROP TABLE IF EXISTS `tasklist_task`;
CREATE TABLE IF NOT EXISTS `tasklist_task` (
  `TaskListID` bigint(20) unsigned NOT NULL COMMENT 'ID of the tasklist',
  `TaskID` bigint(20) unsigned NOT NULL COMMENT 'ID of the task',
  `Sequence` int(10) unsigned NOT NULL COMMENT 'sequence number of the task in the tasklist, starts from 1.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tasklist_task`
--

INSERT INTO `tasklist_task` (`TaskListID`, `TaskID`, `Sequence`) VALUES
(1, 1, 1),
(3, 2, 1),
(3, 3, 2),
(4, 4, 1),
(5, 5, 1),
(6, 6, 1),
(7, 7, 1),
(7, 8, 2),
(8, 9, 1),
(8, 10, 2),
(9, 11, 1);

-- --------------------------------------------------------

--
-- Table structure for table `task_function`
--

DROP TABLE IF EXISTS `task_function`;
CREATE TABLE IF NOT EXISTS `task_function` (
  `TaskID` bigint(20) unsigned NOT NULL COMMENT 'Task ID',
  `FunctionID` bigint(20) unsigned NOT NULL COMMENT 'Function ID',
  `Sequence` int(10) unsigned NOT NULL COMMENT 'Sequence number of the function in the task, starts from 1.',
  `Arguments` varchar(500) DEFAULT NULL COMMENT 'Function arguments separated by ";"'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `task_function`
--

INSERT INTO `task_function` (`TaskID`, `FunctionID`, `Sequence`, `Arguments`) VALUES
(1, 7, 1, NULL),
(1, 9, 2, NULL),
(2, 36, 1, NULL),
(4, 44, 2, NULL),
(4, 48, 1, NULL),
(5, 8, 1, NULL),
(5, 7, 2, NULL),
(6, 36, 1, NULL),
(7, 15, 1, NULL),
(10, 36, 2, NULL),
(10, 38, 1, NULL),
(11, 36, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `transition`
--

DROP TABLE IF EXISTS `transition`;
CREATE TABLE IF NOT EXISTS `transition` (
`ID` bigint(20) unsigned NOT NULL,
  `GNetID` bigint(20) NOT NULL COMMENT 'GoalNetID of the goal net it belongs to',
  `Name` varchar(50) NOT NULL COMMENT 'Name of the transition',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Description of the transition',
  `Type` varchar(50) NOT NULL COMMENT 'Type of the transition: Simple, Reasoning...',
  `TaskListID` bigint(20) DEFAULT '0' COMMENT 'ID of its TaskList',
  `TaskNumber` bigint(20) DEFAULT '0' COMMENT 'Not used currently',
  `ExceptionStateID` int(11) DEFAULT '0' COMMENT 'Not used currently',
  `Level` int(11) DEFAULT '0' COMMENT 'Not used currently',
  `Enabled` tinyint(1) DEFAULT '0' COMMENT 'Indicate if the transition is enabled',
  `Cost` int(11) DEFAULT '0' COMMENT 'Cost of going through the transition',
  `Achievement` int(11) DEFAULT '0' COMMENT 'Achievement of going through the transition',
  `X` int(11) NOT NULL COMMENT 'X coordinate of the transition on the canvas',
  `Y` int(11) NOT NULL COMMENT 'Y coordinate of the transition on the canvas'
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `transition`
--

INSERT INTO `transition` (`ID`, `GNetID`, `Name`, `Description`, `Type`, `TaskListID`, `TaskNumber`, `ExceptionStateID`, `Level`, `Enabled`, `Cost`, `Achievement`, `X`, `Y`) VALUES
(1, 28, 'T1', 'New transition', 'Simple', 1, 0, 0, 0, 0, 0, 0, 313, 159),
(2, 28, 'T2', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 320, 230),
(3, 28, 'T3', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 317, 313),
(5, 29, 'LR in Trust Models', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 115, 357),
(6, 29, 'LR in Trust-based Decision Making', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 117, 424),
(7, 29, 'LR in Goal Generation and Planning', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 136, 498),
(8, 29, 'LR in outcome based trust evaluation', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 138, 589),
(9, 30, 'Look for Sellers', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 144, 186),
(10, 30, 'Rank by Price', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 202, 350),
(11, 30, 'Select Best Seller', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 346, 341),
(12, 30, 'Start Buying', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 502, 214),
(13, 30, 'Save to Log', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 844, 275),
(14, 30, 'Make Down Payment', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 413, 546),
(15, 30, 'Receiving Item', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 518, 398),
(16, 30, 'Make Full Payment', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 665, 426),
(17, 30, 'Timeout', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 654, 570),
(18, 30, 'T0', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 244, 621),
(20, 30, 'Update Trust Belief', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 791, 320),
(21, 30, 'Evaluate Outcome', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 675, 293),
(22, 30, 'Process Rating Request', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 196, 107),
(23, 30, 'Send Rating Back', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 594, 114),
(24, 30, 'Forward Request', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 591, 160),
(25, 30, 'Send ', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 593, 65),
(26, 32, 'T26', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 186, 134),
(27, 32, 'T27', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 451, 188),
(28, 32, 'T28', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 626, 223),
(29, 32, 'T29', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 741, 340),
(30, 32, 'T30', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 369, 65),
(31, 32, 'T31', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 139, 212),
(33, 32, 'T33', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 208, 479),
(34, 32, 'T34', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 264, 434),
(39, 33, 'start driver agent', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 213, 187),
(43, 33, 'start the car', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 99, 519),
(44, 33, 'brake', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 297, 493),
(46, 33, 'end scene', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 654, 147),
(47, 33, 'start police/witness/mechanic agents', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 674, 209),
(50, 33, 'check front', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 171, 475),
(51, 33, 'check car position', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 327, 247),
(53, 33, 'check car/boy status', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 471, 237),
(54, 33, 'start boy agent', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 431, 93),
(56, 34, 'check car proximity', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 214, 145),
(57, 34, 'cross the road', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 368, 135),
(62, 35, 'compute new car position', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 150, 275),
(63, 35, 'update car position', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 212, 199),
(64, 33, 'check environment status', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 438, 510),
(65, 33, 'play scripted dialogues', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 686, 530),
(66, 33, 'stop', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 614, 459),
(67, 37, 'T67', 'New transition', 'Simple', 2, 0, 0, 0, 0, 0, 0, 216, 257),
(68, 37, 'T68', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 530, 259),
(69, 38, 'T69', 'New transition', 'Reasoning', -1, 0, 0, 0, 0, 0, 0, 260, 225),
(70, 38, 'T70', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 381, 290),
(71, 38, 'T71', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 548, 301),
(72, 38, 'T72', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 677, 262),
(73, 39, 'Move to Patient 1', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 68, 218),
(74, 39, 'Examine Patien 1/Check Player Proximity', 'New transition', 'Reasoning', -1, 0, 0, 0, 0, 0, 0, 268, 96),
(75, 39, 'Move to Desk', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 494, 135),
(76, 39, 'Show Scripted Dialogue', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 230, 289),
(78, 39, 'Check Player Proximity', 'New transition', 'Reasoning', -1, 0, 0, 0, 0, 0, 0, 541, 232),
(79, 40, 'T79', 'New transition', 'Simple', 3, 0, 0, 0, 0, 0, 0, 399, 274),
(80, 41, 'T80', 'New transition', 'Simple', 4, 0, 0, 0, 0, 0, 0, 191, 79),
(81, 41, 'T81', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 404, 192),
(82, 41, 'T82', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 520, 193),
(83, 41, 'T83', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 221, 277),
(84, 41, 'T84', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 412, 276),
(85, 41, 'T85', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 245, 456),
(86, 41, 'T86', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 633, 459),
(87, 41, 'T87', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 442, 592),
(88, 40, 'T88', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 401, 467),
(89, 40, 'T89', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 474, 349),
(90, 42, 'T0', 'New transition', 'Simple', 5, 0, 0, 0, 0, 0, 0, 598, 202),
(91, 42, 'T0', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 607, 133),
(92, 44, 'T92', 'New transition', 'Simple', 6, 0, 0, 0, 0, 0, 0, 228, 201),
(93, 44, 'T93', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 418, 201),
(94, 45, 'Activate Teacher Agent', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 102, 290),
(96, 45, 'Monitor Teacher Progress', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 246, 210),
(98, 45, 'Unlock Micro-world', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 407, 248),
(99, 45, 'Monitor Student Location', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 535, 214),
(101, 45, 'Activate Teachable Agent', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 570, 333),
(102, 45, 'T0', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 860, 323),
(103, 46, 'T103', 'New transition', 'Simple', 7, 0, 0, 0, 0, 0, 0, 403, 313),
(104, 46, 'T104', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 656, 311),
(105, 46, 'T105', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 411, 415),
(106, 46, 'T106', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 690, 405),
(107, 46, 'T107', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 572, 232),
(108, 45, 'Monitor Student Location', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 66, 214),
(109, 47, 'T0', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 265, 333),
(110, 47, 'T0', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 314, 241),
(111, 47, 'T0', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 462, 245),
(112, 48, 'Receive Rules from Students', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 277, 270),
(113, 48, 'Check Rule Coherence', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 320, 419),
(114, 48, 'Raise Questions', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 378, 333),
(115, 48, 'T0', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 517, 289),
(116, 49, 'T116', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 585, 309),
(117, 50, 'Create Pig A', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 149, 227),
(118, 50, 'Create Pig B', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 156, 389),
(119, 50, 'Create Pig C', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 158, 574),
(120, 50, 'Gather Straws', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 182, 334),
(121, 50, 'Build Roof', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 289, 336),
(122, 50, 'Build Walls', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 410, 337),
(123, 50, 'Assemble House', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 514, 338),
(124, 50, 'Gather Sticks', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 191, 498),
(125, 50, 'Build Roof', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 293, 499),
(126, 50, 'Build Walls', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 407, 498),
(127, 50, 'Assemble House', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 512, 499),
(128, 50, 'Gather Bricks', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 203, 727),
(129, 50, 'Build Roof', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 311, 729),
(130, 50, 'Build Walls & Chimney', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 420, 729),
(131, 50, 'Assemble House', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 526, 713),
(132, 50, 'Pig A moves into Straw House', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 485, 227),
(133, 50, 'Pig B moves into Stick House', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 493, 389),
(134, 50, 'Pig C moves into Brick House', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 530, 574),
(135, 50, 'Wolf Puffs down Straw House', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 732, 249),
(136, 50, 'Wolf Puffs down Stick House', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 826, 347),
(137, 50, 'Wolf Puffs at Brick House', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 833, 456),
(138, 50, 'Wolf climbs down Chimney', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 725, 577),
(139, 50, 'Pig C lights a fire', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 863, 558),
(140, 50, 'Pig C eats Wolf BBQ', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 952, 643),
(141, 51, 'Create Pig A', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 100, 142),
(142, 51, 'Create Pig B', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 100, 194),
(143, 51, 'Create Pig C', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 100, 241),
(144, 51, 'Check Houses', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 236, 141),
(145, 51, 'Create Wolf', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 416, 154),
(146, 51, 'Check Wolf & Pigs Status', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 538, 160),
(147, 51, 'Display End Scene', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 736, 178),
(148, 52, 'Gather Materials', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 90, 218),
(149, 52, 'Build Roof', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 176, 338),
(150, 52, 'Build Walls & Chimney', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 262, 220),
(151, 52, 'Assemble House', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 389, 283),
(152, 52, 'Stay in the House', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 505, 205),
(154, 52, 'Speak with Wolf', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 575, 359),
(155, 52, 'Run Away', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 818, 361),
(156, 52, 'Final Processing', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 952, 257),
(157, 53, 'Searches for pigs', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 59, 359),
(158, 53, 'Speaks to Pig', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 263, 272),
(159, 53, 'Puffs at the House', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 416, 313),
(160, 53, 'Chases Pig', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 408, 90),
(161, 53, 'Eats Pig', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 442, 196),
(162, 53, 'Climbs Chimney', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 611, 264),
(163, 53, 'Flee', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 764, 283),
(165, 54, 'T165', 'New transition', 'Simple', 8, 0, 0, 0, 0, 0, 0, 137, 182),
(167, 51, 'Backtrack', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 230, 280),
(168, 51, 'Proceed', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 354, 307),
(169, 51, 'Backtrack', 'Backtrack', 'Simple', -1, 0, 0, 0, 0, 0, 0, 502, 270),
(170, 51, 'Proceed', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 647, 273),
(171, 52, 'Backtrack', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 641, 424),
(172, 52, 'Proceed (House Tumbles)', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 720, 381),
(173, 52, 'Proceed (House Withstands)', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 785, 171),
(174, 52, 'Get House Status', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 634, 177),
(175, 53, 'Keeps Chasing', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 439, 134),
(177, 53, 'Ends Chase', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 465, 248),
(178, 53, 'Keeps Searching', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 135, 301),
(179, 53, 'Proceed', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 252, 376),
(180, 55, 'Check User Status/Input', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 69, 376),
(181, 55, 'Record Associative Info', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 216, 328),
(182, 55, 'Return', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 115, 322),
(183, 55, 'Offer Level 1 Hint', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 335, 362),
(184, 55, 'Return', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 299, 298),
(185, 55, 'Check User Status/Input', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 469, 398),
(186, 55, 'Offer Level 2 Hint', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 532, 335),
(187, 55, 'Check User Status/Input', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 662, 398),
(188, 55, 'Return', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 506, 263),
(190, 55, 'Return', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 603, 227),
(191, 55, 'Offer Level 3 Hint', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 690, 274),
(192, 56, 'T192', 'New transition', 'Simple', 9, 0, 0, 0, 0, 0, 0, 326, 269),
(193, 56, 'T193', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 345, 399),
(194, 56, 'T194', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 563, 308),
(195, 56, 'T195', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 560, 415),
(196, 57, 'T196', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 437, 135),
(197, 57, 'T197', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 190, 282),
(198, 57, 'T198', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 328, 283),
(199, 57, 'T199', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 423, 287),
(200, 57, 'T200', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 533, 296),
(201, 57, 'T201', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 649, 297),
(202, 57, 'T202', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 433, 91),
(203, 57, 'T203', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 179, 230),
(204, 57, 'T204', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 277, 231),
(205, 57, 'T205', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 378, 232),
(206, 57, 'T206', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 481, 238),
(207, 57, 'T207', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 594, 239),
(208, 57, 'T208', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 198, 343),
(209, 57, 'T209', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 354, 352),
(210, 57, 'T210', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 504, 351),
(211, 57, 'T211', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 637, 354),
(212, 57, 'T212', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 423, 494),
(213, 57, 'T213', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 656, 507),
(214, 57, 'T214', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 447, 584),
(215, 57, 'T215', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 449, 631),
(216, 57, 'T216', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 414, 31),
(217, 57, 'T217', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 631, 68),
(218, 57, 'T218', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 520, 712),
(219, 57, 'T219', 'New transition', 'Simple', -1, 0, 0, 0, 0, 0, 0, 631, 686);

-- --------------------------------------------------------

--
-- Table structure for table `usergroup`
--

DROP TABLE IF EXISTS `usergroup`;
CREATE TABLE IF NOT EXISTS `usergroup` (
`ID` bigint(20) unsigned NOT NULL,
  `Name` varchar(50) NOT NULL COMMENT 'User group name.',
  `Desc` varchar(255) DEFAULT NULL COMMENT 'User group description.'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usergroup`
--

INSERT INTO `usergroup` (`ID`, `Name`, `Desc`) VALUES
(1, 'Guest (Read Only)', 'Guest User Group ( Can view all goal net designs )'),
(2, 'Super User', 'Read/Write to all goal net designs'),
(3, 'Administrator', 'Read/Write to all goal net designs. Receive emails for admin related problems.');

-- --------------------------------------------------------

--
-- Table structure for table `usergroup_gnet`
--

DROP TABLE IF EXISTS `usergroup_gnet`;
CREATE TABLE IF NOT EXISTS `usergroup_gnet` (
  `UGID` bigint(20) unsigned NOT NULL COMMENT 'User group ID.',
  `GID` bigint(20) NOT NULL COMMENT 'Goal Net ID',
  `Read` tinyint(1) NOT NULL COMMENT 'Read privilege',
  `Writing` tinyint(1) NOT NULL COMMENT 'Writing privilege.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
`ID` bigint(20) unsigned NOT NULL COMMENT 'User ID',
  `Email` varchar(50) NOT NULL COMMENT 'User name, must be unique',
  `Password` varchar(20) NOT NULL COMMENT 'User password',
  `Question` varchar(255) NOT NULL COMMENT 'Secret question (for password retrieval).',
  `Answer` varchar(255) NOT NULL COMMENT 'Answer to secret question.'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID`, `Email`, `Password`, `Question`, `Answer`) VALUES
(1, 'yuha0008@ntu.edu.sg', '19820205', 'who are you?', 'me too'),
(2, 'guest@gnet.org', 'friend', 'how are you?', 'me too');

-- --------------------------------------------------------

--
-- Table structure for table `user_usergroup`
--

DROP TABLE IF EXISTS `user_usergroup`;
CREATE TABLE IF NOT EXISTS `user_usergroup` (
  `UID` bigint(20) unsigned NOT NULL COMMENT 'User ID',
  `GID` bigint(20) unsigned NOT NULL COMMENT 'Group ID',
  `IsAdmin` bigint(20) unsigned NOT NULL COMMENT 'True: this user is the administrator of this group; False: otherwise.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_usergroup`
--

INSERT INTO `user_usergroup` (`UID`, `GID`, `IsAdmin`) VALUES
(2, 1, 0),
(1, 3, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `arc`
--
ALTER TABLE `arc`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `function`
--
ALTER TABLE `function`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `gnet`
--
ALTER TABLE `gnet`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `property`
--
ALTER TABLE `property`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `state`
--
ALTER TABLE `state`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tasklist`
--
ALTER TABLE `tasklist`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `transition`
--
ALTER TABLE `transition`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `usergroup`
--
ALTER TABLE `usergroup`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `arc`
--
ALTER TABLE `arc`
MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID of an Arc',AUTO_INCREMENT=11112;
--
-- AUTO_INCREMENT for table `function`
--
ALTER TABLE `function`
MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=72;
--
-- AUTO_INCREMENT for table `gnet`
--
ALTER TABLE `gnet`
MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=58;
--
-- AUTO_INCREMENT for table `property`
--
ALTER TABLE `property`
MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `state`
--
ALTER TABLE `state`
MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=352;
--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `tasklist`
--
ALTER TABLE `tasklist`
MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `transition`
--
ALTER TABLE `transition`
MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=220;
--
-- AUTO_INCREMENT for table `usergroup`
--
ALTER TABLE `usergroup`
MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
MODIFY `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'User ID',AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
