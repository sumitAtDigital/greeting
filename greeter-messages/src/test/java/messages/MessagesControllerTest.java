/*
 * Copyright 2017-Present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package messages;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MessagesController.class, secure = false)
public class MessagesControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void greetingDefault() throws Exception {
		this.mvc.perform(get("/greeting")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().string("{\"message\":\"Hello, Bob!\"}"));
	}

	@Test
	public void greetingWithParams() throws Exception {
		this.mvc.perform(get("/greeting?salutation=Hi&name=Tom")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().string("{\"message\":\"Hi, Tom!\"}"));
	}

	@Test
	public void greetingWithSalutationParam() throws Exception {
		this.mvc.perform(get("/greeting?salutation=Hi")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().string("{\"message\":\"Hi, Bob!\"}"));
	}

	@Test
	public void greetingWithNameParam() throws Exception {
		this.mvc.perform(get("/greeting?name=Tom")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().string("{\"message\":\"Hello, Tom!\"}"));
	}

	@Test
	public void greetingWithUnknownParams() throws Exception {
		this.mvc.perform(get("/greeting?foo=bar&baz=buz")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().string("{\"message\":\"Hello, Bob!\"}"));
	}
}