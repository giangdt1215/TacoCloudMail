package com.dtg.tacos.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.integration.support.PropertiesBuilder;

import java.util.function.Consumer;

@Configuration
public class TacoOrderEmailIntegrationConfig {

    @Bean
    public IntegrationFlow tacoOrderEmailFlow(
            EmailProperties emailProps,
            EmailToOrderTransformer emailToOrderTransformer,
            OrderSubmitMessageHandler orderSubmitMessageHandler){
       return IntegrationFlows
               .from(Mail.imapInboundAdapter(emailProps.getImapUrl())
                       .shouldMarkMessagesAsRead(true)
                       .shouldDeleteMessages(false)
                       .maxFetchSize(10)
                       .javaMailProperties
                               (p -> p.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
                                       .put("mail.imap.socketFactory.fallback", false)
                                       .put("mail.store.protocol", "imaps")
                                       .put("mail.debug", true)),
                    e -> e.poller(
                       Pollers.fixedDelay(emailProps.getPollRate())))
               .transform(emailToOrderTransformer)
               .handle(orderSubmitMessageHandler)
               .get();
    }
}
