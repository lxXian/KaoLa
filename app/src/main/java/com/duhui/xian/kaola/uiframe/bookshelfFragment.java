package com.duhui.xian.kaola.uiframe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.duhui.xian.kaola.R;
import com.duhui.xian.kaola.bookshelf.Books;
import com.duhui.xian.kaola.bookshelf.BookshelfAdapter;
import com.duhui.xian.kaola.data.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by min on 2017/10/31.
 */

public class bookshelfFragment extends Fragment {

    private ArrayList<Books> booksList;
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> productsList;

    private static String url_all_books="http://192.168.1.23/get_all_books.php";

    //JSON Node names
    private static final String TAG_SUCCESS="success";
    private static final String TAG_PRODUCTS ="author";
    private static final String TAG_PID ="id";
    private static final String TAG_NAME = "name";
    private static final String TAG_SUMMARY = "summary";
    private static final String TAG_PUBLIC_TIME = "public_time";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View v =inflater.inflate(R.layout.fragement_bookshelf,container,false);
        ListView sp = (ListView)v.findViewById(R.id.sp_book);
        initSpinner(sp);
        return v;

    }

    private void initSpinner(ListView sp) {
        booksList = Books.getDefaultList();
        BookshelfAdapter adapter = new BookshelfAdapter(getContext(), R.layout.bookshelf_item, booksList, Color.WHITE);
        sp.setAdapter(adapter);
        sp.setOnItemClickListener(new MyClickListener());
        sp.setOnItemLongClickListener(new MyClickLongListener());
    }

    /**监听列表的短按*/
    private class MyClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?>parent,View view,int position,long id){
            String desc=String.format("点击了第%d个行星，它的名字是%s",position+1,booksList.get(position).name);
            Toast.makeText(getActivity(),desc,Toast.LENGTH_LONG).show();
        }
    }

    /**监听列表的长按*/
    private class MyClickLongListener implements OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?>parent,View view,int position,long id){
            String desc=String.format("长点击了第%d个行星，它的名字是%s",position+1,booksList.get(position).name);
            Toast.makeText(getActivity(),desc,Toast.LENGTH_LONG).show();
            return true;
        }
    }

    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadALLBooks extends AsyncTask<String,String,String>{

        /**
         *Before starting background thread show progress dialog
         **/
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog=new ProgressDialog(getActivity());
            pDialog.setMessage("Loading books,Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         *getting All books from url
         * */
        @Override
        protected String doInBackground(String... args){
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_books, "GET", params);

            try{
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);
                if(success == 1) {
                    // products found
                    // Getting Array of Products
                    products = json.getJSONArray(TAG_PRODUCTS);
                    // looping through All Products
                    for(int i = 0; i < products.length(); i++) {
                        JSONObject c = products.getJSONObject(i);
                        // Storing each json item in variable
                        String id = c.getString(TAG_PID);
                        String name = c.getString(TAG_NAME);
                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();
                        // adding each child node to HashMap key => value
                        map.put(TAG_PID, id);
                        map.put(TAG_NAME, name);
                        // adding HashList to ArrayList
                        productsList.add(map);
                    }
                } else{
                    // no products found
                    // Launch Add New product Activity
                    Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
                    // Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            } catch(JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

}
