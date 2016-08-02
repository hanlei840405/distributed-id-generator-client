package com.xiaoqiaoli;

import com.xiaoqiaoli.service.client.GenerateIdRemoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DistributedIdGeneratorClientApplication.class)
@WebAppConfiguration
public class DistributedIdGeneratorClientApplicationTests {

	@Autowired
	private GenerateIdRemoteService generateIdService;
	@Test
	public void contextLoads() {
		for(int i = 0;i < 100;i++) {
			Runnable runnable = () -> {
				System.out.println(generateIdService.get("mdc","user"));
            };
			Thread thread = new Thread(runnable);
			thread.run();
		}
	}

}
