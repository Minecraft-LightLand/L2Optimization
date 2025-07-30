package dev.xkmc.l2optimization.data;

import dev.xkmc.l2optimization.api.CrowdHook;
import dev.xkmc.l2optimization.init.L2OConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrowdData {

	public final LivingEntity e;

	protected int crowd;
	protected int crowdTime;

	public CrowdData(LivingEntity e) {
		this.e = e;
	}

	public static @Nullable CrowdData of(LivingEntity e) {
		return e instanceof CrowdHook c ? c.l2optimization$getCrowdHook() : null;
	}

	public void tick() {
		if (crowdTime > 0) {
			crowdTime -= L2OConfig.COMMON.globalCheckInterval.get();
			return;
		}
		int range = L2OConfig.COMMON.crowdSearchRange.get();
		int cd = L2OConfig.COMMON.crowdSearchInterval.get();
		var list = e.level().getEntitiesOfClass(e.getClass(), e.getBoundingBox().inflate(range));
		tryRemove(list);
		for (var e : list) {
			var other = of(e);
			if (other != null) {
				crowdTime = cd;
				crowd = list.size();
			}
		}
		crowdTime = cd;
		crowd = list.size();
	}

	protected void tryRemove(List<? extends LivingEntity> list) {

	}

	public <E extends LivingEntity> boolean shouldSkipBrainBehavior(BehaviorControl<E> instance, long time) {
		return false;
	}

	public boolean canBreed() {
		return crowd <= L2OConfig.COMMON.maxCrowdToBreed.get();
	}

}
