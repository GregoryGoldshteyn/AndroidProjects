package edu.stevens.cs522.bookstore.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Author implements Parcelable {
	
	// TODO Modify this to implement the Parcelable interface. //Done

	// NOTE: middleInitial may be NULL!
	
	public String firstName;
	
	public String middleInitial;
	
	public String lastName;

	public Author(){

	};

	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (firstName != null && !"".equals(firstName)) {
			sb.append(firstName);
			sb.append(' ');
		}
		if (middleInitial != null && !"".equals(middleInitial)) {
			sb.append(middleInitial);
			sb.append(' ');
		}
		if (lastName != null && !"".equals(lastName)) {
			sb.append(lastName);
		}
		return sb.toString();
	}

	public int describeContents(){
		return 0;
	}

	public void writeToParcel(Parcel p, int flags){
		p.writeString(firstName);
		p.writeString(middleInitial);
		p.writeString(lastName);
	}

	public static final Parcelable.Creator<Author> CREATOR = new Parcelable.Creator<Author>(){
		public Author createFromParcel(Parcel p){
			return new Author(p);
		}
		public Author[] newArray(int size){
			return new Author[size];
		}
	};

	public Author(Parcel p){
		firstName = p.readString();
		middleInitial = p.readString();
		lastName = p.readString();
	}

}
