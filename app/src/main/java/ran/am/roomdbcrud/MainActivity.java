package ran.am.roomdbcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ran.am.roomdbcrud.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding am;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        am = DataBindingUtil.setContentView(this, R.layout.activity_main);
        am.buttonSave.setOnClickListener(view -> {
            SaveTask st = new SaveTask();
            st.execute();
        });
    }

    public void vw(View view) {
        startActivity(new Intent(MainActivity.this,ShowTblData.class));
    }

    class SaveTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //creating a task
            Task task = new Task();
            task.setTask(am.editTextTask.getText().toString());
            task.setDesc(am.editTextDesc.getText().toString());
            task.setFinishBy(am.editTextFinishBy.getText().toString());
            task.setFinished(false);

            //adding to database
            DatabaseClientt.getInstance(getApplicationContext()).getAppDatabase().taskDao().insert(task);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
          Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
        }
    }
}