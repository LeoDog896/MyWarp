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

package me.taylorkelly.mywarp.service.teleport.strategy;

import com.google.common.base.Optional;

import me.taylorkelly.mywarp.platform.LocalWorld;
import me.taylorkelly.mywarp.util.Vector3;

/**
 * Validates a given position or suggests alternative ones.
 */
public interface PositionValidationStrategy {

  /**
   * Returns an Optional containing the first valid position starting from the given {@code originalPosition} within the
   * given {@code world} or {@code Optional.absent()} if no such position exists.
   *
   * @param originalPosition the original position
   * @param world            the world that contains the position
   * @return the first valid position
   */
  Optional<Vector3> getValidPosition(Vector3 originalPosition, LocalWorld world);
}