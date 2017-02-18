package edu.stevens.cs522.bookstore.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable{
	
	// TODO Modify this to implement the Parcelable interface. //Done

	public int id;
	
	public String title;
	
	public Author[] authors;
	
	public String isbn;
	
	public String price;

	public Book(int id, String title, Author[] author, String isbn, String price) {
		this.id = id;
		this.title = title;
		this.authors = author;
		this.isbn = isbn;
		this.price = price;
	}

	public Book(Parcel p){
		id = p.readInt();
		title = p.readString();
		authors = p.createTypedArray(Author.CREATOR);
		isbn = p.readString();
		price = p.readString();
	}

	public String getFirstAuthor() {
		if (authors != null && authors.length > 0) {
			return authors[0].toString();
		} else {
			return "";
		}
	}

	public int describeContents(){
		return 0;
	}

	public void writeToParcel(Parcel p, int flags){

		p.writeInt(id);
		p.writeString(title);
		p.writeTypedArray(authors, 0);
		p.writeString(isbn);
		p.writeString(price);
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
		public Book createFromParcel(Parcel p){
			return new Book(p);
		}
		public Book[] newArray(int size){
			return new Book[size];
		}
	};

}