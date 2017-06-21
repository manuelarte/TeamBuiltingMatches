package org.manuel.teambuilting.matches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TeamBuiltingMatchesApplication {

	public static void main(final String[] args) {
		SpringApplication.run(TeamBuiltingMatchesApplication.class, args);
	}

}
