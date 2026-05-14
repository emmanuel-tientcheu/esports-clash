package com.example.esport_clash.team.domain;

import com.example.esport_clash.team.domain.model.Role;
import com.example.esport_clash.team.domain.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TeamTest {
    @Nested
    class AddPlayer {
        @Test
        void shouldAddPlayer() {
            var team = new Team("123", "My team");
            team.addMember("player1", Role.TOP);

            Assertions.assertTrue(team.hasMember("player1", Role.TOP));
        }

        @Test
        void whenPlayerIsAlreadyInTeam_shouldThrow() {
            var team = new Team("123", "My team");
            team.addMember("player1", Role.TOP);

            var exception = Assertions.assertThrows(
                    IllegalArgumentException.class,
                    ()-> team.addMember("player1", Role.TOP)
            );

            Assertions.assertEquals("This player is already in the team", exception.getMessage());

        }

        @Test
        void whenRoleIsAlreadyInTaken_shouldThrow() {
            var team = new Team("123", "My team");
            team.addMember("player1", Role.TOP);

            var exception = Assertions.assertThrows(
                    IllegalArgumentException.class,
                    ()-> team.addMember("player2", Role.TOP)
            );

            Assertions.assertEquals("This role is already taken", exception.getMessage());

        }
    }

    @Nested
    class RemovePlayer {
        @Test
        void shouldRemovePlayer() {
            var team = new Team("123", "My team");
            team.addMember("player1", Role.TOP);

            Assertions.assertTrue(team.hasMember("player1", Role.TOP));

            team.removeMember("player1");
            Assertions.assertFalse(team.hasMember("player1", Role.TOP));
        }

        @Test
        void whenPlayerIsNotInTeam_shouldThrow() {
            var team = new Team("123", "My team");

           var exception = Assertions.assertThrows(
                   IllegalArgumentException.class,
                   ()-> team.removeMember("player1")
           );

           Assertions.assertEquals("this player is not in the team", exception.getMessage() );
        }
    }

}
