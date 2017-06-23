package com.example.rosty.architecture.injection;

import com.example.rosty.architecture.data.DataSource;
import com.example.rosty.architecture.presentation.home.MainViewModel;
import com.example.rosty.architecture.presentation.home.users.UsersViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author rebeccafranks
 * @since 2017/05/11.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    DataSource dataSource();

    void inject(MainViewModel viewModel);
    void inject(UsersViewModel viewModel);

    interface Injectable {
        void inject(AppComponent appComponent);
    }
}
