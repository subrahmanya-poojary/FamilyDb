

package com.example.fdms;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class BookAdapter extends BaseAdapter {
    private final Context mContext;
    private final String[] books;

    // 1
    public BookAdapter(Context mContext, String[] books) {
        this.mContext = mContext;
        this.books = books;

    }

    // 2
    @Override
    public int getCount() {
        return books.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView dummyTextView = new TextView(mContext);
        dummyTextView.setText(books[position]);
        return dummyTextView;

    }

}