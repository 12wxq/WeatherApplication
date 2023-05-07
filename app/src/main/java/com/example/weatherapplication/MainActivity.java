package com.example.weatherapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapplication.adapter.FutureWeatherAdapter;
import com.example.weatherapplication.bean.DayWeatherBean;
import com.example.weatherapplication.bean.WeatherBean;
import com.example.weatherapplication.uitl.NetUtil;
import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppCompatSpinner mSpinner;
    private ArrayAdapter<String>mSpAdapter;
    private String[] mCities;//数据资源
    private TextView tvWeather,tvTem,tvTemLowHigh,tvWin,tvAir;
    private ImageView ivWeather;
    private RecyclerView rLvFutureWeather;
    private FutureWeatherAdapter mWeatherAdatper;
    private  DayWeatherBean todayWeather;
    //兰州市、嘉峪关市、金昌市、白银市、天水市、武威市、张掖市、酒泉市、平凉市、庆阳市、定西市、陇南市。
    private String[] hebei=new String[]{"石家庄","辛集","定州","唐山","秦皇岛","邯郸","邢台",
            "保定","张家口","承德","沧州","廊坊","衡水"};
    private String[] shanxi=new String[]{"太原","大同","阳同","长治","晋城","朔州","晋中","运城","忻州","临汾","吕梁"};
    private String[] heilongjiang=new String[]{"哈尔滨","齐齐哈尔",
            "牡丹江","佳木斯","七台河","大庆","黑河","绥化","伊春","鹤岗","双鸭山","鸡西"};
    private String[] jilin=new String[]{"长春","吉林","四平","通化","白山","辽源","白城","松原"};
    private String[] liaoning=new String[]{"沈阳","大连","鞍山","抚顺","本溪","丹东","锦州","营口",
            "阜新","辽阳","铁岭","朝阳","盘锦","葫芦岛"};
    private String[] jiangsu=new String[]{"南京","苏州","无锡","常州","镇江",
            "南通","扬州","泰州","盐城","淮安","宿迁","徐州","连云港"};
    private String[] zhejiang=new String[]{"杭州","舟山","嘉兴","温州","宁波","绍兴","湖州","丽水","台州","金华","衢州"};
    private String[] anhui=new String[]{"合肥","黄山","芜湖","马鞍山","安庆","淮南","阜阳","淮北","铜陵","亳州","宣城","蚌埠",
            "巢湖","六安","滁州","池州","宿州"};
    private String[] fujian=new String[]{"福州","莆田","泉州","漳州","龙岩","三明","南平","宁德"};
    private String[] jiangxi=new String[]{"南昌","九江","上饶","抚州","宜春","吉安","赣州","景德镇","萍乡","新余","鹰潭"};
    private String[] shandong=new String[]{"济南","青岛","淄博","枣庄","东营","烟台","潍坊","济宁","泰安","威海",
            "日照","滨州","德州","聊城" ,"临沂","菏泽","莱芜"};
    private String[] henan=new String[]{"郑州","开封","洛阳","平顶山","安阳","鹤壁","新乡",
            "焦作","濮阳","许昌","漯河","三门峡","商丘","周口","驻马店","南阳","信阳"};
    private String[] hubei=new String[]{"武汉","黄石","襄阳","荆州","宜昌","十堰","孝感","荆门","鄂州","黄冈","咸宁","随州"};
    private String[] hunan=new String[]{"长沙","株洲","湘潭","衡阳","邵阳","岳阳","常德","张家界","益阳","郴州","永州","怀化","娄底"};
    private String[] guangdong=new String[]{"广州","深圳","珠海","东莞","佛山","中山",
            "惠州","汕头","江门","茂名","肇庆","湛江","梅州","汕尾","河源","清远","韶关","揭阳","阳江","潮州","云浮"};
    private String[] hainan=new String[]{"海口","三亚","三沙","儋州"};
    private String[] sichuang=new String[]{"成都","绵阳","自贡","攀枝花","泸州","德阳","广元",
            "遂宁","内江","乐山","资阳","宜宾","南充","达州","雅安","广安","巴中市","眉山市"};
    private String[] guizhou=new String[]{"贵阳","遵义","六盘水","安顺","毕节","铜仁"};
    private String[] yunnan=new String[]{"昆明","曲靖","玉溪","丽江","普洱","保山","昭通","临沧"};
    private String[] shangxi=new String[]{"西安","咸阳","铜川","渭南","延安","榆林","汉中","安康","商洛","宝鸡"};
    private String[] gansu=new String[]{"兰州","嘉峪关","金昌","白银","天水","武威","张掖","酒泉","平凉","庆阳","定西","陇南"};
    private String[] qinghai=new String[]{"西宁","海东"};
    private String[] taiwang=new String[]{"台北","台南","高雄","桃园","台中","新北"};
    private String[] neimenggu=new String[]{"呼和浩特","包头","乌海","赤峰","通辽","鄂尔多斯","呼伦贝尔","乌兰察布","巴彦淖尔"};
    private String[] guangxi=new String[]{"南宁","柳州","桂林","梧州","北海","防城港","钦州","贵港","玉林","百色","贺州","河池","来宾","崇左"};
    private String[] xizang=new String[]{"拉萨","昌都","日喀则","林芝和山南","山南"};
    private String[] ningxia=new String[]{"银川","石嘴山","吴忠","固原","中卫"};
    private String[] xingjiang=new String[]{"乌鲁木齐","克拉玛依","吐鲁番","哈密"};
    private String[] beijing=new String[]{"北京"};
    private String[] tianjing=new String[]{"天津"};
    private String[] shanghai=new String[]{"上海"};
    private String[] chongqing=new String[]{"重庆"};
    private String[] xianggang=new String[]{"香港"};
    private String[] aomen=new String[]{"澳门"};

    private String[][] dataStrings = {hebei,
            shanxi, heilongjiang,jilin,liaoning,jiangsu,zhejiang,anhui,fujian,jiangxi,
            shandong,henan,hubei,hunan,guangdong,hainan,sichuang,guizhou,yunnan,shangxi,gansu,qinghai,
            taiwang,neimenggu,guangxi,xizang,ningxia,xingjiang,beijing,tianjing,shanghai,chongqing,aomen,xianggang
    };

    private AppCompatSpinner mSpinner2;
    private ArrayAdapter<CharSequence> adapter = null;







    private Handler mHandler=new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                String weather =(String) msg.obj;
                Log.d("fan","-----主线程收到了天气数据----"+weather);
                Gson gson=new Gson();
                WeatherBean weatherBean = gson.fromJson(weather, WeatherBean.class);
                Log.d("fan","-----解析后的天气数据----"+weatherBean.toString());
                updataUiOfWeather(weatherBean);
                Toast.makeText(MainActivity.this,"更新天气成功!",Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void updataUiOfWeather(WeatherBean weatherBean) {
        //将数据显示到界面上
        if(weatherBean==null){
            return;
        }
        List<DayWeatherBean> dayWeathers = weatherBean.getDayWeathers();
       todayWeather = dayWeathers.get(0);//显示今天的数据
        if (todayWeather==null){
            return;
        }

        tvTem.setText(todayWeather.getTem());
        tvWeather.setText(todayWeather.getWea()+"("+todayWeather.getDate()+todayWeather.getWeek()+")");
        tvTemLowHigh.setText(todayWeather.getTem2()+"~"+todayWeather.getTem1());
        tvWin.setText(todayWeather.getWin()[0]+todayWeather.getWinSpeed());
        tvAir.setText("空气质量"+todayWeather.getAir()+todayWeather.getAirLevel()+"\n"+todayWeather.getAirTips());
        ivWeather.setImageResource(getImgResOfWeather(todayWeather.getWeaImg()));
        //未来天气展示
        dayWeathers.remove(0);//去掉当天天气
        mWeatherAdatper=new FutureWeatherAdapter(this,dayWeathers);
        rLvFutureWeather.setAdapter(mWeatherAdatper);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rLvFutureWeather.setLayoutManager(layoutManager);

    }
     private int getImgResOfWeather(String Weastr){
        //根据天气显示对应的图标
         int result=0;
         switch (Weastr){
             case "qing":
                 result=R.drawable.biz_plugin_weather_qing;
                 break;
             case "yin":
                 result=R.drawable.biz_plugin_weather_yin;
                 break;
             case "yu":
                 result=R.drawable.biz_plugin_weather_dayu;
                 break;
             case "yun":
                 result=R.drawable.biz_plugin_weather_duoyun;
                 break;
             case "bingbao":
                 result=R.drawable.bingbao;
                 break;
             case "wu":
                 result=R.drawable.biz_plugin_weather_wu;
                 break;
             case "shachen":
                 result=R.drawable.shachenbao;
                 break;
             case "lei":
                 result=R.drawable.biz_plugin_weather_leizhenyu;
                 break;
             case "xue":
                 result=R.drawable.xue;
                 break;
             default:

                 result=R.drawable.biz_plugin_weather_qing;

                 break;
         }
         return result;
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {

    }

    private void initView() {
        mSpinner = findViewById(R.id.sp_p);
        mSpinner2 = findViewById(R.id.sp_city);
        mCities=getResources().getStringArray(R.array.province);
        mSpAdapter=new ArrayAdapter<String>(this,R.layout.sp_item_layout,mCities);
        mSpinner.setAdapter(mSpAdapter);
        //mCities = getResources().getStringArray(R.array.cities);

        // mSpinner.setAdapter(mSpAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item_layout, dataStrings[position]);
                mSpinner2.setAdapter(adapter);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //  String selectedCity = mCities[position];
        //
        //                getWeatherOfCity(selectedCity);
        mSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //parent指的是 spinner2
                String selectedCity = parent.getItemAtPosition(position).toString();
                //getItemPosition获取点击的文本信息
                getWeatherOfCity(selectedCity);

            }

            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        tvWeather = findViewById(R.id.tv_weather);
        tvAir = findViewById(R.id.tv_air);
        tvTem = findViewById(R.id.tv_tem);
        tvTemLowHigh = findViewById(R.id.tv_tem_low_high);
        tvWin = findViewById(R.id.tv_win);
        ivWeather = findViewById(R.id.iv_weather);
        rLvFutureWeather = findViewById(R.id.rlv_future_weather);

        tvAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TipsActivity.class);
                // 将数据传递给tipsActivity
                intent.putExtra("tips", todayWeather);

                startActivity(intent);

            }
        });
    }

    private void getWeatherOfCity(String selectedCity) {
        //开启子线程请求网络
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络
                try {
                    String weatherOfCity = NetUtil.getWeatherOfCity(selectedCity);
                    Message message=Message.obtain();
                    message.what=0;
                    message.obj=weatherOfCity;
                    //使用handler将数据传递给主线程
                    mHandler.sendMessage(message);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}