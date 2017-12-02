package com.plan2.example_rest_api.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.plan2.example_rest_api.R;
import com.plan2.example_rest_api.databinding.TictactoeBinding;
import com.plan2.example_rest_api.viewmodel.TicTacToeViewModel;

public class TicTacToeActivity extends AppCompatActivity {

    TicTacToeViewModel viewModel = new TicTacToeViewModel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TictactoeBinding binding = DataBindingUtil.setContentView(this, R.layout.tictactoe);
        binding.setViewModel(viewModel);
        viewModel.onCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tictactoe, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                viewModel.onClickedRequest();
                return  true;
            case    R.id.action_edit:
                Intent intent=new Intent(TicTacToeActivity.this,    EditText.class);
                startActivity(intent);
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
