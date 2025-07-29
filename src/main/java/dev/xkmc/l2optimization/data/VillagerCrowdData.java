package dev.xkmc.l2optimization.data;

import dev.xkmc.l2optimization.init.L2OConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.OneShot;

public class VillagerCrowdData extends CrowdData {

	private BlockPos prev;
	int stayTime;

	public VillagerCrowdData(LivingEntity e) {
		super(e);
	}

	@Override
	public void tick() {
		super.tick();
		if (e.tickCount % 4 != 0) return;
		if (e.isSleeping()) {
			stayTime = 0;
			prev = e.blockPosition();
		} else if (prev != null && prev.equals(e.blockPosition())) {
			stayTime += 4;
		} else {
			stayTime = 0;
			prev = e.blockPosition();
		}
	}

	@Override
	public <E extends LivingEntity> boolean shouldSkipBrainBehavior(BehaviorControl<E> instance, long time) {
		if (instance instanceof OneShot<E>) {
			if (crowd >= L2OConfig.COMMON.villagerSlowCrowd.get() ||
					stayTime > L2OConfig.COMMON.villagerSlowStayTime.get())
				return time % L2OConfig.COMMON.villagerCheckIntervalSlow.get() != 0;
			else if (crowd >= L2OConfig.COMMON.villagerFastCrowd.get() ||
					stayTime > L2OConfig.COMMON.villagerFastStayTime.get())
				return time % L2OConfig.COMMON.villagerCheckIntervalFast.get() != 0;
		}
		return false;
	}

}
