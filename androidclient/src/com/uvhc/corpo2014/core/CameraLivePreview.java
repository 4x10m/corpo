package com.uvhc.corpo2014.core;

import java.io.IOException;  
import java.util.List;  
  
import android.content.Context;  
import android.hardware.Camera;  
import android.hardware.Camera.Size;  
import android.util.AttributeSet;  
import android.view.SurfaceHolder;  
import android.view.SurfaceView;  
  
/** 
 *  
 * Android Camera API archetype 
 *  
 * Under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html 
 *  
 * @author antoine vianey 
 * 
 */  
public final class CameraLivePreview extends SurfaceView   
        implements SurfaceHolder.Callback {  
      
    private SurfaceHolder holder;  
    private Camera camera;  
  
    /** 
     * Retrieve raw picture data after shooting 
     */  
    private Camera.PictureCallback rawCallback =   
            new Camera.PictureCallback() {  
        public void onPictureTaken(byte[] data, Camera c) {  
            // work with raw data  
            // ...  
        }  
    };  
      
    /** 
     * Retrieve jpeg compress data after shooting 
     */  
    private Camera.PictureCallback jpegCallback =   
            new Camera.PictureCallback() {  
        public void onPictureTaken(byte[] data, Camera c) {  
            // start the camera preview  
            camera.startPreview();  
            // work with the jpeg data  
            // ...  
        }  
    };  
      
    /** 
     * Retrieve frame data for each frame 
     */  
    private Camera.PreviewCallback frameCallback =   
            new Camera.PreviewCallback() {  
        public void onPreviewFrame(byte[] data, Camera camera) {  
            // work with the frame data  
            // ...  
        }  
    };  
      
    /** 
     * Retrieve information about shutter 
     */  
    private Camera.ShutterCallback shutterCallback =   
            new Camera.ShutterCallback() {  
        public void onShutter() {  
            // handle shutter done  
            // ...  
        }  
    };  
      
    /** 
     * Ensure it is supported by adding  
     * android.hardware.camera.autofocus feature 
     * to the application manifest 
     */  
    private Camera.AutoFocusCallback autoFocusCallback =   
            new Camera.AutoFocusCallback() {  
        public void onAutoFocus(boolean success, Camera camera) {  
            // handle focus done  
            // you can choose to take a picture  
            // after auto focus is completed  
            camera.takePicture(shutterCallback, rawCallback, jpegCallback);  
        }  
    };  
  
    public CameraLivePreview(Context context) {  
        super(context);  
        init();  
    }  
  
    public CameraLivePreview(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        init();  
    }  
  
    public CameraLivePreview(Context context, AttributeSet attrs,   
            int defStyle) {  
        super(context, attrs, defStyle);  
        init();  
    }  
      
    private void init() {  
        holder = getHolder();  
        holder.addCallback(this);  
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);  
    }  
      
    public void surfaceCreated(SurfaceHolder holder) {  
        // surface created  
        // we can tell the camera to render  
        // into the surface  
        // but it's not ready to preview yet  
        camera = Camera.open();  
        try {  
            camera.setPreviewDisplay(holder);  
        } catch (IOException exception) {  
            camera.release();  
            camera = null;  
        }  
    }  
  
    public void surfaceDestroyed(SurfaceHolder holder) {  
        // surface destroyed  
        // we must tell the camera to stop it preview  
        camera.stopPreview();  
        camera.release();  
        camera = null;  
    }  
  
    public void surfaceChanged(SurfaceHolder holder,   
            int format, int w, int h) {  
        // we get the surface dimensions  
        // we can configure the preview  
        Camera.Parameters parameters = camera.getParameters();  
  
        List<Size> sizes = parameters.getSupportedPreviewSizes();  
        Size optimalSize = getOptimalPreviewSize(sizes, w, h);  
        parameters.setPreviewSize(optimalSize.width, optimalSize.height);  
  
        camera.setParameters(parameters);  
          
        // let render  
        camera.startPreview();  
        camera.setPreviewCallback(frameCallback);  
    }  
  
    private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {  
          
        // requirement  
        final double ASPECT_TOLERANCE = 0.05;  
          
        double targetRatio = (double) w / h;  
        if (sizes == null) {  
            return null;  
        }  
  
        Size optimalSize = null;  
        double minDiff = Double.MAX_VALUE;  
  
        int targetHeight = h;  
  
        // find a size that match aspect ratio and size  
        for (Size size : sizes) {  
            double ratio = (double) size.width / size.height;  
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)  
                continue;  
            if (Math.abs(size.height - targetHeight) < minDiff) {  
                optimalSize = size;  
                minDiff = Math.abs(size.height - targetHeight);  
            }  
        }  
  
        // it's not possible  
        // ignore the requirement  
        if (optimalSize == null) {  
            minDiff = Double.MAX_VALUE;  
            for (Size size : sizes) {  
                if (Math.abs(size.height - targetHeight) < minDiff) {  
                    optimalSize = size;  
                    minDiff = Math.abs(size.height - targetHeight);  
                }  
            }  
        }  
          
        return optimalSize;  
    }  
  
    public void takePhoto() {  
        // take a photo :  
        // 1 - auto focus  
        // 2 - take the picture in the auto focus callback  
        camera.autoFocus(autoFocusCallback);  
    }  
  
}  
