package com.adg.loader.consumers.stock;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.core.model.stock.StockDTO;
import com.adg.core.service.stock.StockService;
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
 * Created on: 2022.03.10 23:35
 */
@Component
public class StockConsumerService extends AbstractConsumerService {

    @Autowired
    private StockService stockService;


    @KafkaListener(
            topics = PubSubConstants.Stock.TOPIC_NAME,
            containerFactory = PubSubConstants.Stock.LISTENER_CONTAINER_FACTORY
    )
    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 1000, multiplier = 2.0),
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
    )
    @Override
    public void consume(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {
        super.consume(consumerRecord, acknowledgment);
        StockDTO stockDTO = JsonUtils.fromJson(consumerRecord.value(), StockDTO.class);
        stockService.save(stockDTO);
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