package mutantescape.level.register.entity;

import mutantescape.level.register.RegisterEntity;
import mutantescape.level.register.RegisterParticleTypes;
import mutantescape.level.register.RegisterSounds;
import mutantescape.level.register.entity.goal.NearestAttackTargetGoal;
import mutantescape.tools.ModSet;
import mutantescape.tools.RenderTools;
import mutantescape.tools.UtilsModType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class Sheep_Entity extends BaseMonster implements GeoEntity {
    public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(Sheep_Entity.class, EntityDataSerializers.STRING);
    public String animationprocedure = "empty";
    private long lastSwing;
    private boolean swinging;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public Sheep_Entity(PlayMessages.SpawnEntity spawnEntity, Level level) {
        super(RegisterEntity.Sheep_Entity.get(),level);
    }
    public Sheep_Entity(EntityType<? extends Monster> expleEntityEntityType, Level level) {
        super(expleEntityEntityType,level);

    }
    @Override
    public void baseTick() {
        super.baseTick();
        this.refreshDimensions();
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
    }

    @Override
    public @NotNull MobType getMobType() {
        return UtilsModType.MUTANESCAPE;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, false){
            @Override
            protected double getAttackReachSqr(@NotNull LivingEntity entity) {
                return this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth();
            }
        });
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3,new RandomStrollGoal(this,0.6));
        this.targetSelector.addGoal(4, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new FloatGoal(this));
        this.goalSelector.addGoal(7, new NearestAttackTargetGoal<>(this, Player.class, false,48,24));
        this.goalSelector.addGoal(8, new NearestAttackTargetGoal<>(this, LivingEntity.class, false,48,24));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 25);
        builder = builder.add(Attributes.ARMOR, 1.2);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 4.0);
        builder = builder.add(Attributes.FOLLOW_RANGE, 128);
        return builder;
    }
    private PlayState movePredicate(AnimationState event) {
        if (this.animationprocedure.equals("empty")) {
            if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))

            ) {
                event.getController().setAnimationSpeed(1.9D);
                return event.setAndContinue(RawAnimation.begin().thenLoop("animation.unknown.walk"));
            }
            if (this.isDeadOrDying()) {
                event.getController().setAnimationSpeed(1.0D);
                return event.setAndContinue(RawAnimation.begin().thenPlay("animation.unknown.ide"));
            }
            event.getController().setAnimationSpeed(1.0D);
            return event.setAndContinue(RawAnimation.begin().thenLoop("animation.unknown.idle"));
        }
        return PlayState.CONTINUE;
    }
    public float IgetAttackAnim(float pPartialTick) {
        float f = this.attackAnim - this.oAttackAnim;
        if (f < 0.0F) {
            ++f;
        }

        return this.oAttackAnim + f * pPartialTick;
    }
    private PlayState AttackPredicate(AnimationState event) {
        double d1 = this.getX() - this.xOld;
        double d0 = this.getZ() - this.zOld;
        float velocity = (float) Math.sqrt(d1 * d1 + d0 * d0);
        if (this.IgetAttackAnim(event.getPartialTick()) > 0f && !this.swinging) {
            this.swinging = true;
            this.lastSwing = level().getGameTime();
        }
        if (this.swinging && this.lastSwing + 7L <= level().getGameTime()) {
            this.swinging = false;
        }

        if (this.swinging && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
            event.getController().forceAnimationReset();
            return event.setAndContinue(RawAnimation.begin().thenPlay("animation.unknown.att"));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("anim", entityData.get(ANIMATION));
    }


    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(ANIMATION, tag.getString("anim"));
    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ANIMATION, "undefined");
    }
    private PlayState Procedure(AnimationState event) {
        if (!animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
            if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
                this.animationprocedure = "empty";
                event.getController().forceAnimationReset();
            }
        } else if (animationprocedure.equals("empty")) {
            return PlayState.STOP;
        }
        return PlayState.CONTINUE;
    }




    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }



    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "move", 4, this::movePredicate));
        controllerRegistrar.add(new AnimationController<>(this, "attack", 4, this::AttackPredicate));
        controllerRegistrar.add(new AnimationController<>(this, "pro", 4, this::Procedure));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }


    @Override
    protected void tickDeath() {
        ++this.deathTime;
        if (this.deathTime == 20) {
            MinecraftServer server=this.getServer();
            if (server!=null && server.overworld() instanceof ServerLevel) {
                generateBloodSplat(server.overworld(),this.position(), (int) (this.lastHurt*this.random.nextInt(1,3)));
            }
            this.remove(Entity.RemovalReason.KILLED);
            this.dropExperience();
        }
    }

    private void generateBloodSplat(ServerLevel world, Vec3 position, int max) {
        playSoundAtPosition(world,new BlockPos(this.getBlockX(),this.getBlockY(),this.getBlockZ()), RegisterSounds.DEATH_SOUND.get(), SoundSource.BLOCKS, 1.0F, 1.0F, 12.0);
        List<Vec3> Apos = RenderTools.LivingEntitRoundPos(this, 4, 10, 25);
        Apos.forEach(
                vec3 -> {
                    world.sendParticles(RegisterParticleTypes.BLOOD.get(),vec3.x+random.nextDouble()*0.03, vec3.y+(this.getScale()*0.5), vec3.z+random.nextDouble()*0.03, 1, 0.0, 0.0, 0.0, 0.0);
                    world.sendParticles(RegisterParticleTypes.BLOOD_3.get(), vec3.x+random.nextDouble(), vec3.y+this.getScale(), vec3.z+random.nextDouble(), 1, 0.0, 0.0, 0.0, 0.0);
                    world.sendParticles(RegisterParticleTypes.BLOOD_4.get(), vec3.x+random.nextDouble(), vec3.y+this.getScale(), vec3.z+random.nextDouble(), 1, 0.0, 0.0, 0.0, 0.0);
                    world.sendParticles(RegisterParticleTypes.BLOOD_SPLAT.get(), vec3.x+random.nextDouble(), vec3.y, vec3.z+random.nextDouble(), 1, 0.0, 0.0, 0.0, 0.0);
                }
        );
    }
    public static void playSoundAtPosition(ServerLevel world, BlockPos pos, SoundEvent sound, SoundSource source, float volume, float pitch, double range) {
            List<ServerPlayer> Players = world.getChunkSource().chunkMap.getPlayers(new ChunkPos(pos), false);
            Players.forEach(
                player -> {
                    if (player.distanceToSqr(pos.getX(), pos.getY(), pos.getZ()) <= range * range) {
                        world.playSound(null, pos, sound, source, volume, pitch);
                    }
                }
            );
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);
        //this.spawnAtLocation(new ItemStack(SyGItem.ITEM_1.get()));
    }


}
