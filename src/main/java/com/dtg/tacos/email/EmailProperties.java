package com.dtg.tacos.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "tacocloud.mail")
public class EmailProperties {

    private String username;
    private String password;
    private String host;
    private String mailbox;
    private String port;
    private long pollRate = 30000;

    public String getImapUrl(){
        //imap url using ssl
        return String.format("imaps://%s:%s@%s/%s",
                this.username, this.password, this.host, this.mailbox);
    }
}
