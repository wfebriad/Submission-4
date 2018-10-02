package id.web.wfebriadi.submission4.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import id.web.wfebriadi.submission4.FilmItem;
import id.web.wfebriadi.submission4.R;
import id.web.wfebriadi.submission4.adapter.NowPlayingUpcommingAdapter;

import static id.web.wfebriadi.submission4.BuildConfig.API_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment {

    RecyclerView rvNowPlayingFragment;
    NowPlayingUpcommingAdapter listAdapter;
    ArrayList<FilmItem> filmItemArrayList = new ArrayList<>();
    Bundle bundle;
    private JSONObject jsonObject;

    public NowPlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false);
    }
    public void onViewCreated (View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        rvNowPlayingFragment = (RecyclerView)v.findViewById(R.id.rvNowPlaying);
        rvNowPlayingFragment.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        rvNowPlayingFragment.setHasFixedSize(true);

        listAdapter = new NowPlayingUpcommingAdapter(this.getActivity());
        bundle = new Bundle();

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key="+API_KEY+"&language=en_US";

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray getResult = responseObject.getJSONArray("results");
                    jsonObject = responseObject;

                    for (int i = 0; i < getResult.length(); i++) {
                        JSONObject json = getResult.getJSONObject(i);
                        FilmItem film = new FilmItem(json);
                        filmItemArrayList.add(film);
                    }
                    listAdapter.ListFilm(filmItemArrayList);
                    rvNowPlayingFragment.setAdapter(listAdapter);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

}
