package com.example.readexceldata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityShowInfo extends AppCompatActivity {
TextView tvOrdinance,tvTitleNo, tvTitle,tvUrl,tvVersion,tvNum,tvContent,tvReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);
        tvOrdinance=findViewById(R.id.tvOrdinance);
        tvTitleNo=findViewById(R.id.tvTitle_no);
        tvTitle=findViewById(R.id.tvTitle);
        tvUrl=findViewById(R.id.tvUrl);
        tvVersion=findViewById(R.id.tvVersion);
        tvNum=findViewById(R.id.tvNumber);
        tvContent=findViewById(R.id.tvContent);
        tvReference=findViewById(R.id.tvReference);
        Intent intent = getIntent();
        if(intent!=null) {
            int ordinancePositionString = intent.getExtras().getInt("ordinancePosition");
            Log.e("positionVar",ordinancePositionString+"");
            RuleRegulation regulation = MyAdaptor.ruleRegulationList.get(ordinancePositionString);
            tvOrdinance.setText(regulation.getOrdinance());
            tvTitleNo.setText(regulation.getTitle_no());
            tvTitle.setText(regulation.getTitle());
            Log.e("link",regulation.getUrl());
//
//            tvTitle.setClickable(true);
//            tvTitle.setMovementMethod(LinkMovementMethod.getInstance());
//            String text = "<a href='"+regulation.getUrl()+"'>"+regulation.getTitle()+"</a>";
//            tvTitle.setText(Html.fromHtml(text));





//
            tvUrl.setText(regulation.getUrl());
            tvVersion.setText(regulation.getVersion());
            tvNum.setText(regulation.getNum());
            tvContent.setText(regulation.getContent());
            tvContent.append("");
            tvReference.setClickable(true);
            tvReference.setMovementMethod(LinkMovementMethod.getInstance());
            String text = "<a href='"+regulation.getUrl()+"'> Reference</a>";
            tvReference.setText(Html.fromHtml(text));

        }else{

            Toast.makeText(this, "no data found", Toast.LENGTH_SHORT).show();
        }

    }
}