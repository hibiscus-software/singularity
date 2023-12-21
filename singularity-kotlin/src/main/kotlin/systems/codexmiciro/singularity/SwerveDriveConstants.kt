/*
 * Copyright (C), 2023 Codex Microsystems. Some rights reserved. This work is
 * licensed under the terms of the MIT license which can be found in the
 * root directory of this project.
 */

package systems.codexmicro.singularity

final class SwerveDriveConstants() {
  var gyroId: Int = 0

  init {}

  fun withGyroId(gyroId: Int): SwerveDriveConstants {
    this.gyroId = gyroId
    return this
  }
}
