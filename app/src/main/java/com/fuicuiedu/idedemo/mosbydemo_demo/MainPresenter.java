package com.fuicuiedu.idedemo.mosbydemo_demo;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/21 0021.
 */

public class MainPresenter {

    private List<String> mData;
    private MainView mainView;

    public MainPresenter(MainView mainView){
        mData = new ArrayList<>();
        this.mainView = mainView;
    }

    public void loadDate(){
        //异步加载数据
        mainView.showPrb();
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    class MyAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for (int i = 0;i<30;i++){
                mData.add("测试数据="+i);
            }
            mainView.hidePrb();
            mainView.setData(mData);
        }
    }
}
