package com.adg.core.common.enums;


public enum Topic {
    SYNC_STOCK(
            "sync_stock",
            3,
            "data-sink: postgresql, table: stock",
            3000
    );
    public final String topicName;
    public final int numberOfPartitions;
    public final String kafkaGroupId;
    public final long sleepingTime; // in millisecond


    Topic(String topicName, int numberOfPartitions, String kafkaGroupId, long sleepingTime) {
        this.topicName = topicName;
        this.numberOfPartitions = numberOfPartitions;
        this.kafkaGroupId = kafkaGroupId;
        this.sleepingTime = sleepingTime;
    }
}