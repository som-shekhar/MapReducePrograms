package com.lpu;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

import org.apache.hadoop.io.WritableComparable;

public class CustomKey implements WritableComparable<CustomKey> {
	
	String word;

	public CustomKey() {
		
	}
	public CustomKey(String word) {
		this.word = word;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.word = in.readLine();
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeBytes(this.word);
		
	}


	@Override
	public int compareTo(CustomKey o) {
		return this.word.compareTo(o.getWord());
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public int hashCode() {
		return Objects.hash(word);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomKey other = (CustomKey) obj;
		return Objects.equals(word, other.word);
	}

	@Override
	public String toString() {
		return "CustomKey [word=" + word + "]";
	}
	
	

}
