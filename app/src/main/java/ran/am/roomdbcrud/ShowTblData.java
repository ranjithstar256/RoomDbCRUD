package ran.am.roomdbcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.List;

public class ShowTblData extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tbl_data);
        recyclerView = findViewById(R.id.button_sav);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GetTasks gt = new GetTasks();
        gt.execute();
    }

    class GetTasks extends AsyncTask<Void, Void, List<Task>> {

        @Override
        protected List<Task> doInBackground(Void... voids) {
            List<Task> taskList = DatabaseClientt
                    .getInstance(getApplicationContext())
                    .getAppDatabase()
                    .taskDao()
                    .getAll();
            return taskList;
        }

        @Override
        protected void onPostExecute(List<Task> tasks) {
            super.onPostExecute(tasks);
            TasksAdapter adapter = new TasksAdapter(ShowTblData.this, tasks);
            recyclerView.setAdapter(adapter);
        }
    }
}