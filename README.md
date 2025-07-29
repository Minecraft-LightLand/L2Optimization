# L2Optimization

## List of optimizations

### Player motion packet
When player moves, server will notify all clients about the movement, sending 
`ServerboundMovePlayerPacket`. This involves calculating the tracking range of the player.
However, if the player has mounts or passengers, the calculation becomes significant slower.
The calculation is for finding passengers with larger tracking range, but actually player
always have the largest tracking range, and this calculation can be skipped.

This will speed up `ChunkMap$TrackedEntity.getEffectiveRange` calls on player by 99%.
For vehicles, I also improved passenger iteration, 
which speed up `Entity.getIndirectPassengers` by 50%.

### SynchedEntityData Lock Skip
In 1.20.1, `SynchedEntityData` has a Read-Write Lock. This lock has no point and is removed
in 1.21.1, and thus I decided to skip them as well.

This will speed up `SynchedEntityData.getItem` by 90%.

### Enderman Anger Test Caching
Most of the time, EnderMask/EnderVeil-like items does not check which ender man to cancel anger.
I cache Enderman anger event result for each player by 4 ticks.
If there are 25 Endermen around players, 
this optimization will improve Enderman anger test performance by 99%.

### Animal / Villager Breeding Skip
Some players love to horde animals and villagers. I make animals and villagers not breedable when
there are at least 8 of their kind within 4 blocks. Numbers are adjustable in config.

This acts mainly as a prevention to stop players from breeding too many entities.

### Villager Brain Tick Skip
Most of the time Villagers are being trapped in small space.
I skip 80% of the villager OneShot AI triggering (finding work stations, etc) when they are immobile
for too long without sleep or being in a large crowd. I skip 95% of those when the scenario is more severe.
Conditions can be adjusted in config.

This does not affect other behaviors, such as moving to target position.

