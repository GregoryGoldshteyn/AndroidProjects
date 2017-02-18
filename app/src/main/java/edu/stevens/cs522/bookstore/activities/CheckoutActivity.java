package edu.stevens.cs522.bookstore.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import edu.stevens.cs522.bookstore.R;

public class CheckoutActivity extends AppCompatActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.checkout_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		// TODO
		switch (item.getItemId()) {
			case R.id.checkout:
				setResult(Activity.RESULT_OK, new Intent());
				finish();
			case R.id.cancel:
				setResult(Activity.RESULT_CANCELED, new Intent());
				finish();

			default:
				// ORDER: display a toast message of how many books have been ordered and return

				// CANCEL: just return with REQUEST_CANCELED as the result code

				return false;
		}

	}
}