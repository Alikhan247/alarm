package kz.alikhan.midterm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_time_picker.*

class MainActivity : AppCompatActivity() {

    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var statusList = mutableListOf<Boolean>()

    override fun onResume() {
        super.onResume()

        rv_recyclerView.layoutManager = LinearLayoutManager(this)
        rv_recyclerView.adapter = RecyclerAdapter(titlesList, descList, statusList)
        loadData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        postToList()
        loadData()


    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("alarmPref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var set: HashSet<String> = HashSet<String>()

        set.add("Hi")
        editor.putStringSet("alarm", set);
        editor.commit()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("alarmPref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var setHours = java.util.HashSet<String>()
        var setMinutes = java.util.HashSet<String>()
        var setNames = java.util.HashSet<String>()


        val datas = sharedPreferences.getStringSet("hours", setHours)
        val datasMinutes = sharedPreferences.getStringSet("minutes", setMinutes)
        val datasNames = sharedPreferences.getStringSet("names", setNames)

        var counter = 0
        print("The name: ")
        for (name in datasNames!!) {
            for (hours in datas!!) {
                for (minute in datasMinutes!!) {
                    addToList(hours + " : " + minute, name, true)
                }
            }
            print(name)
        }
    }

    private fun addToList(title: String, description: String, status: Boolean) {
        titlesList.add(title)
        descList.add(description)
        statusList.add(status)
    }

    private fun postToList() {
        for (i in 1..10) {
            addToList("Aigerim", "0A", true)
        }
    }

    fun newAlarm(view: View) {
        val intent = Intent(this, TimePickerActivity::class.java)
// To pass any data to next activity
        intent.putExtra("keyIdentifier", 1)
// start your next activity
        startActivity(intent)
    }
}