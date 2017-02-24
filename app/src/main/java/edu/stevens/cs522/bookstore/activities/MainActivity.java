package edu.stevens.cs522.bookstore.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import edu.stevens.cs522.bookstore.entities.Book;
import edu.stevens.cs522.bookstore.R;
import edu.stevens.cs522.bookstore.util.BooksAdapter;

public class MainActivity extends AppCompatActivity {
	
	// Use this when logging errors and warnings.
	private static final String TAG = MainActivity.class.getCanonicalName();
	
	// These are request codes for subactivity request calls
	static final private int ADD_REQUEST = 1;
	
	static final private int CHECKOUT_REQUEST = ADD_REQUEST + 1;
	static final private int VIEW_REQUEST = CHECKOUT_REQUEST + 1;
	public static final String BOOK_RESULT_KEY = "book_result";
	public static final String BOOK_KEY = "book";
	public static final String INDEX_KEY = "index";

	// There is a reason this must be an ArrayList instead of a List.
	private ArrayList<Book> shoppingCart;
	private ListView list;
	private BooksAdapter b;

	private TextView t;
	private MainActivity myself = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		//Toast.makeText(getApplicationContext(), "On create called",Toast.LENGTH_LONG).show();
		// TODO check if there is saved UI state, and if so, restore it (i.e. the cart contents)
		if(savedInstanceState != null){
			//Toast.makeText(getApplicationContext(), "Saved instance state was NOT null",Toast.LENGTH_LONG).show();
		}
		else{
			//Toast.makeText(getApplicationContext(), "Saved instance state WAS null",Toast.LENGTH_LONG).show();
			setContentView(R.layout.cart);
			//String[] test_arr = {"This","That","Another thing"};
			//ListAdapter test_adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, test_arr);
			list = (ListView)findViewById(R.id.list);
			shoppingCart = new ArrayList<Book>();
			b = new BooksAdapter(this, shoppingCart);
			list.setAdapter(b);
			list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView parentView, View childView, int position, long id){
					Intent viewIntent = new Intent(myself, ViewBookActivity.class);
					viewIntent.putExtra(BOOK_KEY, shoppingCart.get(position));
					viewIntent.putExtra(INDEX_KEY, position);
					startActivityForResult(viewIntent, VIEW_REQUEST);
				}
			});
			//list.setAdapter(test_adapt);
		}
		// TODO Set the layout (use cart.xml layout)
		//setContentView(R.layout.cart);
		t = (TextView) findViewById(R.id.empty);
		// TODO use an array adapter to display the cart contents.

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// TODO inflate a menu with ADD and CHECKOUT options
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.bookstore_menu, menu);
        return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
        switch(item.getItemId()) {

            // TODO ADD provide the UI for adding a book
            case R.id.add:
                Intent addIntent = new Intent(this, AddBookActivity.class);
                startActivityForResult(addIntent, ADD_REQUEST);
                break;

            // TODO CHECKOUT provide the UI for checking out
            case R.id.checkout:
				Intent checkoutIntent = new Intent(this, CheckoutActivity.class);
				startActivityForResult(checkoutIntent, CHECKOUT_REQUEST);
                break;

            default:
        }
        return false;
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		// TODO Handle results from the Search and Checkout activities.
		Bundle bundle = intent.getExtras();

        // Use ADD_REQUEST and CHECKOUT_REQUEST codes to distinguish the cases.
        switch(requestCode) {
            case ADD_REQUEST:
                if(resultCode == Activity.RESULT_OK){
					//intent.getParcelableArrayListExtra(BOOK_RESULT_KEY);
					//shoppingCart = bundle.getParcelableArrayList(BOOK_RESULT_KEY);
					Book return_book = bundle.getParcelable(BOOK_RESULT_KEY);
					shoppingCart.add(return_book);
					b.notifyDataSetChanged();
					t.setVisibility(View.GONE);
				}
                break;
            case CHECKOUT_REQUEST:
				if(resultCode == Activity.RESULT_OK){
					//Toast.makeText(getApplicationContext(), "Just bought " + Integer.toString(shoppingCart.size()) + " books",Toast.LENGTH_LONG).show();
					shoppingCart.clear();
					b.notifyDataSetChanged();
					t.setVisibility(View.VISIBLE);
				}
                break;
			case VIEW_REQUEST:
				if(resultCode == Activity.RESULT_OK){
					//Toast.makeText(getApplicationContext(), "Nothing changed",Toast.LENGTH_LONG).show();
				}
				else{
					//Toast.makeText(getApplicationContext(), Integer.toString() ,Toast.LENGTH_LONG).show();
					shoppingCart.remove(bundle.getInt(INDEX_KEY));
					b.notifyDataSetChanged();
					if(shoppingCart.isEmpty()){
						t.setVisibility(View.VISIBLE);
					}
				}
				break;
        }
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// TODO save the shopping cart contents (which should be a list of parcelables).
		//Toast.makeText(getApplicationContext(), "Instance State is Saved",Toast.LENGTH_LONG).show();
		savedInstanceState.putParcelableArrayList(BOOK_RESULT_KEY, shoppingCart);
		super.onSaveInstanceState(savedInstanceState);
	}
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		//Toast.makeText(getApplicationContext(), "Instance state is restored",Toast.LENGTH_LONG).show();
	}
}