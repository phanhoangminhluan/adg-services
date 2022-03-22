package com.adg.core.enums;


import com.adg.core.common.constants.PubSubConstants;

public enum Topic {
    STOCK(
            PubSubConstants.Stock.TOPIC_NAME,
            3,
            "data-sink: postgresql, table: stock",
            3000
    );
    public final String topicName;
    public final int numberOfPartitions;
    public final String groupId;
    public final long sleepingTime; // in millisecond


    Topic(String topicName, int numberOfPartitions, String groupId, long sleepingTime) {
        this.topicName = topicName;
        this.numberOfPartitions = numberOfPartitions;
        this.groupId = groupId;
        this.sleepingTime = sleepingTime;
    }
}