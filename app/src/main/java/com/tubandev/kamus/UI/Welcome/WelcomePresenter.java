package com.tubandev.kamus.UI.Welcome;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.tubandev.kamus.Database.KamusHelper;
import com.tubandev.kamus.Model.KamusModel;
import com.tubandev.kamus.Prefs.AppPreference;
import com.tubandev.kamus.R;
import com.tubandev.kamus.UI.MainActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by sulistiyanto on 17/03/18.
 */

public class WelcomePresenter implements WelcomeContract.UserActionListener {

    private WelcomeContract.View view;

    public WelcomePresenter(WelcomeContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData(Context context) {
        new LoadData(context).execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {

        private Context context;
        final String TAG = LoadData.class.getSimpleName();
        KamusHelper kamusHelper;
        AppPreference appPreference;
        double progress;
        double maxprogress = 100;

        public LoadData(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            kamusHelper = new KamusHelper(context);
            appPreference = new AppPreference(context);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Boolean firstRun = appPreference.getFirstRun();
            if (firstRun) {
                ArrayList<KamusModel> kamusmodelsInEn = preLoadRaw("in");
                ArrayList<KamusModel> kamusmodelsEnIn = preLoadRaw("en");

                kamusHelper.open();

                //progress = 30;
                /*publishProgress((int) progress);
                Double progressMaxInsert = 80.0;
                Double progressDiff = (progressMaxInsert - progress) / kamusmodelsInEn.size();*/

                kamusHelper.beginTransaction();

                try {
                    for (KamusModel model : kamusmodelsInEn) {
                        kamusHelper.insertTransaction(model, "in");
                        Log.d("insert","true");
                        //progress += progressDiff;
                        //publishProgress((int) progress);
                    }

                    for (KamusModel model : kamusmodelsEnIn) {
                        kamusHelper.insertTransaction(model, "en");
                        Log.d("insert2","true2");
                        //progress += progressDiff;
                        //publishProgress((int) progress);
                    }
                    // Jika semua proses telah di set success maka akan di commit ke database
                    kamusHelper.setTransactionSuccess();
                } catch (Exception e) {
                    // Jika gagal maka do nothing
                    Log.e(TAG, "doInBackground: Exception");
                }
                kamusHelper.endTransaction();

                kamusHelper.close();

                appPreference.setFirstRun(false);

                //publishProgress((int) maxprogress);

            } else {
                try {
                    synchronized (this) {
                        this.wait(2000);

                        publishProgress(50);

                        this.wait(2000);
                        publishProgress((int) maxprogress);
                    }
                } catch (Exception e) {
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            view.setProgressBarValues(values[0]);
        }

        @Override
        protected void onPostExecute(Void result) {
            view.finishLoad();
        }

        public ArrayList<KamusModel> preLoadRaw(String key) {
            ArrayList<KamusModel> kamusModels = new ArrayList<>();
            String line = null;
            BufferedReader reader;
            try {
                Resources res = context.getResources();
                InputStream raw_dict;
                if (key.equals("in")) {
                    raw_dict = res.openRawResource(R.raw.indonesia_english);
                } else {
                    raw_dict = res.openRawResource(R.raw.english_indonesia);
                }

                reader = new BufferedReader(new InputStreamReader(raw_dict));
                int count = 0;
                do {
                    line = reader.readLine();
                    String[] splitstr = line.split("\t");

                    KamusModel kamusModel;
                    kamusModel = new KamusModel(splitstr[0], splitstr[1]);
                    kamusModels.add(kamusModel);
                    count++;
                    Log.d("count", count + " l");
                } while (line != null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return kamusModels;
        }
    }
}
