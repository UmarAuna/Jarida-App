package com.example.jaridaapp.jaridaV2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jaridaapp.R;
import com.example.jaridaapp.jaridaV1.Detail;
import com.example.jaridaapp.jaridaV1.MainActivity;
import com.example.jaridaapp.jaridaV1.viewmodel.JaridaViewModel;
import com.example.jaridaapp.jaridaV2.model.ContentItem;
import com.example.jaridaapp.jaridaV2.viewmodel.NewsViewModel;

import java.util.Objects;

public class Details extends AppCompatActivity {
    String updateTitle, updateContent, updateAuthor;
    EditText edtTitles, edtContents, edtAuthors;
    private NewsViewModel countryViewModel;
    Button update, delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Add News");

        edtTitles = findViewById(R.id.detail_titles2);
        edtContents = findViewById(R.id.detail_content2);
        edtAuthors = findViewById(R.id.detail_author2);
        update = findViewById(R.id.update2);
        delete = findViewById(R.id.delete2);
        update.setText("Add Jarida");
        delete.setVisibility(View.GONE);
        countryViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postNews();
               /* Toast.makeText(Details.this, "News Added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Details.this, MainActivity.class));*/
            }
        });
    }

    private void postNews() {
        String title, content, author;
        title = edtTitles.getText().toString();
        content = edtContents.getText().toString();
        author = edtAuthors.getText().toString();
        countryViewModel.postAllNews(title, content, author).observe(this, new Observer<ContentItem>() {
            @Override
            public void onChanged(ContentItem contentItem) {
                contentItem.setTitle(title);
                contentItem.setContent(author);
                contentItem.setAuthor(author);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}