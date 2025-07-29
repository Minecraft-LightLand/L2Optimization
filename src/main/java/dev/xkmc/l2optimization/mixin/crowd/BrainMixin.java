package dev.xkmc.l2optimization.mixin.crowd;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import dev.xkmc.l2optimization.data.CrowdData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Brain.class)
public class BrainMixin {

	@WrapWithCondition(method = "startEachNonRunningBehavior", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/behavior/BehaviorControl;tryStart(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;J)Z"))
	private <E extends LivingEntity> boolean l2optimization$skip(BehaviorControl<E> instance, ServerLevel level, E e, long time) {
		var crowd = CrowdData.of(e);
		return crowd == null || !crowd.shouldSkipBrainBehavior(instance, time);
	}

}
