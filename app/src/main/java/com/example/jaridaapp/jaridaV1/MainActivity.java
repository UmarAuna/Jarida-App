package com.example.jaridaapp.jaridaV1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
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
import android.widget.Button;
import android.widget.EditText;

import com.example.jaridaapp.R;
import com.example.jaridaapp.jaridaV1.adapter.JaridaAdapter;
import com.example.jaridaapp.jaridaV1.model.JaridaListItem;
import com.example.jaridaapp.jaridaV1.viewmodel.JaridaViewModel;
import com.example.jaridaapp.jaridaV2.MainActivity2;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private JaridaViewModel countryViewModel;

    JaridaAdapter countryAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerViewNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewNews = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        countryViewModel = new ViewModelProvider(this).get(JaridaViewModel.class);
        getCountries();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCountries();
            }
        });
    }

   /* private void postCountries(){
        String title, content, author;
        title = edtTitle.getText().toString();
        content = edtContent.getText().toString();
        author = edtAuthor.getText().toString();
        countryViewModel.postAllCountry(title,content,author).observe(this, new Observer<JaridaListItem>() {
            @Override
            public void onChanged(JaridaListItem jaridaListItem) {
                jaridaListItem.setTitle(title);
                jaridaListItem.setContent(content);
                jaridaListItem.setAuthor(author);
            }
        });
    }*/

    private void getCountries() {
        swipeRefreshLayout.setRefreshing(true);
        countryViewModel.getAllCountry().observe(this, new Observer<List<JaridaListItem>>() {
            @Override
            public void onChanged(List<JaridaListItem> jaridaListItems) {
                swipeRefreshLayout.setRefreshing(false);
                prepareRecyclerView(jaridaListItems);
            }
        });
    }

    private void prepareRecyclerView(List<JaridaListItem> countryList){
        countryAdapter = new JaridaAdapter(countryList);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerViewNews.setLayoutManager(new LinearLayoutManager(this));
        }else {
            recyclerViewNews.setLayoutManager(new GridLayoutManager(this, 2));
        }
        recyclerViewNews.setItemAnimator(new DefaultItemAnimator());
        recyclerViewNews.setAdapter(countryAdapter);
        countryAdapter.notifyDataSetChanged();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addPost) {
            startActivity(new Intent(MainActivity.this, Detail.class));
            return true;
        }else if(item.getItemId() == R.id.viewJarida2) {
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
       getCountries();
    }
}