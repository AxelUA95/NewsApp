package com.proj.andoid.localnews.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.proj.andoid.localnews.R;
import com.proj.andoid.localnews.api.NYTimesLoader;
import com.proj.andoid.localnews.model.ny_times_responce.Doc;
import com.proj.andoid.localnews.model.ny_times_responce.NYTimesResponce;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * created by Alex Ivanov on 12/20/15.
 */
public class NewsFragment extends BaseFragment {

    @Bind(R.id.tab_recycler_view)
    protected RecyclerView recyclerView;

    private NYTimesLoader timesLoader;
    private NewsAdapter adapter;
    private List<Doc> data = new ArrayList<>();

    @Override
    protected int getContentResource() {
        return R.layout.tab_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new NewsAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        timesLoader = new NYTimesLoader();
        timesLoader.loadByTag("Kiev", new Callback<NYTimesResponce>() {
            @Override
            public void success(NYTimesResponce responce, Response response) {
                data = responce.getResponse().getDocs();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {}
        });
    }

    private class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

        private LayoutInflater inflater;

        public NewsAdapter(Context c) {
            inflater = LayoutInflater.from(c);
        }

        @Override
        public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NewsViewHolder(inflater.inflate(R.layout.times_item, parent, false));
        }

        @Override
        public void onBindViewHolder(NewsViewHolder holder, int position) {
            holder.init(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.textHeadline)
        protected TextView textHeadline;
        @Bind(R.id.textInfo)
        protected TextView textInfo;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void init(Doc doc) {
            textHeadline.setText(doc.getHeadline().getMain());
            textInfo.setText(doc.getSnippet());
        }
    }
}
