package com.lpu;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

import org.apache.hadoop.io.Writable;

public class CustomValue implements Writable {

	int value=1;
	
	public CustomValue() {
		this.value=1;
	}
	@Override
	public void readFields(DataInput in) throws IOException {
		this.value=in.readInt();

	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(getValue());

	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomValue other = (CustomValue) obj;
		return value == other.value;
	}
	@Override
	public String toString() {
		return "CustomValue [value=" + value + "]";
	}

}
