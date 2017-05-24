/*
 * Copyright (c) 2010 - 2017, Nordic Semiconductor ASA
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form, except as embedded into a Nordic
 *    Semiconductor ASA integrated circuit in a product or a software update for
 *    such product, must reproduce the above copyright notice, this list of
 *    conditions and the following disclaimer in the documentation and/or other
 *    materials provided with the distribution.
 *
 * 3. Neither the name of Nordic Semiconductor ASA nor the names of its
 *    contributors may be used to endorse or promote products derived from this
 *    software without specific prior written permission.
 *
 * 4. This software, with or without modification, must only be used with a
 *    Nordic Semiconductor ASA integrated circuit.
 *
 * 5. Any software provided in binary form under this license must not be reverse
 *    engineered, decompiled, modified and/or disassembled.
 *
 * THIS SOFTWARE IS PROVIDED BY NORDIC SEMICONDUCTOR ASA "AS IS" AND ANY EXPRESS
 * OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY, NONINFRINGEMENT, AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL NORDIC SEMICONDUCTOR ASA OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package no.nordicsemi.android.nrfthingy.configuration;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import no.nordicsemi.android.nrfthingy.R;
import no.nordicsemi.android.nrfthingy.common.Utils;
import no.nordicsemi.android.thingylib.ThingySdkManager;

public class AdvancedConfigurationFragment extends Fragment implements ThingeeAdvancedSettingsChangeListener {

    private BluetoothDevice mDevice;
    private boolean mBound;
    private int mSettingsMode;
    private ThingySdkManager mThingySdkManager;

    private TextView mTemperatureIntervalSummary;
    private TextView mPressureIntervalSummary;
    private TextView mHumidityIntervalSummary;
    private TextView mColorIntensityIntervalSummary;
    private TextView mGasModeSummary;
    private TextView mPedometerIntervalSummary;
    private TextView mMotionTemperatureIntervalSummary;
    private TextView mCompassIntervalSummary;
    private TextView mMotionIntervalSummary;
    private TextView mWakeOnMotionSummary;

    public AdvancedConfigurationFragment() {

    }

    public static AdvancedConfigurationFragment getInstance(final BluetoothDevice device) {
        AdvancedConfigurationFragment fragment = new AdvancedConfigurationFragment();
        final Bundle args = new Bundle();
        args.putParcelable(Utils.CURRENT_DEVICE, device);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDevice = getArguments().getParcelable(Utils.CURRENT_DEVICE);
        }
        mThingySdkManager = ThingySdkManager.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_advanced_configuration, container, false);
        final LinearLayout temperature = (LinearLayout) rootView.findViewById(R.id.category_temp_interval);
        mTemperatureIntervalSummary = (TextView) rootView.findViewById(R.id.category_temp_interval_summary);
        final LinearLayout pressure = (LinearLayout) rootView.findViewById(R.id.category_pressure_interval);
        mPressureIntervalSummary = (TextView) rootView.findViewById(R.id.category_pressure_interval_summary);
        final LinearLayout humidity = (LinearLayout) rootView.findViewById(R.id.category_humidity_interval);
        mHumidityIntervalSummary = (TextView) rootView.findViewById(R.id.category_humidity_interval_summary);
        final LinearLayout colorIntensity = (LinearLayout) rootView.findViewById(R.id.category_color_intensity_interval);
        mColorIntensityIntervalSummary = (TextView) rootView.findViewById(R.id.category_color_intensity_interval_summary);
        final LinearLayout gasMode = (LinearLayout) rootView.findViewById(R.id.category_gas_mode);
        mGasModeSummary = (TextView) rootView.findViewById(R.id.category_gas_mode_summary);
        final LinearLayout pedometer = (LinearLayout) rootView.findViewById(R.id.category_pedometer_interval);
        mPedometerIntervalSummary = (TextView) rootView.findViewById(R.id.category_pedometer_interval_summary);
        final LinearLayout temperatureMotion = (LinearLayout) rootView.findViewById(R.id.category_motion_temperature_interval);
        mMotionTemperatureIntervalSummary = (TextView) rootView.findViewById(R.id.category_motion_temperature_interval_summary);
        final LinearLayout compass = (LinearLayout) rootView.findViewById(R.id.category_compass_interval);
        mCompassIntervalSummary = (TextView) rootView.findViewById(R.id.category_compass_interval_summary);
        final LinearLayout motion = (LinearLayout) rootView.findViewById(R.id.category_motion_interval);
        mMotionIntervalSummary = (TextView) rootView.findViewById(R.id.category_motion_interval_summary);
        final LinearLayout wakeOnMotion = (LinearLayout) rootView.findViewById(R.id.category_wake_on_motion);
        mWakeOnMotionSummary = (TextView) rootView.findViewById(R.id.category_wake_on_motion_summary);

        temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mThingySdkManager != null) {
                    mSettingsMode = 0;
                    final EnvironmentConfigurationDialogFragment fragment = EnvironmentConfigurationDialogFragment.newInstance(mSettingsMode, mDevice);
                    fragment.show(getChildFragmentManager(), null);
                }
            }
        });

        pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mThingySdkManager != null) {
                    mSettingsMode = 1;
                    final EnvironmentConfigurationDialogFragment fragment = EnvironmentConfigurationDialogFragment.newInstance(mSettingsMode, mDevice);
                    fragment.show(getChildFragmentManager(), null);
                }
            }
        });

        humidity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mThingySdkManager != null) {
                    mSettingsMode = 2;
                    final EnvironmentConfigurationDialogFragment fragment = EnvironmentConfigurationDialogFragment.newInstance(mSettingsMode, mDevice);
                    fragment.show(getChildFragmentManager(), null);
                }
            }
        });

        colorIntensity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mThingySdkManager != null) {
                    mSettingsMode = 3;
                    final EnvironmentConfigurationDialogFragment fragment = EnvironmentConfigurationDialogFragment.newInstance(mSettingsMode, mDevice);
                    fragment.show(getChildFragmentManager(), null);
                }
            }
        });

        gasMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mThingySdkManager != null) {
                    mSettingsMode = 4;
                    final EnvironmentConfigurationDialogFragment fragment = EnvironmentConfigurationDialogFragment.newInstance(mSettingsMode, mDevice);
                    fragment.show(getChildFragmentManager(), null);
                }
            }
        });

        pedometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mThingySdkManager != null) {
                    mSettingsMode = 0;
                    final MotionConfigurationDialogFragment fragment = MotionConfigurationDialogFragment.newInstance(mSettingsMode, mDevice);
                    fragment.show(getChildFragmentManager(), null);
                }
            }
        });

        temperatureMotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mThingySdkManager != null) {
                    mSettingsMode = 1;
                    final MotionConfigurationDialogFragment fragment = MotionConfigurationDialogFragment.newInstance(mSettingsMode, mDevice);
                    fragment.show(getChildFragmentManager(), null);
                }
            }
        });

        compass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mThingySdkManager != null) {
                    mSettingsMode = 2;
                    final MotionConfigurationDialogFragment fragment = MotionConfigurationDialogFragment.newInstance(mSettingsMode, mDevice);
                    fragment.show(getChildFragmentManager(), null);
                }
            }
        });

        motion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mThingySdkManager != null) {
                    mSettingsMode = 3;
                    final MotionConfigurationDialogFragment fragment = MotionConfigurationDialogFragment.newInstance(mSettingsMode, mDevice);
                    fragment.show(getChildFragmentManager(), null);
                }
            }
        });

        wakeOnMotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mThingySdkManager != null) {
                    mSettingsMode = 4;
                    final MotionConfigurationDialogFragment fragment = MotionConfigurationDialogFragment.newInstance(mSettingsMode, mDevice);
                    fragment.show(getChildFragmentManager(), null);
                }
            }
        });

        updateTemperatureInterval();
        updatePressureInterval();
        updateHumidityInterval();
        updateColorIntensityInterval();
        updateGasMode();
        updatePedometerInterval();
        updateMotionTemperatureInterval();
        updateCompassInterval();
        updateMotionInterval();
        updateWakeOnMotion();

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mConfigurationBroadcastReceiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void updateTemperatureInterval() {
        mTemperatureIntervalSummary.setText(getString(R.string.interval_ms, mThingySdkManager.getEnvironmentTemperatureInterval(mDevice)));
    }

    @Override
    public void updatePressureInterval() {
        mPressureIntervalSummary.setText(getString(R.string.interval_ms, mThingySdkManager.getPressureInterval(mDevice)));

    }

    @Override
    public void updateHumidityInterval() {
        mHumidityIntervalSummary.setText(getString(R.string.interval_ms, mThingySdkManager.getHumidityInterval(mDevice)));
    }

    @Override
    public void updateColorIntensityInterval() {
        mColorIntensityIntervalSummary.setText(getString(R.string.interval_ms, mThingySdkManager.getColorIntensityInterval(mDevice)));
    }

    @Override
    public void updateGasMode() {
        final int gasMode = mThingySdkManager.getGasMode(mDevice);
        if (gasMode == 1) {
            mGasModeSummary.setText(R.string.gas_mode_one);
        } else if (gasMode == 2) {
            mGasModeSummary.setText(R.string.gas_mode_two);
        } else if (gasMode == 3) {
            mGasModeSummary.setText(R.string.gas_mode_three);
        }
    }

    @Override
    public void updatePedometerInterval() {
        mPedometerIntervalSummary.setText(getString(R.string.interval_ms, mThingySdkManager.getPedometerInterval(mDevice)));
    }

    @Override
    public void updateMotionTemperatureInterval() {
        mMotionTemperatureIntervalSummary.setText(getString(R.string.interval_ms, mThingySdkManager.getMotionTemperatureInterval(mDevice)));
    }

    @Override
    public void updateCompassInterval() {
        mCompassIntervalSummary.setText(getString(R.string.interval_ms, mThingySdkManager.getCompassInterval(mDevice)));
    }

    @Override
    public void updateMotionInterval() {
        mMotionIntervalSummary.setText(getString(R.string.interval_hz, mThingySdkManager.getMotionInterval(mDevice)));
    }

    @Override
    public void updateWakeOnMotion() {
        mWakeOnMotionSummary.setText(String.valueOf(mThingySdkManager.getWakeOnMotionState(mDevice) ? getString(R.string.on) : getString(R.string.off)));
    }
}