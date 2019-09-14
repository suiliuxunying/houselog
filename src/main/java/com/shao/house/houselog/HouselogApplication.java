package com.shao.house.houselog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shao.house.houselog.mapper")
public class HouselogApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouselogApplication.class, args);
	}

}
