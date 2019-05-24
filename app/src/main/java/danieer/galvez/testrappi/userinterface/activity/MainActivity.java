package danieer.galvez.testrappi.userinterface.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import danieer.galvez.testrappi.R;
import danieer.galvez.testrappi.userinterface.fragment.PopularMoviesFragment;
import danieer.galvez.testrappi.userinterface.fragment.RatedMoviesFragment;
import danieer.galvez.testrappi.userinterface.fragment.UpcomingMoviesFragment;
import danieer.galvez.testrappi.userinterface.presenter.adapter.ViewPagerAdapter;
import danieer.galvez.testrappi.userinterface.presenter.interfaces.OnQueryChangedListener;
import danieer.galvez.testrappi.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private RatedMoviesFragment ratedMoviesFragment;
    private UpcomingMoviesFragment upcomingMoviesFragment;
    private PopularMoviesFragment popularMoviesFragment;
    private ViewPagerAdapter adapter;

    private MainViewModel mainViewModel;
    private SearchView searchView;
    private OnQueryChangedListener onQueryChangedListener, onQueryChangedListener2, onQueryChangedListener3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializeViews();
    }

    private void initializeViews() {
        viewPager = findViewById(R.id.fragment_view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        initializeElements();
    }


    private void initializeElements() {

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        setupViewModel();
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        upcomingMoviesFragment = UpcomingMoviesFragment.newInstance();
        popularMoviesFragment = PopularMoviesFragment.newInstance();
        ratedMoviesFragment = RatedMoviesFragment.newInstance();
        adapter.addFragment(upcomingMoviesFragment, "Upcoming");
        setOnQueryChangedListener(upcomingMoviesFragment.onQueryChangedListener);
        adapter.addFragment(popularMoviesFragment, "Popular");
        setOnQueryChangedListener2(popularMoviesFragment.onQueryChangedListener);
        adapter.addFragment(ratedMoviesFragment, "Rated");
        setOnQueryChangedListener3(ratedMoviesFragment.onQueryChangedListener);
        viewPager.setAdapter(adapter);
    }

    private void setupViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (viewPager.getCurrentItem() == 0) {
                    onQueryChangedListener.onQueryChanged(query);
                } else if (viewPager.getCurrentItem() == 1) {
                    onQueryChangedListener2.onQueryChanged(query);
                } else if (viewPager.getCurrentItem() == 2) {
                    onQueryChangedListener3.onQueryChanged(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (viewPager.getCurrentItem() == 0) {
                    onQueryChangedListener.onQueryChanged(newText);
                } else if (viewPager.getCurrentItem() == 1) {
                    onQueryChangedListener2.onQueryChanged(newText);
                } else if (viewPager.getCurrentItem() == 2) {
                    onQueryChangedListener3.onQueryChanged(newText);
                }
                return false;
            }
        });
        return true;
    }


    public void setOnQueryChangedListener(OnQueryChangedListener onQueryChangedListener) {
        this.onQueryChangedListener = onQueryChangedListener;
    }

    public void setOnQueryChangedListener2(OnQueryChangedListener onQueryChangedListener2) {
        this.onQueryChangedListener2 = onQueryChangedListener2;
    }

    public void setOnQueryChangedListener3(OnQueryChangedListener onQueryChangedListener3) {
        this.onQueryChangedListener3 = onQueryChangedListener3;
    }
}
