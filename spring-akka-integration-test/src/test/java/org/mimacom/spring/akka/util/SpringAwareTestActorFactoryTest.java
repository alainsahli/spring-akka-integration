/*
 * Copyright 2013 the original author or authors.
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

package org.mimacom.spring.akka.util;

import akka.testkit.TestActorRef;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mimacom.spring.akka.util.stub.DummyActor;
import org.mimacom.spring.akka.util.stub.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SpringAwareTestActorFactoryTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private DummyService dummyService;

	@Test
	public void testTestActorRef() throws Exception {
		TestActorRef<DummyActor> testActorRef = SpringAwareTestActorFactory.create(DummyActor.class, applicationContext);
		DummyActor dummyActor = testActorRef.underlyingActor();
		dummyActor.onReceive(new Object());

		verify(dummyService, times(1)).doIt();
	}
}
