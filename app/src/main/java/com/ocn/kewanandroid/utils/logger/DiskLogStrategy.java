package com.ocn.kewanandroid.utils.logger;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.LogStrategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by kevin on 2018/4/11.
 */

public class DiskLogStrategy implements LogStrategy {

    private final Handler handler;

    DiskLogStrategy(Handler handler){
        this.handler = handler;
    }

    @Override
    public void log(int priority, @Nullable String tag, @NonNull String message) {
        handler.sendMessage(handler.obtainMessage(priority,message));
    }
    static class WriteHandler extends Handler{
        /**
         * 保留7天的日志
         */
        private static final long SAVE_DAYS = 1000 * 60 * 60 * 24 * 7;
        /**
         * 日志文件名格式
         */
        private SimpleDateFormat fileFormat = new SimpleDateFormat("yyyy-MM-dd_HH", Locale.ENGLISH);
        private final String folder;
        private String appName = "Logger";

        WriteHandler(Looper looper,String folder,String appName){
            super(looper);
            this.folder = folder + new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());
            this.appName = appName;
            deleteLoggerFile(folder);
        }

        @Override
        public void handleMessage(Message msg) {
            String content = (String) msg.obj;
            FileWriter fileWriter = null;
            File logFile = getLogFile(folder,appName);
            try {
                fileWriter = new FileWriter(logFile,true);
                writeLog(fileWriter,content);
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (fileWriter!=null){
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        private File getLogFile(String folderName, String fileName) {
            File folder = new File(fileName);
            if (!folder.exists()){
                folder.mkdirs();
            }
            return new File(folder,String.format("%s_%s.log",fileFormat.format(new Date())));
        }

        private void writeLog(FileWriter fileWriter, String content) throws IOException {
            fileWriter.append(content);
        }



        /**
         * 删除SAVE_DAYS天前的日志
         */
        private synchronized void deleteLoggerFile(String path) {
            File file = new File(path);
            if(!file.exists()){
               return;
            }
            File[] files = file.listFiles();
            for (File fil:files){
                // 删除最后修改日期早于七天前的日志
                if(System.currentTimeMillis() - fil.lastModified()>SAVE_DAYS){
                    deleteDirWhiteFile(fil);
                }
            }
        }

        /**
         * 删除指定文件目录下的所有文件
         * @param dir
         */
        private synchronized void deleteDirWhiteFile(File dir) {
            if(dir == null || !dir.exists() || !dir.isDirectory()){
                return;
            }
            for (File file:dir.listFiles()){
                if(file.isFile()){
                    file.delete();
                }else if(file.isDirectory()){
                    deleteDirWhiteFile(file);
                }
            }
            // 删除目录本身
            dir.delete();
        }

    }
}
