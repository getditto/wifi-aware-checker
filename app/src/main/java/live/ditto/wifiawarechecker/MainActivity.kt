package live.ditto.wifiawarechecker

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.Locale


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hasFeatureTextView: TextView = findViewById(R.id.has_feature_text_view)
        val iconImageView: ImageView = findViewById(R.id.icon_image_view)
        val modelAndManufacturerTextView: TextView =
            findViewById(R.id.model_and_manufacturer_text_view)
        val androidVersionTextView: TextView = findViewById(R.id.android_version_text_view)

        modelAndManufacturerTextView.text = getDeviceName()
        val version = Build.VERSION.SDK_INT.toString()
        val versionRelease = Build.VERSION.RELEASE
        androidVersionTextView.text =
            getString(R.string.label_android_version, version, versionRelease)


        val learnMoreButton: Button = findViewById(R.id.learn_more_button)
        learnMoreButton.setOnClickListener {
            val uri: Uri =
                Uri.parse("https://developer.android.com/guide/topics/connectivity/wifi-aware")
            val browserIntent = Intent(ACTION_VIEW)
            browserIntent.data = uri
            ContextCompat.startActivity(this, browserIntent, null)
        }

        val hasSystemFeature = packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI_AWARE)
        if (hasSystemFeature) {
            hasFeatureTextView.text = getString(R.string.label_wifi_aware_available)
            hasFeatureTextView.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorAvailable
                )
            )
            iconImageView.setImageDrawable(getDrawable(R.drawable.ic_wifi_yes))
        } else {
            hasFeatureTextView.text = getString(R.string.label_wifi_aware_unavailable)
            hasFeatureTextView.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorUnavailable
                )
            )
            iconImageView.setImageDrawable(getDrawable(R.drawable.ic_wifi_no))
        }
    }

    private fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            capitalize(model)
        } else {
            capitalize(manufacturer) + " " + model
        }
    }


    private fun capitalize(s: String?): String {
        if (s.isNullOrEmpty()) {
            return ""
        }
        return s.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    }
}
