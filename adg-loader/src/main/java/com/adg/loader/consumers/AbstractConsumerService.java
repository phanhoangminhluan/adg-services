package com.adg.loader.consumers;

import com.adg.core.enums.Module;
import com.adg.core.enums.SlackAuthor;
import com.adg.core.service.slack.SlackService;
import com.merlin.asset.core.utils.LogUtils;
import com.merlin.asset.core.utils.SlackUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 14:48
 */
@Component
public abstract class AbstractConsumerService {

    private final static Logger logger = LoggerFactory.getLogger(AbstractConsumerService.class);

    @Autowired
    private SlackService slackService;

    public void consume(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {
//        logger.info("Message consumed. " + LogUtils.buildLogMsg(
//                "Topic", consumerRecord.topic(),
//                "Key", consumerRecord.key(),
//                "Value", consumerRecord.value()
//        ));
    }

    protected void dlt(Message<String> in,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.EXCEPTION_MESSAGE) String exception,
                       @Header(KafkaHeaders.EXCEPTION_STACKTRACE) byte[] exceptionStacktrace,
                       Acknowledgment acknowledgment
    ) {
        String logId = LogUtils.genLogId("DLT_" + topic);
        try {
            String stackTrace = new String(exceptionStacktrace, StandardCharsets.UTF_8);

            logger.error(logId + " Message listening encounters a failure. " + LogUtils.buildLogMsg(
                    "Topic", topic,
                    "Value", in.getPayload(),
                    "Exception message", exception,
                    "Exception stacktrace", stackTrace
            ));

            slackService.sendNotification(
                    Module.ADG_LOADER,
                    SlackAuthor.LUAN_PHAN,
                    logId,
                    "Message consuming has been failed on " + topic,
                    SlackUtils.buildSlackMessage(
                            SlackUtils.boldText("Kafka message"), SlackUtils.wrapCode(in.getPayload()),
                            SlackUtils.boldText("Exception"), SlackUtils.wrapCode(stackTrace)
                    ));

        } catch (Exception exception1) {
            exception1.printStackTrace();
        } finally {
            acknowledgment.acknowledge();
        }
    }
}