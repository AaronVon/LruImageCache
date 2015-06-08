package com.pioneer.aaron.lruimagecache;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class MainActivity extends ActionBarActivity {
    static final String[] imgUrl = {"http://www.c168c.com/images/attachement/jpg/site132/20110302/0016ecaae7b30ed8449e1c.jpg",
    "http://img0.bdstatic.com/img/image/4a75a05f8041bf84df4a4933667824811426747915.jpg",
    "http://a.hiphotos.baidu.com/image/w%3D310/sign=b0fccc9b8518367aad8979dc1e728b68/3c6d55fbb2fb43166d8f7bc823a4462308f7d3eb.jpg",
    "http://h.hiphotos.baidu.com/image/w%3D310/sign=1db9b167d03f8794d3ff4e2fe2190ead/f636afc379310a553ce58c2bb34543a98326102f.jpg",
    "http://b.hiphotos.baidu.com/image/w%3D310/sign=1af250b2d9c451daf6f60aea86fc52a5/8326cffc1e178a82b826b74cf203738da877e8c0.jpg",
    "http://c.hiphotos.baidu.com/image/w%3D310/sign=0b44bba3b019ebc4c0787098b227cf79/7af40ad162d9f2d32559a029aaec8a136227cca8.jpg",
    "http://e.hiphotos.baidu.com/image/w%3D310/sign=45482aa64cc2d562f208d6ecd71090f3/bd3eb13533fa828b5a6e1925fe1f4134960a5aa3.jpg",
    "http://c.hiphotos.baidu.com/image/w%3D310/sign=8a32ababd12a283443a6300a6bb4c92e/8c1001e93901213f5265f56a51e736d12f2e9536.jpg",
    "http://d.hiphotos.baidu.com/image/w%3D310/sign=385a4532d162853592e0d420a0ee76f2/b03533fa828ba61e0bd9f7ef4534970a304e593e.jpg",
    "http://f.hiphotos.baidu.com/image/w%3D310/sign=e47c1c81d443ad4ba62e40c1b2025a89/8601a18b87d6277fa360d31c2d381f30e924fca0.jpg",
    "http://e.hiphotos.baidu.com/image/w%3D310/sign=442be0de48086e066aa8394a320a7b5a/6a63f6246b600c337bf6a2271e4c510fd8f9a156.jpg",
    "http://h.hiphotos.baidu.com/image/w%3D310/sign=66bd054d5dee3d6d22c681ca73176d41/b7003af33a87e95002aae58614385343faf2b49e.jpg",
    "http://c.hiphotos.baidu.com/image/w%3D310/sign=27b24327952397ddd6799e056983b216/b58f8c5494eef01f78a36205e4fe9925bd317dbe.jpg",
    "http://d.hiphotos.baidu.com/image/w%3D310/sign=06b756084410b912bfc1f0fff3fcfcb5/83025aafa40f4bfb304b8fe7004f78f0f63618ed.jpg",
    "http://h.hiphotos.baidu.com/image/w%3D310/sign=421965d3942397ddd6799e056982b216/b58f8c5494eef01f1d0844f1e5fe9925bc317de1.jpg",
    "http://d.hiphotos.baidu.com/image/w%3D310/sign=52ef001f922bd40742c7d5fc4b889e9c/3812b31bb051f819ddd39d7ddfb44aed2e73e717.jpg",
    "http://f.hiphotos.baidu.com/image/w%3D310/sign=f2843e160e46f21fc9345852c6256b31/96dda144ad34598297f9957f08f431adcaef84ef.jpg",
    "http://a.hiphotos.baidu.com/image/w%3D310/sign=f18ba35fa244ad342ebf8186e0a30c08/95eef01f3a292df58056ada7bf315c6035a873ee.jpg"};
    static final String KEY_URL = "url";
    private ListView mListView;
    private LazyAdapter mAdapter;
    private LinkedList<HashMap<String, Object>> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        items = new LinkedList<>();
        for (String i : imgUrl) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put(KEY_URL, i);
            items.add(hashMap);
        }

        mAdapter = new LazyAdapter(this, items);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
