package naumen.framework.base;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Work with FTP
 *
 */
public class FTPUtils {

	private static final int _TIMEOUT = 60;
	static final String PROPERTIES_FILE = "ftp.properties";
	private static PropertiesResourceManager props = new PropertiesResourceManager(PROPERTIES_FILE);
	private FTPClient client;
	private FileInputStream fis = null;

	/**
	 * Default constructor
	 */
	public FTPUtils() {
		this("pgz3");
	}

	public FTPUtils(String stage) {
		String domain = null;
		String user = null;
		String password = null;
		domain = props.getProperty("domain"+stage);
		user = props.getProperty("login"+stage);
		password = props.getProperty("password"+stage);
		Logger.getInstance().info("ftp domain:" + domain);
		Logger.getInstance().info("ftp user:" + user);
		Logger.getInstance().info("ftp password:" + password);
		client = new FTPClient();
		try {
	    client.connect(domain);
	    client.login(user, password);
	    client.enterLocalPassiveMode();
	    Logger.getInstance().info(client.getStatus());
	    if (stage.equalsIgnoreCase("pgz3")) {
	    	stage+="_out";
	    }
		client.changeWorkingDirectory(String.format("/%1$s/vsrz/in/",stage));
		} catch(IOException e) {
			Logger.getInstance().info("Exception" + e.getMessage());
		}
	}

	/**
	 * Change directory
	 * @param stage stage
	 * @param dir dir
	 * @return FTPUtils
	 */
	public FTPUtils setDir(final String stage, final String dir) {
	    String directory = null;
		if (dir.equalsIgnoreCase("etp")) {
			if (stage.equalsIgnoreCase("pgz3")) {
				directory="/pgz3_out/etp/in/";
		    } else {
		    	directory = "/" + stage + "/etp/in/";
		    }
	    }
		try {
			Logger.getInstance().info("DIRECTORY = " + directory);
			client.changeWorkingDirectory(directory);
		} catch (IOException e) {
			Logger.getInstance().info("Exception" + e.getMessage());
		}
		return this;
	}

	/**
	 * Upload file
	 * @param path filepath
	 * @return FTPUtils
	 */
	public FTPUtils uploadFile(final String path) {
		try {
		    fis = new FileInputStream(path);
		    String outputfile = path.split("/")[1];
		    client.storeFile(outputfile, fis);
		    Logger.getInstance().info(client.getReplyString());
		} catch (IOException e) {
		    e.printStackTrace();
		    close();
		}
		return this;
	}

	/**
	 * Upload file
	 * @param path filepath
	 * @return FTPUtils
	 */
	public boolean isFileExists2(final String path) {
		    InputStream inputStream;
		    FileOutputStream fos;
			try {
				fos = new FileOutputStream(path);
				inputStream = client.retrieveFileStream(path);
		    int returnCode = client.getReplyCode();
		    if (inputStream == null || returnCode == 550) {
		        return false;
		    }
			} catch (Exception e) {
				Logger.getInstance().debug(e.getMessage());
				e.printStackTrace();
				Assert.assertTrue(false, "Error during isPresent method on FTP");
			}
		    return true;
	}

	/**
	 * Upload file
	 * @return FTPUtils
	 */
	public FTPFile[] listFiles() {
		    try {
				return client.listFiles();
			} catch (IOException e) {
				e.printStackTrace();
				Assert.assertTrue(false, "Error during isPresent method on FTP");
			}
			return null;

	}

	/**
	 * Is File exists
	 * @param targetFileName targetFileName
	 * @return boolean
	 */
	public boolean isFileExist(final String targetFileName) {
	boolean flag = false;
	FTPFile[] ftpFileArr = listFiles();
	for (FTPFile ftpFile : ftpFileArr) {
		if (ftpFile.getName().equalsIgnoreCase(targetFileName)) {
			flag = true;
			break;
		}
	}
	return flag;
}

	/**
	 * Wait until file and file error dissappear
	 * @param path path
	 * @param timeout timeout
	 */
	public void waitForFileProceed(final String path, int timeout) {
		while ((timeout>0)) {
			Logger.getInstance().info(String.format("Файл %1$s есть? \t.......%2$s",path + ".error",String.valueOf(isFileExist(path + ".error")).toUpperCase()));
			Logger.getInstance().info(String.format("Файл %1$s есть? \t.......%2$s",path,String.valueOf(isFileExist(path)).toUpperCase()));
			Logger.getInstance().info(String.format("Файл %1$s есть? \t.......%2$s",path + ".lock",String.valueOf(isFileExist(path + ".lock")).toUpperCase()));
			if (!(isFileExist(path))&&(!isFileExist(path + ".error"))&&(!isFileExist(path + ".lock"))) {
				break;
			}
			try {
				Thread.sleep(1000);
				Logger.getInstance().info("Ожидание..... "+timeout--);;
			} catch (InterruptedException e) {
			}
		}
		Assert.assertFalse(isFileExist(path + ".error"), String.format("File %1$s is on ftp server!",path + ".error"));
		Assert.assertFalse(isFileExist(path + ""), String.format("File %1$s is on ftp server!",path));
		try {
			Thread.sleep(5000);
			timeout--;
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Wait until file and file error dissappear
	 * @param path path
	 */
	public void waitForFileProceed(final String path) {
		waitForFileProceed(path,_TIMEOUT);
	}

	/**
	 * Close FTP connection
	 */
	public void close() {
		try {
		    fis.close();
		    client.logout();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

}



