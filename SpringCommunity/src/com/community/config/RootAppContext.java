package com.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.community.dto.User;


// 프로젝트 작업시 사용할 bean을 정의하는 클래스
@Configuration
public class RootAppContext {//데이터를 저장하거나 관리할때
	
	//User @Component 대신 빈 등록하는 이유 -> User 다앙햔 빈으로 사용
	@Bean("loginUser")
	@SessionScope
	public User loginUser() {
		return new User();
	}
	
}
