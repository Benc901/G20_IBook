CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `test`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: g10mygas
-- ------------------------------------------------------
-- Server version	5.7.9

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customercars`
--



DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `userName` varchar(45) NOT NULL,
  `passWord` varchar(45) NOT NULL,
  `permission` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `photo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Ben','1234',1,0,'Ben','Cohen','benc901@gmail.com','ben'),(2,'Shany','1234',2,0,'Shany','Avital','Shanyavital@gmail.com','shany'),(3,'Ori','1234',3,0,'Ori','Sayda','Ori@gmail.com','ori'),(4,'Gidi','1234',4,0,'Gidi','Buks','Gidi@gmail.com','gidi');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `reader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reader` (
  `id` int(11) NOT NULL,
  `subscription` int(11) NOT NULL,
  `card_num` varchar(45) NOT NULL,
  `valid_m` varchar(45) NOT NULL,
  `valid_y` varchar(45) NOT NULL,
  `cvv` varchar(45) NOT NULL,
  `rId` varchar(45) NOT NULL,
  `confirm` int(11) NOT NULL,
  `book_left` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel`
--

LOCK TABLES `reader` WRITE;
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
INSERT INTO `reader` VALUES (3,0,'1234123412341234','02','2018','365','305061632',1,10),(2,0,'5678567856785678','08','2021','899','203484571',1,10),(4,0,'1234123412341234','01','2019','112','253786483',1,10);
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `language` varchar(45) NOT NULL,
  `summary`varchar(1000) NOT NULL,
  `content` varchar(45) NOT NULL,
  `keywords` varchar(45) NOT NULL,
  `genre` varchar(45) NOT NULL,
  `subject` varchar(45) NOT NULL,
  `photo` varchar(45) NOT NULL,
  `numOfSearch` int(11) DEFAULT NULL,
  `numOfPurchace` int(11) NOT NULL,
  `hidden` int(11) NOT NULL,
  `totalRank` int(11) NOT NULL,
  `genreRank` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Robin hobb','Assassins Apprentice','English','Young Fitz is the bastard son of the noble Prince Chivalry, raised in the shadow of the royal court by his father’s gruff stableman. He is treated as an outcast by all the royalty except the devious King Shrewd, who has him secretly tutored in the arts of the assassin. For in Fitz’s blood runs the magic Skill—and the darker knowledge of a child raised with the stable hounds and rejected by his family.
 As barbarous raiders ravage the coasts, Fitz is growing to manhood. Soon he will face his first dangerous, soul-shattering mission. And though some regard him as a threat to the throne, he may just be the key to the survival of the kingdom.','s','keyword','Teen Books','adventure','RobinHobb',1,1,1,1,1),
(2,'The Hunger Games','Suzanne Collins','English','In the ruins of a place once known as North America lies the nation of Panem, a shining Capitol surrounded by twelve outlying districts. Long ago the districts waged war on the Capitol and were defeated. As part of the surrender terms, each district agreed to send one boy and one girl to appear in an annual televised event called, "The Hunger Games," a fight to the death on live TV. Sixteen-year-old Katniss Everdeen, who lives alone with her mother and younger sister, regards it as a death sentence when she is forced to represent her district in the Games. The terrain, rules, and level of audience participation may change but one thing is constant: kill or be killed.','s','keyword','Teen Books','adventure','Thehungergames',1,1,0,1,1),
(3,'Twilight',' Stephenie Meyer','English','Isabella Swans move to Forks, a small, perpetually rainy town in Washington, could have been the most boring move she ever made. But once she meets the mysterious and alluring Edward Cullen, Isabellas life takes a thrilling and terrifying turn. Up until now, Edward has managed to keep his vampire identity a secret in the small community he lives in, but now nobody is safe, especially Isabella, the person Edward holds most dear. The lovers find themselves balanced precariously on the point of a knife-between desire and danger.Deeply romantic and extraordinarily suspenseful, Twilight captures the struggle between defying our instincts and satisfying our desires. This is a love story with bite.','s','keyword','Teen Books','adventure','twilight',1,1,0,1,1),
(4,'Harry Potter And The Chamber Of Secrets',' J. K. Rowling','English','The Dursleys were so mean that hideous that summer that all Harry Potter wanted was to get back to the Hogwarts School for Witchcraft and Wizardry. But just as hes packing his bags, Harry receives a warning from a strange, impish creature named Dobby who says that if Harry Potter returns to Hogwarts, disaster will strike.And strike it does. For in Harrys second year at Hogwarts, fresh torments and horrors arise, including an outrageously stuck-up new professor, Gilderoy Lockheart, a spirit named Moaning Myrtle who haunts the girls bathroom, and the unwanted attentions of Ron Weasleys younger sister, Ginny.','s','keyword','Teen Books','adventure','Harry',1,1,0,1,1),
(5,'The Hidden Tools of Comedy',' Steve Kaplan','English','While other books give you tips on how to �?write funny,�? this book offers a paradigm shift in understanding the mechanics and art of comedy, and the proven, practical tools that help writers translate that understanding into successful, commercial scripts. The Hidden Tools of Comedy unlocks the unique secrets and techniques of writing comedy. Kaplan deconstructs sequences in popular films and TV that work and don’t work, and explains what tools were used (or should have been used).','s','keyword','three','four','Comedy',1,1,0,1,1),
(6,'My Favorite Husband',' Pam McCutcheon','English','After five years of being lost in the Amazon, archaeologist Chaz Vincent has finally come home to his wife, Kelly, only to learn that she has had him declared dead. When he attends his own funeral, Chaz finds out that Kelly married another man just that morning. Now she has to choose between them. Who will be her favorite husband? ','s','keyword','three','four','Romance',1,1,0,1,1),
(7,'The unsolved mysteries',' Muhammad Asim ','English','It’s the human nature to look always through the close doors, to unveil the golden secret and to uncover the hidden truth. That’s what our ancestors did and that’s what we are doing. The human race has faced hundreds of unknown phenomena in their entire history, some of which were solved by the great and intelligent minds of mankind while other are still unsolved to date.','s','keyword','three','four','history',1,1,0,1,1),
(8,'The Secret Price of History',' Gayle Ridinger','English','1850s Rome. Goffredo, Sandor, and Eleonora, selfless idealists fighting for Italian unification, find a medallion after a violent face-off with French soldiers during the battle that temporarily dashes all hopes for the fledging Italian Republic. ','s','keyword','three','four','history2',1,1,1,1,1),
(9,'A Brief History of Time',' Stephen Hawking','English','A landmark volume in science writing by one of the great minds of our time, Stephen Hawking’s book explores such profound questions as: How did the universe begin—and what made its start possible? Does time always flow forward? Is the universe unending—or are there boundaries? Are there other dimensions in space? What will happen when it all ends?','s','keyword','Science','Math','Math',1,1,0,1,1);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `reader_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reader_book` (
  `id` int(11) NOT NULL,
  `bookId` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `review` int(11) NOT NULL,
  PRIMARY KEY (`id`,`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel`
--

LOCK TABLES `reader_book` WRITE;
/*!40000 ALTER TABLE `reader_book` DISABLE KEYS */;
INSERT INTO `reader_book` VALUES (2,1,'Robin hobb',0),(2,2,'The Hunger Games',0),(2,3,'Twilight',1);
/*!40000 ALTER TABLE `reader_book` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `id` int(11) NOT NULL,
  `bookId` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `userId` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `userphoto` varchar(45) NOT NULL,
  `bookphoto` varchar(45) NOT NULL,
  `review` varchar(200) NOT NULL,
  `rate`int(11) NOT NULL,
  `confirm` varchar(45) NOT NULL,
  `workerId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,1,'Robin hobb','Assassins Apprentice',1,'Shany','shany','RobinHobb','Perfect book',4,1,12),
(2,2,'Twilight','Stephenie Meyer',1,'Ben','ben','twilight','The book is very good, interesting and nice',4,1,12);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `genere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genere` (
  `id` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel`
--

LOCK TABLES `genere` WRITE;
/*!40000 ALTER TABLE `genere` DISABLE KEYS */;
INSERT INTO `genere` VALUES (1,'Teen'),(2,'Culture'),(3,'Guide'),(4,'Science');
/*!40000 ALTER TABLE `genere` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL,
  `genere_id` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,1,'Adventure'),(2,1,'Magics'),(3,1,'Scary'),(4,2,'Music'),(5,2,'Art'),(6,2,'Food'),(7,3,'Trips'),(8,3,'Cooking'),(9,3,'Art'),(10,4,'Mathematics'),(11,4,'Geometry'),(12,4,'Physics');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `pairing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pairing` (
  `book_id` int(11) NOT NULL,
  `genere_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  PRIMARY KEY (`book_id`,`genere_id`,`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel`
--

LOCK TABLES `pairing` WRITE;
/*!40000 ALTER TABLE `pairing` DISABLE KEYS */;
INSERT INTO `pairing` VALUES (1,1,1),(2,1,3),(3,1,2),(4,1,1),(5,3,9),(6,1,1),(7,1,1),(8,1,1),(9,1,1);
/*!40000 ALTER TABLE `pairing` ENABLE KEYS */;
UNLOCK TABLES;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-17 22:17:24
