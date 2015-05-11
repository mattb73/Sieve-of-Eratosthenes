package ucsb.mattbao.sieveoferatosthenes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by MattB on 5/10/2015.
 */
public class OverLimitAlertFragment extends DialogFragment {

    // Public interface for adding button click action in main activity
    public interface AlertButtonListener{
        public void onPositiveClick();
    }

    AlertButtonListener mListener;

    public void setAlertButtonListener(AlertButtonListener listener){
        mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.over_alert_title).setMessage(R.string.over_alert_message);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onPositiveClick();
            }
        });
        return builder.create();
    }
}
