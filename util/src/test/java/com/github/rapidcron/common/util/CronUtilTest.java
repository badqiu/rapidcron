package com.github.rapidcron.common.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CronUtilTest {

	@Test
	public void test() {
		Crontab crontab = CronUtil.parseCronLine("*/5  1/3   * * * /bin/bash /data/app/offline_analyse/shell/flow.sh -DflowId=stage2mysql > /tmp/stage2myql.log");
		assertEquals("Crontab [cron=*/5  1/3   * * * , cmd=/bin/bash /data/app/offline_analyse/shell/flow.sh -DflowId=stage2mysql > /tmp/stage2myql.log]",crontab.toString());
		
		crontab = CronUtil.parseCronLine("0 12 * * * /bin/bash /data/backup/shell/backup_dwd_app_new.sh >> /data/backup/logs/backup_dwd_app_new.log");
		assertEquals("Crontab [cron=0 12 * * * , cmd=/bin/bash /data/backup/shell/backup_dwd_app_new.sh >> /data/backup/logs/backup_dwd_app_new.log]",crontab.toString());
		
		crontab = CronUtil.parseCronLine(null);
	}

}
