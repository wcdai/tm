package com.dwc.tranmanagement;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.dwc.tranmanagement.dao")
public class TranmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TranmanagementApplication.class, args);
	}


}
