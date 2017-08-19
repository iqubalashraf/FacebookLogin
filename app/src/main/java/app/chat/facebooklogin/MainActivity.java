package app.chat.facebooklogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

import app.chat.facebooklogin.dataModel.FacebookLoginResult;

public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        // App code
                        Log.d("MainActivity", loginResult.getAccessToken().toString());
                        GraphRequest req = GraphRequest.newGraphPathRequest(loginResult.getAccessToken(), "me", new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                if(response!=null){
                                    Log.d("FBResponse", response.getJSONObject().toString());
                                    FacebookLoginResult facebookLoginResult = new Gson().fromJson(response.getJSONObject().toString(), FacebookLoginResult.class);
                                    Log.d("Id", facebookLoginResult.getId());
                                    Log.d("Name", facebookLoginResult.getName());
                                    Log.d("email", facebookLoginResult.getEmail());
                                    Log.d("gender", facebookLoginResult.getGender());
                                    Log.d("Name", Profile.getCurrentProfile().getName());
                                    Log.d("Id", Profile.getCurrentProfile().getId());
                                    Log.d("First Name", Profile.getCurrentProfile().getFirstName());
                                    Log.d("Last Name", Profile.getCurrentProfile().getLastName());
                                    Log.d("Link URi", Profile.getCurrentProfile().getLinkUri().toString());
                                    Log.d("Name", Profile.getCurrentProfile().getProfilePictureUri(100,100).toString());
                                }
                            }
                        });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "name,email,gender,birthday");
                        req.setParameters(parameters);
                        req.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        if (exception instanceof FacebookAuthorizationException)
                            if (AccessToken.getCurrentAccessToken() != null)
                                LoginManager.getInstance().logOut();
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
