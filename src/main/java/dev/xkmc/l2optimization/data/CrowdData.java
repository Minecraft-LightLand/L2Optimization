package dev.xkmc.l2optimization.data;

import dev.xkmc.l2optimization.api.CrowdHook;
import dev.xkmc.l2optimization.init.L2OConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import org.jetbrains.annotations.Nullable;

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
			crowdTime--;
			return;
		}
		int range = 4;
		int cd = 100;
		var list = e.level().getEntitiesOfClass(e.getClass(), e.getBoundingBox().inflate(range));
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

	public <E extends LivingEntity> boolean shouldSkipBrainBehavior(BehaviorControl<E> instance, long time) {
		return false;
	}

	public boolean canBreed() {
		return crowd <= L2OConfig.COMMON.maxCrowdToBreed.get();
	}

}
