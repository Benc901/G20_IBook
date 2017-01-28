CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `test`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `language` varchar(45) NOT NULL,
  `summary` varchar(1000) NOT NULL,
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
  `price` int(11) NOT NULL,
  `pdf` longblob,
  `doc` longblob,
  `fb2` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Robin hobb','Assassins Apprentice','English','Young Fitz is the bastard son of the noble Prince Chivalry, raised in the shadow of the royal court by his father’s gruff stableman. He is treated as an outcast by all the royalty except the devious King Shrewd, who has him secretly tutored in the arts of the assassin. For in Fitz’s blood runs the magic Skill—and the darker knowledge of a child raised with the stable hounds and rejected by his family. As barbarous raiders ravage the coasts, Fitz is growing to manhood. Soon he will face his first dangerous, soul-shattering mission. And though some regard him as a threat to the throne, he may just be the key to the survival of the kingdom.','s','keyword','genre','subject','1',1,1,1,1,1,10,NULL,NULL,NULL),(2,'The Hunger Games','Suzanne Collins','English','In the ruins of a place once known as North America lies the nation of Panem, a shining Capitol surrounded by twelve outlying districts. Long ago the districts waged war on the Capitol and were defeated. As part of the surrender terms, each district agreed to send one boy and one girl to appear in an annual televised event called, \"The Hunger Games,\" a fight to the death on live TV. Sixteen-year-old Katniss Everdeen, who lives alone with her mother and younger sister, regards it as a death sentence when she is forced to represent her district in the Games. The terrain, rules, and level of audience participation may change but one thing is constant: kill or be killed.','s','keyword','genre','subject','2',1,1,0,1,1,10,NULL,NULL,NULL),(3,'Twilight',' Stephenie Meyer','English','Isabella Swans move to Forks, a small, perpetually rainy town in Washington, could have been the most boring move she ever made. But once she meets the mysterious and alluring Edward Cullen, Isabellas life takes a thrilling and terrifying turn. Up until now, Edward has managed to keep his vampire identity a secret in the small community he lives in, but now nobody is safe, especially Isabella, the person Edward holds most dear. The lovers find themselves balanced precariously on the point of a knife-between desire and danger.Deeply romantic and extraordinarily suspenseful, Twilight captures the struggle between defying our instincts and satisfying our desires. This is a love story with bite.','s','keyword','genre','subject','3',1,1,0,1,1,10,NULL,NULL,NULL),(4,'Harry Potter And The Chamber Of Secrets','J. K. Rowling','English','The Dursleys were so mean that hideous that summer that all Harry Potter wanted was to get back to the Hogwarts School for Witchcraft and Wizardry. But just as hes packing his bags, Harry receives a warning from a strange, impish creature named Dobby who says that if Harry Potter returns to Hogwarts, disaster will strike.And strike it does. For in Harrys second year at Hogwarts, fresh torments and horrors arise, including an outrageously stuck-up new professor, Gilderoy Lockheart, a spirit named Moaning Myrtle who haunts the girls bathroom, and the unwanted attentions of Ron Weasleys younger sister, Ginny.','s','keyword','genre','subject','4',1,1,0,1,1,10,NULL,NULL,NULL),(5,'The Hidden Tools of Comedy','Steve Kaplan','English','While other books give you tips on how to “write funny,” this book offers a paradigm shift in understanding the mechanics and art of comedy, and the proven, practical tools that help writers translate that understanding into successful, commercial scripts. The Hidden Tools of Comedy unlocks the unique secrets and techniques of writing comedy. Kaplan deconstructs sequences in popular films and TV that work and don’t work, and explains what tools were used (or should have been used).','s','keyword','genre','subject','5',1,1,0,1,1,10,NULL,NULL,NULL),(6,'My Favorite Husband','Pam McCutcheon','English','After five years of being lost in the Amazon, archaeologist Chaz Vincent has finally come home to his wife, Kelly, only to learn that she has had him declared dead. When he attends his own funeral, Chaz finds out that Kelly married another man just that morning. Now she has to choose between them. Who will be her favorite husband? ','s','keyword','gnre','subject','6',1,1,0,1,1,10,NULL,NULL,NULL),(7,'The unsolved mysteries','Muhammad Asim ','English','It’s the human nature to look always through the close doors, to unveil the golden secret and to uncover the hidden truth. That’s what our ancestors did and that’s what we are doing. The human race has faced hundreds of unknown phenomena in their entire history, some of which were solved by the great and intelligent minds of mankind while other are still unsolved to date.','s','keyword','gnre','subject','7',1,1,0,1,1,10,NULL,NULL,NULL),(8,'The Secret Price of History','Gayle Ridinger','English','1850s Rome. Goffredo, Sandor, and Eleonora, selfless idealists fighting for Italian unification, find a medallion after a violent face-off with French soldiers during the battle that temporarily dashes all hopes for the fledging Italian Republic. ','s','keyword','gnre','subject','8',1,1,1,1,1,10,NULL,NULL,NULL),(9,'A Brief History of Time',' Stephen Hawking','English','A landmark volume in science writing by one of the great minds of our time, Stephen Hawking’s book explores such profound questions as: How did the universe begin—and what made its start possible? Does time always flow forward? Is the universe unending—or are there boundaries? Are there other dimensions in space? What will happen when it all ends?','s','keyword','genre','subject','8',1,1,0,1,1,10,NULL,NULL,NULL),(10,'Trump: The Art of the Deal','Tony Schwartz','English','President-elect Donald J. Trump lays out his professional and personal worldview in this classic work—a firsthand account of the rise of America’s foremost deal-maker.','s','keyword','gnre','subject','10',1,1,0,1,1,10,NULL,NULL,NULL),(11,'Green Thumb: A Short Story','Earl Hatsby','English','They call the serial killer “Green Thumb\" due to his quirky habit of leaving small potted plants at the sites of his abductions. Michael will soon learn what that name really means.','s','keyword','gnre','subject','11',1,1,0,1,1,10,NULL,NULL,NULL),(12,'The Winter Over','Matthew Iden','English','Each winter the crew at the Shackleton South Pole Research Facility faces nine months of isolation, round-the-clock darkness, and one of the most extreme climates on the planet. For thirty-something mechanical engineer Cass Jennings, Antarctica offers an opportunity to finally escape the guilt of her troubled past and to rebuild her life.','s','keyword','genre','subject','12',1,1,0,1,1,10,NULL,NULL,NULL),(13,'Harry Potter and the Half','J.K. Rowling','English','The war against Voldemort is not going well; even the Muggles have been affected. Dumbledore is absent from Hogwarts for long stretches of time, and the Order of the Phoenix has already suffered losses. ','s','keyword','gnre','subject','13',1,1,0,1,1,10,NULL,NULL,NULL),(14,'Harry Potter and the Cursed Child','J.K. Rowling','English','Based on an original new story by J.K. Rowling, Jack Thorne and John Tiffany, a new play by Jack Thorne, Harry Potter and the Cursed Child is the eighth story in the Harry Potter series and the first official Harry Potter story to be presented on stage. The play will receive its world premiere in London’s West End on July 30, 2016.','s','keyword','genre','subject','14',1,1,0,1,1,10,NULL,NULL,NULL),(15,'Harry Potter and the Sorcerers Stone','J.K. Rowling','English','Harry Potter has no idea how famous he is. Thats because hes being raised by his miserable aunt and uncle who are terrified Harry will learn that hes really a wizard, just as his parents were. But everything changes when Harry is summoned to attend an infamous school for wizards, and he begins to discover some clues about his illustrious birthright. From the surprising way he is greeted by a lovable giant, to the unique curriculum and colorful faculty at his unusual school, Harry finds himself drawn deep inside a mystical world he never knew existed and closer to his own noble destiny.','s','keyword','genre','subject','15',1,1,0,1,1,10,NULL,NULL,NULL),(16,'Harry Potter and  The Illustrated Edition','J.K. Rowling','English','For the first time, J.K. Rowlings beloved Harry Potter books will be presented in lavishly illustrated full-color editions. Kate Greenaway-award-winning artist Jim Kay has created over 100 stunning illustrations, making this deluxe format a perfect gift as much for a child being introduced to the series, as for the dedicated fan.','s','keyword','genre','subject','16',1,1,0,1,1,10,NULL,NULL,NULL),(17,'Harry Potter and the Chamber of Secrets','J.K. Rowling','English','The Dursleys were so mean and hideous that summer that all Harry Potter wanted was to get back to the Hogwarts School for Witchcraft and Wizardry. But just as hes packing his bags, Harry receives a warning from a strange, impish creature named Dobby who says that if Harry Potter returns to Hogwarts, disaster will strike.','s','keyword','genre','subject','17',1,1,0,1,1,10,NULL,NULL,NULL),(18,'Harry Potter and the Prisoner of Azkaban','J.K. Rowling','English','For twelve long years, the dread fortress of Azkaban held an infamous prisoner named Sirius Black. Convicted of killing thirteen people with a single curse, he was said to be the heir apparent to the Dark Lord, Voldemort.','s','keyword','genre','subject','18',1,1,0,1,1,10,NULL,NULL,NULL),(19,'Harry Potter Coloring Book','J.K. Rowling','English','Unleash your creativity and escape to one of the most beloved series of all time.From the heraldry of the four Hogwarts houses to the extravagant wares of Weasleys Wizard Wheezes, the world of Harry Potter overflows with radiant color. Filled with intricate illustrations and elaborate designs used in the making of the Harry Potter films','s','keyword','genre','subject','19',1,1,0,1,1,10,NULL,NULL,NULL),(20,'Harry Potter And The Goblet Of Fire','J.K. Rowling','English','The paperback edition of the legendary, record-breaking, best-selling fourth Harry Potter novel.Harry Potter is midway through his training as a wizard and his coming of age. Harry wants to get away from the pernicious Dursleys and go to the International Quidditch Cup','s','keyword','genre','subject','20',1,1,0,1,1,10,NULL,NULL,NULL),(21,'Harry Potter and the Deathly Hallows','J.K. Rowling','English','Readers beware. The brilliant, breathtaking conclusion to J.K. Rowlings spellbinding series is not for the faint of heart--such revelations, battles, and betrayals await in Harry Potter and the Deathly Hallows that no fan will make it to the end unscathed.','s','keyword','genre','subject','21',1,1,0,1,1,10,NULL,NULL,NULL),(22,'Numbers Colors Shapes','Roger Priddy','English','Your little one will soon learn their first numbers, shapse and colors with this bright board book. There are 100 color pictures which they will love to look at, and 100 simple first words to learn, too. The pages are made from tough board for hours of fun reading, and the cover is softly padded for little hands to hold.','s','keyword','genre','subject','22',1,1,0,1,1,10,NULL,NULL,NULL),(23,'Dr. Seusss ABC','Dr. Seuss','English','With Dr. Seuss as your guide, learning the alphabet is as easy as A, B, C','s','keyword','genre','subject','23',1,1,0,1,1,10,NULL,NULL,NULL),(24,'Mastering Essential Math Skills',' Richard W. Fisher ','English','Perfect Math For Students Who Are Math Challenged,Students will master Problem Solving,Start with one-step problems and work your way up to mult-step problems','s','keyword','genre','subject','24',1,1,0,1,1,10,NULL,NULL,NULL),(25,'How to Be a Math Genius','Mike Goldsmith','English','In this clever guide, young readers previously daunted by algebra, logic, algorithms, and all things math will discover they are better at it than they thought.','s','keyword','genre','subject','25',1,1,0,1,1,10,NULL,NULL,NULL);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `id` int(11) NOT NULL,
  `file` longblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genere`
--

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
-- Dumping data for table `genere`
--

LOCK TABLES `genere` WRITE;
/*!40000 ALTER TABLE `genere` DISABLE KEYS */;
INSERT INTO `genere` VALUES (1,'Adventure'),(2,'Culture'),(3,'Romance'),(4,'Science'),(5,'Math'),(6,'Children'),(7,'History');
/*!40000 ALTER TABLE `genere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pairing`
--

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
-- Dumping data for table `pairing`
--

LOCK TABLES `pairing` WRITE;
/*!40000 ALTER TABLE `pairing` DISABLE KEYS */;
INSERT INTO `pairing` VALUES (1,1,1),(1,2,2),(2,1,2),(2,1,3),(3,1,2),(4,1,1),(5,3,9),(6,1,1),(7,1,1),(8,1,1),(9,1,1),(10,2,13),(11,1,3),(12,7,14),(13,1,1),(14,1,1),(15,1,1),(16,1,1),(17,1,1),(18,1,1),(19,1,1),(20,1,1),(21,1,1),(22,6,16),(22,6,17),(22,6,18),(23,6,15),(24,5,10),(24,5,11),(24,5,12),(25,5,10);
/*!40000 ALTER TABLE `pairing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reader`
--

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
-- Dumping data for table `reader`
--

LOCK TABLES `reader` WRITE;
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
INSERT INTO `reader` VALUES (2,1,'5678567856785678','08','2021','899','203484571',1,0),(3,1,'1234123412341234','02','2018','365','305061632',1,10);
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reader_book`
--

DROP TABLE IF EXISTS `reader_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reader_book` (
  `id` int(11) NOT NULL,
  `bookId` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `review` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`,`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reader_book`
--

LOCK TABLES `reader_book` WRITE;
/*!40000 ALTER TABLE `reader_book` DISABLE KEYS */;
INSERT INTO `reader_book` VALUES (1,2,'The Hunger Games',0,'2017-01-02'),(2,1,'Robin hobb',0,'2017-11-02'),(2,2,'The Hunger Games',0,'2017-11-02'),(2,3,'Twilight',1,'2017-01-02'),(3,2,'The Hunger Games',0,'2017-09-02'),(4,2,'The Hunger Games',0,'2016-01-02'),(5,2,'The Hunger Games',0,'2017-12-02'),(6,2,'The Hunger Games',0,'2017-02-02'),(7,2,'The Hunger Games',0,'2017-01-02'),(8,2,'The Hunger Games',0,'2017-07-02'),(9,2,'The Hunger Games',0,'2015-01-02'),(11,2,'The Hunger Games',0,'2017-08-02'),(22,2,'The Hunger Games',0,'2017-02-02'),(23,2,'The Hunger Games',0,'2017-01-02'),(24,2,'The Hunger Games',0,'2017-09-02'),(25,2,'The Hunger Games',0,'2014-01-02'),(26,2,'The Hunger Games',0,'2017-07-02'),(27,2,'The Hunger Games',0,'2017-02-02'),(28,2,'The Hunger Games',0,'2017-09-02'),(29,2,'The Hunger Games',0,'2015-01-02'),(30,2,'The Hunger Games',0,'2017-03-02'),(31,2,'The Hunger Games',0,'2017-02-02');
/*!40000 ALTER TABLE `reader_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

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
  `rate` int(11) NOT NULL,
  `confirm` varchar(45) NOT NULL,
  `workerId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,1,'Robin hobb','Assassins Apprentice',1,'Shany','shany','RobinHobb','Perfect book',4,'1',12),(2,2,'Twilight','Stephenie Meyer',1,'Ben','ben','twilight','The book is very good, interesting and nice',4,'1',12);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `search_book`
--

DROP TABLE IF EXISTS `search_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `search_book` (
  `book_id` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `search_book`
--

LOCK TABLES `search_book` WRITE;
/*!40000 ALTER TABLE `search_book` DISABLE KEYS */;
INSERT INTO `search_book` VALUES (1,'2017-01-20'),(2,'2017-01-20'),(2,'2017-01-20');
/*!40000 ALTER TABLE `search_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

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
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,1,'Teen'),(2,1,'Magics'),(3,1,'Scary'),(4,2,'Music'),(5,2,'Art'),(6,2,'Food'),(7,3,'Trips'),(8,2,'Cooking'),(9,3,'Art'),(10,5,'Mathematics'),(11,5,'Geometry'),(12,5,'Physics'),(13,2,'Politics'),(14,7,'Mystery'),(15,6,'ABC'),(16,6,'Colors'),(17,6,'Numbers'),(18,6,'Shapes');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
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
  `confirm` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Ben','1234',1,0,'Ben','Cohen','benc901@gmail.com','ben',0),(2,'Shany','1234',2,0,'Shany','Avital','Shanyavital@gmail.com','shany',1),(3,'Ori','1234',3,0,'Ori','Sayda','Ori@gmail.com','ori',1),(4,'Gidi','1234',4,1,'Gidi','Buks','Gidi@gmail.com','gidi',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'test'
--

--
-- Dumping routines for database 'test'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-28  0:41:04
