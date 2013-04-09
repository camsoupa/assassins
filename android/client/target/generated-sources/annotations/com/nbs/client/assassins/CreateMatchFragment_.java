//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package com.nbs.client.assassins;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.nbs.client.assassins.R.layout;

public final class CreateMatchFragment_
    extends CreateMatchFragment
{

    private View contentView_;
    private Handler handler_ = new Handler();

    private void init_(Bundle savedInstanceState) {
        restClient = new HuntedRestClient_();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    private void afterSetContentView_() {
        password = ((EditText) findViewById(com.nbs.client.assassins.R.id.edit_match_password));
        matchName = ((EditText) findViewById(com.nbs.client.assassins.R.id.edit_match_name));
        btnCreate = ((Button) findViewById(com.nbs.client.assassins.R.id.create_match));
        {
            View view = findViewById(com.nbs.client.assassins.R.id.create_match);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        CreateMatchFragment_.this.onCreateMatchClicked();
                    }

                }
                );
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView_ = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView_ == null) {
            contentView_ = inflater.inflate(layout.create_match_fragment, container, false);
        }
        afterSetContentView_();
        return contentView_;
    }

    public View findViewById(int id) {
        if (contentView_ == null) {
            return null;
        }
        return contentView_.findViewById(id);
    }

    public static CreateMatchFragment_.FragmentBuilder_ builder() {
        return new CreateMatchFragment_.FragmentBuilder_();
    }

    @Override
    public void matchCreatedResult(final MatchResponse response) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    CreateMatchFragment_.super.matchCreatedResult(response);
                } catch (RuntimeException e) {
                    Log.e("CreateMatchFragment_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void createMatchInBackground(final CreateMatchMessage msg) {
        BackgroundExecutor.execute(new Runnable() {


            @Override
            public void run() {
                try {
                    CreateMatchFragment_.super.createMatchInBackground(msg);
                } catch (RuntimeException e) {
                    Log.e("CreateMatchFragment_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    public static class FragmentBuilder_ {

        private Bundle args_;

        private FragmentBuilder_() {
            args_ = new Bundle();
        }

        public CreateMatchFragment build() {
            CreateMatchFragment_ fragment_ = new CreateMatchFragment_();
            fragment_.setArguments(args_);
            return fragment_;
        }

    }

}