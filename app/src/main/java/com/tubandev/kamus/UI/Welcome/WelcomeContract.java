package com.tubandev.kamus.UI.Welcome;

import android.content.Context;

/**
 * Created by sulistiyanto on 17/03/18.
 */

public interface WelcomeContract {

    interface View {
        void setProgressBarValues(Integer value);
        void finishLoad();

    }

    interface UserActionListener {
        void loadData(Context context);
    }
}
