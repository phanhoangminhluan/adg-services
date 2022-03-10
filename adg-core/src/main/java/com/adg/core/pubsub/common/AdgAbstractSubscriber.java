package com.adg.core.pubsub.common;

import com.adg.core.common.enums.Topic;
import com.merlin.asset.core.pubsub.kafka.AbstractSubscriber;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.08 16:39
 */
public abstract class AdgAbstractSubscriber<K, V> extends AbstractSubscriber<K, V> {

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
