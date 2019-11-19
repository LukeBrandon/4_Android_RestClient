package dev.lukeb.a4_android_restclient.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import dev.lukeb.a4_android_restclient.R;
import dev.lukeb.a4_android_restclient.model.Comment;

public class CreateCommentActivity extends AppCompatActivity {
    private static final String TAG = "CreateCommentActivity";

    public static final String COMMENT_INTENT_TAG = "Comment";

    Comment comment;

    EditText emailEditText;
    EditText nameEditText;
    EditText bodyEditText;

    Button postComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_comment);

        this.emailEditText = findViewById(R.id.emailEditText);
        this.nameEditText = findViewById(R.id.nameEditText);
        this.bodyEditText = findViewById(R.id.bodyEditText);

        this.postComment = findViewById(R.id.postCommentButton);
        this.postComment.setOnClickListener(v -> {
            postComment();
        });


    }

    private void postComment(){
        comment = new Comment(101, 101, nameEditText.getText().toString(), emailEditText.getText().toString(), bodyEditText.getText().toString());

        Intent data = new Intent();
        data.putExtra(COMMENT_INTENT_TAG, comment);
        setResult(RESULT_OK, data);

        finish();
    }
}
