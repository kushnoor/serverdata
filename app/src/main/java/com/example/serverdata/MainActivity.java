package com.example.serverdata;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.serverdata.webservice.WebServiceHandler;

import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    TextView uid;
    TextView uname;
    TextView uemail;

    private ProgressDialog pd;
    private static String id;
    private static String name;
    private static String email;
    private static String url = "http://10.0.2.2:8080/serverdata/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uid = (TextView) findViewById(R.id.uid);
        uname = (TextView) findViewById(R.id.uname);
        uemail = (TextView) findViewById(R.id.uemail);
        new ParseJSONTask().execute();
    }

    private class ParseJSONTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MainActivity.this);
            pd.setCancelable(false);
            pd.setTitle("Waiting");
            pd.setMessage("Loading Data, Please wait...");
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            WebServiceHandler webServiceHandler = new WebServiceHandler();
            String jsonstr = webServiceHandler.GetJSONData(url);

            try {
                JSONObject pobj = new JSONObject(jsonstr);
                JSONObject cobj = pobj.getJSONObject("user");
                id = cobj.getString("id");
                name = cobj.getString("name");
                email = cobj.getString("email");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pd.isShowing()) {
                pd.dismiss();
                upDateUI();
            }
        }
    }

    public void upDateUI() {
        uid.setText(id);
        uname.setText(name);
        uemail.setText(email);
    }
}