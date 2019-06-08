package Bot;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args)
            throws LoginException, InterruptedException
    {
        JDA jda = new JDABuilder("NTg2OTk4MjQ5NTQ2ODQyMTE1.XPwK2Q.q5BcyS_8KWd7ZB1IsSDDLwJt9XY")
                .addEventListener(new ReadyListener())
                .addEventListener(new MessageListener())
                .build();
        jda.awaitReady();
    }
}
