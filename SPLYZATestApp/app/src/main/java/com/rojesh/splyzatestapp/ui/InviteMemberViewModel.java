package com.rojesh.splyzatestapp.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rojesh.splyzatestapp.QRCodeGenerator;
import com.rojesh.splyzatestapp.networking.AppExecutors;
import com.rojesh.splyzatestapp.networking.RepoService;
import com.rojesh.splyzatestapp.ui.model.InvitePayLoad;
import com.rojesh.splyzatestapp.ui.model.InviteResponse;
import com.rojesh.splyzatestapp.ui.model.Team;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Random;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InviteMemberViewModel extends ViewModel {

    private final MutableLiveData<Team> teams = new MutableLiveData<>();
    private final MutableLiveData<InviteResponse> inviteURL = new MutableLiveData<>();

    private AppExecutors appExecutors;

    private final RepoService repoService;

    @Inject
    public InviteMemberViewModel(RepoService repoService) {
        this.repoService = repoService;
        appExecutors = new AppExecutors();
        appExecutors.networkID().execute(() -> loadTeam());
    }

    LiveData<Team> getTeams() {
        return teams;
    }

    LiveData<InviteResponse> getInviteULR() {
        return inviteURL;
    }

    private void loadTeam() {
        //Dummy API Call
        repoService.getTeamDetails().enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                //Success
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                //Failure
            }
        });
        //Handle Response
        handleResponse();
    }

    private void handleResponse() {
        //set Dummy Data
        //return dummy data
        String dummyResponse1 = "{\n" +
                "\"id\": \"57994f271ca5dd20847b910c\",\n" +
                "\"members\": {\n" +
                "\"total\": 89,\n" +
                "\"administrators\": 1,\n" +
                "\"managers\": 18,\n" +
                "\"editors\": 6,\n" +
                "\"members\": 58,\n" +
                "\"supporters\": 6\n" +
                "},\n" +
                "\"plan\": {\n" +
                "\"memberLimit\": 100,\n" +
                "\"supporterLimit\": 20\n" +
                "} }";

        String dummyResponse2 = "{\n" +
                "\"id\": \"57994f271ca5dd20847b910c\",\n" +
                "\"members\": {\n" +
                "\"total\": 89,\n" +
                "\"administrators\": 1,\n" +
                "\"managers\": 18,\n" +
                "\"editors\": 6,\n" +
                "\"members\": 77,\n" +
                "\"supporters\": 0\n" +
                "},\n" +
                "\"plan\": {\n" +
                "\"memberLimit\": 100,\n" +
                "\"supporterLimit\": 0\n" +
                "} }";

        String dummyResponse3 = "{\n" +
                "\"id\": \"57994f271ca5dd20847b910c\",\n" +
                "\"members\": {\n" +
                "\"total\": 89,\n" +
                "\"administrators\": 1,\n" +
                "\"managers\": 18,\n" +
                "\"editors\": 6,\n" +
                "\"members\": 100,\n" +
                "\"supporters\": 0\n" +
                "},\n" +
                "\"plan\": {\n" +
                "\"memberLimit\": 100,\n" +
                "\"supporterLimit\": 0\n" +
                "} }";

        String dummyResponse4 = "{\n" +
                "\"id\": \"57994f271ca5dd20847b910c\",\n" +
                "\"members\": {\n" +
                "\"total\": 89,\n" +
                "\"administrators\": 1,\n" +
                "\"managers\": 18,\n" +
                "\"editors\": 6,\n" +
                "\"members\": 10,\n" +
                "\"supporters\": 20\n" +
                "},\n" +
                "\"plan\": {\n" +
                "\"memberLimit\": 100,\n" +
                "\"supporterLimit\": 20\n" +
                "} }";

        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Team> adapter = moshi.adapter(Team.class);
        Random randomGenerator = new Random();
        int ran = randomGenerator.nextInt(4) + 1;
        String response = null;
        try {
            switch (ran) {
                case 1:
                    response = dummyResponse1;
                    break;
                case 2:
                    response = dummyResponse2;
                    break;
                case 3:
                    response = dummyResponse3;
                    break;
                case 4:
                    response = dummyResponse4;
                    break;
            }
            Team team = adapter.fromJson(response);
            appExecutors.mainThread().execute(() -> teams.setValue(team));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fetchInviteURL(String type) {
        //Generating dummy url
        String url = "";
        String payLoad = "";

        switch (type) {
            case "Coach":
                payLoad = "manager";
                url = "https://example.com/ti/manager%22";
                break;
            case "Player Coach":
                payLoad = "editor";
                url = "https://example.com/ti/editor%66";
                break;
            case "Player":
                payLoad = "member";
                url = "https://example.com/ti/member%55";
                break;
            case "Supporter":
                payLoad = "readonly";
                url = "https://example.com/ti/readonly%44";
                break;

        }

        // Fetch URL Dummy API Call
        InvitePayLoad payLoadReq = new InvitePayLoad(payLoad);
        repoService.getInviteURL(payLoadReq).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<InviteResponse> call, Response<InviteResponse> response) {
                //Success
            }

            @Override
            public void onFailure(Call<InviteResponse> call, Throwable t) {
                //Failure
            }
        });

        //Success Response
        InviteResponse inviteResponse = new InviteResponse(url);
        appExecutors.mainThread().execute(() -> inviteURL.setValue(inviteResponse));
    }

}
