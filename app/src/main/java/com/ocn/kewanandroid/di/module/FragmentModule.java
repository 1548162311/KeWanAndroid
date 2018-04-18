package com.ocn.kewanandroid.di.module;

import android.app.Activity;
import android.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.ocn.kewanandroid.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kevin on 2018/4/18.
 */
@Module
public class FragmentModule {
    private Fragment fragment;
    private DialogFragment dialogFragment;

    public FragmentModule(Fragment fragment){
        this.dialogFragment = null;
        this.fragment = fragment;
    }
    public FragmentModule(DialogFragment dialogFragment){
        this.fragment = null;
        this.dialogFragment = dialogFragment;
    }
    @Provides
    @FragmentScope
    Activity provideActivity(){
        if (fragment == null){
            return dialogFragment.getActivity();
        }else{
            return fragment.getActivity();
        }

    }
}
