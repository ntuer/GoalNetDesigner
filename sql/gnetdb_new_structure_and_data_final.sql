-- phpMyAdmin SQL Dump
-- version 4.2.8.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 09, 2015 at 10:53 AM
-- Server version: 5.5.40
-- PHP Version: 5.4.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `gnetdb_new`
--
CREATE DATABASE IF NOT EXISTS `gnetdb_new` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `gnetdb_new`;

-- --------------------------------------------------------

--
-- Table structure for table `action_log`
--

DROP TABLE IF EXISTS `action_log`;
CREATE TABLE IF NOT EXISTS `action_log` (
  `UserID` char(36) NOT NULL COMMENT 'User of this action',
  `ID` char(36) NOT NULL COMMENT 'Unique Identifier',
  `Action` varchar(20) NOT NULL COMMENT 'Create, Update, Delete',
  `GNetID` char(36) DEFAULT NULL COMMENT 'Action performed on GNet',
  `OperationTargetObject` varchar(20) DEFAULT NULL COMMENT 'Target object of this action: arc/transition/state/task/function',
  `TargetObjectID` char(36) DEFAULT NULL COMMENT 'Target object''s ID',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Time of action'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Record user behaviors for analytics';

--
-- Dumping data for table `action_log`
--

INSERT INTO `action_log` (`UserID`, `ID`, `Action`, `GNetID`, `OperationTargetObject`, `TargetObjectID`, `Timestamp`) VALUES
('yuhan', '25987725-5f3f-45d6-8852-2e15d7eb610e', 'Create', '445b84d0-096e-4ea8-9939-76e40b07cdb0', 'Arc', 'ffa5e8ac-2671-4581-927c-4a245e8fd25e', '2015-03-09 06:49:06'),
('yuhan', '35ecfed1-28a7-4c26-8a1c-9a9205646366', 'Create', '445b84d0-096e-4ea8-9939-76e40b07cdb0', 'Arc', '5ef426fd-e797-4e53-ba47-fc56470b628a', '2015-03-09 06:49:02'),
('yuhan', 'b5ee4249-692c-4b78-a565-d722bc3cebd3', 'Create', '445b84d0-096e-4ea8-9939-76e40b07cdb0', 'Arc', '350b3f75-198d-46b7-ab95-5e24f1b98569', '2015-03-09 06:49:20');

-- --------------------------------------------------------

--
-- Table structure for table `arc`
--

DROP TABLE IF EXISTS `arc`;
CREATE TABLE IF NOT EXISTS `arc` (
  `ID` char(36) NOT NULL COMMENT 'ID of an Arc',
  `GNetID` char(36) NOT NULL COMMENT 'GoalNetID of the goal net it belongs to.',
  `Name` varchar(50) NOT NULL COMMENT 'Name of the arc',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Description of the arc',
  `Direction` tinyint(1) NOT NULL COMMENT 'Yes means the arc is from a state to a transition, no means otherwise',
  `IsDirect` tinyint(1) NOT NULL COMMENT 'Whether the arc is a direct arc or a parallel one',
  `InputID` char(36) DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0' COMMENT 'Input entity id',
  `OutputID` char(36) DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0' COMMENT 'Output entity id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arc`
--

INSERT INTO `arc` (`ID`, `GNetID`, `Name`, `Description`, `Direction`, `IsDirect`, `InputID`, `OutputID`) VALUES
('0d3209c2-4d08-4287-ad2b-807cf5002cea', 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', 'Arc', NULL, 0, 1, '41b8a9cc-4741-43e3-a4e0-ffe09d3575c0', 'a95cd2fd-85a7-4402-ad52-2bcded916aa0'),
('177931f6-48a7-4e9a-ab93-46d4f6a84cc3', 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', 'Arc', NULL, 0, 1, '41b8a9cc-4741-43e3-a4e0-ffe09d3575c0', '18ec018c-bf16-46cf-af68-ac3d219b5aca'),
('350b3f75-198d-46b7-ab95-5e24f1b98569', '445b84d0-096e-4ea8-9939-76e40b07cdb0', 'Arc', NULL, 1, 1, '5c4ace4b-afd1-4d7c-a6dc-40921a578687', '24a1b01f-3f7e-4d76-aeb7-607a4f5df5a6'),
('5ef426fd-e797-4e53-ba47-fc56470b628a', '445b84d0-096e-4ea8-9939-76e40b07cdb0', 'Arc', NULL, 1, 1, '7b9c5d3b-c7ad-41c9-98d3-f43e21597f24', '24a1b01f-3f7e-4d76-aeb7-607a4f5df5a6'),
('b4cc1fa8-0a86-4f13-8dc7-031f8953bd01', 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', 'Arc', NULL, 0, 1, 'f40ab67b-3aea-4448-8e44-e84f6c38035b', 'a95cd2fd-85a7-4402-ad52-2bcded916aa0'),
('d1245265-4420-4745-9677-0cf9a07a5a99', '445b84d0-096e-4ea8-9939-76e40b07cdb0', 'Arc', NULL, 1, 1, '7b9c5d3b-c7ad-41c9-98d3-f43e21597f24', '9cab4c9c-457d-4616-9d63-de4e8580216b'),
('e3ff49b2-5fb7-4ef0-b4a2-8b4fa492d470', '445b84d0-096e-4ea8-9939-76e40b07cdb0', 'Arc', NULL, 0, 1, '9cab4c9c-457d-4616-9d63-de4e8580216b', '5c4ace4b-afd1-4d7c-a6dc-40921a578687'),
('e5247b50-a8d0-4914-8c7a-c7b916224bf4', 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', 'Arc', NULL, 1, 1, '519f007c-0e77-4ed3-8385-608f0785173a', '41b8a9cc-4741-43e3-a4e0-ffe09d3575c0'),
('e7b49900-18fb-4919-9a91-b5f6c3673cc3', 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', 'Arc', NULL, 1, 1, 'dbdf395a-402b-4e98-b8a9-45cd65580eb4', 'f40ab67b-3aea-4448-8e44-e84f6c38035b'),
('ffa5e8ac-2671-4581-927c-4a245e8fd25e', '445b84d0-096e-4ea8-9939-76e40b07cdb0', 'Arc', NULL, 0, 1, '24a1b01f-3f7e-4d76-aeb7-607a4f5df5a6', '5c5d8ddf-67c6-4b24-b1c6-e73b3acb0ae6');

-- --------------------------------------------------------

--
-- Table structure for table `feedback_log`
--

DROP TABLE IF EXISTS `feedback_log`;
CREATE TABLE IF NOT EXISTS `feedback_log` (
  `ID` char(36) NOT NULL,
  `QuestionID` char(36) NOT NULL,
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UserID` char(36) NOT NULL,
  `Version` varchar(20) NOT NULL,
  `Answer` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `feedback_log`
--

INSERT INTO `feedback_log` (`ID`, `QuestionID`, `Timestamp`, `UserID`, `Version`, `Answer`) VALUES
('34a917e7-dbcf-4497-b52f-461950ff3dab', '2d7a8182-b8e0-11e4-b791-002556c2fbd9', '2015-02-20 09:40:09', 'lisiyao', '1.0', ''),
('abce0a99-7bfe-479a-bf30-8d7de93b2e17', '2d7ab880-b8e0-11e4-b791-002556c2fbd9', '2015-02-20 09:40:09', 'lisiyao', '1.0', '');

-- --------------------------------------------------------

--
-- Table structure for table `gnet`
--

DROP TABLE IF EXISTS `gnet`;
CREATE TABLE IF NOT EXISTS `gnet` (
  `ID` char(36) NOT NULL,
  `Name` varchar(50) NOT NULL COMMENT 'Name of the goal net',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Description of the goal net',
  `StateCount` bigint(20) unsigned DEFAULT '0' COMMENT 'total number of states in the goal net',
  `TransitionCount` bigint(20) unsigned DEFAULT '0' COMMENT 'total number of transition in the goal net.',
  `StartStateID` char(36) DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0' COMMENT 'start state id',
  `EndStateID` char(36) DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0' COMMENT 'end state id',
  `RootID` char(36) DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0' COMMENT 'root state id',
  `GoalSelectionType` tinyint(1) DEFAULT '0' COMMENT 'True if fast goal selection is required. False to select the best goal',
  `IsOpen` tinyint(1) DEFAULT '0' COMMENT 'Mutex lock to ensure one goal net is not simultaneously being edited by more than one user. Yes means it is being edited.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gnet`
--

INSERT INTO `gnet` (`ID`, `Name`, `Description`, `StateCount`, `TransitionCount`, `StartStateID`, `EndStateID`, `RootID`, `GoalSelectionType`, `IsOpen`) VALUES
('445b84d0-096e-4ea8-9939-76e40b07cdb0', 'GTransition', '', NULL, NULL, '7b9c5d3b-c7ad-41c9-98d3-f43e21597f24', '5c4ace4b-afd1-4d7c-a6dc-40921a578687', '5c5d8ddf-67c6-4b24-b1c6-e73b3acb0ae6', 0, 0),
('acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', 'GTest', '', NULL, NULL, NULL, NULL, NULL, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `method`
--

DROP TABLE IF EXISTS `method`;
CREATE TABLE IF NOT EXISTS `method` (
  `ID` char(36) NOT NULL,
  `Alias` varchar(50) DEFAULT NULL COMMENT 'Alias of the function',
  `FileName` varchar(255) NOT NULL COMMENT 'Dll filename from which the function comes',
  `RTType` varchar(50) NOT NULL DEFAULT 'void' COMMENT 'return type of the function',
  `Name` varchar(50) NOT NULL COMMENT 'function name',
  `Params` varchar(500) DEFAULT NULL COMMENT 'parameters of the function in sequence separated by semicolons, eg int,float,string,char',
  `PValues` varchar(500) DEFAULT NULL COMMENT 'Values of each parameter in the same sequence.',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Description for the function',
  `GNetID` varchar(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `method`
--

INSERT INTO `method` (`ID`, `Alias`, `FileName`, `RTType`, `Name`, `Params`, `PValues`, `Description`, `GNetID`) VALUES
('41b9a62e-0f59-440d-bebd-37b6eae541a1', NULL, 'filename', 'void', 'F4', NULL, NULL, NULL, '445b84d0-096e-4ea8-9939-76e40b07cdb0'),
('475e8eb3-1dda-49e5-af1b-7d9c6d0abfc5', NULL, 'filename', 'void', 'F5', NULL, NULL, NULL, '445b84d0-096e-4ea8-9939-76e40b07cdb0'),
('737a863b-9a77-11e4-a7a6-002556c2fbd9', NULL, '', 'void', 'function1', NULL, NULL, NULL, '445b84d0-096e-4ea8-9939-76e40b07cdb0'),
('737aabc5-9a77-11e4-a7a6-002556c2fbd9', NULL, '', 'void', 'Function2', NULL, NULL, NULL, '445b84d0-096e-4ea8-9939-76e40b07cdb0'),
('7870c1fa-95ff-487b-a02d-15a7390e333f', NULL, 'filename2', 'void', 'function 3', NULL, NULL, NULL, '445b84d0-096e-4ea8-9939-76e40b07cdb0'),
('7d5051ff-98e3-42a5-b560-568950fad2ff', NULL, 'filename', 'void', 'F4', NULL, NULL, NULL, 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a'),
('db8e6c17-164f-4a1a-aae2-fbf5fad8006d', NULL, 'filename2', 'void', 'function 3', NULL, NULL, NULL, 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a');

-- --------------------------------------------------------

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
CREATE TABLE IF NOT EXISTS `property` (
  `ID` char(20) NOT NULL,
  `ParentID` bigint(20) NOT NULL COMMENT 'Id of the entity that the property belongs to.',
  `Type` varchar(50) NOT NULL COMMENT 'Data type',
  `Name` varchar(50) NOT NULL COMMENT 'Name of the property',
  `Val` varchar(255) DEFAULT NULL COMMENT 'Value of the property, to be converted to Type in the program',
  `ST` tinyint(1) NOT NULL COMMENT 'Yes means the parent entity is a State, no means it is a transition'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `ID` char(36) NOT NULL COMMENT 'Question ID',
  `Body` text NOT NULL COMMENT 'Question body'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`ID`, `Body`) VALUES
('2d7a8182-b8e0-11e4-b791-002556c2fbd9', 'Do you think this designer is easy to use?'),
('2d7ab880-b8e0-11e4-b791-002556c2fbd9', 'Do you think this designer is problematic?');

-- --------------------------------------------------------

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
CREATE TABLE IF NOT EXISTS `state` (
  `ID` char(36) NOT NULL,
  `GNetID` char(36) DEFAULT NULL COMMENT 'GoalNetID of the goal net it belongs to',
  `ParentGNetID` char(36) DEFAULT NULL COMMENT 'Parent State ID of this state (>=1). Value may be invalid since only the start and end state of a sub goal net has a parent.',
  `Name` varchar(50) NOT NULL COMMENT 'Name of the state',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Description of the state',
  `Composite` tinyint(1) NOT NULL COMMENT 'Indicating whether the state is composite(Yes) or atomic(No)',
  `Cost` int(11) DEFAULT '0' COMMENT 'Cost of achieving the state',
  `Achievement` int(11) DEFAULT '0' COMMENT 'The benefit of achieving the state',
  `SubGNetStartID` char(36) DEFAULT NULL COMMENT 'State goal id if it is a composite state',
  `SubGNetEndID` char(36) DEFAULT NULL COMMENT 'End goal id if it is a composite state',
  `Token` bigint(20) DEFAULT '0' COMMENT 'Whether there is a token and the value of the token. no token means 0.',
  `X` int(11) NOT NULL COMMENT 'X coordinate of the state on the canvas',
  `Y` int(11) NOT NULL COMMENT 'Y coordinate of the state on the canvas'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `state`
--

INSERT INTO `state` (`ID`, `GNetID`, `ParentGNetID`, `Name`, `Description`, `Composite`, `Cost`, `Achievement`, `SubGNetStartID`, `SubGNetEndID`, `Token`, `X`, `Y`) VALUES
('0dfee222-c88c-49f7-a6d0-56ca1a5deef1', 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', NULL, 'State', NULL, 1, 0, 0, '9f041653-25be-4975-b68b-6d1638a4e462', 'a95cd2fd-85a7-4402-ad52-2bcded916aa0', NULL, 118, 297),
('18ec018c-bf16-46cf-af68-ac3d219b5aca', 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', NULL, 'State2', NULL, 0, 0, 0, NULL, NULL, NULL, 367, 94),
('519f007c-0e77-4ed3-8385-608f0785173a', 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', NULL, 'State1', NULL, 0, 0, 0, NULL, NULL, NULL, 69, 141),
('5c4ace4b-afd1-4d7c-a6dc-40921a578687', '445b84d0-096e-4ea8-9939-76e40b07cdb0', NULL, 'State2', NULL, 0, 0, 0, NULL, NULL, NULL, 225, 265),
('5c5d8ddf-67c6-4b24-b1c6-e73b3acb0ae6', '445b84d0-096e-4ea8-9939-76e40b07cdb0', NULL, 'State', NULL, 0, 0, 0, NULL, NULL, NULL, 501, 265),
('7b9c5d3b-c7ad-41c9-98d3-f43e21597f24', '445b84d0-096e-4ea8-9939-76e40b07cdb0', NULL, 'State1', NULL, 0, 0, 0, NULL, NULL, NULL, 66, 103),
('9f041653-25be-4975-b68b-6d1638a4e462', 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', NULL, 'State1n', NULL, 0, 0, 0, NULL, NULL, NULL, 25, 225),
('a95cd2fd-85a7-4402-ad52-2bcded916aa0', 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', NULL, 'State2n', NULL, 0, 0, 0, NULL, NULL, NULL, 380, 238),
('dbdf395a-402b-4e98-b8a9-45cd65580eb4', 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', NULL, 'State', NULL, 0, 0, 0, NULL, NULL, NULL, 77, 366);

-- --------------------------------------------------------

--
-- Table structure for table `state_function`
--

DROP TABLE IF EXISTS `state_function`;
CREATE TABLE IF NOT EXISTS `state_function` (
  `StateID` char(36) NOT NULL COMMENT 'State ID',
  `FunctionID` char(36) NOT NULL COMMENT 'Function ID',
  `Sequence` int(10) unsigned NOT NULL COMMENT 'The sequence number of the function in the current state, starting from 1.',
  `Arguments` varchar(500) DEFAULT NULL COMMENT 'Function arguments separated by ";"'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `state_function`
--

INSERT INTO `state_function` (`StateID`, `FunctionID`, `Sequence`, `Arguments`) VALUES
('18ec018c-bf16-46cf-af68-ac3d219b5aca', '737aabc5-9a77-11e4-a7a6-002556c2fbd9', 1, NULL),
('519f007c-0e77-4ed3-8385-608f0785173a', '737a863b-9a77-11e4-a7a6-002556c2fbd9', 1, NULL),
('519f007c-0e77-4ed3-8385-608f0785173a', '737aabc5-9a77-11e4-a7a6-002556c2fbd9', 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE IF NOT EXISTS `task` (
  `ID` char(36) NOT NULL,
  `Name` varchar(50) NOT NULL COMMENT 'Task name',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Task description',
  `Composite` tinyint(1) DEFAULT '0' COMMENT 'whether this task is composed of other tasks',
  `ClassName` varchar(50) DEFAULT NULL COMMENT 'For a non-composite task, the class name must be given so that process unit knows whether to load this task.',
  `ChildrenTaskCount` int(11) DEFAULT '0' COMMENT 'total number of children task for a composite task',
  `ChildTaskID` char(36) DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0' COMMENT 'the child task ID',
  `Cost` int(11) DEFAULT '0' COMMENT 'Cost of execuing the task.',
  `Achievement` int(11) DEFAULT '0' COMMENT 'Achievement after executing the task.',
  `GNetID` varchar(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`ID`, `Name`, `Description`, `Composite`, `ClassName`, `ChildrenTaskCount`, `ChildTaskID`, `Cost`, `Achievement`, `GNetID`) VALUES
('9158dc04-d18d-4e28-b73a-840227a789e7', 'Task2', NULL, 0, NULL, 0, NULL, 0, 0, '445b84d0-096e-4ea8-9939-76e40b07cdb0'),
('aeb6ab9d-f3a1-49cd-a984-804270c4e73f', 'Task1', NULL, 0, NULL, 0, NULL, 0, 0, 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a');

-- --------------------------------------------------------

--
-- Table structure for table `tasklist`
--

DROP TABLE IF EXISTS `tasklist`;
CREATE TABLE IF NOT EXISTS `tasklist` (
  `ID` char(36) NOT NULL,
  `Name` varchar(50) NOT NULL COMMENT 'Tasklist name',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Tasklist Description'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tasklist`
--

INSERT INTO `tasklist` (`ID`, `Name`, `Description`) VALUES
('e37f6ccf-d603-4495-9965-c7ec2b2fc07c', 'New tasklist', NULL),
('f001660b-df05-4f04-9f9f-381fcc3dfc04', 'New tasklist', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tasklist_task`
--

DROP TABLE IF EXISTS `tasklist_task`;
CREATE TABLE IF NOT EXISTS `tasklist_task` (
  `TaskListID` char(36) NOT NULL COMMENT 'ID of the tasklist',
  `TaskID` char(36) NOT NULL COMMENT 'ID of the task',
  `Sequence` int(10) unsigned NOT NULL COMMENT 'sequence number of the task in the tasklist, starts from 1.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `task_function`
--

DROP TABLE IF EXISTS `task_function`;
CREATE TABLE IF NOT EXISTS `task_function` (
  `TaskID` char(36) NOT NULL COMMENT 'Task ID',
  `FunctionID` char(36) NOT NULL COMMENT 'Function ID',
  `Sequence` int(10) unsigned NOT NULL COMMENT 'Sequence number of the function in the task, starts from 1.',
  `Arguments` varchar(500) DEFAULT NULL COMMENT 'Function arguments separated by ";"'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `task_function`
--

INSERT INTO `task_function` (`TaskID`, `FunctionID`, `Sequence`, `Arguments`) VALUES
('9158dc04-d18d-4e28-b73a-840227a789e7', '41b9a62e-0f59-440d-bebd-37b6eae541a1', 1, NULL),
('aeb6ab9d-f3a1-49cd-a984-804270c4e73f', 'db8e6c17-164f-4a1a-aae2-fbf5fad8006d', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `transition`
--

DROP TABLE IF EXISTS `transition`;
CREATE TABLE IF NOT EXISTS `transition` (
  `ID` char(36) NOT NULL,
  `GNetID` char(36) NOT NULL COMMENT 'GoalNetID of the goal net it belongs to',
  `Name` varchar(50) NOT NULL COMMENT 'Name of the transition',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Description of the transition',
  `Type` varchar(50) NOT NULL COMMENT 'Type of the transition: Simple, Reasoning...',
  `TaskListID` char(36) DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0' COMMENT 'ID of its TaskList',
  `TaskCount` bigint(20) DEFAULT '0' COMMENT 'Not used currently',
  `ExceptionStateID` int(11) DEFAULT '0' COMMENT 'Not used currently',
  `Level` int(11) DEFAULT '0' COMMENT 'Not used currently',
  `Enabled` tinyint(1) DEFAULT '0' COMMENT 'Indicate if the transition is enabled',
  `Cost` int(11) DEFAULT '0' COMMENT 'Cost of going through the transition',
  `Achievement` int(11) DEFAULT '0' COMMENT 'Achievement of going through the transition',
  `X` int(11) NOT NULL COMMENT 'X coordinate of the transition on the canvas',
  `Y` int(11) NOT NULL COMMENT 'Y coordinate of the transition on the canvas'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `transition`
--

INSERT INTO `transition` (`ID`, `GNetID`, `Name`, `Description`, `Type`, `TaskListID`, `TaskCount`, `ExceptionStateID`, `Level`, `Enabled`, `Cost`, `Achievement`, `X`, `Y`) VALUES
('24a1b01f-3f7e-4d76-aeb7-607a4f5df5a6', '445b84d0-096e-4ea8-9939-76e40b07cdb0', 'Transition', NULL, 'reasoning', NULL, NULL, 0, 0, 0, 0, 0, 313, 54),
('41b8a9cc-4741-43e3-a4e0-ffe09d3575c0', 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', 'Transition1', NULL, 'simple', 'f001660b-df05-4f04-9f9f-381fcc3dfc04', NULL, 0, 0, 0, 0, 0, 224, 209),
('8bf42759-a4ac-4807-bb39-3da6c5715720', 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', 'Transition', NULL, 'simple', NULL, NULL, 0, 0, 0, 0, 0, 309, 496),
('9cab4c9c-457d-4616-9d63-de4e8580216b', '445b84d0-096e-4ea8-9939-76e40b07cdb0', 'Transition1', NULL, 'simple', NULL, NULL, 0, 0, 0, 0, 0, 23, 349),
('f40ab67b-3aea-4448-8e44-e84f6c38035b', 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', 'Transition', NULL, 'simple', NULL, NULL, 0, 0, 0, 0, 0, 263, 353);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `ID` char(36) NOT NULL COMMENT 'User ID',
  `Email` varchar(50) NOT NULL COMMENT 'User name, must be unique',
  `Password` varchar(20) NOT NULL COMMENT 'User password',
  `Question` varchar(255) NOT NULL COMMENT 'Secret question (for password retrieval).',
  `Answer` varchar(255) NOT NULL COMMENT 'Answer to secret question.',
  `Age` int(11) NOT NULL,
  `EducationLevel` enum('High School and Below','Undergraduate','Graduate') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID`, `Email`, `Password`, `Question`, `Answer`, `Age`, `EducationLevel`) VALUES
('lisiyao', 'lisi0010@e.ntu.edu.sg', 'lisiyao', 'lisiyao', 'lisiyao', 0, 'High School and Below'),
('yuhan', 'yuhan@ntu.edu.sg', 'yuhan', 'yuhan', 'yuhan', 0, 'High School and Below');

-- --------------------------------------------------------

--
-- Table structure for table `user_gnet`
--

DROP TABLE IF EXISTS `user_gnet`;
CREATE TABLE IF NOT EXISTS `user_gnet` (
  `UserID` varchar(36) NOT NULL,
  `GNetID` varchar(36) NOT NULL,
  `AccessLevel` enum('Read','Write','Admin') NOT NULL DEFAULT 'Read'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_gnet`
--

INSERT INTO `user_gnet` (`UserID`, `GNetID`, `AccessLevel`) VALUES
('lisiyao', '445b84d0-096e-4ea8-9939-76e40b07cdb0', 'Admin'),
('lisiyao', 'acc8e102-c71b-4487-8b5e-8d15b5bbcd7a', 'Admin'),
('yuhan', '445b84d0-096e-4ea8-9939-76e40b07cdb0', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `action_log`
--
ALTER TABLE `action_log`
 ADD PRIMARY KEY (`ID`), ADD KEY `UserID` (`UserID`), ADD KEY `GNetID` (`GNetID`);

--
-- Indexes for table `arc`
--
ALTER TABLE `arc`
 ADD PRIMARY KEY (`ID`), ADD KEY `GNetID` (`GNetID`,`InputID`,`OutputID`), ADD KEY `InputID` (`InputID`), ADD KEY `OutputID` (`OutputID`), ADD KEY `OutputID_2` (`OutputID`), ADD KEY `InputID_2` (`InputID`,`OutputID`);

--
-- Indexes for table `feedback_log`
--
ALTER TABLE `feedback_log`
 ADD PRIMARY KEY (`ID`), ADD KEY `QuestionID` (`QuestionID`,`UserID`), ADD KEY `UserID` (`UserID`);

--
-- Indexes for table `gnet`
--
ALTER TABLE `gnet`
 ADD PRIMARY KEY (`ID`), ADD KEY `StartStateID` (`StartStateID`), ADD KEY `EndStateID` (`EndStateID`), ADD KEY `RootID` (`RootID`);

--
-- Indexes for table `method`
--
ALTER TABLE `method`
 ADD PRIMARY KEY (`ID`), ADD KEY `GNetID` (`GNetID`);

--
-- Indexes for table `property`
--
ALTER TABLE `property`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `state`
--
ALTER TABLE `state`
 ADD PRIMARY KEY (`ID`), ADD KEY `SubGNetStartID` (`SubGNetStartID`), ADD KEY `SubGNetEndID` (`SubGNetEndID`), ADD KEY `ParentGNetID` (`ParentGNetID`), ADD KEY `GNetID` (`GNetID`);

--
-- Indexes for table `state_function`
--
ALTER TABLE `state_function`
 ADD PRIMARY KEY (`StateID`,`FunctionID`), ADD KEY `FunctionID` (`FunctionID`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
 ADD PRIMARY KEY (`ID`), ADD KEY `ChildTaskID` (`ChildTaskID`), ADD KEY `GNetID` (`GNetID`);

--
-- Indexes for table `tasklist`
--
ALTER TABLE `tasklist`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tasklist_task`
--
ALTER TABLE `tasklist_task`
 ADD PRIMARY KEY (`TaskListID`,`TaskID`), ADD KEY `TaskID` (`TaskID`);

--
-- Indexes for table `task_function`
--
ALTER TABLE `task_function`
 ADD PRIMARY KEY (`TaskID`,`FunctionID`), ADD KEY `FunctionID` (`FunctionID`);

--
-- Indexes for table `transition`
--
ALTER TABLE `transition`
 ADD PRIMARY KEY (`ID`), ADD KEY `GNetID` (`GNetID`), ADD KEY `TaskListID` (`TaskListID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `user_gnet`
--
ALTER TABLE `user_gnet`
 ADD PRIMARY KEY (`UserID`,`GNetID`), ADD KEY `UserID` (`UserID`), ADD KEY `GNetID` (`GNetID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `action_log`
--
ALTER TABLE `action_log`
ADD CONSTRAINT `action_log_gnet_id` FOREIGN KEY (`GNetID`) REFERENCES `gnet` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `action_log_userid_id` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `arc`
--
ALTER TABLE `arc`
ADD CONSTRAINT `arc_gnet_id` FOREIGN KEY (`GNetID`) REFERENCES `gnet` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `feedback_log`
--
ALTER TABLE `feedback_log`
ADD CONSTRAINT `feedback_log_question_id` FOREIGN KEY (`QuestionID`) REFERENCES `question` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `feedback_log_user_id` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `gnet`
--
ALTER TABLE `gnet`
ADD CONSTRAINT `gnet_end_id` FOREIGN KEY (`EndStateID`) REFERENCES `state` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `gnet_root_id` FOREIGN KEY (`RootID`) REFERENCES `state` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `gnet_start_id` FOREIGN KEY (`StartStateID`) REFERENCES `state` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `method`
--
ALTER TABLE `method`
ADD CONSTRAINT `method_gnet_id` FOREIGN KEY (`GNetID`) REFERENCES `gnet` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `state`
--
ALTER TABLE `state`
ADD CONSTRAINT `state_gnet_id` FOREIGN KEY (`GNetID`) REFERENCES `gnet` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `state_parent_state_id` FOREIGN KEY (`ParentGNetID`) REFERENCES `state` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `state_sub_end_id` FOREIGN KEY (`SubGNetEndID`) REFERENCES `state` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `state_sub_start_id` FOREIGN KEY (`SubGNetStartID`) REFERENCES `state` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `state_function`
--
ALTER TABLE `state_function`
ADD CONSTRAINT `state_function_ibfk_1` FOREIGN KEY (`StateID`) REFERENCES `state` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `state_function_ibfk_2` FOREIGN KEY (`FunctionID`) REFERENCES `method` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `task`
--
ALTER TABLE `task`
ADD CONSTRAINT `task_gnet_id` FOREIGN KEY (`GNetID`) REFERENCES `gnet` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tasklist_task`
--
ALTER TABLE `tasklist_task`
ADD CONSTRAINT `tasklist_task_ibfk_1` FOREIGN KEY (`TaskListID`) REFERENCES `tasklist` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `tasklist_task_ibfk_2` FOREIGN KEY (`TaskID`) REFERENCES `task` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `task_function`
--
ALTER TABLE `task_function`
ADD CONSTRAINT `task_function_ibfk_1` FOREIGN KEY (`TaskID`) REFERENCES `task` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `task_function_ibfk_2` FOREIGN KEY (`FunctionID`) REFERENCES `method` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transition`
--
ALTER TABLE `transition`
ADD CONSTRAINT `transition_gnet_id` FOREIGN KEY (`GNetID`) REFERENCES `gnet` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `transition_tasklist_id` FOREIGN KEY (`TaskListID`) REFERENCES `tasklist` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_gnet`
--
ALTER TABLE `user_gnet`
ADD CONSTRAINT `user_gnet_gnet_id` FOREIGN KEY (`GNetID`) REFERENCES `gnet` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `user_gnet_user_id` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
