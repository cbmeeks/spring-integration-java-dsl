/*
 * Copyright 2016 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.dsl.kafka;

import java.util.regex.Pattern;

import org.apache.kafka.common.TopicPartition;

import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;

/**
 * Factory class for Kafka-0.9 components.
 *
 * @author Artem Bilan
 * @since 1.2
 */
public abstract class Kafka09 {

	/**
	 * Create an initial {@link Kafka09ProducerMessageHandlerSpec}.
	 * @param kafkaTemplate the {@link KafkaTemplate} to use
	 * @return the Kafka09ProducerMessageHandlerSpec.
	 */
	public static <K, V> Kafka09ProducerMessageHandlerSpec<K, V>
	outboundChannelAdapter(KafkaTemplate<K, V> kafkaTemplate) {
		return new Kafka09ProducerMessageHandlerSpec<K, V>(kafkaTemplate);
	}

	/**
	 * Create an initial {@link Kafka09ProducerMessageHandlerSpec} with ProducerFactory.
	 * @param producerFactory the {@link ProducerFactory} Java 8 Lambda.
	 * @return the KafkaProducerMessageHandlerSpec.
	 * @see <a href="https://kafka.apache.org/documentation.html#producerconfigs">Kafka Producer Configs</a>
	 */
	public static <K, V> Kafka09ProducerMessageHandlerSpec.KafkaProducerMessageHandlerTemplateSpec
	outboundChannelAdapter(ProducerFactory<K, V> producerFactory) {
		return new Kafka09ProducerMessageHandlerSpec.KafkaProducerMessageHandlerTemplateSpec<K, V>(producerFactory);
	}

	/**
	 * Create an initial {@link Kafka09MessageDrivenChannelAdapterSpec}.
	 * @param listenerContainer the {@link AbstractMessageListenerContainer}.
	 * @return the Kafka09MessageDrivenChannelAdapterSpec.
	 */
	public static Kafka09MessageDrivenChannelAdapterSpec messageDriverChannelAdapter(
			AbstractMessageListenerContainer<?, ?> listenerContainer) {
		return new Kafka09MessageDrivenChannelAdapterSpec(listenerContainer);
	}

	/**
	 * Create an initial
	 * {@link Kafka09MessageDrivenChannelAdapterSpec.KafkaMessageDrivenChannelAdapterListenerContainerSpec}.
	 * @param consumerFactory the {@link ConsumerFactory}.
	 * @param topicPartitions the {@link TopicPartition} vararg.
	 * @return the KafkaMessageDrivenChannelAdapterSpec.KafkaMessageDrivenChannelAdapterListenerContainerSpec.
	 */
	public static Kafka09MessageDrivenChannelAdapterSpec.KafkaMessageDrivenChannelAdapterListenerContainerSpec
	messageDriverChannelAdapter(ConsumerFactory<?, ?> consumerFactory, TopicPartition... topicPartitions) {
		return messageDriverChannelAdapter(
				new Kafka09MessageDrivenChannelAdapterSpec.KafkaMessageListenerContainerSpec(consumerFactory,
						topicPartitions));
	}

	/**
	 * Create an initial
	 * {@link Kafka09MessageDrivenChannelAdapterSpec.KafkaMessageDrivenChannelAdapterListenerContainerSpec}.
	 * @param consumerFactory the {@link ConsumerFactory}.
	 * @param topics the topics vararg.
	 * @return the KafkaMessageDrivenChannelAdapterSpec.KafkaMessageDrivenChannelAdapterListenerContainerSpec.
	 */
	public static Kafka09MessageDrivenChannelAdapterSpec.KafkaMessageDrivenChannelAdapterListenerContainerSpec
	messageDriverChannelAdapter(ConsumerFactory<?, ?> consumerFactory, String... topics) {
		return messageDriverChannelAdapter(
				new Kafka09MessageDrivenChannelAdapterSpec.KafkaMessageListenerContainerSpec(consumerFactory,
						topics));
	}

	/**
	 * Create an initial
	 * {@link Kafka09MessageDrivenChannelAdapterSpec.KafkaMessageDrivenChannelAdapterListenerContainerSpec}.
	 * @param consumerFactory the {@link ConsumerFactory}.
	 * @param topicPattern the topicPattern vararg.
	 * @return the KafkaMessageDrivenChannelAdapterSpec.KafkaMessageDrivenChannelAdapterListenerContainerSpec.
	 */
	public static Kafka09MessageDrivenChannelAdapterSpec.KafkaMessageDrivenChannelAdapterListenerContainerSpec
	messageDriverChannelAdapter(ConsumerFactory<?, ?> consumerFactory, Pattern topicPattern) {
		return messageDriverChannelAdapter(
				new Kafka09MessageDrivenChannelAdapterSpec.KafkaMessageListenerContainerSpec(consumerFactory,
						topicPattern));
	}

	private static Kafka09MessageDrivenChannelAdapterSpec.KafkaMessageDrivenChannelAdapterListenerContainerSpec
	messageDriverChannelAdapter(Kafka09MessageDrivenChannelAdapterSpec.KafkaMessageListenerContainerSpec spec) {
		return new Kafka09MessageDrivenChannelAdapterSpec.KafkaMessageDrivenChannelAdapterListenerContainerSpec(spec);
	}

}