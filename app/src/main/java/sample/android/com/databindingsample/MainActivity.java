package sample.android.com.databindingsample;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.chip.Chip;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

import sample.android.com.databindingsample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recChips;
    private List<String> chipNames = new ArrayList<>();
    private ChipAdapter chipAdapter;

    private CustomPagerAdapter mCustomPagerAdapter;

    private ViewPager mViewPager;

    private PageIndicatorView indicator;

    private int count;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User();
        user.setUsername("Akhil");
        user.setUserDetails("Android Developer");
        user.setUserPhoneNumber("8341770556");
        binding.setUser(user);

        mViewPager = findViewById(R.id.viewPager);

        indicator = findViewById(R.id.pageIndicator);
        indicator.setViewPager(mViewPager);
        /*recChips = findViewById(R.id.rec);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recChips.setLayoutManager(layoutManager);
        setupLeftSwipeListener();
        setupRightSwipeListener();*/

        populateData();


    }

    private void setupLeftSwipeListener() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    Log.i("direction", "left");

                }

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recChips);
    }

    private void setupRightSwipeListener() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.RIGHT) {
                    Log.i("direction", "right");
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recChips);
    }


    // Specifications viewpager pages count
    public int viewPagerCount(List<String> specializationList) {

        if (specializationList.size() > 0) {

            double listCount = specializationList.size();

            double pages = listCount / 10.0;

            count = specializationList.size() / 10;

            if (count < pages) {

                count = count + 1;
            }

            if (count <= 1) {
                return 1;
            } else {
                return count;
            }
        } else {
            count = specializationList.size();
            return count;
        }
    }

    private void populateData() {
        chipNames.add("Business Network");
        chipNames.add("Strictly Startups");
        chipNames.add("Life sciences");
        chipNames.add("Fun zone");
        chipNames.add("Techie zone");
        chipNames.add("Business Network");
        chipNames.add("Strictly Startups");
        chipNames.add("Life sciences");
        chipNames.add("Fun zone");
        chipNames.add("Techie zone");
        chipNames.add("Business Network");
        chipNames.add("Strictly Startups");
        chipNames.add("Life sciences");
        chipNames.add("Fun zone");
        chipNames.add("Techie zone");
        chipNames.add("Fun zone");
        chipNames.add("Techie zone");
        chipNames.add("Business Network");
        chipNames.add("Strictly Startups");
        chipNames.add("Life sciences");
        chipNames.add("Fun zone");
        chipNames.add("Techie zone");

        mViewPager.setOffscreenPageLimit(viewPagerCount(chipNames));
        mCustomPagerAdapter = new CustomPagerAdapter(this, chipNames);
        mViewPager.setAdapter(mCustomPagerAdapter);
        indicator.setViewPager(mViewPager);

       /* chipAdapter = new ChipAdapter(chipNames);
        recChips.setAdapter(chipAdapter);*/
    }

    /*Specialization custom adapter*/
    private class CustomPagerAdapter extends PagerAdapter {
        private RecyclerView recyclerView;
        final Context mContext;
        private ChipAdapter chipAdapter;
        final LayoutInflater mLayoutInflater;

        final List<String> specializationList;

        CustomPagerAdapter(Context context, List<String> specializationList) {
            mContext = context;
            this.specializationList = specializationList;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            Log.i("count", "" + viewPagerCount(specializationList));

            //return specializationList.size();
            return viewPagerCount(specializationList);
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View itemView = mLayoutInflater.inflate(R.layout.chips, container, false);
            List<String> specialization = new ArrayList<>();
            initialiseRecyclerView(itemView);

            if (count <= 1) {
                specialization.addAll(specializationList);
            } else {

                int min = (position * 10);
                int max = min + 10;


                for (int i = min; i < max && i < specializationList.size(); i++) {
                    specialization.add(specializationList.get(i));
                }
            }
            chipAdapter = new ChipAdapter(specialization, mContext);
            recyclerView.setAdapter(chipAdapter);


            container.addView(itemView);

            return itemView;
        }

        private void initialiseRecyclerView(View itemView) {
            recyclerView = itemView.findViewById(R.id.rec);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext, 3);
            recyclerView.setLayoutManager(layoutManager);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

}
