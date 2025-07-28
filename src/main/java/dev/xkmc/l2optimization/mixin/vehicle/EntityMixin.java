package dev.xkmc.l2optimization.mixin.vehicle;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Mixin(Entity.class)
public class EntityMixin {

	@Inject(method = "getIndirectPassengers", cancellable = true, at = @At("HEAD"))
	public void l2optimization$getPassengers(CallbackInfoReturnable<Iterable<Entity>> cir) {
		List<Entity> list = new ArrayList<>();
		Queue<Entity> queue = new ArrayDeque<>();
		queue.add((Entity) (Object) this);
		while (!queue.isEmpty()) {
			var e = queue.poll();
			for (var p : e.getPassengers()) {
				list.add(p);
				queue.add(p);
			}
		}
		cir.setReturnValue(list);
	}

}
