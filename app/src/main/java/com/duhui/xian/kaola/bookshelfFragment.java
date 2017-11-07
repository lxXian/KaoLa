package com.duhui.xian.kaola;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.graphics.Color;

import java.util.ArrayList;

import com.duhui.xian.kaola.bookshelf.Books;
import com.duhui.xian.kaola.bookshelf.BookshelfAdapter;

/**
 * Created by min on 2017/10/31.
 */

public class bookshelfFragment extends Fragment {

    private ArrayList<Books> booksList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View v =inflater.inflate(R.layout.fragement_bookshelf,container,false);
        initSpinner();
        return v;
    }

    private void initSpinner() {
        booksList = Books.getDefaultList();
 //       BookshelfAdapter adapter = new BookshelfAdapter(getActivity(), R.layout.bookshelf_item, booksList, Color.WHITE);
        Spinner sp = (Spinner)getActivity().findViewById(R.id.sp_book);
        sp.setPrompt("请选择行星");
 /*       sp.setAdapter(adapter);
        sp.setSelection(0);
        sp.setOnItemSelectedListener(new MySelectedListener());*/
    }

    private class MySelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            Toast.makeText(getActivity(), "您选择的是"+ booksList.get(arg2).name, Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}
