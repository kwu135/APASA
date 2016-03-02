package com.uscapasa.apasa;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uscapasa.apasa.dummy.DummyContent;
import com.uscapasa.apasa.dummy.DummyContent.DummyItem;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {
    private static final String WP_JSON_URL = "http://www.uscapasa.com/wp-json/wp/v2/posts?page=";
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MyAsyncTask().execute();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }

    public void getWordpressJSON(int page) {

    }

    private class MyAsyncTask extends AsyncTask<String, String, String> {

        protected String doInBackground(String... arg0) {
            String result = null;
            try {
                URL url = new URL(WP_JSON_URL + 4);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                        Log.d("ItemFragment", line);
                    }
                    Log.d("ItemFragment", "REACHED");
                    Log.d("ItemFragment", stringBuilder.toString().substring(0,10));
                    result = stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            /*DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
            HttpPost httpPost = new HttpPost(schoolInfo);
            httpPost.setHeader("Content-type", "application/json");
            InputStream inputStream = null;
            String result = null;
            try {
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                result = stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Exception e) {
                }
            }*/
            /*try {
                JSONArray jsonArray= new JSONArray(result);
                /*JSONObject jsonObject = jsonArray.getJSONObject(0);
                String school_des = jsonObject.getString("SOC_SCHOOL_DESCRIPTION");
                Log.v("TEST", school_des);
                test += school_des;
                Log.v("TEST2", test);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            return result;
        }

        /*protected void onPostExecute(String result) {
            LinearLayout ll = (LinearLayout) findViewById(R.id.buttonLayout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 6, 0, 6);
            Context context = getApplicationContext();
            /*Button myButton = new Button(context);
            myButton.setText("TEXT");
            myButton.setBackgroundColor(Color.LTGRAY);
            myButton.setTextColor(Color.GRAY);
            myButton.setPadding(0,5,0,5);
            ll.addView(myButton, lp);
            Log.v("INC_TEST", "TEST");
            try {
                jsonArray = new JSONArray(result);
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Button myButton = new Button(context);
                    school_des = jsonObject.getString("SOC_SCHOOL_DESCRIPTION");
                    //Log.v("school_des", school_des);
                    myButton.setText(school_des);
                    myButton.setBackgroundColor(Color.LTGRAY);
                    myButton.setTextColor(Color.BLACK);
                    myButton.setId(i);
                    ll.addView(myButton, lp);
                    myButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View arg0) {
                            //Starting a new Intent
                            Intent nextScreen = new Intent(getApplicationContext(), DeptActivity.class);
                            nextScreen.putExtra("term_num", term_num);
                            try {
                                JSONObject tempJSONObject = jsonArray.getJSONObject(arg0.getId());
                                String tempSchoolCode = tempJSONObject.getString("SOC_SCHOOL_CODE");
                                nextScreen.putExtra("school", tempSchoolCode);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //Log.v("ID", "" + arg0.getId());
                            startActivity(nextScreen);
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/
    }
}
