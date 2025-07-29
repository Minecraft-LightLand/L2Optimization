package dev.xkmc.l2optimization.data;

import dev.xkmc.l2optimization.util.ExpandableIntStorage;
import dev.xkmc.l2optimization.util.RegistryMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;

public class EntityCountData {

	private static MinecraftServer SERVER;
	private static EntityCountData CACHE;

	public static EntityCountData of(ServerLevel sl) {
		if (sl.getServer() != SERVER) CACHE = null;
		if (CACHE == null) {
			SERVER = sl.getServer();
			CACHE = new EntityCountData(SERVER);
		}
		return CACHE;
	}

	private final MinecraftServer server;
	private final RegistryMap<EntityType<?>> index = new RegistryMap<>(BuiltInRegistries.ENTITY_TYPE);
	private long gameTime;
	private ExpandableIntStorage prev = new ExpandableIntStorage();
	private ExpandableIntStorage counter = new ExpandableIntStorage();

	public EntityCountData(MinecraftServer server) {
		this.server = server;
	}

	public void onTick(CrowdData data) {
		long time = server.overworld().getGameTime();
		if (time > gameTime) {
			prev = counter;
			counter = new ExpandableIntStorage();
			gameTime = time;
		}
		int id = index.of(data.e.getType());
		counter.increment(id);
	}

	public int getCount(EntityType<?> type) {
		return prev.get(index.of(type));
	}

}
