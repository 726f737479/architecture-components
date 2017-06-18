package com.example.rosty.architecture;

import android.app.Application;

import com.example.rosty.architecture.injection.AppComponent;
import com.example.rosty.architecture.injection.AppModule;
import com.example.rosty.architecture.injection.DaggerAppComponent;

/**
 * Created by rosty on 6/18/17.
 */

public class ArchiApplication extends Application {

    private final AppComponent appComponent = createAppComponent();

    protected AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}