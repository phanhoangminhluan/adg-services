package com.adg.scheduler.producers.misa.employee;

import com.adg.core.common.constants.PubSubConstants;
import com.adg.scheduler.producers.misa.AbstractProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.11 11:46
 */
@Component
public class EmployeeProducerService extends AbstractProducerService<EmployeeWebClientService> {

    @Autowired
    private EmployeeWebClientService employeeWebClientService;

    @Override
    protected EmployeeWebClientService getWebClientService() {
        return this.employeeWebClientService;
    }

    @Override
    protected String getTopicName() {
        return PubSubConstants.Employee.TOPIC_NAME;
    }

}
