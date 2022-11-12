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
   user_id INT NOT NULL,
   session_id VARCHAR(50) NOT NULL,
   created DATETIME NOT NULL,
   updated DATETIME NOT NULL,
   PRIMARY KEY(id)
);
/** ----------------------------------------------------------------------------- */

/** セキュリティロール */
CREATE TABLE security_roles(
  id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(32) NOT NULL,
  PRIMARY KEY(id)
);

/** セキュリティログインユーザー */
CREATE TABLE security_login_user(
   id INTEGER NOT NULL AUTO_INCREMENT,
   name VARCHAR(128) NOT NULL,
   email VARCHAR(256) NOT NULL,
   password VARCHAR(128) NOT NULL,
   created DATETIME NOT NULL,
   updated DATETIME NOT NULL,
   PRIMARY KEY(id)
);

/** ユーザーとロールの対応付け */
CREATE TABLE security_user_role(
    user_id INTEGER,    -- ユーザーのID
    role_id INTEGER,    -- ロールのID
    CONSTRAINT pk_user_role PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES security_login_user(id),
    CONSTRAINT fk_user_role_role_id FOREIGN KEY (role_id) REFERENCES security_roles(id)
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
