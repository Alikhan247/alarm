package kz.alikhan.midterm

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_time_picker.*
import java.util.HashSet

class TimePickerActivity : AppCompatActivity() {
    var am_pm = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_picker)

        val timePicker = findViewById<TimePicker>(R.id.picker)
        picker.setOnTimeChangedListener { _, hour, minute -> var hour = hour

            // AM_PM decider logic
            when {hour == 0 -> { hour += 12
                am_pm = "AM"
            }
                hour == 12 -> am_pm = "PM"
                hour > 12 -> { hour -= 12
                    am_pm = "PM"
                }
                else -> am_pm = "AM"
            }

            Toast.makeText(this, am_pm, Toast.LENGTH_SHORT)
            Toast.makeText(this, hour.toString(), Toast.LENGTH_SHORT)
            Toast.makeText(this, minute.toString(), Toast.LENGTH_SHORT)
//            if (textView != null) {
//                val hour = if (hour < 10) "0" + hour else hour
//                val min = if (minute < 10) "0" + minute else minute
//                // display format of time
//                val msg = "Time is: $hour : $min $am_pm"
//                textView.text = msg
//                textView.visibility = ViewGroup.VISIBLE
//            }
        }
    }




    @RequiresApi(Build.VERSION_CODES.M)
    fun saveData(view: View) {
        val sharedPreferences = getSharedPreferences("alarmPref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        var setHours = HashSet<String>()
        var setMinutes = HashSet<String>()
        var setNames = HashSet<String>()


        val datas = sharedPreferences.getStringSet("hours", setHours)
        val datasMinutes = sharedPreferences.getStringSet("minutes", setMinutes)
        val datasNames = sharedPreferences.getStringSet("names", setNames)

        datas!!.add(picker.hour.toString())
        datasMinutes!!.add(picker.minute.toString())
        datasNames!!.add(alarmName.text.toString())

        for (name in datas!!){
            println("hours:" + name)
        }

        for (name in datasMinutes!!){
            println("minutes:" + name)
        }
        for (name in datasNames!!){
            println("name:" + name)
        }


        editor.putStringSet("hours", datas);
        editor.putStringSet("minutes", datasMinutes);
        editor.putStringSet("names", datasNames);
        editor.commit()
    }

}