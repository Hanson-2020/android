package cn.mmvtc.shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingActivity extends Activity{
    private ListView mListView;
    SQLiteHelper mSQLiteHelper;
    String id;
    TextView Shopping_name,content;
    //商品名称与价格数据集合
    private String[] titles={
            "静夜","观音莲","树冰","乌木","月影","白美人","碧光环","蓝石莲","蓝苹果","黑法师","五十铃玉","碧玉","菲欧娜","黄熊(熊童子)","劳伦斯","奥利维亚"
    };
    private  String[] prices={
            "10元","5元","20元","12元","30元","2元","5元/5个","10元","5元","25元","15元","5元","5元","65元","15元","4元"
    };
    //图片数据集合
    private int[] icons={R.drawable.num1,R.drawable.num2,R.drawable.
            num3,R.drawable.num4,R.drawable.num5,R.drawable.num6,R.drawable.num7,R.drawable.num8,R.drawable.num9,R.drawable.num10,R.drawable.num11,R.drawable.num12,R.drawable.num13,R.drawable.mun14,R.drawable.num15,R.drawable.num16};
    private android.view.LayoutInflater LayoutInflater;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        ImageView car=(ImageView) findViewById(R.id.car);
        mListView=(ListView) findViewById(R.id.lv);//初始化ListView控件
        MybaseAdapter mAdapter =new MybaseAdapter();//
        mSQLiteHelper = new SQLiteHelper(this);
        mListView.setAdapter(mAdapter);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShoppingActivity.this,ShoppinglistActivity.class);
                startActivityForResult(intent,1);
            }
        });
        initData();
    }
    protected void initData(){
//        mSQLiteHelper=new SQLiteHelper(this);
//        Shopping_name.setText("购物车");
}
    class MybaseAdapter extends BaseAdapter {
            @Override
            public int getCount() { //获取item的总数
                return titles.length;//返回ListView Item条目的总数
            }
            @Override
            public Object getItem(int position) {
                return titles[position];//返回Item的数据对象
            }
            @Override
            public long getItemId(int position) {
                return position; //返回Item的id
            }
            //得到Item的View视图
            class ViewHolder {
                TextView title, price;
                ImageView iv;
                Button addshop;

            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = View.inflate(ShoppingActivity.this, R.layout.list_item, null);
                    holder = new ViewHolder();
                    holder.title = (TextView) convertView.findViewById(R.id.title);
                    holder.price = (TextView) convertView.findViewById(R.id.price);
                    holder.iv = (ImageView) convertView.findViewById(R.id.iv);
                    holder.addshop = (Button) convertView.findViewById(R.id.addshop);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.addshop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean n = mSQLiteHelper.insertData(titles[position],prices[position],icons[position]);
                        if(n){
                            Toast.makeText(ShoppingActivity.this,"加入购物车成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ShoppingActivity.this,"加入购物车失败",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                holder.title.setText(titles[position]);
                holder.price.setText(prices[position]);
                holder.iv.setBackgroundResource(icons[position]);
                return convertView;
            }
        }
    }
