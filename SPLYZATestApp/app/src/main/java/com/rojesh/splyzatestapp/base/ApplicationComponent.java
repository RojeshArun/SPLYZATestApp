package com.rojesh.splyzatestapp.base;

import android.view.View;

import com.rojesh.splyzatestapp.networking.NetworkModule;
import com.rojesh.splyzatestapp.ui.InviteMembersScreen;
import com.rojesh.splyzatestapp.viewmodel.ViewModelModule;

import java.nio.channels.NetworkChannel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ViewModelModule.class,})
public interface ApplicationComponent {
    void inject(InviteMembersScreen inviteMembersScreen);
}
