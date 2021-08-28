package com.example.covidtracker.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.L;
import com.example.covidtracker.Adapters.NewsAdapter;
import com.example.covidtracker.R;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.List;


public class NewsFragment extends Fragment {

    private View view;
    private NewsApiClient newsApiClient;
    private NewsAdapter adapter;
    private RecyclerView newsRecyclerView;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_news, container, false);


        newsApiClient = new NewsApiClient("486c7c896e0144838b78b4cd3f4e588e");
        newsRecyclerView=view.findViewById(R.id.newsRecyclerView);
        newsRecyclerView.setHasFixedSize(true);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getNews();







        return  view;
    }

    private void getNews() {

        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .q("covid")
                        .language("en")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        List<Article> articlesItems=response.getArticles();
                        adapter=new NewsAdapter(getContext(),articlesItems);
                        newsRecyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );

    }
}