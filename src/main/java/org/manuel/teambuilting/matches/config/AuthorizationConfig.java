package org.manuel.teambuilting.matches.config;

import lombok.AllArgsConstructor;
import org.manuel.teambuilting.authorization.AppAuthorizationManager;
import org.manuel.teambuilting.authorization.AppEntityAuthorization;
import org.manuel.teambuilting.authorization.impl.AppAuthorizationManagerImpl;
import org.manuel.teambuilting.authorization.impl.AppEntityAuthorizationImpl;
import org.manuel.teambuilting.authorization.impl.AppPermissionAndRightConstraintsImpl;
import org.manuel.teambuilting.authorization.impl.AppRightConstraintOfSeveralConstraints;
import org.manuel.teambuilting.authorization.rights.AppPermissionAndRightConstraints;
import org.manuel.teambuilting.authorization.roles.AppRole;
import org.manuel.teambuilting.matches.model.Match;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.manuel.teambuilting.authorization.functions.GenericFunctions.allow;
import static org.manuel.teambuilting.authorization.functions.GenericFunctions.deny;

/**
 * @author Manuel Doncel Martos
 * @since 31/07/2017.
 */
@Configuration
@AllArgsConstructor
public class AuthorizationConfig {

    @Bean
    public AppAuthorizationManager appAuthorizationManager() {

        return AppAuthorizationManagerImpl.builder()
            .forEntity(Match.class, createAppEntityAuthorizationForMatch())
            .build();
    }

    private AppEntityAuthorization<Match> createAppEntityAuthorizationForMatch() {
        final AppPermissionAndRightConstraints<Match> visitorRoleForPlayerToTeamSportDetails
            = new AppPermissionAndRightConstraintsImpl.PermissionAndRightConstraintsImplBuilder()
            .read(AppRightConstraintOfSeveralConstraints.of(allow()))
            .build();

        final AppPermissionAndRightConstraints<Match> rightsForMatchEntity
            = new AppPermissionAndRightConstraintsImpl.PermissionAndRightConstraintsImplBuilder()
            .create(AppRightConstraintOfSeveralConstraints.of(allow()))
            .read(AppRightConstraintOfSeveralConstraints.of(allow()))
            .update(AppRightConstraintOfSeveralConstraints.of(deny()))
            .delete(AppRightConstraintOfSeveralConstraints.of(deny()))
            .build();

        return AppEntityAuthorizationImpl.<Match>builder()
            .constraint(AppRole.VISITOR, visitorRoleForPlayerToTeamSportDetails)
            .constraint(AppRole.ADMIN, rightsForMatchEntity)
            .constraint(AppRole.FREE, rightsForMatchEntity)
            .constraint(AppRole.GOLD, rightsForMatchEntity)
            .constraint(AppRole.PREMIUM, rightsForMatchEntity)
            .build();
    }

}
