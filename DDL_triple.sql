/* triple 데이터베이스 DDL */
CREATE DATABASE triple default CHARACTER SET UTF8;

/* REVIEW 테이블 DDL */
CREATE TABLE `triple`.`REVIEW` (
 `REVIEW_ID` varchar(36) NOT NULL COMMENT '리뷰ID',
  `CONTENT` varchar(200) DEFAULT NULL COMMENT '리뷰내용',
  `USER_ID` varchar(36) NOT NULL COMMENT '작성자 ID',
  `ATTACHED_PHOTO_IDS_STRING` varchar(200) DEFAULT NULL COMMENT '이미지 경로 집합. 콤마로 구분됨',
  `PLACE_ID` varchar(36) NOT NULL COMMENT '장소ID',
  `DELETE_YN` varchar(1) DEFAULT NULL COMMENT '삭제여부',
  `RGST_DTM` datetime NOT NULL COMMENT '생성일자',
  `RGST_ID` varchar(36) NOT NULL COMMENT '생성자ID',
  `CHG_DTM` datetime DEFAULT NULL COMMENT '변경일자',
  `CHG_ID` varchar(36) DEFAULT NULL COMMENT '변경자ID',
  PRIMARY KEY (`REVIEW_ID`),
  UNIQUE KEY `id_UNIQUE` (`REVIEW_ID`),
  KEY `serch_index` (`REVIEW_ID`,`USER_ID`,`PLACE_ID`));
  
  
  /* PLACE 테이블 DDL */
  CREATE TABLE `triple`.`PLACE` (
  `PLACE_ID` varchar(36) NOT NULL COMMENT '장소 ID',
  `NAME` varchar(45) NOT NULL COMMENT '장소명',
  `RGST_DTM` datetime NOT NULL COMMENT '생성일자',
  `RGST_ID` varchar(36) NOT NULL COMMENT '생성자ID',
  `CHG_DTM` datetime DEFAULT NULL COMMENT '변경일자',
  `CHG_ID` varchar(36) DEFAULT NULL COMMENT '변경자ID',
  PRIMARY KEY (`PLACE_ID`),
  UNIQUE KEY `PLACE_ID_UNIQUE` (`PLACE_ID`));
  
  
  /* CODE 테이블 DDL */
  CREATE TABLE `triple`.`CODE` (
  `CODE_VALUE` varchar(20) NOT NULL COMMENT '코드 값',
  `CODE_DESC` varchar(45) DEFAULT NULL COMMENT '코드명',
  `RGST_DTM` datetime NOT NULL COMMENT '생성일자',
  `RGST_ID` varchar(36) NOT NULL COMMENT '생성자ID',
  `CHG_DTM` datetime DEFAULT NULL COMMENT '변경일자',
  `CHG_ID` varchar(36) DEFAULT NULL COMMENT '변경자ID',
  PRIMARY KEY (`CODE_VALUE`),
  UNIQUE KEY `CODE_VALUE_UNIQUE` (`CODE_VALUE`));
  
  
  /* USER 테이블 DDL */
  CREATE TABLE `triple`.`USER` (
    `USER_ID` varchar(36) NOT NULL COMMENT '사용자ID',
  `NAME` varchar(45) NOT NULL COMMENT '사용자명',
  `RGST_DTM` datetime NOT NULL COMMENT '생성일자',
  `RGST_ID` varchar(36) NOT NULL COMMENT '생성자ID',
  `CHG_DTM` datetime DEFAULT NULL COMMENT '변경일자',
  `CHG_ID` varchar(36) DEFAULT NULL COMMENT '생성자ID',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_ID_UNIQUE` (`USER_ID`)
  );


 /* POINT 테이블 DDL */
 CREATE TABLE `triple`.`POINT` (
  `POINT_ID` VARCHAR(36) NOT NULL COMMENT '포인트ID',
  `USER_ID` VARCHAR(36) NOT NULL COMMENT '사용자ID',
  `VALUE` INT NOT NULL DEFAULT 0 COMMENT '포인트값',
  `RGST_DTM` DATETIME NOT NULL COMMENT '생성일자',
  `RGST_ID` VARCHAR(36) NOT NULL COMMENT '생성자ID',
  `CHG_DTM` DATETIME COMMENT '변경일자',
  `CHG_ID` VARCHAR(36) COMMENT '변경자ID',
  PRIMARY KEY (`POINT_ID`),
  UNIQUE INDEX `POINT_ID_UNIQUE` (`POINT_ID` ASC) VISIBLE);
  
  
  /* POINT_HISTORY 테이블 DDL */
  CREATE TABLE `triple`.`POINT_HISTORY` (
  `HISTORY_ID` varchar(36) NOT NULL,
  `USER_ID` varchar(36) NOT NULL COMMENT '사용자ID',
  `REVIEW_ID` varchar(36) NOT NULL COMMENT '리뷰ID',
  `ACTION` varchar(10) DEFAULT NULL COMMENT '포인트 회수/획득 구분. MINUS/PLUS',
  `TYPE` varchar(20) DEFAULT NULL COMMENT '타입 구분 (CONTENT_TEXT, CONTENT_PHOTO, BONUS_FIRST)',
  `VALUE` int DEFAULT NULL COMMENT '포인트값',
  `RGST_DTM` datetime NOT NULL COMMENT '생성일자',
  `RGST_ID` varchar(36) NOT NULL COMMENT '생성자ID',
  `CHG_DTM` datetime DEFAULT NULL COMMENT '변경일자',
  `CHG_ID` varchar(36) DEFAULT NULL COMMENT '변경자ID',
  PRIMARY KEY (`HISTORY_ID`),
  UNIQUE KEY `HISTORY_ID_UNIQUE` (`HISTORY_ID`));
  
  
  /* REWARD 테이블 DDL */
 CREATE TABLE `triple`.`REWARD` (
    `ACTION` varchar(10) NOT NULL COMMENT '포인트 회수/획득 구분. MINUS/PLUS',
  `TYPE` varchar(20) NOT NULL COMMENT '타입 구분',
  `VALUE` int NOT NULL COMMENT '포인트값',
  `USE_YN` varchar(1) DEFAULT NULL COMMENT '사용여부',
  `RGST_DTM` datetime NOT NULL COMMENT '생성일자',
  `RGST_ID` varchar(36) NOT NULL COMMENT '생성자ID',
  `CHG_DTM` datetime DEFAULT NULL COMMENT '변경일자',
  `CHG_ID` varchar(36) DEFAULT NULL COMMENT '변경자ID',
  PRIMARY KEY (`ACTION`,`TYPE`,`VALUE`),
  KEY `SEARCH_INDEX` (`ACTION`,`TYPE`,`USE_YN`));

