package com.example.mike.mvpsample;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by MWidlok on 04.04.2017.
 */

public class NewNoteDialog extends DialogFragment
{

	public static interface MyDialogListener
	{
		public void userSelectedValue(String value);
		public void userCanceled();
	}

	public int test;

	MyDialogListener myDialogListener;

	public MyDialogListener getMyDialogListener() {
		return myDialogListener;
	}

	public void setMyDialogListener(MyDialogListener myDialogListener) {
		this.myDialogListener = myDialogListener;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		final View view = inflater.inflate(R.layout.dialog_new_note, null);

		builder.setView(view)
		       .setPositiveButton("Hinzufügen", new DialogInterface.OnClickListener() {
			       @Override
			       public void onClick(DialogInterface dialogInterface, int i) {
					   EditText etItemText = (EditText) view.findViewById(R.id.edItemText);
				       Log.i("info", "now call the presenter to add the note");

					   if (etItemText != null)
					   		myDialogListener.userSelectedValue(etItemText.getText().toString());
					   else
						   Log.e("info","EditText is null");

					   dismiss();

			       }
		       })
		       .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
			       @Override
			       public void onClick(DialogInterface dialogInterface, int i) {
				       myDialogListener.userCanceled();
					   dismiss();
			       }
		       });

		return builder.create();
	}
}