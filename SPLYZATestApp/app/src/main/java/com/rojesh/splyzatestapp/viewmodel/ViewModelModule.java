package com.rojesh.splyzatestapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.rojesh.splyzatestapp.ui.InviteMemberViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(InviteMemberViewModel.class)
    abstract ViewModel bindInviteMemberViewModel(InviteMemberViewModel viewModel);
}
