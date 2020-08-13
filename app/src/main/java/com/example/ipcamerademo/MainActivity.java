package com.example.ipcamerademo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.rvirin.onvif.onvifcamera.OnvifListener;
import com.rvirin.onvif.onvifcamera.OnvifRequest;
import com.rvirin.onvif.onvifcamera.OnvifResponse;

import static com.rvirin.onvif.onvifcamera.OnvifDeviceKt.currentDevice;

public class MainActivity extends AppCompatActivity implements OnvifListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void requestPerformed(OnvifResponse response) {
        if (!response.getSuccess()) {

            Toast.makeText(this, "⛔️ Request failed: ${response.request.type}", Toast.LENGTH_SHORT).show();
        }
        // if GetServices have been completed, we request the device information

//        else if (response.getRequest().getType()== OnvifRequest.Type.GetDeviceInformation) {
//            currentDevice.getDeviceInformation();
//        }

        // if GetDeviceInformation have been completed, we request the profiles
        else if (response.getRequest().getType()== OnvifRequest.Type.GetDeviceInformation) {


            Toast.makeText(this, "Device information retrieved", Toast.LENGTH_SHORT).show();

            currentDevice.getProfiles();

        }
        // if GetProfiles have been completed, we request the Stream URI
        else if (response.getRequest().getType()== OnvifRequest.Type.GetProfiles) {
            int profilesCount = currentDevice.getMediaProfiles().size();
            Toast.makeText(this, "$profilesCount profiles retrieved 😎", Toast.LENGTH_SHORT).show();
            currentDevice.getStreamURI();

        }
        // if GetStreamURI have been completed, we're ready to play the video
        else if (response.getRequest().getType()== OnvifRequest.Type.GetStreamURI) {

            Toast.makeText(this, "Stream URI retrieved,\\nready for the movie \uD83C\uDF7F", Toast.LENGTH_SHORT).show();
        }
    }
}
