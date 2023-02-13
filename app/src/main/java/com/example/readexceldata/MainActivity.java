package com.example.readexceldata;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class MainActivity extends AppCompatActivity {
   ArrayList<RuleRegulation> myList = new ArrayList<RuleRegulation>();
    String TAG ="main";
    private TextView textView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
       List<RuleRegulation> myList =  readExcelFileFromAssets();
        MyAdaptor myAdaptor = new MyAdaptor(myList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdaptor);
    }
    public List<RuleRegulation> readExcelFileFromAssets() {
        try {
            InputStream myInput;
            // initialize asset manager
            AssetManager assetManager = getAssets();
            //  open excel sheet
            myInput = assetManager.open("mdata.xls");
            // Create a POI File System object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            // We now need something to iterate through the cells.
            Iterator<Row> rowIter = mySheet.rowIterator();
            Toast.makeText(this, "running", Toast.LENGTH_SHORT).show();
            int rowno =0;
            textView.append("\n");
            while (rowIter.hasNext()) {
                RuleRegulation ruleRegulation = new RuleRegulation();
                Log.e(TAG, " row no "+ rowno );
                HSSFRow myRow = (HSSFRow) rowIter.next();
                if(rowno !=0) {
                    Iterator<Cell> cellIter = myRow.cellIterator();
                    int colno =0;
                    String sno="", date="", det="";
                    while (cellIter.hasNext()) {
                        HSSFCell myCell = (HSSFCell) cellIter.next();
                        if (colno==0){
                            ruleRegulation.setOrdinance(myCell.toString());
                            sno = myCell.toString();
                        }else if (colno==1){
                            ruleRegulation.setTitle_no(myCell.toString());
                            date = myCell.toString();
                        }else if (colno==2){
                            ruleRegulation.setTitle(myCell.toString());
                            det = myCell.toString();
                        }else if (colno==3){
                            ruleRegulation.setUrl(myCell.toString());
                            det = myCell.toString();
                        }else if (colno==4){
                            ruleRegulation.setVersion(myCell.toString());
                            det = myCell.toString();
                        }else if (colno==5){
                            ruleRegulation.setNum(myCell.toString());
                            det = myCell.toString();
                        }else if (colno==6){
                            ruleRegulation.setContent(myCell.toString());
                            det = myCell.toString();
                        }
                        colno++;
                        Log.e(TAG, " Index :" + myCell.getColumnIndex() + " -- " + myCell.toString());
                    }
                    myList.add(ruleRegulation);
                }
                rowno++;
            }

        } catch (Exception e) {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            Log.e("errorTag", "error "+ e.toString());
        }
        return myList;
    }

}