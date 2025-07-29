package dev.xkmc.l2optimization.util;

import java.util.Arrays;

public class ExpandableIntStorage {

	int[] data = new int[0];

	private void validate(int index) {
		if (data.length <= index) {
			data = Arrays.copyOf(data, index + 1);
		}
	}

	public void set(int index, int val) {
		validate(index);
		data[index] = val;
	}

	public void increment(int index) {
		data[index]++;
	}

	public int get(int index) {
		if (index < 0 || index >= data.length) return 0;
		return data[index];
	}

}
