package com.adg.scheduler.producers.misa.organization_unit;

import com.adg.scheduler.producers.misa.AbstractWebClientService;
import org.springframework.stereotype.Component;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2022.03.10 13:42
 */
@Component
public class OrganizationUnitWebClientService extends AbstractWebClientService {

    @Override
    protected String getUriPath() {
        return "OrganizationUnits";
    }
}
