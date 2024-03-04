package com.springboot.blog;

import com.springboot.blog.controller.PostRestController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootBlogAppApplicationTests {

	@Autowired
	PostRestController postRestController;

	@Test
	void contextLoads() {
		Assertions.assertThat(postRestController).isNotNull();
	}

}
