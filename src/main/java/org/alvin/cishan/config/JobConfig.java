package org.alvin.cishan.config;

import org.alvin.cishan.sys.util.CMDUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@SpringBootConfiguration
@EnableScheduling
public class JobConfig implements InitializingBean {

	@Value("${spring.datasource.username}")
	private String rootName;// root
	@Value("${spring.datasource.password}")
	private String dataPassword;// T_dl123456
	@Value("${mysql_ip}")
	private String mysql_ip;


	@Scheduled(fixedDelay = 1 * 60 * 10000)
	public void backup() {
		String cmd = "mysqldump  -u" + rootName + " " + " -p" + dataPassword + " -h " + mysql_ip;
		File file = new File("db_backup", UUID.randomUUID().toString());
		if (!file.exists()) {
			file.getParentFile().mkdirs();
		}
		System.out.println("启动数据库备份");
		try {
			CMDUtils.execute(cmd + " erp_laozhang " + "> " + file.getAbsolutePath(), new StringBuffer(), new StringBuffer());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD");
			File sqlFile = new File(file.getParent(), simpleDateFormat.format(new Date()).concat(".sql"));
			sqlFile.delete();
			System.gc();
			file.renameTo(sqlFile);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
//		new Thread(this::backup).start();
	}
}
