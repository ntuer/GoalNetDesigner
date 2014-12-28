-- phpMyAdmin SQL Dump
-- version 4.2.8.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 28, 2014 at 08:47 AM
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
  `GNetID` char(36) NOT NULL COMMENT 'Action performed on GNet',
  `OperationTargetObject` varchar(20) NOT NULL COMMENT 'Target object of this action: arc/transition/state/task/function',
  `TargetObjectID` char(36) NOT NULL COMMENT 'Target object''s ID',
  `Timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Time of action'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Record user behaviors for analytics';

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
  `Version` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `function`
--

DROP TABLE IF EXISTS `function`;
CREATE TABLE IF NOT EXISTS `function` (
  `ID` char(36) NOT NULL,
  `Alias` varchar(50) DEFAULT NULL COMMENT 'Alias of the function',
  `FileName` varchar(255) NOT NULL COMMENT 'Dll filename from which the function comes',
  `RTType` varchar(50) NOT NULL DEFAULT 'void' COMMENT 'return type of the function',
  `Name` varchar(50) NOT NULL COMMENT 'function name',
  `Params` varchar(500) DEFAULT NULL COMMENT 'parameters of the function in sequence separated by semicolons, eg int,float,string,char',
  `Values` varchar(500) DEFAULT NULL COMMENT 'Values of each parameter in the same sequence.',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Description for the function'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

-- --------------------------------------------------------

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
CREATE TABLE IF NOT EXISTS `state` (
  `ID` char(36) NOT NULL,
  `GNetID` bigint(20) NOT NULL COMMENT 'GoalNetID of the goal net it belongs to',
  `ParentGNetID` char(36) DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0' COMMENT 'Parent State ID of this state (>=1). Value may be invalid since only the start and end state of a sub goal net has a parent.',
  `Name` varchar(50) NOT NULL COMMENT 'Name of the state',
  `Description` varchar(255) DEFAULT NULL COMMENT 'Description of the state',
  `Composite` tinyint(1) NOT NULL COMMENT 'Indicating whether the state is composite(Yes) or atomic(No)',
  `Cost` int(11) DEFAULT '0' COMMENT 'Cost of achieving the state',
  `Achievement` int(11) DEFAULT '0' COMMENT 'The benefit of achieving the state',
  `SubGNetStartID` char(36) DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0' COMMENT 'State goal id if it is a composite state',
  `SubGNetEndID` char(36) DEFAULT '0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0' COMMENT 'End goal id if it is a composite state',
  `Token` bigint(20) DEFAULT '0' COMMENT 'Whether there is a token and the value of the token. no token means 0.',
  `X` int(11) NOT NULL COMMENT 'X coordinate of the state on the canvas',
  `Y` int(11) NOT NULL COMMENT 'Y coordinate of the state on the canvas'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `Achievement` int(11) DEFAULT '0' COMMENT 'Achievement after executing the task.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

-- --------------------------------------------------------

--
-- Table structure for table `usergroup`
--

DROP TABLE IF EXISTS `usergroup`;
CREATE TABLE IF NOT EXISTS `usergroup` (
  `ID` char(36) NOT NULL,
  `Name` varchar(50) NOT NULL COMMENT 'User group name.',
  `Desc` varchar(255) DEFAULT NULL COMMENT 'User group description.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `usergroup_gnet`
--

DROP TABLE IF EXISTS `usergroup_gnet`;
CREATE TABLE IF NOT EXISTS `usergroup_gnet` (
  `UserGroupID` char(36) NOT NULL COMMENT 'User group ID.',
  `GNetID` char(36) NOT NULL COMMENT 'Goal Net ID',
  `Read` tinyint(1) NOT NULL COMMENT 'Read privilege',
  `Write` tinyint(1) NOT NULL COMMENT 'Writing privilege.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `Answer` varchar(255) NOT NULL COMMENT 'Answer to secret question.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_usergroup`
--

DROP TABLE IF EXISTS `user_usergroup`;
CREATE TABLE IF NOT EXISTS `user_usergroup` (
  `UserID` char(36) NOT NULL COMMENT 'User ID',
  `UserGroupID` char(36) NOT NULL COMMENT 'Group ID',
  `IsAdmin` bigint(20) unsigned NOT NULL COMMENT 'True: this user is the administrator of this group; False: otherwise.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `action_log`
--
ALTER TABLE `action_log`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `arc`
--
ALTER TABLE `arc`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `feedback_log`
--
ALTER TABLE `feedback_log`
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
-- Indexes for table `question`
--
ALTER TABLE `question`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `state`
--
ALTER TABLE `state`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `state_function`
--
ALTER TABLE `state_function`
 ADD PRIMARY KEY (`StateID`,`FunctionID`), ADD KEY `FunctionID` (`FunctionID`);

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
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `usergroup`
--
ALTER TABLE `usergroup`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `usergroup_gnet`
--
ALTER TABLE `usergroup_gnet`
 ADD PRIMARY KEY (`UserGroupID`,`GNetID`), ADD KEY `GNetID` (`GNetID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `user_usergroup`
--
ALTER TABLE `user_usergroup`
 ADD PRIMARY KEY (`UserID`,`UserGroupID`), ADD KEY `UserGroupID` (`UserGroupID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `state_function`
--
ALTER TABLE `state_function`
ADD CONSTRAINT `state_function_ibfk_1` FOREIGN KEY (`StateID`) REFERENCES `state` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `state_function_ibfk_2` FOREIGN KEY (`FunctionID`) REFERENCES `function` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

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
ADD CONSTRAINT `task_function_ibfk_2` FOREIGN KEY (`FunctionID`) REFERENCES `function` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `usergroup_gnet`
--
ALTER TABLE `usergroup_gnet`
ADD CONSTRAINT `usergroup_gnet_ibfk_1` FOREIGN KEY (`UserGroupID`) REFERENCES `usergroup` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `usergroup_gnet_ibfk_2` FOREIGN KEY (`GNetID`) REFERENCES `gnet` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_usergroup`
--
ALTER TABLE `user_usergroup`
ADD CONSTRAINT `user_usergroup_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `user_usergroup_ibfk_2` FOREIGN KEY (`UserGroupID`) REFERENCES `usergroup` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
