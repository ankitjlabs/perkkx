package com.example.perkkxapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity {
 
  private static String CLIENT_ID = "607861358613-0rj48gh23nbrbier5hg9sa59m1r9q7nk.apps.googleusercontent.com";
//Use your own client id
  private static String CLIENT_SECRET ="4/rr_jhnfoWurwUKZsMrvPSeydlMu0IaXPYgDVPEXI8";
//Use your own client secret
//private static String REDIRECT_URI="urn:ietf:wg:oauth:2.0:oob";
  private static String REDIRECT_URI="http://localhost";
  private static String GRANT_TYPE="authorization_code";
  private static String TOKEN_URL ="https://accounts.google.com/o/oauth2/token";
  private static String OAUTH_URL ="https://accounts.google.com/o/oauth2/auth";
  private static String OAUTH_SCOPE="https://www.googleapis.com/auth/urlshortener";
  private static String TAG= LoginActivity.class.getSimpleName();
  //Change the Scope as you need
  WebView web;
  Button auth;
  SharedPreferences pref;
  //TextView Access;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login_layout);
    pref = getSharedPreferences("AppPref", MODE_PRIVATE);
        //Access =(TextView)findViewById(R.id.desc);
    auth = (Button)findViewById(R.id.button);
    auth.setOnClickListener(new View.OnClickListener() {
      Dialog auth_dialog;
      @SuppressLint("SetJavaScriptEnabled") @Override
      public void onClick(View arg0) {
        // TODO Auto-generated method stub
        auth_dialog = new Dialog(LoginActivity.this);
        auth_dialog.setContentView(R.layout.auth_dialog);
        web = (WebView)auth_dialog.findViewById(R.id.webv);
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(OAUTH_URL+"?redirect_uri="+REDIRECT_URI+"&response_type=code&client_id="+CLIENT_ID+"&scope="+OAUTH_SCOPE);
        web.setWebViewClient(new WebViewClient() {
 
              boolean authComplete = false;
              Intent resultIntent = new Intent();
 
              @Override
              public void onPageStarted(WebView view, String url, Bitmap favicon){
               super.onPageStarted(view, url, favicon);
 
              }
              String authCode;
              @Override
              public void onPageFinished(WebView view, String url) {
                  super.onPageFinished(view, url);
                  Log.d(TAG, "Here Checking Url:-" + url);
 
                  if (url.contains("?code=") && authComplete != true) {
                      Uri uri = Uri.parse(url);
                      authCode = uri.getQueryParameter("code");
                      Log.i("", "CODE : " + authCode);
                      authComplete = true;
                      resultIntent.putExtra("code", authCode);

                      Intent i=new Intent(LoginActivity.this,MainActivity.class);
                      startActivity(i);
                     // MainActivity.this.setResult(Activity.RESULT_OK, resultIntent);
                     // setResult(Activity.RESULT_CANCELED, resultIntent);
                      SharedPreferences.Editor edit = pref.edit();
                      edit.putString("Code", authCode);
                      edit.commit();
                      auth_dialog.dismiss();
                      new TokenGet().execute();
                     Toast.makeText(getApplicationContext(), "Authorization Code is: " + authCode, Toast.LENGTH_SHORT).show();
                  }else if(url.contains("error=access_denied")){
                      Log.i("", "ACCESS_DENIED_HERE");
                      resultIntent.putExtra("code", authCode);
                      authComplete = true;
                      setResult(Activity.RESULT_CANCELED, resultIntent);
                      Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                      auth_dialog.dismiss();
                  }
              }
          });
        auth_dialog.show();
        auth_dialog.setTitle("Authorize With Google");
        auth_dialog.setCancelable(true);
      }
    });
  }
 
   private class TokenGet extends AsyncTask<String, String, JSONObject> {
          private ProgressDialog pDialog;
          String Code;
         @Override
         protected void onPreExecute() {
             super.onPreExecute();
             pDialog = new ProgressDialog(LoginActivity.this);
             pDialog.setMessage("Contacting Google ...");
             pDialog.setIndeterminate(false);
             pDialog.setCancelable(true);
             Code = pref.getString("Code", "");
             pDialog.show();
         }
 
         @Override
         protected JSONObject doInBackground(String... args) {
             GetAccessToken jParser = new GetAccessToken();
             JSONObject json = jParser.gettoken(TOKEN_URL,Code,CLIENT_ID,CLIENT_SECRET,REDIRECT_URI,GRANT_TYPE);
             Log.d(TAG, "Checking here json object again" + json);
             return json;
         }
 
          @Override
          protected void onPostExecute(JSONObject json) {
              pDialog.dismiss();
              if (json != null){
 
               try {
 
              String tok = json.getString("access_token");
              String expire = json.getString("expires_in");
              String refresh = json.getString("refresh_token");
 
                 Log.d("Token Access", tok);
                 Log.d("Expire", expire);
                 Log.d("Refresh", refresh);
                   auth.setText("Authenticated");
                  // Access.setText("Access Token:"+tok+"nExpires:"+expire+"nRefresh Token:"+refresh);
          } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
 
                    }else{
               Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
               pDialog.dismiss();
           }
          }
     }
 
}