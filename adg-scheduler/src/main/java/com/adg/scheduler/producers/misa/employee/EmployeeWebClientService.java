package com.adg.scheduler.producers.misa.employee;

import com.adg.scheduler.producers.misa.AbstractWebClientService;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.10 14:18
 */
@Component
public class EmployeeWebClientService extends AbstractWebClientService {
    @Override
    protected String getUriPath() {
        return "employees";
    }
}
