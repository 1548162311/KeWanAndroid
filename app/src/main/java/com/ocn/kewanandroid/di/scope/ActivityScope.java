package com.ocn.kewanandroid.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by kevin on 2018/4/16.
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
