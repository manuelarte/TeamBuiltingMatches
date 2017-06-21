package org.manuel.teambuilting.matches.model.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.mongodb.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = UnRegisteredPlayerInfo.UnRegisteredPlayerInfoBuilder.class)
@Immutable
@Document
@Data
@lombok.Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UnRegisteredPlayerInfo implements PlayerInfo {

	private final String id;

	@NotNull
	private final String name;

	@JsonPOJOBuilder(withPrefix = "")
	public static class UnRegisteredPlayerInfoBuilder {

	}
	
}
