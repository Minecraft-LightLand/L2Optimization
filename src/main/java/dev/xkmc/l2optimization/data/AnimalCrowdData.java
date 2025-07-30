package dev.xkmc.l2optimization.data;

import dev.xkmc.l2optimization.init.L2OConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class AnimalCrowdData extends CrowdData {

	public AnimalCrowdData(LivingEntity e) {
		super(e);
	}

	@Override
	protected void tryRemove(List<? extends LivingEntity> list) {
		if (list.size() <= L2OConfig.COMMON.crowdAnimalRemoval.get())
			return;
		var sel = list.get(e.getRandom().nextInt(list.size()));
		if (sel.hasCustomName()) return;
		sel.remove(Entity.RemovalReason.KILLED);
	}

}
