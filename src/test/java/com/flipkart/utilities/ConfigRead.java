package com.flipkart.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigRead {
	static Properties porp;
	public ConfigRead(){
		File src=new File("./config.properties");
		try {
			FileInputStream dest=new FileInputStream(src);
			porp=new Properties();
			porp.load(dest);
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
	public String getbrowser() {
		String browse=porp.getProperty("browser");
		return browse;
	}
	public String geturl() {
		String appurl=porp.getProperty("url");
		return appurl;
	}
	public String getname() {
		String nameuser=porp.getProperty("username");
		return nameuser;
	}
	public String getpassword() {
		String namepassword=porp.getProperty("password");
		return namepassword;
	}


}
