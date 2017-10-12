package anastasakis.kosta.nounlister;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class ListCreateFormActivity extends AppCompatActivity {

    ArrayList<String> fullWordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_create_form);

        NumberPicker picker = (NumberPicker) findViewById(R.id.picker);
        picker.setMinValue(1);
        picker.setMaxValue(100);
        picker.setWrapSelectorWheel(true);

        Button createBtn = (Button) findViewById(R.id.btn_generate);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NumberPicker picker = (NumberPicker) findViewById(R.id.picker);
                ArrayList<String> words = generateList(picker.getValue());

                Intent intent = new Intent(getBaseContext(), WordList.class);
                intent.putStringArrayListExtra("wordlist", words);
                startActivity(intent);
            }
        });

        fullWordList = new ArrayList<>();
        try {
            AssetManager am = getAssets();

            InputStream is = am.open("words.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while(line != null){
                fullWordList.add(line);
                line = reader.readLine();
            }
        }
        catch (Exception e) {}

    }

    private ArrayList<String> generateList(int n){
        ArrayList<String> words = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < n; i++){
            words.add(
                fullWordList.get(
                        rand.nextInt(fullWordList.size())));
        }

        return words;
    }


}
