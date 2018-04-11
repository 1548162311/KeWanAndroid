package com.ocn.kewanandroid.core.db;

import com.ocn.kewanandroid.core.dao.HistoryData;

import java.util.List;

/**
 * Created by kevin on 2018/4/11.
 */

public interface DbHelper {

    List<HistoryData> addHistoryData(String data);

    void clearHistoryData();

    List<HistoryData> loadAllHistoryData();
}
