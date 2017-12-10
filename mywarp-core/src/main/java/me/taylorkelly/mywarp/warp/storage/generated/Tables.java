/*
 * Copyright (C) 2011 - 2016, MyWarp team and contributors
 *
 * This file is part of MyWarp.
 *
 * MyWarp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MyWarp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MyWarp. If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * This class is generated by jOOQ
 */
package me.taylorkelly.mywarp.warp.storage.generated;


import me.taylorkelly.mywarp.warp.storage.generated.tables.Group;
import me.taylorkelly.mywarp.warp.storage.generated.tables.Player;
import me.taylorkelly.mywarp.warp.storage.generated.tables.Warp;
import me.taylorkelly.mywarp.warp.storage.generated.tables.WarpGroupMap;
import me.taylorkelly.mywarp.warp.storage.generated.tables.WarpPlayerMap;
import me.taylorkelly.mywarp.warp.storage.generated.tables.World;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in mywarp
 */
@Generated(value = {"http://www.jooq.org", "jOOQ version:3.6.2"}, comments = "This class is generated by jOOQ")
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Tables {

  /**
   * The table mywarp.group
   */
  public static final Group GROUP = Group.GROUP;

  /**
   * The table mywarp.player
   */
  public static final Player PLAYER = Player.PLAYER;

  /**
   * The table mywarp.warp
   */
  public static final Warp WARP = Warp.WARP;

  /**
   * The table mywarp.warp_group_map
   */
  public static final WarpGroupMap WARP_GROUP_MAP = WarpGroupMap.WARP_GROUP_MAP;

  /**
   * The table mywarp.warp_player_map
   */
  public static final WarpPlayerMap WARP_PLAYER_MAP = WarpPlayerMap.WARP_PLAYER_MAP;

  /**
   * The table mywarp.world
   */
  public static final World WORLD = World.WORLD;
}