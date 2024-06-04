package mutantescape.level.register.entity.goal;


import mutantescape.level.attributes.Attrubes;
import mutantescape.level.register.RegisterEffect;
import mutantescape.level.register.entity.Exple_entity;
import mutantescape.tools.ModSet;
import mutantescape.tools.Utils.MCUtils;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class NearestAttackTargetGoal<T extends LivingEntity> extends TargetGoal {
    public TargetingConditions targetEntitySelectorXRay;
    private static final int DEFAULT_RANDOM_INTERVAL = 10;
    protected final Class<T> targetType;
    protected final int randomInterval;
    private List<Class<? extends LivingEntity>> TargetEntity=new ArrayList<>();
    @Nullable
    protected LivingEntity target;
    private double IFOLLOW_RANGE;
    private double XAER_RANGE;
    protected TargetingConditions targetConditions;
    public NearestAttackTargetGoal(Mob pMob, Class<T> pTargetType, boolean pMustSee, double follow_range, double xaer_range) {
        this(pMob, pTargetType, 10, pMustSee, false, (Predicate<LivingEntity>)null,follow_range,xaer_range);
    }

    public NearestAttackTargetGoal(Mob pMob, Class<T> pTargetType, boolean pMustSee, Predicate<LivingEntity> pTargetPredicate, double follow_range, double xaer_range) {
        this(pMob, pTargetType, 10, pMustSee, false, pTargetPredicate,follow_range,xaer_range);
    }

    public NearestAttackTargetGoal(Mob pMob, Class<T> pTargetType, boolean pMustSee, boolean pMustReach, double follow_range, double xaer_range) {
        this(pMob, pTargetType, 10, pMustSee, pMustReach, (Predicate<LivingEntity>)null,follow_range,xaer_range);
    }

    public NearestAttackTargetGoal(Mob pMob, Class<T> pTargetType, int pRandomInterval, boolean pMustSee, boolean pMustReach, @Nullable Predicate<LivingEntity> pTargetPredicate, double follow_range, double xaer_range) {
        super(pMob, pMustSee, pMustReach);
        this.targetType = pTargetType;
        this.randomInterval = reducedTickDelay(pRandomInterval);
        this.setFlags(EnumSet.of(Flag.TARGET));
        this.targetConditions = TargetingConditions.forCombat().range(this.getFollowDistance()).selector(pTargetPredicate);
        this.targetEntitySelectorXRay =this.targetConditions.copy().ignoreLineOfSight();
        this.IFOLLOW_RANGE=follow_range;
        this.XAER_RANGE=xaer_range;
        MCUtils.setAttributeValue(pMob,Attributes.FOLLOW_RANGE,this.IFOLLOW_RANGE);
        MCUtils.setAttributeValue(pMob, Attrubes.XRAY_FOLLOW_RANGE,this.XAER_RANGE);
        addEntity();
    }

  void addEntity(){
        //只攻击这些生物
      TargetEntity.add(Villager.class);
      TargetEntity.add(ServerPlayer.class);
      TargetEntity.add(LocalPlayer.class);
      TargetEntity.add(IronGolem.class);
      TargetEntity.add(Player.class);
      TargetEntity.add(Zombie.class);
  }


    @Override
    public boolean canUse() {
        if (this.randomInterval > 0 && this.mob.getRandom().nextInt(this.randomInterval) != 0) {
            return false;
        } else {
            this.findTarget();
             return this.target != null;
        }

    }

    protected AABB getTargetSearchArea(double pTargetDistance) {
        return this.mob.getBoundingBox().inflate(pTargetDistance, pTargetDistance, pTargetDistance);
    }

    protected void findTarget() {
        if (this.targetType != Player.class && this.targetType != ServerPlayer.class) {
            this.target = this.mob.level().getNearestEntity(this.mob.level().getEntitiesOfClass(this.targetType, this.getTargetSearchArea(this.getFollowDistance()), (Entity) -> {
                if (TargetEntity.contains(Entity.getClass())){
                  return true;
              }else {
                  return false;
              }
            }), this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
           //-----------------------------------------------------------------------------------------------------------------------分隔线下方处理透视
            if (this.target == null && this.getFollowXRayDistance() > 0d) {
                this.target = this.mob.level().getNearestEntity(this.mob.level().getEntitiesOfClass(this.targetType, this.getTargetSearchArea(this.getFollowDistance()), (Entity) -> {
                    if (TargetEntity.contains(Entity.getClass())){
                        return true;
                    }else {
                        return false;
                    }
                }), this.targetEntitySelectorXRay.range(this.getFollowXRayDistance()), this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            }
        } else{
                this.target = this.mob.level().getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
                //-----------------------------------------------------------------------------------------------------------------------分隔线下方处理透视
                if (this.target == null && this.getFollowXRayDistance() > 0d) {
                    this.target = this.mob.level().getNearestPlayer(this.targetEntitySelectorXRay.range(this.getFollowXRayDistance()), this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
                }
        }
    }




    @Override
    public void start() {
        this.mob.setTarget(this.target);
        super.start();
    }



    public void setTarget(@Nullable LivingEntity pTarget) {
        this.target = pTarget;
    }




    protected double getFollowXRayDistance() {
      return this.mob.getAttributeValue(Attrubes.XRAY_FOLLOW_RANGE);
    }



}
