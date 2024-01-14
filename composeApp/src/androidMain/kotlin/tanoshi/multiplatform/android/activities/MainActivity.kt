package tanoshi.multiplatform.android.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import tanoshi.multiplatform.android.util.ApplicationActivity
import tanoshi.multiplatform.android.util.setCrashActivity

class MainActivity : ApplicationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCrashActivity = CrashActivity::class.java
        setContent {
            Column( verticalArrangement = Arrangement.Center , modifier = Modifier.fillMaxSize() ) {
                Text( "Main Acitivity" )
                Button( {
                    throw Exception( "\n\n\n\nWhatever" )
                } ) {
                    Text( "Portrait : ${sharedApplicationData?.portrait}" )
                }
                Button( {
                    startActivity(
                        Intent( this@MainActivity , SecondActivity::class.java )
                    )
                } ) {
                    Text( "Change Activity" )
                }
            }
        }
    }

}

@Preview
@Composable
fun AppAndroidPreview() {}