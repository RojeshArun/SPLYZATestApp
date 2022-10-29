package com.rojesh.splyzatestapp.networking;

import com.rojesh.splyzatestapp.ui.model.InvitePayLoad;
import com.rojesh.splyzatestapp.ui.model.InviteResponse;
import com.rojesh.splyzatestapp.ui.model.Team;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoService {
    //TODO

    @GET("")
    Call<Team> getTeamDetails();

    @GET("teams/{teamId}/invites")
    Call<InviteResponse> getInviteURL(@Path("teamId") @Body InvitePayLoad payload);
}
