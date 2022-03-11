package com.adg.loader.consumers;

import com.merlin.asset.core.utils.JsonUtils;
import com.merlin.asset.core.utils.MapUtils;

import java.util.Map;
import java.util.Optional;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 14:48
 */
public abstract class AbstractConsumerService {

    public void consume(String message) {

        Map<String, Object> map = JsonUtils.fromJson(message, JsonUtils.TYPE_TOKEN.MAP_STRING_OBJECT.type);
        System.out.printf("id: %s -- modified_date: %s\n",
                Optional.of(MapUtils.getString(map, "id"))
                        .orElse(MapUtils.getString(map, "async_id")),
                MapUtils.getString(map, "modified_date")
        );

    }

}
