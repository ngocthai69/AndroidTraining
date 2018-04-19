package android.thaihn.androidadvance.urlconnection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.thaihn.androidadvance.BaseActivity;
import android.thaihn.androidadvance.R;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class URLConnectionActivity extends BaseActivity {

    public static final String TAG = URLConnectionActivity.class.getSimpleName();

    private RecyclerView mRecycle;
    private RepoAdapter mAdapter;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_urlconnection;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        mRecycle = findViewById(R.id.recycle_repo);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        );
        mRecycle.setHasFixedSize(true);
        mRecycle.setLayoutManager(linearLayoutManager);
        mRecycle.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RepoAdapter(this, new ArrayList<RepositoryObject>());
        mRecycle.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        new LoadRepo().execute("https://api.github.com/users/google/repos");
    }


    public class LoadRepo extends AsyncTask<String, Void, ArrayList<RepositoryObject>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<RepositoryObject> doInBackground(String... strings) {
            HttpURLConnection urlConnection = null;
            ArrayList<RepositoryObject> list = null;
            String result = "";
            URL url = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                int code = urlConnection.getResponseCode();

                if (code == 200) {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    if (in != null) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                        String line = "";

                        while ((line = bufferedReader.readLine()) != null) {
                            result += line;
                        }
                    }
                    in.close();
                }
                list = parseData(result);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<RepositoryObject> repositoryObjects) {
            super.onPostExecute(repositoryObjects);
            if (repositoryObjects != null) {
                mAdapter.setListRepo(repositoryObjects);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    private ArrayList<RepositoryObject> parseData(String line) {
        ArrayList<RepositoryObject> listRepo = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(line);
            Log.i(TAG, "parseData: size " + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                RepositoryObject repositoryObject = new RepositoryObject();

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject jsonOwner = jsonObject.getJSONObject("owner");
                // parser owner
                String login = jsonOwner.optString("login", "thaihn");
                int id = jsonOwner.optInt("id", 69);
                String avatar = jsonOwner.optString("avatar_link", "aaaa");
                String url = jsonOwner.optString("url", "aaa");
                String repo_url = jsonOwner.optString("repos_url", "aaa");
                OwnerObject object = new OwnerObject(login, id, avatar, url, repo_url);
                repositoryObject.setOwner(object);
                // repo
                String name = jsonObject.optString("name", "GitHub");
                repositoryObject.setName(name);
                String language = jsonObject.optString("language", "Android");
                repositoryObject.setLanguage(language);
                String branch = jsonObject.optString("default_branch", "Thaihn");
                repositoryObject.setDefault_branch(branch);
                String ssh = jsonObject.optString("ssh_url", "aaaa");
                repositoryObject.setSsh_url(ssh);
                String desc = jsonObject.optString("description", "Not desc");
                repositoryObject.setDescription(desc);
                listRepo.add(repositoryObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listRepo;
    }
}
