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
    "http://a.hiphotos.baidu.com/image/w%3D310/sign=f18ba35fa244ad342ebf8186e0a30c08/95eef01f3a292df58056ada7bf315c6035a873ee.jpg",
    "http://a.hiphotos.baidu.com/image/w%3D310/sign=8f6522cdd500baa1ba2c41ba7710b9b1/d043ad4bd11373f0925a80bea00f4bfbfbed04ad.jpg",
    "http://d.hiphotos.baidu.com/image/w%3D310/sign=67352a678fb1cb133e693a12ed5556da/738b4710b912c8fc9053b745fe039245d7882191.jpg",
    "http://h.hiphotos.baidu.com/image/w%3D310/sign=aeab35d374094b36db921dec93cc7c00/35a85edf8db1cb13d652f71fdf54564e92584b87.jpg",
    "http://f.hiphotos.baidu.com/image/w%3D310/sign=afa57fe3958fa0ec7fc7620c1696594a/5ab5c9ea15ce36d3184c3ee03ef33a87e850b192.jpg",
    "http://d.hiphotos.baidu.com/image/w%3D310/sign=2a21c7ae5bee3d6d22c681ca73166d41/b7003af33a87e9504e36276512385343fbf2b492.jpg",
    "http://g.hiphotos.baidu.com/image/w%3D310/sign=92ee541e1cd8bc3ec60800cbb28ba6c8/0ff41bd5ad6eddc4afb555df3cdbb6fd52663340.jpg",
    "http://a.hiphotos.baidu.com/image/w%3D310/sign=e54ea3bfa486c91708035438f93c70c6/34fae6cd7b899e517f377d5341a7d933c8950d3f.jpg",
    "http://h.hiphotos.baidu.com/image/w%3D310/sign=aa549dcbb0fb43161a1f7c7b10a44642/e850352ac65c1038c47a33c2b0119313b07e89ae.jpg",
    "http://e.hiphotos.baidu.com/image/w%3D310/sign=f71dd8278244ebf86d71623ee9f8d736/30adcbef76094b3630d2fac9a6cc7cd98d109d56.jpg",
    "http://e.hiphotos.baidu.com/image/w%3D310/sign=381e622a31a85edffa8cf822795409d8/bba1cd11728b47103d3df6b6c6cec3fdfc0323bd.jpg",
    "http://d.hiphotos.baidu.com/image/w%3D310/sign=19dc6cb7cd95d143da76e22243f18296/b21c8701a18b87d67c042bab020828381e30fdc4.jpg",
    "http://f.hiphotos.baidu.com/image/w%3D310/sign=c68ff88f4e36acaf59e090fd4cd88d03/5fdf8db1cb134954044353d2534e9258d1094a22.jpg",
    "http://e.hiphotos.baidu.com/image/w%3D310/sign=0984239595ef76c6d0d2fd2aad17fdf6/a9d3fd1f4134970adc17e19b90cad1c8a7865d1a.jpg",
    "http://e.hiphotos.baidu.com/image/w%3D310/sign=a6f0b497708da9774e2f802a8050f872/908fa0ec08fa513dcaf71b7a3e6d55fbb2fbd931.jpg",
    "http://h.hiphotos.baidu.com/image/w%3D310/sign=35749c03e3fe9925cb0c6f5104a95ee4/3ac79f3df8dcd100b5fc7ce7718b4710b8122f97.jpg"};
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
