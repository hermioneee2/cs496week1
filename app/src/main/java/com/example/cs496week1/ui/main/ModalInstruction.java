package com.example.cs496week1.ui.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.cs496week1.R;

public class ModalInstruction extends DialogFragment {

//    public static final String TAG_EVENT_DIALOG = "dialog_event";
//    public static interface Callback
//    {
//        public void accept();
//        public void decline();
//        public void cancel();
//    }
//
//    public ModalInstruction() {}
//    public static ModalInstruction getInstance() {
//        ModalInstruction e = new ModalInstruction();
//        return e;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.modal_instruction, container);
//        Button mConfirmBtn  = (Button) v.findViewById(R.id.btnStart);
//        mConfirmBtn.setOnClickListener(this);
//        return v;
//    }
//
//    @Override
//    public void onClick(View view) {
//        dismiss();
//    }
//
//    /* The activity that creates an instance of this dialog fragment must
//     * implement this interface in order to receive event callbacks.
//     * Each method passes the DialogFragment in case the host needs to query it. */
////    public interface NoticeDialogListener  {
////        public void onDialogPositiveClick(DialogFragment dialog);
////        public void onDialogNegativeClick(DialogFragment dialog);
////    }
//
////    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
////    @Override
////    public void onAttach(Context context) {
////        super.onAttach(context);
////        // Verify that the host activity implements the callback interface
////        try {
////            // Instantiate the NoticeDialogListener so we can send events to the host
////            listener = (NoticeDialogListener) context;
////        } catch (ClassCastException e) {
////            // The activity doesn't implement the interface, throw exception
////            throw new ClassCastException(getActivity().toString()
////                    + " must implement NoticeDialogListener");
////        }
////    }
////
////    @Override
////    public Dialog onCreateDialog(Bundle savedInstanceState) {
////        // Build the dialog and set up the button click handlers
////        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
////        builder.setMessage(R.string.dialog_start_game)
////                .setPositiveButton(R.string.start, new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int id) {
////                        // Send the positive button event back to the host activity
////                        listener.onDialogPositiveClick(ModalInstruction.this);
////                    }
////                })
////                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int id) {
////                        // Send the negative button event back to the host activity
////                        listener.onDialogNegativeClick(ModalInstruction.this);
////                    }
////                });
////        return builder.create();
////    }

    private static final String TAG = "MyCustomDialog";

    public interface OnInputSelected{
        void sendInput(String input);
    }
    public OnInputSelected mOnInputSelected;

    //widgets
    private EditText mInput;
    private TextView mActionOk, mActionCancel;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_instruction, container);
        mActionOk = view.findViewById(R.id.action_ok);
        mActionCancel = view.findViewById(R.id.action_cancel);
        mInput = view.findViewById(R.id.input);

        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input.");

                String input = mInput.getText().toString();
                if(!input.equals("")){
//
//                    //Easiest way: just set the value.
//                    MainFragment fragment = (MainFragment) getActivity().getFragmentManager().findFragmentByTag("MainFragment");
//                    fragment.mInputDisplay.setText(input);

                    mOnInputSelected.sendInput(input);
                }


                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputSelected = (OnInputSelected) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage() );
        }
    }
}
