package com.example.rosty.architecture.injection;

import com.example.rosty.architecture.data.DataSource;
import com.example.rosty.architecture.presentation.home.MainViewModel;
import com.example.rosty.architecture.presentation.home.settings.SettingsViewModel;
import com.example.rosty.architecture.presentation.home.repos.ReposViewModel;
import com.example.rosty.architecture.presentation.home.users.UsersViewModel;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    DataSource dataSource();

    void inject(MainViewModel viewModel);
    void inject(UsersViewModel viewModel);
    void inject(ReposViewModel viewModel);
    void inject(SettingsViewModel viewModel);

    interface Injectable {
        void inject(AppComponent appComponent);
    }
}
