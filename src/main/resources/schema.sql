/** ユーザーテーブル */
CREATE TABLE home_user
(
   id INT NOT NULL AUTO_INCREMENT,
   kind_id INT NOT NULL,
   name VARCHAR(20) NOT NULL,
   email VARCHAR(20) NOT NULL,
   password VARCHAR(20) NOT NULL,
   created DATETIME NOT NULL,
   updated DATETIME NOT NULL,
   PRIMARY KEY(id)
);

/** ログインテーブル */
CREATE TABLE login_user
(
   id INT NOT NULL AUTO_INCREMENT,
   login_user INT NOT NULL,
   created DATETIME NOT NULL,
   updated DATETIME NOT NULL,
   PRIMARY KEY(id)
);

/** ----------------------------------------------------------------------------- */

/** 問い合わせテーブル */
CREATE TABLE inquiry
(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(20) NOT NULL,
   email VARCHAR(50) NOT NULL,
   comment VARCHAR(500) NOT NULL,
   created DATETIME NOT NULL,
   PRIMARY KEY(id)
);

/** 問い合わせ返信テーブル */
CREATE TABLE inquiry_reply
(
   id INT NOT NULL AUTO_INCREMENT,
   inquiry_id INT NOT NULL,
   name VARCHAR(50) NOT NULL,
   email VARCHAR(50) NOT NULL,
   comment VARCHAR(500) NOT NULL,
   created DATETIME NOT NULL,
   PRIMARY KEY(id)
);

/** ----------------------------------------------------------------------------- */

/** 調査テーブル */
CREATE TABLE survey
(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	age INT NOT NULL,
	profession INT NOT NULL,
	ismen INT NOT NULL,
	satisfaction INT NOT NULL,
	comment VARCHAR(100) NOT NULL,
	created DATETIME NOT NULL,
	PRIMARY KEY(id)
);

/** ----------------------------------------------------------------------------- */

/** ブログテーブル */
CREATE TABLE blog_main
(
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(20) NOT NULL,
	tag VARCHAR(15) NOT NULL,
	comment VARCHAR(600) NOT NULL,
	thanksCnt INT NOT NULL,
	created DATETIME NOT NULL,
	updated DATETIME NOT NULL,
	PRIMARY KEY(id)
);

/** ブログ返信テーブル */
CREATE TABLE blog_reply
(
	id INT NOT NULL AUTO_INCREMENT,
	blog_id INT NOT NULL,
	name VARCHAR(20) NOT NULL,
	comment VARCHAR(40) NOT NULL,
	thanksCnt INT NOT NULL,
	created DATETIME NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE blog_dubble_reply
(
	id INT NOT NULL AUTO_INCREMENT,
	replyid INT NOT NULL,
	comment VARCHAR(40) NOT NULL,
	thanksCnt INT NOT NULL,
	created DATETIME NOT NULL,
	PRIMARY KEY(id)
);

/** ブログタグテーブル */
CREATE TABLE blog_tag(
	id INT NOT NULL AUTO_INCREMENT,
	tag VARCHAR(15) NOT NULL,
	PRIMARY KEY(id)
);

/** ----------------------------------------------------------------------------- */
