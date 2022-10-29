package com.rojesh.splyzatestapp.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rojesh.splyzatestapp.QRCodeGenerator;
import com.rojesh.splyzatestapp.networking.AppExecutors;
import com.rojesh.splyzatestapp.ui.model.Team;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Random;

public class InviteMemberViewModel extends ViewModel {

    private final MutableLiveData<Team> teams = new MutableLiveData<>();
    private AppExecutors appExecutors;

    public InviteMemberViewModel() {
        appExecutors = new AppExecutors();
        appExecutors.networkID().execute(() -> loadTeam());
    }

    LiveData<Team> getTeams() {
        return teams;
    }

    private void loadTeam() {
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

    public void fetchInviteURLandGenerateQRCode() {
        //TODO Fetch URL
        String url = "";

        //TODo Generate QR Code
        QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
        qrCodeGenerator.generateQRCode(url);
    }


}
