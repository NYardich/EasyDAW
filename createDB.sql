CREATE DATABASE `easydaw` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `easydaw`.`projects` (
  `PROJNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SONGKEY` char(1) NOT NULL,
  `TEMPO` int NOT NULL,
  `GRADE` smallint DEFAULT NULL,
  `NOTEPAD` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`PROJNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `easydaw`.`users` (
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PASSWORD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SECQUES` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `SECANS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TEACHER` smallint NOT NULL,
  `TEACH` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FORGOTPASS` smallint NOT NULL,
  `RATING` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `easydaw`.users VALUES ("N/A", "N/A", "N/A", "N/A", 1, "N/A", 0, "100")