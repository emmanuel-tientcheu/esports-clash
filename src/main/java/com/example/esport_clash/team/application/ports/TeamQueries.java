package com.example.esport_clash.team.application.ports;

import com.example.esport_clash.team.domain.viewModel.TeamViewModel;

public interface TeamQueries {
    public TeamViewModel getTeamById(String id);
}
