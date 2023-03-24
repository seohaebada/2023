/*
 * Copyright 2019 the original author or authors.
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
// tag::controller[]
package com.greglturnquist.hackingspringboot.reactive;

import reactor.core.publisher.Flux;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

	private final KitchenService kitchen;

	public ServerController(KitchenService kitchen) {
		this.kitchen = kitchen;
	}

	/**
	 * 호출 후 종료가 되지않음
	 * @return
	 */
	@GetMapping(value = "/server", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<Dish> serveDishes() {
		return this.kitchen.getDishes();
	}
	// end::controller[]

	// tag::deliver[]
	@GetMapping(value = "/served-dishes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<Dish> deliverDishes() {
		return this.kitchen.getDishes() //
				// Flux 클래스의 map() 호출
				.map(dish -> Dish.deliver(dish)); // delivered 값을 true로 셋팅한다.
	}
	// end::deliver[]
}
