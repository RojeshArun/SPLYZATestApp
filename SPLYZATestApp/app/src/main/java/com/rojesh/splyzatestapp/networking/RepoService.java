package com.rojesh.splyzatestapp.networking;

import com.rojesh.splyzatestapp.ui.model.InvitePayLoad;
import com.rojesh.splyzatestapp.ui.model.InviteResponse;
import com.rojesh.splyzatestapp.ui.model.Team;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RepoService {

    @GET("teams/{teamId}")
    Call<Team> getTeamDetails(@Path("teamId") String id);

    @POST("teams/{teamId}/invites")
    Call<InviteResponse> getInviteURL(@Path("teamId") String id, @Body InvitePayLoad payload);
}
