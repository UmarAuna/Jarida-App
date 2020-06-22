package com.example.jaridaapp.jaridaV1;

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
import com.example.jaridaapp.jaridaV1.model.JaridaListItem;
import com.example.jaridaapp.jaridaV1.viewmodel.JaridaViewModel;

import java.util.Objects;

public class Detail extends AppCompatActivity {
    String updateTitle, updateContent, updateAuthor;
    EditText edtTitles, edtContents, edtAuthors;
    private JaridaViewModel countryViewModel;
    Button update, delete;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Add Jarida");
        edtTitles = findViewById(R.id.detail_titles);
        edtContents = findViewById(R.id.detail_content);
        edtAuthors = findViewById(R.id.detail_author);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        update.setText("Add Jarida");
        delete.setVisibility(View.GONE);
        countryViewModel = new ViewModelProvider(this).get(JaridaViewModel.class);

        Intent i = getIntent();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postCountries();
                Toast.makeText(Detail.this, "Jarida Added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Detail.this, MainActivity.class));
            }
        });

        if (i != null && i.hasExtra("id")) {
            id = i.getIntExtra("id",id);
            updateTitle = i.getStringExtra("title");
            updateContent = i.getStringExtra("content");
            updateAuthor = i.getStringExtra("author");

            //save_farm_btn.setText(R.string.update);
            setTitle("Update Jarida");
            update.setText("Update Jarida");

            delete.setVisibility(View.VISIBLE);
            update.setVisibility(View.VISIBLE);

            edtTitles.setText(updateTitle);
            edtContents.setText(updateContent);
            edtAuthors.setText(updateAuthor);

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title, content, author;
                    title = edtTitles.getText().toString();
                    content = edtContents.getText().toString();
                    author = edtAuthors.getText().toString();
                    countryViewModel.updateAllCountry(id, title, content,author).observe(Detail.this, new Observer<JaridaListItem>() {
                        @Override
                        public void onChanged(JaridaListItem jaridaListItem) {
                            jaridaListItem.setId(id);
                            jaridaListItem.setTitle(title);
                            jaridaListItem.setContent(content);
                            jaridaListItem.setAuthor(author);
                            Toast.makeText(Detail.this, "Jarida Updated", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Detail.this, MainActivity.class));
                        }
                    });
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    countryViewModel.deleteAllCountry(id).observe(Detail.this, new Observer<JaridaListItem>() {
                        @Override
                        public void onChanged(JaridaListItem jaridaListItem) {
                            jaridaListItem.setId(id);
                            Toast.makeText(Detail.this, "Jarida Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Detail.this, MainActivity.class));
                        }
                    });
                }
            });

        }

    }



    private void postCountries(){
        String title, content, author;
        title = edtTitles.getText().toString();
        content = edtContents.getText().toString();
        author = edtAuthors.getText().toString();
        countryViewModel.postAllCountry(title,content,author).observe(this, new Observer<JaridaListItem>() {
            @Override
            public void onChanged(JaridaListItem jaridaListItem) {
                jaridaListItem.setTitle(title);
                jaridaListItem.setContent(content);
                jaridaListItem.setAuthor(author);
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