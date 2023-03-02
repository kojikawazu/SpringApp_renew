package com.example.demo.common.log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * ログ出力用クラス
 * @author nanai
 *
 */
public class LogWriter implements SuperLogWriter {

	/** ログ名(デフォルト) */
	protected static final String DEFAULT_LOGNAME_LOGWRITER = "LogWriter";

	/** ファイルの最大容量(デフォルト) */
	protected static final int DEFAULT_LIMIT_FILE_SIZE = 1048576;

	/** ファイルの最大数(デフォルト) */
	protected static final int DEFAULT_LIMIT_FILE_MAX = 10000;

	/** ログレベル(デフォルト) */
	protected static final Level DEFAULT_LOG_LEVEL = Level.ALL;

	/** ログファイル拡張子 */
	protected static final String EXTENSION_LOG = ".log";

	/** Linux ディレクトリ */
	protected static final String LINUX_APP_LOG_DIR = "/var/tomcat/introapp";

	/** OS名(Windows) */
	protected static final String OS_WINDOWS_NAME = "windows";

	/** Lock用Object */
	private final Object LOCK_OBJECT_LOG_WRITE = new Object();
	// -------------------------------------------------------------------------------------------------------------------------

	/** ログ名 */
	private String logName    = DEFAULT_LOGNAME_LOGWRITER;

	/** ファイルの最大容量 */
	private int limitFileSize = DEFAULT_LIMIT_FILE_SIZE;

	/** ファイルの最大数 */
	private int limitFileMax  = DEFAULT_LIMIT_FILE_MAX;

	/** ログレベル */
	private Level logLevel    = DEFAULT_LOG_LEVEL;

	/** ログファイル名 */
	private String logFileName = "";

	/** ログディレクトリ */
	private String logDir = "";

	/** ログファイルフルパス */
	private String logFileFullPath = "";
	
	/** 現在のOS */
	private String currentOS = "";

	/** ログクラス */
	private Logger logger = null;

	// -------------------------------------------------------------------------------------------------------------------------

	/**
	 * コンストラクタ
	 */
	protected LogWriter() {

	}

	/**
	 * 初期化
	 */
	@Override
	public void init() {
		synchronized (LOCK_OBJECT_LOG_WRITE) {
			if (this.logger == null) {
				this.logFileName = this.logName + ".%u.%g" + EXTENSION_LOG;
				this.logger      = Logger.getLogger(this.logName);

				try {
					// ファイルフルパスの設定
					this.currentOS = System.getProperty("os.name").toLowerCase();
					if (this.currentOS.indexOf(OS_WINDOWS_NAME) != -1) {
						// windows
						// ディレクトリ設定
						this.logDir          = System.getProperty("user.dir") + "\\logs";
						Path directoryPath = Paths.get(this.logDir);
						// ディレクトリチェック
						if (!Files.isDirectory(directoryPath)) {
							// ディレクトリ作成
							Files.createDirectories(directoryPath);
						}
						// フルパス設定
						this.logFileFullPath = new File(this.logDir, this.logFileName).getPath();
					} else {
						// Linux
						// ディレクトリ設定
						this.logDir        = LINUX_APP_LOG_DIR;
						Path directoryPath = Paths.get(this.logDir);
						// ディレクトリチェック
						if (!Files.isDirectory(directoryPath)) {
							// ディレクトリ作成
							Files.createDirectories(directoryPath);
						}
						// フルパス設定
						this.logFileFullPath = this.logDir + "/" + this.logFileName;
					}
					System.out.println("Create Log File Path: " + this.logFileFullPath);

					// ファイル出力の設定
					Handler handler = new FileHandler(this.logFileFullPath, this.limitFileSize, this.limitFileMax);
					handler.setFormatter(new SimpleFormatter());
					this.logger.addHandler(handler);
					this.logger.setLevel(logLevel);

					// 標準出力の設定
					//ConsoleHandler consoleHandler = new ConsoleHandler();
					//consoleHandler.setLevel(Level.ALL);
					//this.logger.addHandler(consoleHandler);
				} catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	// ----------------------------------------------------------------------------------------------------------------
	/**
	 * ログ名の設定
	 * @param logName {@link String}
	 */
	protected void setLogName(String logName) {
		if (logName.equals(""))	return ;
		synchronized (LOCK_OBJECT_LOG_WRITE) {
			this.logName = logName;
		}
	}

	/**
	 * ログレベルの設定
	 * @param level {@link Level}
	 */
	protected void setLogLevel(Level level) {
		if (level == null)	return ;
		synchronized (LOCK_OBJECT_LOG_WRITE) {
			this.logLevel = level;
			if (this.logger != null) {
				// ログレベルの変更
				this.logger.setLevel(logLevel);
			}
		}
	}

	/**
	 * ログレベルALLへ変更
	 */
	public void setLogLevelAll() {
		this.setLogLevel(DEFAULT_LOG_LEVEL);
	}

	/**
	 * ログレベルwarningへ変更
	 */
	public void setLogLevelWarning() {
		this.setLogLevel(Level.WARNING);
	}
	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * 呼び出し元クラス名メソッド名を取得
	 * @return メソッド名(クラス名:呼び出し行番号)
	 */
	private String calledFrom() {
		StackTraceElement[] steArray = Thread.currentThread().getStackTrace();
		if (steArray.length <= 3) {
		    return "";
		}
		StackTraceElement ste = steArray[3];
		StringBuilder     sb  = new StringBuilder();
		/** メソッド名取得*/
		sb.append(ste.getMethodName())
			.append("(")
			/** ファイル名取得 */
			.append(ste.getClassName())
			.append(":")
			/** 行番号取得 */
			.append(ste.getLineNumber())
			.append(")");

		return sb.toString();
    }

	/**
	 * 準備チェック
	 * @return true 準備OK false 準備NG
	 */
	@Override
	public boolean isSetting() {
		if (this.logger == null) {
			System.out.println("Please set logger");
			return false;
		}
		return true;
	}

	/**
	 * 開始ログ
	 * @param log {@link String}
	 */
	@Override
	public void start(String log) {
		if (!this.isSetting())	return ;
		synchronized (LOCK_OBJECT_LOG_WRITE) {
			String calledFromString = this.calledFrom();
			this.logger.info(calledFromString + ":[start] " + log);
		}
	}

	/**
	 * 成功ログ 
	 * @param log {@link String}
	 */
	@Override
	public void successed(String log) {
		if (!this.isSetting())	return ;
		synchronized (LOCK_OBJECT_LOG_WRITE) {
			String calledFromString = this.calledFrom();
			this.logger.info(calledFromString + ":[successed] " + log);
		}
	}

	/**
	 * 通常ログ
	 * @param log {@link String}
	 */
	@Override
	public void info(String log) {
		if (!this.isSetting())	return ;
		synchronized (LOCK_OBJECT_LOG_WRITE) {
			String calledFromString = this.calledFrom();
			this.logger.info(calledFromString + " " + log);
		}
	}

	/**
	 * warningログ
	 * @param log {@link String}
	 */
	@Override
	public void warning(String log) {
		if (!this.isSetting())	return ;
		synchronized (LOCK_OBJECT_LOG_WRITE) {
			String calledFromString = this.calledFrom();
			this.logger.warning(calledFromString + ":[warning] " + log);
		}
	}

	/**
	 * エラーログ
	 * @param log {@link String}
	 */
	@Override
	public void error(String log) {
		if (!this.isSetting())	return ;
		synchronized (LOCK_OBJECT_LOG_WRITE) {
			String calledFromString = this.calledFrom();
			this.logger.severe(calledFromString + ":[error] " + log);
		}
	}
}
