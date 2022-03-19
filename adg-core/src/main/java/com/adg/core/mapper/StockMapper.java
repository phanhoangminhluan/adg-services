package com.adg.core.mapper;

import com.adg.core.model.stock.StockEntity;
import com.merlin.mapper.MerlinMapper;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.18 14:38
 */
public class StockMapper extends MerlinMapper<StockEntity> {

    public StockMapper(Class<StockEntity> entityClass) {
        super(entityClass);
    }
}
