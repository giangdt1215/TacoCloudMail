package com.dtg.tacos.email;

import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class EmailToOrderTransformer extends AbstractMailMessageTransformer<EmailOrder> {


    @Override
    protected AbstractIntegrationMessageBuilder<EmailOrder>
            doTransform(javax.mail.Message mailMessage) throws Exception {

        return null;
    }
}
