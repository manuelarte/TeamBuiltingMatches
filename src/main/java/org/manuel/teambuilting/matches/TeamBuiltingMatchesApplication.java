package org.manuel.teambuilting.matches;

import org.manuel.teambuilting.core.config.CoreConfig;
import org.manuel.teambuilting.core.config.EnableCoreFunctionalities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableCoreFunctionalities
@Import({CoreConfig.class})
public class TeamBuiltingMatchesApplication {

	public static void main(final String[] args) {
		SpringApplication.run(TeamBuiltingMatchesApplication.class, args);
	}

}
