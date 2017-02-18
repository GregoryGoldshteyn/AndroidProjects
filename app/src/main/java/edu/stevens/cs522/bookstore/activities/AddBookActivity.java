package edu.stevens.cs522.bookstore.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import edu.stevens.cs522.bookstore.R;
import edu.stevens.cs522.bookstore.entities.Book;
import edu.stevens.cs522.bookstore.util.Utils;

public class AddBookActivity extends AppCompatActivity {
	
	// Use this as the key to return the book details as a Parcelable extra in the result intent.
	public static final String BOOK_RESULT_KEY = "book_result";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_book);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.addbook_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch(item.getItemId()) {

			// TODO ADD provide the UI for adding a book
			case R.id.add:
				//Toast.makeText(getApplicationContext(), "DATA SENT BACK " + ((EditText)findViewById(R.id.search_title)).getText().toString(),Toast.LENGTH_LONG).show();
				Intent resintent = new Intent();
				resintent.putExtra(BOOK_RESULT_KEY, addBook());
				setResult(Activity.RESULT_OK, resintent);
				finish();
			// TODO CHECKOUT provide the UI for checking out
			case R.id.cancel:
				setResult(Activity.RESULT_CANCELED);
				finish();

			default:
		}
		return false;
	}
	
	public Book addBook(){
		// TODO Just build a Book object with the search criteria and return that.
		Book retBook = new Book(0, ((EditText)findViewById(R.id.search_title)).getText().toString(), Utils.parseAuthors(((EditText) findViewById(R.id.search_author)).getText().toString()), ((EditText)findViewById(R.id.search_isbn)).getText().toString(), "$20");
		return retBook;
	}

}