package com.plan2.example_rest_api.viewmodel;

import android.app.Dialog;
import android.content.Context;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.plan2.example_rest_api.ParkSDK.Debug.Loging;
import com.plan2.example_rest_api.ParkSDK.Network.Volley.CustomVolley;
import com.plan2.example_rest_api.R;
import com.plan2.example_rest_api.model.Board;
import com.plan2.example_rest_api.model.Gson_data;
import com.plan2.example_rest_api.model.Player;

import org.json.JSONObject;

public class TicTacToeViewModel implements ViewModel {

    private Board                                       model;
    private Gson_data                                   gson_data;
    private Context                                     context;
    private static  final   String                      LOG_NAME    =   "TicTacToeViewModel";

    public  final   ObservableArrayMap<String, String>  cells       =   new ObservableArrayMap<>();
    public  final   ObservableField<String>             winner      =   new ObservableField<>();
    public  final   ObservableField<String>             url         =   new ObservableField<>("");
    public  final   ObservableField<String>             palam       =   new ObservableField<>("");
    public  final   ObservableField<String>             response    =   new ObservableField<>("");

    public TicTacToeViewModel() {
        model       =   new Board();
        gson_data   =   new Gson_data();
    }

    public TicTacToeViewModel(Context context) {
        model           =   new Board();
        gson_data       =   new Gson_data();
        this.context    =   context;
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
        String  strUrl      =   url.get();
        String  strPalam    =   palam.get();
        String[]  result    =   strPalam.split(",");

        CustomVolley    customVolley    =   new CustomVolley(context,   LOG_NAME);
        customVolley.setURL(strUrl);

        if(result   !=  null && !result.equals("") && result.length !=  1){
            for (String strResult : result){
                String[]    nameValue   =   strResult.split("=");
                String      name        =   nameValue[0];
                String      value       =   nameValue[1];

                customVolley.setJsonData(name, value);//String

            }
        }else if(!strPalam.isEmpty()){
            String[]    nameValue   =   strPalam.split("=");
            String      name        =   nameValue[0];
            String      value       =   nameValue[1];

            customVolley.setJsonData(name, value);//String
        }

        customVolley.networkConnect(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Loging.i(LOG_NAME, "onResponse(JSONObject response) response : " + response);

                setResponse(response.toString());
                //A_CD_bind responseData = new Gson().fromJson(response.toString(), A_CD_bind.class);
                //setCouponDetail_initData(responseData);

            }
        });
    }

    private void  setResponse(String    response){
        this.response.set(response);
    }
}
