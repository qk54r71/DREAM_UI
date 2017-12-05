package com.plan2.example_rest_api.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;
import android.util.Log;

import com.plan2.example_rest_api.ParkSDK.Network.OKHTTP.HttpConnection;
import com.plan2.example_rest_api.model.Board;
import com.plan2.example_rest_api.model.Gson_data;
import com.plan2.example_rest_api.model.Player;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class TicTacToeViewModel implements ViewModel {

    private Board model;
    private Gson_data gson_data;
    private Context context;
    private static final String LOG_NAME = "TicTacToeViewModel";
    private JSONObject mJsonObject;
    private HttpConnection httpConnection;

    public final ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();
    public final ObservableField<String> winner = new ObservableField<>();
    public final ObservableField<String> url = new ObservableField<>("");
    public final ObservableField<String> palam = new ObservableField<>("");
    public final ObservableField<String> response = new ObservableField<>("");

    public TicTacToeViewModel() {
        model = new Board();
        gson_data = new Gson_data();
    }

    public TicTacToeViewModel(Context context) {
        model = new Board();
        gson_data = new Gson_data();
        this.context = context;
    }

    @Override
    public void onCreate() {
        url.set("http://www.dreamlib.co.kr/rest/");
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void onResetSelected() {
        model.restart();
        winner.set(null);
        cells.clear();
    }

    public void onClickedCellAt(int row, int col) {
        Player playerThatMoved = model.mark(row, col);
        cells.put("" + row + col, playerThatMoved == null ? null : playerThatMoved.toString());
        winner.set(model.getWinner() == null ? null : model.getWinner().toString());
    }

    public void onClickedRequest() {
        httpConnection  =   new HttpConnection();
        String strPalam = palam.get();
        String[] result = strPalam.split(",");

        mJsonObject = new JSONObject();

        if (result != null && !result.equals("") && result.length != 1) {
            for (String strResult : result) {
                String[] nameValue = strResult.split("=");
                String name = nameValue[0];
                String value = nameValue[1];
                try {
                    mJsonObject.put(name, value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (!strPalam.isEmpty()) {
            String[] nameValue = strPalam.split("=");
            String name = nameValue[0];
            String value = nameValue[1];
            try {
                mJsonObject.put(name, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        new Thread() {
            public void run() {
                try {
                    String result = httpConnection.post(url.get(), String.valueOf(mJsonObject));
                    Log.d("TicTacToe", result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void setResponse(String response) {
        this.response.set(response);
    }
}
