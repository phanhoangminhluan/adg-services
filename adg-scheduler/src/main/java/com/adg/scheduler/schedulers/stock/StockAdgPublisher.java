package com.adg.scheduler.schedulers.stock;

import com.adg.core.common.enums.Topic;
import com.adg.core.pubsub.common.AdgAbstractPublisher;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.08 16:46
 */

@Component
public class StockAdgPublisher extends AdgAbstractPublisher<String, String> {

    @Override
    protected Topic getTopic() {
        return Topic.SYNC_STOCK;
    }

    @Override
    protected Class<? extends Serializer<String>> getKeySerializer() {
        return StringSerializer.class;
    }

    @Override
    protected Class<? extends Serializer<String>> getValueSerializer() {
        return StringSerializer.class;
    }

    @Override
    protected void onSuccess(RecordMetadata recordMetadata, String key, String value) {
        this.debugOnSuccess(recordMetadata, key, value);
    }
}
