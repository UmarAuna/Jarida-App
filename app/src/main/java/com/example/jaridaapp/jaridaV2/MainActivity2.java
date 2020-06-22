package com.example.jaridaapp.jaridaV2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.jaridaapp.R;
import com.example.jaridaapp.jaridaV1.Detail;
import com.example.jaridaapp.jaridaV1.MainActivity;
import com.example.jaridaapp.jaridaV1.viewmodel.JaridaViewModel;
import com.example.jaridaapp.jaridaV2.adapter.UserAdapter;
import com.example.jaridaapp.jaridaV2.model.ContentItem;
import com.example.jaridaapp.jaridaV2.model.User;
import com.example.jaridaapp.jaridaV2.viewmodel.UserViewModel;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView recyclerView;
    UserViewModel userViewModel;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.recyclerView2);
        swipeRefreshLayout = findViewById(R.id.swiperefresh2);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        getNews();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userViewModel.refresh();
            }
        });

    }

    public void getNews(){
        swipeRefreshLayout.setRefreshing(true);
        UserAdapter adapter = new UserAdapter();

        userViewModel.userPagedList.observe(this, new Observer<PagedList<ContentItem>>() {
            @Override
            public void onChanged(PagedList<ContentItem> contentItems) {
                adapter.submitList(contentItems);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addNews) {
            startActivity(new Intent(MainActivity2.this, Details.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNews();
    }
}