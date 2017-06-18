package com.example.rosty.architecture.injection;

import com.example.rosty.architecture.presentation.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author rebeccafranks
 * @since 2017/05/11.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainViewModel viewModel);

    interface Injectable {
        void inject(AppComponent appComponent);
    }
}
