/*
 * Copyright 2013 Michael McKnight. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and contributors and should not be interpreted as representing official policies,
 * either expressed or implied, of anybody else.
 */

package com.forgenz.mobmanager.abilities.abilities;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

import com.forgenz.mobmanager.abilities.AbilityType;
import com.forgenz.mobmanager.abilities.config.MobAbilityConfig;
import com.forgenz.mobmanager.common.util.RandomUtil;

public class VillagerAbility extends Ability
{

	public static Ability ability = new VillagerAbility();
	
	private VillagerAbility()
	{
		
	}
	
	@Override
	public void addAbility(LivingEntity entity)
	{
		if (entity instanceof Zombie == false)
			return;
		
		Zombie zombie = (Zombie) entity;
		
		zombie.setVillager(true);
	}
	
	public static void addByChance(LivingEntity entity, MobAbilityConfig ma)
	{
		if (ma == null || !isValid(entity))
			return;
		
		if (ma.villagerRate <= 1.0 && ma.villagerRate != 0.0)
		{
			// If the random number is lower than the villager rate we turn it into a villager
			if (ma.villagerRate == 1.0F || RandomUtil.i.nextFloat() < ma.villagerRate)
			{
				ability.addAbility(entity);
			}
		}
	}
	
	public static boolean isValid(LivingEntity entity)
	{
		return entity instanceof Zombie;
	}
	
	public static boolean isValid(EntityType entity)
	{
		if (entity == null || entity.getEntityClass() == null)
			return false;
		
		return Zombie.class.isAssignableFrom(entity.getEntityClass());
	}

	@Override
	public AbilityType getAbilityType()
	{
		return AbilityType.VILLAGER;
	}

}