package app.chat.facebooklogin;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by ashrafiqubal on 19/08/17.
 */

public class ApplicationClass extends Application {

    public ApplicationClass() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
