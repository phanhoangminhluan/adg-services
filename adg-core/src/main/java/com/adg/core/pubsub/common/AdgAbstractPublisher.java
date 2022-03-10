package com.adg.core.pubsub.common;

import com.adg.core.common.enums.Topic;
import com.merlin.asset.core.pubsub.kafka.AbstractPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.08 16:39
 */
@Component
public abstract class AdgAbstractPublisher<K, V> extends AbstractPublisher<K, V> {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    protected abstract Topic getTopic();

    @Override
    protected String getBootstrapServer() {
        return this.bootstrapServer;
    }

    @Override
    public final String getTopicName() {
        return this.getTopic().topicName;
    }

    @Override
    public final int getNumberOfPartitions() {
        return this.getTopic().numberOfPartitions;
    }

    @Override
    public final String getGroupId() {
        return this.getTopic().kafkaGroupId;
    }

    @Override
    public final long getSleepingTime() {
        return this.getTopic().sleepingTime;
    }

}
