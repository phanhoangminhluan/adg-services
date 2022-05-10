package com.adg.core.service.MisaTool.stock;

import com.adg.core.mapper.StockMapper;
import com.adg.core.model.stock.StockDTO;
import com.adg.core.model.stock.StockEntity;
import com.adg.core.repository.stock.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.18 20:45
 */
@Component
public class StockServiceImpl implements StockService{

    private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    @Override
    public void save(StockDTO stockDTO) {
        StockEntity stockEntity = stockMapper.toEntity(stockDTO);
        StockEntity savedStockEntity = stockRepository.save(stockEntity);
//        System.out.println(savedStockEntity);
    }
}
