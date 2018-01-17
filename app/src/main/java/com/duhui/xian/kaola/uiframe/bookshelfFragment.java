package com.duhui.xian.kaola.uiframe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import com.duhui.xian.kaola.R;
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


}
