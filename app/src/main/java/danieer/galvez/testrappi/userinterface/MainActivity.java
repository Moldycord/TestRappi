package danieer.galvez.testrappi.userinterface;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import danieer.galvez.testrappi.R;
import danieer.galvez.testrappi.userinterface.fragment.PopularMoviesFragment;
import danieer.galvez.testrappi.userinterface.fragment.RatedMoviesFragment;
import danieer.galvez.testrappi.userinterface.fragment.UpcomingMoviesFragment;
import danieer.galvez.testrappi.userinterface.presenter.adapter.ViewPagerAdapter;
import danieer.galvez.testrappi.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private RatedMoviesFragment ratedMoviesFragment;
    private UpcomingMoviesFragment upcomingMoviesFragment;
    private PopularMoviesFragment popularMoviesFragment;

    private MainViewModel mainViewModel;

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
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        upcomingMoviesFragment = UpcomingMoviesFragment.newInstance();
        popularMoviesFragment = PopularMoviesFragment.newInstance();
        ratedMoviesFragment = RatedMoviesFragment.newInstance();
        adapter.addFragment(upcomingMoviesFragment, "Upcoming");
        adapter.addFragment(popularMoviesFragment, "Popular");
        adapter.addFragment(ratedMoviesFragment, "Rated");
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


}
