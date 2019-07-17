package com.sale.flashsale.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest(value = SaleController.class, secure = false)
public class SaleController {
	@Autowired
	private MockMvc mockMvc;
	
//	Course mockCourse = new Course("Course1", "Spring", "10 Steps",
//			Arrays.asList("Learn Maven", "Import Project", "First Example",
//					"Second Example"));
	
}
