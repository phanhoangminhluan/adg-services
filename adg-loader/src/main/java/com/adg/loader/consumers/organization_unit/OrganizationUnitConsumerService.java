package com.adg.loader.consumers.organization_unit;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.core.model.organization_unit.OrganizationUnitDTO;
import com.adg.core.service.MisaTool.organization_unit.OrganizationUnitService;
import com.adg.loader.consumers.AbstractConsumerService;
import com.merlin.asset.core.utils.JsonUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 11:52
 */
@Component
public class OrganizationUnitConsumerService extends AbstractConsumerService {


    @Autowired
    private OrganizationUnitService service;

    @KafkaListener(
            topics = PubSubConstants.OrganizationUnit.TOPIC_NAME,
            containerFactory = PubSubConstants.OrganizationUnit.LISTENER_CONTAINER_FACTORY
    )
    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 1000, multiplier = 2.0),
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
    )
    @Override
    public void consume(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {
        super.consume(consumerRecord, acknowledgment);
        OrganizationUnitDTO dto = JsonUtils.fromJson(consumerRecord.value(), OrganizationUnitDTO.class);
        this.service.save(dto);
    }

    @DltHandler
    protected void dlt(Message<String> in,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.EXCEPTION_MESSAGE) String exception,
                       @Header(KafkaHeaders.EXCEPTION_STACKTRACE) byte[] exceptionStacktrace,
                       Acknowledgment acknowledgment
    ) {
        super.dlt(in, topic, exception, exceptionStacktrace, acknowledgment);
    }
}
