package dev.xkmc.l2optimization.util;

import net.minecraft.core.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegistryMap<T> {

	private final Registry<T> reg;
	private int[] index = new int[0];
	private List<T> list = new ArrayList<>();

	public RegistryMap(Registry<T> reg) {
		this.reg = reg;
	}

	public int of(T type) {
		int id = reg.getId(type);
		if (id >= index.length) {
			index = Arrays.copyOf(index, id + 1);
		}
		if (index[id] == 0) {
			list.add(type);
			index[id] = list.size();
		}
		return index[id] - 1;
	}

	public T get(int id) {
		return list.get(id);
	}

}
