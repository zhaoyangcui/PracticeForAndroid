package app.company.com.a21_contentprovider;

import android.content.ContentResolver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_add;
    private Button btn_delete;
    private Button btn_notify;
    private Button btn_query;
    private static ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_notify = (Button) findViewById(R.id.btn_notify);
        btn_query = (Button) findViewById(R.id.btn_query);
        resolver = this.getContentResolver();

        btn_add.setOnClickListener(new AddListener());
        btn_query.setOnClickListener(new QueryListener());
        btn_notify.setOnClickListener(new UpdateListener());
        btn_delete.setOnClickListener(new DeleteListener());
    }

    public static ContentResolver getResolver() {
        return resolver;
    }
}
