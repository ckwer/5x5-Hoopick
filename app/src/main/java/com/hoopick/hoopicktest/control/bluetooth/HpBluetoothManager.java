package com.hoopick.hoopicktest.control.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by pro on 2016-11-22.
 */
public class HpBluetoothManager {

    public static final String TAG = "HpBluetoothManager";

    private HpBluetoothListener mListener = null;

    private static HpBluetoothSerialService mSerialService = null;
    private BluetoothAdapter mBluetoothAdapter = null;

    public void init() {

        if (null == mBluetoothAdapter) {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }

        if (null == mSerialService) {
            mSerialService = new HpBluetoothSerialService(mHandlerBT);
        }

    }

    public void resume() {

        if ( (mBluetoothAdapter != null)  && (!mBluetoothAdapter.isEnabled()) ) {

        }

        if (mSerialService != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mSerialService.getState() == HpBluetoothSerialService.STATE_NONE) {
                // Start the Bluetooth chat services
                mSerialService.start();
            }
        }

    }

    public boolean write(String aStr) {

        if (mSerialService.getState() == HpBluetoothSerialService.STATE_CONNECTED) {
            HpBluetoothManager.get().getSerialService().write(aStr.getBytes());
            return true;
        }

        return false;
    }

    //

    public HpBluetoothListener getListener() {
        return mListener;
    }

    public void setListener(HpBluetoothListener mListener) {
        this.mListener = mListener;
    }

    public static HpBluetoothSerialService getSerialService() {
        return mSerialService;
    }

    public static void setSerialService(HpBluetoothSerialService mSerialService) {
        HpBluetoothManager.mSerialService = mSerialService;
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return mBluetoothAdapter;
    }

    public void setBluetoothAdapter(BluetoothAdapter mBluetoothAdapter) {
        this.mBluetoothAdapter = mBluetoothAdapter;
    }

    // The Handler that gets information back from the BluetoothService
    private final Handler mHandlerBT = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {

                case HpBluetoothSerialService.MESSAGE_STATE_CHANGE:

                    Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);

                    switch (msg.arg1) {

                        case HpBluetoothSerialService.STATE_CONNECTED:
                            break;

                        case HpBluetoothSerialService.STATE_CONNECTING:
                            break;

                        case HpBluetoothSerialService.STATE_LISTEN:
                            break;

                        case HpBluetoothSerialService.STATE_NONE:
                            break;
                    }

                    if (null != mListener) {
                        mListener.onBluetoothStateChange(msg.arg1);
                    }

                    break;

                case HpBluetoothSerialService.MESSAGE_WRITE:
                    break;

                case HpBluetoothSerialService.MESSAGE_DEVICE_NAME:
                    break;

                case HpBluetoothSerialService.MESSAGE_TOAST:
                    break;

            }

        }

    };


    // -------------------------------------------------------------
    // start singleton

    private static HpBluetoothManager gHpBluetoothManager;

    public static HpBluetoothManager get() {
        if (null == gHpBluetoothManager) {
            gHpBluetoothManager = new HpBluetoothManager();
        }
        return gHpBluetoothManager;
    }

    private HpBluetoothManager() {
        init();
    }

}
