package com.chiplua.listviewadapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyListView extends Activity {
    private ListView listView;
    private ImageButton imgbt_sum;
    private ListViewAdapter listViewAdapter;
    private List<Map<String, Object>> listItems;
    private Integer[] imgeIDs = {R.drawable.cake, R.drawable.gift,
                                 R.drawable.letter, R.drawable.love,
                                 R.drawable.mouse, R.drawable.music, R.drawable.phone};
    private String[] goodsNames = {"蛋糕", "礼物", "邮票", "爱心", "鼠标", "音乐CD", "手机"};
    private String [] goodsDetails = {
            "蛋糕：好好吃。",
            "礼物：礼轻情重。",
            "邮票：环游世界。",
            "爱心：世界都有爱。",
            "鼠标：反应敏捷。",
            "音乐CD：酷我音乐。",
            "手机：智能世界。"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_list_view);

        listView = (ListView) findViewById(R.id.list_goods);
        imgbt_sum = (ImageButton) findViewById(R.id.imgbt_sum);
        imgbt_sum.setOnClickListener(new ClickEvent());
        listItems = getListItems();
        listViewAdapter = new ListViewAdapter(this, listItems); //创建适配器
        listView.setAdapter(listViewAdapter);
    }

    /*
    初始化商品信息
    */
    private List<Map<String, Object>> getListItems() {
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < goodsNames.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", imgeIDs[i]);       //图片资源
            map.put("title", "物品名称：");       //物品标题
            map.put("info", goodsNames[i]);     //物品名称
            map.put("detail", goodsDetails[i]); //物品详情
            listItems.add(map);
        }
        return listItems;
    }

    class ClickEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String goodsList = "";
            for (int i = 0; i < listItems.size(); i++) {
                goodsList += listViewAdapter.hasChecked(i)? goodsNames[i] + " ": "";
            }

            new AlertDialog.Builder(MyListView.this)
                    .setTitle("购物清单：")
                    .setMessage("你好， 你选择了如下上商品：\n" + goodsList)
                    .setPositiveButton("确定", null)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
