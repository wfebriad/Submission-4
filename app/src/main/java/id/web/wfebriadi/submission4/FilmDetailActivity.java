package id.web.wfebriadi.submission4;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FilmDetailActivity extends AppCompatActivity {

    public static String TITLE = "title";
    public static String OVERVIEW = "overview";
    public static String RELEASE_DATE = "release_date";
    public static String POSTER = "poster";
    public static String BACKDROP_POSTER = "backdrop_poster";

    private TextView tvTitle, tvOverview, tvReleaseDate;
    private ImageView imgPoster, imgBackdropPoster;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        tvTitle = (TextView)findViewById(R.id.tv_judulFilm);
        tvOverview = (TextView)findViewById(R.id.tv_deskripsiFilm);
        tvReleaseDate = (TextView)findViewById(R.id.tv_tanggalRilis);
        imgPoster = (ImageView)findViewById(R.id.img_posterFilm);
        imgBackdropPoster = (ImageView)findViewById(R.id.poster_backdrop);

        String title = getIntent().getStringExtra(TITLE);
        String overview = getIntent().getStringExtra(OVERVIEW);
        String release_date = getIntent().getStringExtra(RELEASE_DATE);
        String poster = getIntent().getStringExtra(POSTER);
        String backdrop_poster = getIntent().getStringExtra(BACKDROP_POSTER);

        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(release_date);
            SimpleDateFormat new_format_tanggal = new SimpleDateFormat("EEEE, dd/MM/yyyy");
            String tanggal_rilis = new_format_tanggal.format(date);
            tvReleaseDate.setText(tanggal_rilis);
        } catch (Exception e){
            e.printStackTrace();
        }
        tvTitle.setText(title);
        tvOverview.setText(overview);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w500/" + poster).into(imgPoster);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w780/" + backdrop_poster).into(imgBackdropPoster);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(title);


        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
    }
}
