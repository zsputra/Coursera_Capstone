package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication{

	static{
		Scanner scanner = new Scanner(System.in);
		int B = scanner.nextInt();
		int H = scanner.nextInt();
		int area = B*H;
		if(B <=0 || H <=0){
			try {
				throw new Exception("dfdasf");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(area);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}


}
